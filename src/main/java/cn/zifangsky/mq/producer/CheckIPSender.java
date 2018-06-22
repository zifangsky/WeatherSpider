package cn.zifangsky.mq.producer;

import cn.zifangsky.model.bo.ProxyIpBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component("checkIPSender")
public class CheckIPSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckIPSender.class);

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

	/**
	 * 发送检测某个代理IP可用性的消息到指定队列
	 * @param topicName  队列名称
	 * @param proxyIpBO  消息内容
	 */
	public void send(String topicName,ProxyIpBO proxyIpBO){
        LOGGER.info(MessageFormat.format("开始向Kafka推送数据，topicName：{0}，代理IP：{1}",topicName, proxyIpBO));

        try {
            kafkaTemplate.send(topicName, proxyIpBO);
            LOGGER.info("推送数据成功！");
        } catch (Exception e) {
            LOGGER.error(MessageFormat.format("推送数据出错，topicName:{0},代理IP:{1}"
                    ,topicName,proxyIpBO),e);
        }
	}

}
