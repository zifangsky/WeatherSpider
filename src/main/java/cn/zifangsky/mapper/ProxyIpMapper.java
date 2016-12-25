package cn.zifangsky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.ProxyIp;

public interface ProxyIpMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProxyIp proxyIp);

    int insertSelective(ProxyIp proxyIp);

    ProxyIp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProxyIp proxyIp);

    int updateByPrimaryKey(ProxyIp proxyIp);
    
    /**
     * 查询数据总数
     * @return
     */
    Long findAllCount(ProxyIp proxyIp);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
    List<ProxyIp> findAll(@Param("pageInfo") PageInfo pageInfo,@Param("proxyIp") ProxyIp proxyIp);
    
    /**
     * 根据IP和端口查询
     * @param ip
     * @param port
     * @return
     */
    ProxyIp selectByIPPort(@Param("ip") String ip,@Param("port") Integer port);
    
    /**
     * 无条件查询所有
     * @return
     */
    List<ProxyIp> selectAll();
}