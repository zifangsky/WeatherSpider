package cn.zifangsky.webservice.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.Holder;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.ProxyIpManager;
import cn.zifangsky.model.ProxyIp;
import cn.zifangsky.webservice.ProxyIpService;

public class ProxyIpServiceImpl implements ProxyIpService {

	@Resource(name="proxyIpManager")
	private ProxyIpManager proxyIpManager;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return proxyIpManager.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProxyIp proxyIp) {
		return proxyIpManager.insert(proxyIp);
	}

	@Override
	public int insertSelective(ProxyIp proxyIp) {
		return proxyIpManager.insertSelective(proxyIp);
	}

	@Override
	public ProxyIp selectByPrimaryKey(Long id) {
		return proxyIpManager.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ProxyIp proxyIp) {
		return proxyIpManager.updateByPrimaryKeySelective(proxyIp);
	}

	@Override
	public int updateByPrimaryKey(ProxyIp proxyIp) {
		return proxyIpManager.updateByPrimaryKey(proxyIp);
	}

	@Override
	public Long findAllCount(ProxyIp proxyIp) {
		return proxyIpManager.findAllCount(proxyIp);
	}

	@Override
	public List<ProxyIp> findAll(Holder<PageInfo> pageInfoHolder, ProxyIp proxyIp) {
		pageInfoHolder.value.setCount(proxyIpManager.findAllCount(proxyIp));
		return proxyIpManager.findAll(pageInfoHolder.value, proxyIp);
	}

	@Override
	public List<ProxyIp> selectAll() {
		return proxyIpManager.selectAll();
	}

	@Override
	public ProxyIp selectRandomIP() {
		return proxyIpManager.selectRandomIP();
	}

}
