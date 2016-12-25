package cn.zifangsky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherStation;

public interface WeatherStationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WeatherStation station);

    int insertSelective(WeatherStation station);

    WeatherStation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WeatherStation station);

    int updateByPrimaryKey(WeatherStation station);
    /**
     * 查询数据总数
     * @return
     */
    Long findAllCount(WeatherStation station);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
    List<WeatherStation> findAll(@Param("pageInfo") PageInfo pageInfo,@Param("station") WeatherStation station);
    
    /**
     * 无条件查询所有
     * @return
     */
    List<WeatherStation> selectAll();
    
    /**
     * 通过code查询ID
     * @param code
     * @return
     */
    Long selectIdByCode(String code);
}