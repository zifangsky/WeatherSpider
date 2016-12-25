package cn.zifangsky.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zifangsky.manager.CrawlManager;
import cn.zifangsky.spider.CustomPipeline;
import cn.zifangsky.spider.ProxyIPPipeline;
import cn.zifangsky.spider.ProxyIPSpider;
import cn.zifangsky.spider.ProxyIPSpider2;
import cn.zifangsky.spider.WeatherSpider;
import us.codecraft.webmagic.model.OOSpider;

@Service("crawlManager")
public class CrawlManagerImpl implements CrawlManager {
	
	@Resource(name="customPipeline")
	private CustomPipeline customPipeline;
	
	@Resource(name="proxyIPPipeline")
	private ProxyIPPipeline proxyIPPipeline;
	
	@Override
	public void weatherCrawl(String stationCode) {
		OOSpider.create(new WeatherSpider()).addPipeline(customPipeline)
		.addUrl("http://www.weather.com.cn/weather/" + stationCode + ".shtml")
		.thread(1)
		.run();
	}

	@Override
	public void proxyIPCrawl() {
		OOSpider.create(new ProxyIPSpider())
		.addUrl("http://www.xicidaili.com/nn/1").addPipeline(proxyIPPipeline)
		.thread(3)
		.run();
	}

	@Override
	public void proxyIPCrawl2() {
		OOSpider.create(new ProxyIPSpider2())
		.addUrl("http://www.kuaidaili.com/free/inha/1/").addPipeline(proxyIPPipeline)
		.thread(2)
		.run();
	}

}
