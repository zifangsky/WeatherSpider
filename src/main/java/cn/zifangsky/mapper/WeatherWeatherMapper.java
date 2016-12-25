package cn.zifangsky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherWeather;

public interface WeatherWeatherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WeatherWeather weather);

    int insertSelective(WeatherWeather weather);

    WeatherWeather selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WeatherWeather weather);

    int updateByPrimaryKey(WeatherWeather weather);
    /**
     * 查询数据总数
     * @return
     */
    Long findAllCount(WeatherWeather weather);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
    List<WeatherWeather> findAll(@Param("pageInfo") PageInfo pageInfo,@Param("weather") WeatherWeather weather);
    
    /**
     * 无条件查询所有
     * @return
     */
    List<WeatherWeather> selectAll();
    
    /**
     * 通过stationId查询记录
     * @param stationId
     * @return
     */
    WeatherWeather selectByStationId(Long stationId);
    
    /**
     * 通过城镇Code查询天气
     * @param stationCode
     * @return
     */
    WeatherWeather selectByStationCode(String stationCode);
    
    /**
     * 通过城镇名字查询天气（模糊查询）
     * @param stationName
     * @return
     */
    List<WeatherWeather> selectByStationName(String stationName);
}