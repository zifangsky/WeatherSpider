package cn.zifangsky.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;

import org.springframework.context.annotation.Scope;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherProvince;

@Scope("prototype")
@WebService
public interface WeatherProvinceService {
	@WebMethod
    public int deleteByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int insert(@WebParam(name="province") WeatherProvince province);

	@WebMethod
    public int insertSelective(@WebParam(name="province") WeatherProvince province);

	@WebMethod
    public WeatherProvince selectByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int updateByPrimaryKeySelective(@WebParam(name="province") WeatherProvince province);

	@WebMethod
    public int updateByPrimaryKey(@WebParam(name="province") WeatherProvince province);
    
    /**
     * 查询数据总数
     * @return
     */
	@WebMethod
    public Long findAllCount(@WebParam(name="province") WeatherProvince province);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param province
     * @return
     */
	@WebMethod
    public List<WeatherProvince> findAll(@WebParam(name="pageInfoHolder",mode=WebParam.Mode.INOUT) Holder<PageInfo> pageInfoHolder,@WebParam(name="province") WeatherProvince province);
}
