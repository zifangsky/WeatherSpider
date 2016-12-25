package cn.zifangsky.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.WeatherStationManager;
import cn.zifangsky.mapper.WeatherStationMapper;
import cn.zifangsky.model.WeatherStation;

@Service("weatherStationManager")
public class WeatherStationManagerImpl implements WeatherStationManager {
	@Autowired
	private WeatherStationMapper stationMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return stationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeatherStation station) {
		return stationMapper.insert(station);
	}

	@Override
	public int insertSelective(WeatherStation station) {
		return stationMapper.insertSelective(station);
	}

	@Override
	public WeatherStation selectByPrimaryKey(Long id) {
		return stationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeatherStation station) {
		return stationMapper.updateByPrimaryKeySelective(station);
	}

	@Override
	public int updateByPrimaryKey(WeatherStation station) {
		return stationMapper.updateByPrimaryKey(station);
	}

	@Override
	public Long findAllCount(WeatherStation station) {
		return stationMapper.findAllCount(station);
	}

	@Override
	public List<WeatherStation> findAll(PageInfo pageInfo, WeatherStation station) {
		return stationMapper.findAll(pageInfo, station);
	}

	@Override
	public List<WeatherStation> selectAll() {
		return stationMapper.selectAll();
	}

	@Override
	public Long selectIdByCode(String code) {
		return stationMapper.selectIdByCode(code);
	}

}
