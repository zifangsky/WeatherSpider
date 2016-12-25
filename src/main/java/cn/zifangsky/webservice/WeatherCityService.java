package cn.zifangsky.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;

import org.springframework.context.annotation.Scope;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherCity;

@Scope("prototype")
@WebService
public interface WeatherCityService {
	
	@WebMethod
    public int deleteByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int insert(@WebParam(name="city") WeatherCity city);

	@WebMethod
    public int insertSelective(@WebParam(name="city") WeatherCity city);

	@WebMethod
    public WeatherCity selectByPrimaryKey(@WebParam(name="id") Long id);

    @WebMethod
    public int updateByPrimaryKeySelective(@WebParam(name="city") WeatherCity city);

    @WebMethod
    public int updateByPrimaryKey(@WebParam(name="city") WeatherCity city);
    
    /**
     * 查询数据总数
     * @return
     */
    @WebMethod
    public Long findAllCount(@WebParam(name="city") WeatherCity city);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
    @WebMethod
    public List<WeatherCity> findAll(@WebParam(name="pageInfoHolder",mode=WebParam.Mode.INOUT) Holder<PageInfo> pageInfoHolder,@WebParam(name="city") WeatherCity city);
}
