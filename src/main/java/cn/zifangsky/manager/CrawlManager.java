package cn.zifangsky.manager;

public interface CrawlManager {
	
	/**
	 * 天气爬虫
	 * @param stationCode 县城（区）的CODE
	 */
	public void weatherCrawl(String stationCode);
	
	/**
	 * 代理IP爬虫，地址：http://www.xicidaili.com
	 */
	public void proxyIPCrawl();
	
	/**
	 * 代理IP爬虫，地址：http://www.kuaidaili.com
	 */
	public void proxyIPCrawl2();
}
