package cn.zifangsky.activemq.consumer;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.zifangsky.manager.ProxyIpManager;
import cn.zifangsky.model.ProxyIp;
import cn.zifangsky.model.bo.ProxyIpBO;
import cn.zifangsky.model.bo.ProxyIpBO.CheckIPType;
import cn.zifangsky.spider.CheckIPUtils;

@Component("checkIPReceiver")
public class CheckIPReceiver {

	@Resource(name = "proxyIpManager")
	private ProxyIpManager proxyIpManager;

	/**
	 * 接收消息并处理
	 * @param proxyIpBO
	 */
	public void handle(ProxyIpBO proxyIpBO) {
		// 根据该IP是待入库的新IP或者数据库中的旧IP分两种情况判断
		if (proxyIpBO.getCheckType() == CheckIPType.ADD) {
			if (CheckIPUtils.checkValidIP(proxyIpBO.getIp(), proxyIpBO.getPort())) {
				System.out.println("--|" + proxyIpBO.getIp() + " -- " + proxyIpBO.getPort());

				// 1 查询该IP是否已存在
				ProxyIp oldIP = proxyIpManager.selectByIPPort(proxyIpBO.getIp(), proxyIpBO.getPort());

				// 2如果不存在则插入数据
				if (oldIP == null) {
					proxyIpBO.setUsed(false);
					proxyIpManager.insertSelective(proxyIpBO);
				}
			}
		} else if (proxyIpBO.getCheckType() == CheckIPType.UPDATE) {
			// 不能使用则删除
			if (!CheckIPUtils.checkValidIP(proxyIpBO.getIp(), proxyIpBO.getPort())) {
				proxyIpManager.deleteByPrimaryKey(proxyIpBO.getId());
			}
		}
	}

}
