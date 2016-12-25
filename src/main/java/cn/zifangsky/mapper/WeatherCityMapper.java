package cn.zifangsky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherCity;

public interface WeatherCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WeatherCity city);

    int insertSelective(WeatherCity city);

    WeatherCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WeatherCity city);

    int updateByPrimaryKey(WeatherCity city);
    
    /**
     * 查询数据总数
     * @return
     */
    Long findAllCount(WeatherCity city);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
    List<WeatherCity> findAll(@Param("pageInfo") PageInfo pageInfo,@Param("city") WeatherCity city);
    
    /**
     * 无条件查询所有
     * @return
     */
    List<WeatherCity> selectAll();
}