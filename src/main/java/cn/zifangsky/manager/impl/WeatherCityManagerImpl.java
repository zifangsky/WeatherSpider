package cn.zifangsky.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherCityManager;
import cn.zifangsky.mapper.WeatherCityMapper;
import cn.zifangsky.model.WeatherCity;

@Service("weatherCityManager")
public class WeatherCityManagerImpl implements WeatherCityManager {

	@Autowired
	private WeatherCityMapper cityMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return cityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherCity city) {
		return cityMapper.insert(city);
	}

	@Override
	public int insertSelective(WeatherCity city) {
		return cityMapper.insertSelective(city);
	}

	@Override
	public WeatherCity selectByPrimaryKey(Long id) {
		return cityMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherCity city) {
		return cityMapper.updateByPrimaryKeySelective(city);
	}

	@Override
	public int updateByPrimaryKey(WeatherCity city) {
		return cityMapper.updateByPrimaryKey(city);
	}

	@Override
	public Long findAllCount(WeatherCity city) {
		return cityMapper.findAllCount(city);
	}

	@Override
	public List<WeatherCity> findAll(PageInfo pageInfo, WeatherCity city) {
		return cityMapper.findAll(pageInfo, city);
	}

}
