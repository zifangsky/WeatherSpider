package cn.zifangsky.manager.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.ProxyIpManager;
import cn.zifangsky.mapper.ProxyIpMapper;
import cn.zifangsky.model.ProxyIp;

@Service("proxyIpManager")
public class ProxyIpManagerImpl implements ProxyIpManager {
	@Autowired
	private ProxyIpMapper proxyIpMapper;
	
	@Override
	public List<ProxyIp> selectAll() {
		return proxyIpMapper.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return proxyIpMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProxyIp proxyIp) {
		return proxyIpMapper.insert(proxyIp);
	}

	@Override
	public int insertSelective(ProxyIp proxyIp) {
		return proxyIpMapper.insertSelective(proxyIp);
	}

	@Override
	public ProxyIp selectByPrimaryKey(Long id) {
		return proxyIpMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ProxyIp proxyIp) {
		return proxyIpMapper.updateByPrimaryKeySelective(proxyIp);
	}

	@Override
	public int updateByPrimaryKey(ProxyIp proxyIp) {
		return proxyIpMapper.updateByPrimaryKey(proxyIp);
	}

	@Override
	public Long findAllCount(ProxyIp proxyIp) {
		return proxyIpMapper.findAllCount(proxyIp);
	}

	@Override
	public List<ProxyIp> findAll(PageInfo pageInfo, ProxyIp proxyIp) {
		return proxyIpMapper.findAll(pageInfo, proxyIp);
	}

	@Override
	public ProxyIp selectByIPPort(String ip, Integer port) {
		return proxyIpMapper.selectByIPPort(ip, port);
	}

	@Override
	public ProxyIp selectRandomIP() {
		List<ProxyIp> list = proxyIpMapper.selectAll();
		if(list != null && list.size() > 0){
			int size = list.size();
			Random random = new Random();
			int result = 0;
			if(size >= 10){
				result = random.nextInt(10);
			}else{
				result = random.nextInt(size);
			}
			return list.get(result);
		}else{
			return null;
		}
		
	}

}
