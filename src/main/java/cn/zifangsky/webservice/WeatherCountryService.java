package cn.zifangsky.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;

import org.springframework.context.annotation.Scope;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherCountry;

@Scope("prototype")
@WebService
public interface WeatherCountryService {
	
	@WebMethod
    public int deleteByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int insert(@WebParam(name="country") WeatherCountry country);
	
	@WebMethod
    public int insertSelective(@WebParam(name="country") WeatherCountry country);

	@WebMethod
    public WeatherCountry selectByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int updateByPrimaryKeySelective(@WebParam(name="country") WeatherCountry country);

	@WebMethod
    public int updateByPrimaryKey(@WebParam(name="country") WeatherCountry country);
    
    /**
     * 查询数据总数
     * @return
     */
	@WebMethod
    public Long findAllCount(@WebParam(name="country") WeatherCountry country);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param country
     * @return
     */
	@WebMethod
    public List<WeatherCountry> findAll(@WebParam(name="pageInfoHolder",mode=WebParam.Mode.INOUT) Holder<PageInfo> pageInfoHolder,@WebParam(name="country") WeatherCountry country);
}
