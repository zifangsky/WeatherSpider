package cn.zifangsky.manager;

import java.util.List;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherCountry;

public interface WeatherCountryManager {
    public int deleteByPrimaryKey(Long id);

    public int insert(WeatherCountry country);

    public int insertSelective(WeatherCountry country);

    public WeatherCountry selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(WeatherCountry country);

    public int updateByPrimaryKey(WeatherCountry country);
    
    /**
     * 查询数据总数
     * @return
     */
    public Long findAllCount(WeatherCountry country);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param country
     * @return
     */
    public List<WeatherCountry> findAll(PageInfo pageInfo,WeatherCountry country);
}
