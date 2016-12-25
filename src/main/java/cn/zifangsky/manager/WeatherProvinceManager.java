package cn.zifangsky.manager;

import java.util.List;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherProvince;

public interface WeatherProvinceManager {
    public int deleteByPrimaryKey(Long id);

    public int insert(WeatherProvince province);

    public int insertSelective(WeatherProvince province);

    public WeatherProvince selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(WeatherProvince province);

    public int updateByPrimaryKey(WeatherProvince province);
    
    /**
     * 查询数据总数
     * @return
     */
    public Long findAllCount(WeatherProvince province);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param province
     * @return
     */
    public List<WeatherProvince> findAll(PageInfo pageInfo,WeatherProvince province);
    
}
