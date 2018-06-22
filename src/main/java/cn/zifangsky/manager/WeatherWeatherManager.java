package cn.zifangsky.manager;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherWeather;
import cn.zifangsky.model.bo.WeatherWeatherBO;

import java.util.List;

public interface WeatherWeatherManager {
    public int deleteByPrimaryKey(Long id);

    public int insert(WeatherWeather weather);

    public int insertSelective(WeatherWeather weather);

    public WeatherWeather selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(WeatherWeather weather);

    public int updateByPrimaryKey(WeatherWeather weather);
    /**
     * 查询数据总数
     * @return
     */
    public Long findAllCount(WeatherWeather weather);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param weather
     * @return
     */
    public List<WeatherWeather> findAll(PageInfo pageInfo,WeatherWeather weather);
    
    /**
     * 无条件查询所有
     * @return
     */
    public List<WeatherWeather> selectAll();
    
    /**
     * 通过stationId查询记录
     * @param stationId
     * @return
     */
    public WeatherWeather selectByStationId(Long stationId);
    
    /**
     * 通过城镇Code查询天气
     * @param stationCode
     * @return
     */
    public WeatherWeatherBO selectByStationCode(String stationCode);
    
    /**
     * 通过城镇名字查询天气（模糊查询）
     * @param stationName
     * @return
     */
    public List<WeatherWeatherBO> selectByStationName(String stationName);
}
