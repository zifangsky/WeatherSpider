package cn.zifangsky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherProvince;

public interface WeatherProvinceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WeatherProvince province);

    int insertSelective(WeatherProvince province);

    WeatherProvince selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WeatherProvince province);

    int updateByPrimaryKey(WeatherProvince province);
    
    /**
     * 查询数据总数
     * @return
     */
    Long findAllCount(WeatherProvince province);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param province
     * @return
     */
    List<WeatherProvince> findAll(@Param("pageInfo") PageInfo pageInfo,@Param("province") WeatherProvince province);
    
    /**
     * 无条件查询所有
     * @return
     */
    List<WeatherProvince> selectAll();
}