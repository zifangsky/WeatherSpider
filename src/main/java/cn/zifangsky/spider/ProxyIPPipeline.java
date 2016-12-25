package cn.zifangsky.spider;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.zifangsky.activemq.producer.CheckIPSender;
import cn.zifangsky.model.ProxyIp;
import cn.zifangsky.model.bo.ProxyIpBO;
import cn.zifangsky.model.bo.ProxyIpBO.CheckIPType;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 自定义Pipeline处理抓取的数据
 * @author zifangsky
 *
 */
@Component("proxyIPPipeline")
public class ProxyIPPipeline implements Pipeline {
	
	@Value("${activemq.queue.checkIP}")
	private String checkIPQueueName;
	
	@Resource(name="checkIPSender")
	private CheckIPSender checkIPSender;
	
	/**
	 * 保存数据
	 */
	@Override
	public void process(ResultItems resultItems, Task task) {
		List<ProxyIp> list = resultItems.get("result");
		
		if(list != null && list.size() > 0){
			for(ProxyIp proxyIp : list){
				ProxyIpBO proxyIpBO = new ProxyIpBO();
				proxyIpBO.setId(proxyIp.getId());
				proxyIpBO.setIp(proxyIp.getIp());
				proxyIpBO.setPort(proxyIp.getPort());
				proxyIpBO.setType(proxyIp.getType());
				proxyIpBO.setAddr(proxyIp.getAddr());
				proxyIpBO.setUsed(proxyIp.getUsed());
				proxyIpBO.setOther(proxyIp.getOther());
				proxyIpBO.setCheckType(CheckIPType.ADD);
				
				//检测任务添加到队列中
				checkIPSender.send(checkIPQueueName, proxyIpBO);
			}
		}

	}

}
