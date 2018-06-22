package cn.zifangsky.mq.consumer;

import cn.zifangsky.manager.ProxyIpManager;
import cn.zifangsky.model.ProxyIp;
import cn.zifangsky.model.bo.ProxyIpBO;
import cn.zifangsky.spider.CheckIPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.MessageFormat;

@Component("checkIPReceiver")
public class CheckIPReceiver {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckIPReceiver.class);

	@Resource(name = "proxyIpManager")
	private ProxyIpManager proxyIpManager;

	/**
	 * 接收消息并处理
	 * @param proxyIpBO 待处理的代理IP
	 */
	@KafkaListener(topics = {"${mq.topicName.checkIP}"},groupId = "group2")
	public void handle(ProxyIpBO proxyIpBO) {
        LOGGER.info(MessageFormat.format("接收到消息，代理IP:{0}", proxyIpBO));

        // 根据该IP是待入库的新IP或者数据库中的旧IP分两种情况判断
		if (proxyIpBO.getCheckType() == ProxyIpBO.CheckIPType.ADD) {
			if (CheckIPUtils.checkValidIP(proxyIpBO.getIp(), proxyIpBO.getPort())) {
//				System.out.println("--|" + proxyIpBO.getIp() + " -- " + proxyIpBO.getPort());

				// 1 查询该IP是否已存在
				ProxyIp oldIP = proxyIpManager.selectByIPPort(proxyIpBO.getIp(), proxyIpBO.getPort());

				// 2如果不存在则插入数据
				if (oldIP == null) {
					proxyIpBO.setUsed(false);
					proxyIpManager.insertSelective(proxyIpBO);
				}
			}
		} else if (proxyIpBO.getCheckType() == ProxyIpBO.CheckIPType.UPDATE) {
			// 不能使用则删除
			if (!CheckIPUtils.checkValidIP(proxyIpBO.getIp(), proxyIpBO.getPort())) {
				proxyIpManager.deleteByPrimaryKey(proxyIpBO.getId());
			}
		}

	}

}
