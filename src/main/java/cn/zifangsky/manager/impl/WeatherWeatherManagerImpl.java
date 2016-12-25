package cn.zifangsky.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherWeatherManager;
import cn.zifangsky.mapper.WeatherCityMapper;
import cn.zifangsky.mapper.WeatherCountryMapper;
import cn.zifangsky.mapper.WeatherProvinceMapper;
import cn.zifangsky.mapper.WeatherStationMapper;
import cn.zifangsky.mapper.WeatherWeatherMapper;
import cn.zifangsky.model.WeatherCity;
import cn.zifangsky.model.WeatherCountry;
import cn.zifangsky.model.WeatherProvince;
import cn.zifangsky.model.WeatherStation;
import cn.zifangsky.model.WeatherWeather;
import cn.zifangsky.model.bo.WeatherWeatherBO;

@Service("weatherWeatherManager")
public class WeatherWeatherManagerImpl implements WeatherWeatherManager {
	@Autowired
	private WeatherWeatherMapper weatherMapper;
	
	@Autowired
	private WeatherStationMapper stationMapper;
	
	@Autowired
	private WeatherCityMapper cityMapper;
	
	@Autowired
	private WeatherProvinceMapper provinceMapper;
	
	@Autowired
	private WeatherCountryMapper countryMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return weatherMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherWeather weather) {
		return weatherMapper.insert(weather);
	}

	@Override
	public int insertSelective(WeatherWeather weather) {
		return weatherMapper.insertSelective(weather);
	}

	@Override
	public WeatherWeather selectByPrimaryKey(Long id) {
		return weatherMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherWeather weather) {
		return weatherMapper.updateByPrimaryKeySelective(weather);
	}

	@Override
	public int updateByPrimaryKey(WeatherWeather weather) {
		return weatherMapper.updateByPrimaryKey(weather);
	}

	@Override
	public Long findAllCount(WeatherWeather weather) {
		return weatherMapper.findAllCount(weather);
	}

	@Override
	public List<WeatherWeather> findAll(PageInfo pageInfo, WeatherWeather weather) {
		return weatherMapper.findAll(pageInfo, weather);
	}

	@Override
	public List<WeatherWeather> selectAll() {
		return weatherMapper.selectAll();
	}

	@Override
	public WeatherWeather selectByStationId(Long stationId) {
		return weatherMapper.selectByStationId(stationId);
	}

	@Override
	public WeatherWeatherBO selectByStationCode(String stationCode) {
		WeatherWeatherBO result = new WeatherWeatherBO();
		WeatherWeather weather = weatherMapper.selectByStationCode(stationCode);
		result.setWeather(weather);
		
		if(weather != null){
			WeatherStation station = stationMapper.selectByPrimaryKey(weather.getStationId());
			result.setStation(station);
			
			WeatherCity city = cityMapper.selectByPrimaryKey(station.getCityId());
			result.setCity(city);
			
			WeatherProvince province = provinceMapper.selectByPrimaryKey(city.getProvinceId());
			result.setProvince(province);
			
			WeatherCountry country = countryMapper.selectByPrimaryKey(province.getCountryId());
			result.setCountry(country);
		}

		return result;
	}

	@Override
	public List<WeatherWeatherBO> selectByStationName(String stationName) {
		List<WeatherWeatherBO> result = new ArrayList<>();
		List<WeatherWeather> weatherList = weatherMapper.selectByStationName(stationName);
		
		if(weatherList != null && weatherList.size() > 0){
			//遍历返回所有匹配关键字的城镇天气
			for(WeatherWeather weather : weatherList){
				WeatherWeatherBO weatherBO = new WeatherWeatherBO();
				weatherBO.setWeather(weather);
				
				WeatherStation station = stationMapper.selectByPrimaryKey(weather.getStationId());
				weatherBO.setStation(station);
				
				WeatherCity city = cityMapper.selectByPrimaryKey(station.getCityId());
				weatherBO.setCity(city);
				
				WeatherProvince province = provinceMapper.selectByPrimaryKey(city.getProvinceId());
				weatherBO.setProvince(province);
				
				WeatherCountry country = countryMapper.selectByPrimaryKey(province.getCountryId());
				weatherBO.setCountry(country);
				
				result.add(weatherBO);
			}
		}

		return result;
	}
	
}
