package cn.zifangsky.manager;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.ProxyIp;

import java.util.List;

public interface ProxyIpManager {
    public int deleteByPrimaryKey(Long id);

    public int insert(ProxyIp proxyIp);

    public int insertSelective(ProxyIp proxyIp);

    public ProxyIp selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(ProxyIp proxyIp);

    public int updateByPrimaryKey(ProxyIp proxyIp);
    
    /**
     * 查询数据总数
     * @return
     */
    public Long findAllCount(ProxyIp proxyIp);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param proxyIp
     * @return
     */
    public List<ProxyIp> findAll(PageInfo pageInfo,ProxyIp proxyIp);
    
    /**
     * 根据IP和端口查询
     * @param ip
     * @param port
     * @return
     */
    public ProxyIp selectByIPPort(String ip,Integer port);
	/**
     * 无条件查询所有
     * @return
     */
    public List<ProxyIp> selectAll();
    
	/**
	 * 从代理IP池的前10条数据中任意返回一条给用户
	 * @return
	 */
    public ProxyIp selectRandomIP();
}
