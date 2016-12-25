package cn.zifangsky.webservice.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.Holder;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherCityManager;
import cn.zifangsky.model.WeatherCity;
import cn.zifangsky.webservice.WeatherCityService;

public class WeatherCityServiceImpl implements WeatherCityService {
	
	@Resource(name="weatherCityManager")
	private WeatherCityManager cityManager;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return cityManager.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherCity city) {
		return cityManager.insert(city);
	}

	@Override
	public int insertSelective(WeatherCity city) {
		return cityManager.insertSelective(city);
	}

	@Override
	public WeatherCity selectByPrimaryKey(Long id) {
		return cityManager.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherCity city) {
		return cityManager.updateByPrimaryKeySelective(city);
	}

	@Override
	public int updateByPrimaryKey(WeatherCity city) {
		return cityManager.updateByPrimaryKey(city);
	}

	@Override
	public Long findAllCount(WeatherCity city) {
		return cityManager.findAllCount(city);
	}

	@Override
	public List<WeatherCity> findAll(Holder<PageInfo> pageInfoHolder, WeatherCity city) {
		pageInfoHolder.value.setCount(cityManager.findAllCount(city));
		return cityManager.findAll(pageInfoHolder.value, city);
	}

}
