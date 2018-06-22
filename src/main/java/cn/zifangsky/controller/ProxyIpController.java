package cn.zifangsky.controller;

import cn.zifangsky.manager.ProxyIpManager;
import cn.zifangsky.model.ProxyIp;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 代理IP相关的对外RESTful接口
 *
 * @author zifangsky
 * @date 2018/6/20
 * @since 1.0.0
 */
@RestController
@RequestMapping("/proxyIp")
public class ProxyIpController {
	@Resource(name="proxyIpManager")
	private ProxyIpManager proxyIpManager;

    /**
     * 返回数据库中所有可用的代理IP
     * @author zifangsky
     * @date 2018/6/21 10:40
     * @since 1.0.0
     * @return java.util.List<cn.zifangsky.model.ProxyIp>
     */
    @RequestMapping(value = "/selectAll",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<ProxyIp> selectAll(){
        return proxyIpManager.selectAll();
    }

    /**
     * 从数据库中随机返回一个可用的代理IP
     * @author zifangsky
     * @date 2018/6/21 10:41
     * @since 1.0.0
     * @return cn.zifangsky.model.ProxyIp
     */
    @RequestMapping(value = "/selectRandomIP",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ProxyIp selectRandomIP(){
        return proxyIpManager.selectRandomIP();
    }

}
