package cn.zifangsky.test.spider;

import cn.zifangsky.manager.CrawlManager;
import cn.zifangsky.manager.ProxyIpManager;
import cn.zifangsky.model.bo.ProxyIpBO;
import cn.zifangsky.spider.CheckIPUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * 测试基于WebMagic框架的爬虫效果
 * @author zifangsky
 * @date 2018/6/21
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestSpider{
	@Resource(name="crawlManager")
	private CrawlManager crawlManager;

    @Resource(name = "proxyIpManager")
    private ProxyIpManager proxyIpManager;

	/**
	 * 测试获取某个城镇CODE对应的天气
	 * @author zifangsky
	 * @date 2018/6/21 10:43
	 * @since 1.0.0
	 */
	@Test
	public void testWeatherCrawl(){
		crawlManager.weatherCrawl("101010700");
	}

    /**
     * 测试检测代理IP是否可用
     * @author zifangsky
     * @date 2018/6/21 14:08
     * @since 1.0.0
     */
    @Test
	public void testCheckProxyIp(){
        ProxyIpBO proxyIpBO = new ProxyIpBO();
        proxyIpBO.setIp("122.72.18.160");
        proxyIpBO.setPort(80);


        if (!CheckIPUtils.checkValidIP(proxyIpBO.getIp(), proxyIpBO.getPort())) {
            proxyIpManager.deleteByPrimaryKey(proxyIpBO.getId());
        }
    }
    
    /**
     * 测试通过接口1获取代理IP
     * @author zifangsky
     * @date 2018/6/21 14:13
     * @since 1.0.0
     */
    @Test
    public void crawlProxyIp1(){
        crawlManager.proxyIPCrawl();
    }

    /**
     * 测试通过接口2获取代理IP
     * @author zifangsky
     * @date 2018/6/21 14:13
     * @since 1.0.0
     */
    @Test
    public void crawlProxyIp2(){
        crawlManager.proxyIPCrawl2();
    }

}
