package cn.zifangsky.activemq.producer;

import javax.annotation.Resource;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import cn.zifangsky.model.bo.ProxyIpBO;

@Component("checkIPSender")
public class CheckIPSender {
	
	@Resource(name="jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	
	/**
	 * 发送检测某个代理IP可用性的消息到指定队列
	 * @param queueName  队列名称
	 * @param message  消息内容
	 */
	public void send(String queueName,final ProxyIpBO message){
		jmsTemplate.convertAndSend(queueName, message);
	}

}
