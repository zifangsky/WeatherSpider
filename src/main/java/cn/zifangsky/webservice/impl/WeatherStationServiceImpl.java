package cn.zifangsky.webservice.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.Holder;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherStationManager;
import cn.zifangsky.model.WeatherStation;
import cn.zifangsky.webservice.WeatherStationService;

public class WeatherStationServiceImpl implements WeatherStationService {
	
	@Resource(name="weatherStationManager")
	private WeatherStationManager stationManager;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return stationManager.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherStation station) {
		return stationManager.insert(station);
	}

	@Override
	public int insertSelective(WeatherStation station) {
		return stationManager.insertSelective(station);
	}

	@Override
	public WeatherStation selectByPrimaryKey(Long id) {
		return stationManager.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherStation station) {
		return stationManager.updateByPrimaryKeySelective(station);
	}

	@Override
	public int updateByPrimaryKey(WeatherStation station) {
		return stationManager.updateByPrimaryKey(station);
	}

	@Override
	public Long findAllCount(WeatherStation station) {
		return stationManager.findAllCount(station);
	}

	@Override
	public List<WeatherStation> findAll(Holder<PageInfo> pageInfoHolder, WeatherStation station) {
		pageInfoHolder.value.setCount(stationManager.findAllCount(station));
		return stationManager.findAll(pageInfoHolder.value, station);
	}

}
