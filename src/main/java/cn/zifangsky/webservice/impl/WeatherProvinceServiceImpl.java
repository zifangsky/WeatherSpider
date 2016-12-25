package cn.zifangsky.webservice.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.Holder;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherProvinceManager;
import cn.zifangsky.model.WeatherProvince;
import cn.zifangsky.webservice.WeatherProvinceService;

public class WeatherProvinceServiceImpl implements WeatherProvinceService {
	@Resource(name="weatherProvinceManager")
	private WeatherProvinceManager provinceManager;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return provinceManager.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherProvince province) {
		return provinceManager.insert(province);
	}

	@Override
	public int insertSelective(WeatherProvince province) {
		return provinceManager.insertSelective(province);
	}

	@Override
	public WeatherProvince selectByPrimaryKey(Long id) {
		return provinceManager.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherProvince province) {
		return provinceManager.updateByPrimaryKeySelective(province);
	}

	@Override
	public int updateByPrimaryKey(WeatherProvince province) {
		return provinceManager.updateByPrimaryKey(province);
	}

	@Override
	public Long findAllCount(WeatherProvince province) {
		return provinceManager.findAllCount(province);
	}

	@Override
	public List<WeatherProvince> findAll(Holder<PageInfo> pageInfoHolder, WeatherProvince province) {
		pageInfoHolder.value.setCount(provinceManager.findAllCount(province));
		return provinceManager.findAll(pageInfoHolder.value, province);
	}

}
