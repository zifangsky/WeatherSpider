package cn.zifangsky.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Holder;

import org.springframework.context.annotation.Scope;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.ProxyIp;

@Scope("prototype")
@WebService
public interface ProxyIpService {
	@WebMethod
    public int deleteByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int insert(@WebParam(name="proxyIp") ProxyIp proxyIp);

	@WebMethod
    public int insertSelective(@WebParam(name="proxyIp") ProxyIp proxyIp);

	@WebMethod
    public ProxyIp selectByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int updateByPrimaryKeySelective(@WebParam(name="proxyIp") ProxyIp proxyIp);

	@WebMethod
    public int updateByPrimaryKey(@WebParam(name="proxyIp") ProxyIp proxyIp);
    
    /**
     * 查询数据总数
     * @return
     */
	@WebMethod
    public Long findAllCount(@WebParam(name="proxyIp") ProxyIp proxyIp);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
	@WebMethod
    public List<ProxyIp> findAll(@WebParam(name="pageInfoHolder",mode=WebParam.Mode.INOUT) Holder<PageInfo> pageInfoHolder,@WebParam(name="proxyIp") ProxyIp proxyIp);

	/**
     * 无条件查询所有
     * @return
     */
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
    public List<ProxyIp> selectAll();
	
	/**
	 * 从代理IP池的前10条数据中任意返回一条给用户
	 * @return
	 */
	@GET
	@Path("/getRandomOne")
	@Produces(MediaType.APPLICATION_JSON)
	@WebMethod
	public ProxyIp selectRandomIP();
}
