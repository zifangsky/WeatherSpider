package com.whf.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.whf.util.HttpRequest;

/**
 * 解决显示乱码的问题
 * 
 * @author whf
 *
 */
public class HttpPost {
	private static final Logger logger = Logger.getLogger(HttpPost.class);

	@Test
	public void testCode() {
		String result = HttpRequest.sendPost(
				"http://localhost:8080/WeatherSpider/services/rest/weatherService/getWeatherByStationName?stationName=%E6%9C%9D%E9%98%B3",
				"");
		logger.debug("请求返回结果是：" + result);
	}
}
