package cn.zifangsky.job;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.zifangsky.manager.CrawlManager;

/**
 * 代理IP定时获取任务
 * @author zifangsky
 *
 */
public class ProxyIpGetJob2 extends QuartzJobBean {
	private static Logger logger = Logger.getLogger(WeatherUpdateJob.class);
	
	@Resource(name="crawlManager")
	private CrawlManager crawlManager;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		Date current = new Date();
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.debug("开始执行代理IP定时获取任务，Date： " + format.format(current));
		
		crawlManager.proxyIPCrawl2();
	}

}
