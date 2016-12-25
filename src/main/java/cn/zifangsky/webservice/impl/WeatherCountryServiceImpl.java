package cn.zifangsky.webservice.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.Holder;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherCountryManager;
import cn.zifangsky.model.WeatherCountry;
import cn.zifangsky.webservice.WeatherCountryService;

public class WeatherCountryServiceImpl implements WeatherCountryService {
	
	@Resource(name="weatherCountryManager")
	private WeatherCountryManager countryManager;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return countryManager.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherCountry country) {
		return countryManager.insert(country);
	}

	@Override
	public int insertSelective(WeatherCountry country) {
		return countryManager.insertSelective(country);
	}

	@Override
	public WeatherCountry selectByPrimaryKey(Long id) {
		return countryManager.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherCountry country) {
		return countryManager.updateByPrimaryKeySelective(country);
	}

	@Override
	public int updateByPrimaryKey(WeatherCountry country) {
		return countryManager.updateByPrimaryKey(country);
	}

	@Override
	public Long findAllCount(WeatherCountry country) {
		return countryManager.findAllCount(country);
	}

	@Override
	public List<WeatherCountry> findAll(Holder<PageInfo> pageInfoHolder, WeatherCountry country) {
		pageInfoHolder.value.setCount(countryManager.findAllCount(country));
		return countryManager.findAll(pageInfoHolder.value, country);
	}

}
