package cn.zifangsky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherCountry;

public interface WeatherCountryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WeatherCountry country);

    int insertSelective(WeatherCountry country);

    WeatherCountry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WeatherCountry country);

    int updateByPrimaryKey(WeatherCountry country);
    
    /**
     * 查询数据总数
     * @return
     */
    Long findAllCount(WeatherCountry country);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param country
     * @return
     */
    List<WeatherCountry> findAll(@Param("pageInfo") PageInfo pageInfo,@Param("country") WeatherCountry country);
    
    /**
     * 无条件查询所有
     * @return
     */
    List<WeatherCountry> selectAll();
}