package cn.zifangsky.activemq.consumer;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.zifangsky.manager.CrawlManager;

@Component("weatherUpdateReceiver")
public class WeatherUpdateReceiver{
	@Resource(name="crawlManager")
	private CrawlManager crawlManager;

	/**
	 * 接收消息并处理
	 * @param stationCode
	 */
	public void handle(String stationCode){
		//更新天气
		crawlManager.weatherCrawl(stationCode);
	}

}
