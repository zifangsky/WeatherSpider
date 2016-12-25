package cn.zifangsky.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherProvinceManager;
import cn.zifangsky.mapper.WeatherProvinceMapper;
import cn.zifangsky.model.WeatherProvince;

@Service("weatherProvinceManager")
public class WeatherProvinceManagerImpl implements WeatherProvinceManager {

	@Autowired
	private WeatherProvinceMapper provinceMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return provinceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherProvince province) {
		return provinceMapper.insert(province);
	}

	@Override
	public int insertSelective(WeatherProvince province) {
		return provinceMapper.insertSelective(province);
	}

	@Override
	public WeatherProvince selectByPrimaryKey(Long id) {
		return provinceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherProvince province) {
		return provinceMapper.updateByPrimaryKeySelective(province);
	}

	@Override
	public int updateByPrimaryKey(WeatherProvince province) {
		return provinceMapper.updateByPrimaryKey(province);
	}

	@Override
	public Long findAllCount(WeatherProvince province) {
		return provinceMapper.findAllCount(province);
	}

	@Override
	public List<WeatherProvince> findAll(PageInfo pageInfo, WeatherProvince province) {
		return provinceMapper.findAll(pageInfo, province);
	}

}
