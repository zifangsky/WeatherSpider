package cn.zifangsky.manager;

import java.util.List;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherCity;

public interface WeatherCityManager {
    public int deleteByPrimaryKey(Long id);

    public int insert(WeatherCity city);

    public int insertSelective(WeatherCity city);

    public WeatherCity selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(WeatherCity city);

    public int updateByPrimaryKey(WeatherCity city);
    
    /**
     * 查询数据总数
     * @return
     */
    public Long findAllCount(WeatherCity city);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
    public List<WeatherCity> findAll(PageInfo pageInfo,WeatherCity city);
    
}
