package cn.zifangsky.job;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.zifangsky.activemq.producer.CheckIPSender;
import cn.zifangsky.manager.ProxyIpManager;
import cn.zifangsky.model.ProxyIp;
import cn.zifangsky.model.bo.ProxyIpBO;
import cn.zifangsky.model.bo.ProxyIpBO.CheckIPType;

/**
 * 代理IP定时检测可用性任务
 * @author zifangsky
 *
 */
public class ProxyIpCheckJob extends QuartzJobBean{
	private static Logger logger = Logger.getLogger(WeatherUpdateJob.class);
	
	@Resource(name="proxyIpManager")
	private ProxyIpManager proxyIpManager;
	
	@Value("${activemq.queue.checkIP}")
	private String checkIPQueueName;
	
	@Resource(name="checkIPSender")
	private CheckIPSender checkIPSender;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Date current = new Date();
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.debug("开始执行代理IP定时检测任务，Date： " + format.format(current));
		
		//1 查询数据库中所有代理IP
		List<ProxyIp> list = proxyIpManager.selectAll();
		
		if(list != null && list.size() > 0){
			//2 遍历
			for(ProxyIp proxyIp : list){
				ProxyIpBO proxyIpBO = new ProxyIpBO();
				proxyIpBO.setId(proxyIp.getId());
				proxyIpBO.setIp(proxyIp.getIp());
				proxyIpBO.setPort(proxyIp.getPort());
				proxyIpBO.setType(proxyIp.getType());
				proxyIpBO.setAddr(proxyIp.getAddr());
				proxyIpBO.setUsed(proxyIp.getUsed());
				proxyIpBO.setOther(proxyIp.getOther());
				proxyIpBO.setCheckType(CheckIPType.UPDATE);
				
				//3 添加到队列中
				checkIPSender.send(checkIPQueueName, proxyIpBO);
			}
		}
	}

}
