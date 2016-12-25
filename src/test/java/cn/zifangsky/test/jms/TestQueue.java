package cn.zifangsky.test.jms;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zifangsky.activemq.producer.CheckIPSender;
import cn.zifangsky.activemq.producer.WeatherUpdateSender;
import cn.zifangsky.manager.ProxyIpManager;
import cn.zifangsky.mapper.WeatherStationMapper;
import cn.zifangsky.model.ProxyIp;
import cn.zifangsky.model.WeatherStation;
import cn.zifangsky.model.bo.ProxyIpBO;
import cn.zifangsky.model.bo.ProxyIpBO.CheckIPType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:context/context.xml","classpath:context/context_activemq.xml"})
public class TestQueue {
	
	@Value("${activemq.queue.checkIP}")
	private String checkIPQueueName;
	
	@Resource(name="checkIPSender")
	private CheckIPSender checkIPSender;
	
	@Resource(name="proxyIpManager")
	private ProxyIpManager proxyIpManager;
	
	@Value("${activemq.queue.weather}")
	private String weatherQueueName;
	
	@Resource(name="weatherUpdateSender")
	private WeatherUpdateSender weatherUpdateSender;
	
	@Autowired
	WeatherStationMapper weatherStationMapper;
	
	@Test
	public void testSend(){
		//1 查询数据库中所有代理IP
		List<ProxyIp> list = proxyIpManager.selectAll();
		
		if(list != null && list.size() > 0){
			//2 遍历并检测其可用性
			for(ProxyIp proxyIp : list){
				ProxyIpBO proxyIpBO = new ProxyIpBO();
				proxyIpBO.setId(proxyIp.getId());
				proxyIpBO.setIp(proxyIp.getIp());
				proxyIpBO.setPort(proxyIp.getPort());
				proxyIpBO.setType(proxyIp.getType());
				proxyIpBO.setAddr(proxyIp.getAddr());
				proxyIpBO.setUsed(proxyIp.getUsed());
				proxyIpBO.setOther(proxyIp.getOther());
				proxyIpBO.setCheckType(CheckIPType.UPDATE);
				
				//3 添加到队列中
				checkIPSender.send(checkIPQueueName, proxyIpBO);
				break;
			}
		}
	
	}
	
	/**
	 * 测试更新天气
	 */
	@Test
	public void testUpdateWeather(){
		List<WeatherStation> list = weatherStationMapper.selectAll();

//		if(list != null && list.size() > 0){
//			for(WeatherStation station : list){
//				weatherUpdateSender.updateWeather(weatherQueueName, station.getCode());
//			}
//			
//		}
		
	}
}
