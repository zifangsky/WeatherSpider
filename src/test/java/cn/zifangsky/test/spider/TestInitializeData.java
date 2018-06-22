package cn.zifangsky.test.spider;

import cn.zifangsky.mapper.WeatherCityMapper;
import cn.zifangsky.mapper.WeatherProvinceMapper;
import cn.zifangsky.mapper.WeatherStationMapper;
import cn.zifangsky.model.WeatherCity;
import cn.zifangsky.model.WeatherProvince;
import cn.zifangsky.model.WeatherStation;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

/**
 * 为数据库配置初始化数据
 * @author zifangsky
 * @date 2018/6/21
 * @since 1.0.0
 * @param 
 * @return 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestInitializeData {
	@Autowired
	private WeatherProvinceMapper provinceMapper;
	@Autowired
	private WeatherCityMapper cityMapper;
	@Autowired
	private WeatherStationMapper stationMapper;
	
	/**
	 * 为数据库中的weather_province表设置初始化数据
	 * @author zifangsky
	 * @date 2018/6/21 10:51
	 * @since 1.0.0
	 */
	@Test
	public void getProvince() throws Exception{
		URL url = new URL("http://js.weather.com.cn/data/city3jdata/china.html");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setReadTimeout(3000);
		connection.setConnectTimeout(3000);
		connection.setRequestMethod("GET");
		
		if(connection.getResponseCode() == 200){
			InputStream inputStream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = "";
			
			while((line = reader.readLine()) != null){
				JSONObject jsonObject = JSONObject.parseObject(line);

				for(Entry<String, Object> entry : jsonObject.entrySet()){
//					String value = entry.getValue().toString();
//					System.out.println(entry.getKey() + "********" + value);
					WeatherProvince province = new WeatherProvince();
					province.setCode(entry.getKey());
					province.setName(entry.getValue().toString());
					province.setCountryId((long) 1);
					
					provinceMapper.insertSelective(province);
				}
			}
	
		}
	
	}

    /**
     * 为数据库中的weather_city表设置初始化数据
     * @author zifangsky
     * @date 2018/6/21 10:55
     * @since 1.0.0
     */
	@Test
	public void getCity() throws Exception{
		List<WeatherProvince> provinceList = provinceMapper.selectAll();
		for(WeatherProvince p : provinceList){
			
			URL url = new URL("http://js.weather.com.cn/data/city3jdata/provshi/" +p.getCode() + ".html");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(10000);
			connection.setRequestMethod("GET");
			
			if(connection.getResponseCode() == 200){
				InputStream inputStream = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				String line = "";
				
				while((line = reader.readLine()) != null){
					JSONObject jsonObject = JSONObject.parseObject(line);
					for(Entry<String, Object> entry : jsonObject.entrySet()){
						String value = entry.getValue().toString();
						System.out.println(entry.getKey() + "********" + value);

						WeatherCity city = new WeatherCity();
						city.setCode(p.getCode() + entry.getKey());
						city.setName(entry.getValue().toString());
						city.setProvinceId(p.getId());
						
						cityMapper.insertSelective(city);
					}
				}
				
				reader.close();
				inputStream.close();
			}
			connection.disconnect();
			Thread.sleep(1000);
		}
		
	}

    /**
     * 为数据库中的weather_station表设置初始化数据
     * @author zifangsky
     * @date 2018/6/21 10:59
     * @since 1.0.0
     */
	@Test
	public void getStation() throws Exception{
		List<WeatherCity> cityList = cityMapper.selectAll();
		for(WeatherCity c : cityList){
			if(c.getId() <= 140)
				continue;
			
			URL url = new URL("http://js.weather.com.cn/data/city3jdata/station/" +c.getCode() + ".html");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestMethod("GET");
			
			if(connection.getResponseCode() == 200){
				InputStream inputStream = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				String line = "";
				
				while((line = reader.readLine()) != null){
					JSONObject jsonObject = JSONObject.parseObject(line);
					for(Entry<String, Object> entry : jsonObject.entrySet()){
						String value = entry.getValue().toString();
						System.out.println(entry.getKey() + "********" + value);

						WeatherStation station = new WeatherStation();
						station.setCode(c.getCode() + entry.getKey());
						station.setName(entry.getValue().toString());
						station.setCityId(c.getId());
						
						stationMapper.insertSelective(station);
					}
				}
				
				reader.close();
				inputStream.close();
			}
			connection.disconnect();
			Thread.sleep(1000);
		}
		
	}
}
