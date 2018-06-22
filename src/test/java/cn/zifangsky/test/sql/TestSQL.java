package cn.zifangsky.test.sql;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.mapper.ProxyIpMapper;
import cn.zifangsky.mapper.WeatherCountryMapper;
import cn.zifangsky.mapper.WeatherStationMapper;
import cn.zifangsky.mapper.WeatherWeatherMapper;
import cn.zifangsky.model.ProxyIp;
import cn.zifangsky.model.WeatherCountry;
import cn.zifangsky.model.WeatherWeather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * 测试基本数据库连接
 * @author zifangsky
 * @date 2018/6/11
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestSQL {
	@Autowired
	private WeatherCountryMapper countryMapper;
	@Autowired
	WeatherStationMapper stationMapper;
	@Autowired
	WeatherWeatherMapper weatherMapper;
	@Autowired
	private ProxyIpMapper proxyIpMapper;
	
	@Test
	public void testConn(){
		WeatherCountry country = countryMapper.selectByPrimaryKey((long) 1);
		System.out.println(country.getName());
	}
	
	@Test
	public void findAll(){
		PageInfo pageInfo = new PageInfo();
		pageInfo.setFrom((long) 0);
		pageInfo.setPerSize((long)2);
		pageInfo.setSortName("id");
		pageInfo.setSortOrder("ASC");
		WeatherCountry country = new WeatherCountry();
		country.setName("中国");
		
		List<WeatherCountry> list = countryMapper.findAll(pageInfo, country);
		System.out.println(list.get(0).getName());
	}
	
	@Test
	public void testFindAllCount(){
		WeatherWeather weather = new WeatherWeather();
		weather.setStationId((long) 937);
		
		System.out.println(weatherMapper.findAllCount(weather));
	}
	
	@Test
	public void testSelectAll(){
		List<WeatherCountry> list = countryMapper.selectAll();
		System.out.println(list.get(0).getName());
	}
	
	@Test
	public void testSelectIdByCode(){
		System.out.println(stationMapper.selectIdByCode("101060101"));
	}
	
	@Test
	public void testSelectIdByStationId(){
		System.out.println(weatherMapper.selectByStationId((long) 2550).getId());
	}
	
	@Test
	public void testSelectByIPPort(){
		ProxyIp ip = proxyIpMapper.selectByIPPort("127.0.0.1", 80);
		System.out.println(ip.getIp());
	}
	
	@Test
	public void testProxyIPAll(){
		List<ProxyIp> list = proxyIpMapper.selectAll();
		for(ProxyIp ip : list){
			System.out.println(ip.getIp());
		}
	}
	
	@Test
	public void testSelectByStationCode(){
		WeatherWeather weather = weatherMapper.selectByStationCode("101060404");
		System.out.println(weather.getToday());
	}
	
	@Test
	public void testSelectByStationName(){
		List<WeatherWeather> weather = weatherMapper.selectByStationName("通州");
		System.out.println(weather.get(0).getToday());
	}
}
