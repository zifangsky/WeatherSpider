package cn.zifangsky.manager;

import java.util.List;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherStation;

public interface WeatherStationManager {
    public int deleteByPrimaryKey(Long id);

    public int insert(WeatherStation station);

    public int insertSelective(WeatherStation station);

    public WeatherStation selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(WeatherStation station);

    public int updateByPrimaryKey(WeatherStation station);
    /**
     * 查询数据总数
     * @return
     */
    public Long findAllCount(WeatherStation station);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
    public List<WeatherStation> findAll(PageInfo pageInfo,WeatherStation station);
    
    /**
     * 无条件查询所有
     * @return
     */
    public List<WeatherStation> selectAll();
    
    /**
     * 通过code查询ID
     * @param code
     * @return
     */
    public Long selectIdByCode(String code);
}
