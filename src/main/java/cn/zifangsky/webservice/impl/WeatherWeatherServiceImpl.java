package cn.zifangsky.webservice.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.Holder;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherWeatherManager;
import cn.zifangsky.model.WeatherWeather;
import cn.zifangsky.model.bo.WeatherWeatherBO;
import cn.zifangsky.webservice.WeatherWeatherService;

public class WeatherWeatherServiceImpl implements WeatherWeatherService {

	@Resource(name="weatherWeatherManager")
	private WeatherWeatherManager weatherManager;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return weatherManager.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherWeather weather) {
		return weatherManager.insert(weather);
	}

	@Override
	public int insertSelective(WeatherWeather weather) {
		return weatherManager.insertSelective(weather);
	}

	@Override
	public WeatherWeather selectByPrimaryKey(Long id) {
		return weatherManager.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherWeather weather) {
		return weatherManager.updateByPrimaryKeySelective(weather);
	}

	@Override
	public int updateByPrimaryKey(WeatherWeather weather) {
		return weatherManager.updateByPrimaryKey(weather);
	}

	@Override
	public Long findAllCount(WeatherWeather weather) {
		return weatherManager.findAllCount(weather);
	}

	@Override
	public List<WeatherWeather> findAll(Holder<PageInfo> pageInfoHolder, WeatherWeather weather) {
		pageInfoHolder.value.setCount(weatherManager.findAllCount(weather));
		return weatherManager.findAll(pageInfoHolder.value, weather);
	}

	@Override
	public WeatherWeatherBO selectByStationCode(String stationCode) {
		return weatherManager.selectByStationCode(stationCode);
	}

	@Override
	public List<WeatherWeatherBO> selectByStationName(String stationName) {
		return weatherManager.selectByStationName(stationName);
	}

	@Override
	public WeatherWeatherBO selectByStationCodeRest(String stationCode) {
		return weatherManager.selectByStationCode(stationCode);
	}

	@Override
	public List<WeatherWeatherBO> selectByStationNameRest(String stationName) {
		return weatherManager.selectByStationName(stationName);
	}

}
