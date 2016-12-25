package cn.zifangsky.test.spider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.zifangsky.mapper.WeatherCityMapper;
import cn.zifangsky.mapper.WeatherProvinceMapper;
import cn.zifangsky.mapper.WeatherStationMapper;
import cn.zifangsky.model.WeatherCity;
import cn.zifangsky.model.WeatherProvince;
import cn.zifangsky.model.WeatherStation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:context/context.xml"})
public class SaveCity {
	@Autowired
	WeatherProvinceMapper provinceMapper;
	@Autowired
	WeatherCityMapper cityMapper;
	@Autowired
	WeatherStationMapper stationMapper;
	
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
				jsonObject.entrySet();
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
