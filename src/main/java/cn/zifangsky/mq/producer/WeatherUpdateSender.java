package cn.zifangsky.mq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component("weatherUpdateSender")
public class WeatherUpdateSender {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherUpdateSender.class);

	@Autowired
	private KafkaTemplate<Object,Object> kafkaTemplate;

	/**
	 * 城镇天气更新发送者
	 * 向接收者发送需要更新的城镇天气的stationCode
	 * @param topicName 天气更新队列的名称
	 * @param stationCode 城镇代码
	 */
	public void updateWeather(String topicName,String stationCode){
        LOGGER.info(MessageFormat.format("开始向Kafka推送数据，topicName:{0}，城镇CODE：{1}", topicName,stationCode));

        try {
            kafkaTemplate.send(topicName, stationCode);
            LOGGER.info("推送数据成功！");
        } catch (Exception e) {
            LOGGER.error(MessageFormat.format("推送数据出错，topicName:{0}，stationCode:{1}"
                    ,topicName,stationCode),e);
        }
	}
}
