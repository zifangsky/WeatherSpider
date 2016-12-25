package cn.zifangsky.job;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.zifangsky.activemq.producer.WeatherUpdateSender;
import cn.zifangsky.mapper.WeatherStationMapper;
import cn.zifangsky.model.WeatherStation;

/**
 * 天气定时更新任务
 * @author zifangsky
 *
 */
public class WeatherUpdateJob extends QuartzJobBean{
	private static Logger logger = Logger.getLogger(WeatherUpdateJob.class);
	
	@Value("${activemq.queue.weather}")
	private String weatherQueueName;
	
	@Resource(name="weatherUpdateSender")
	private WeatherUpdateSender weatherUpdateSender;
	
	@Autowired
	WeatherStationMapper weatherStationMapper;
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		Date current = new Date();
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("开始执行天气定时更新任务，Date：" + format.format(current));
		logger.debug("开始执行天气定时更新任务，Date： " + format.format(current));
		
        List<WeatherStation> list = weatherStationMapper.selectAll();
		if(list != null && list.size() > 0){
			for(WeatherStation station : list){
				weatherUpdateSender.updateWeather(weatherQueueName, station.getCode());
			}
			
		}
	}

}
