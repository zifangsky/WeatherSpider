package cn.zifangsky.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;

import org.springframework.context.annotation.Scope;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherStation;

@Scope("prototype")
@WebService
public interface WeatherStationService {
	@WebMethod
    public int deleteByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int insert(@WebParam(name="station") WeatherStation station);

	@WebMethod
    public int insertSelective(@WebParam(name="station") WeatherStation station);

	@WebMethod
    public WeatherStation selectByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int updateByPrimaryKeySelective(@WebParam(name="station") WeatherStation station);

	@WebMethod
    public int updateByPrimaryKey(@WebParam(name="station") WeatherStation station);
    /**
     * 查询数据总数
     * @return
     */
	@WebMethod
    public Long findAllCount(@WebParam(name="station") WeatherStation station);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
	@WebMethod
    public List<WeatherStation> findAll(@WebParam(name="pageInfoHolder",mode=WebParam.Mode.INOUT) Holder<PageInfo> pageInfoHolder,@WebParam(name="station") WeatherStation station);
}
