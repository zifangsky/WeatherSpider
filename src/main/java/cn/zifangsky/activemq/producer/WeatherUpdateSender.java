package cn.zifangsky.activemq.producer;

import javax.annotation.Resource;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("weatherUpdateSender")
public class WeatherUpdateSender {
	
	@Resource(name="jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	
	/**
	 * 城镇天气更新发送者
	 * 向接收者发送需要更新的城镇天气的stationCode
	 * @param queueName 天气更新队列的名称
	 * @param stationCode 城镇代码
	 */
	public void updateWeather(String queueName,final String stationCode){
		jmsTemplate.convertAndSend(queueName, stationCode);
	}
}
