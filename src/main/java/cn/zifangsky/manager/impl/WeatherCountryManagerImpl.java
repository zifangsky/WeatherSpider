package cn.zifangsky.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherCountryManager;
import cn.zifangsky.mapper.WeatherCountryMapper;
import cn.zifangsky.model.WeatherCountry;

@Service("weatherCountryManager")
public class WeatherCountryManagerImpl implements WeatherCountryManager {

	@Autowired
	private WeatherCountryMapper countryMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return countryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherCountry country) {
		return countryMapper.insert(country);
	}

	@Override
	public int insertSelective(WeatherCountry country) {
		return countryMapper.insertSelective(country);
	}

	@Override
	public WeatherCountry selectByPrimaryKey(Long id) {
		return countryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherCountry country) {
		return countryMapper.updateByPrimaryKeySelective(country);
	}

	@Override
	public int updateByPrimaryKey(WeatherCountry country) {
		return countryMapper.updateByPrimaryKey(country);
	}

	@Override
	public Long findAllCount(WeatherCountry country) {
		return countryMapper.findAllCount(country);
	}

	@Override
	public List<WeatherCountry> findAll(PageInfo pageInfo, WeatherCountry country) {
		return countryMapper.findAll(pageInfo, country);
	}

}
