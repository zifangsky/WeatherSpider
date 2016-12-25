package cn.zifangsky.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Holder;

import org.springframework.context.annotation.Scope;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.model.WeatherWeather;
import cn.zifangsky.model.bo.WeatherWeatherBO;

@Scope("prototype")
@WebService
public interface WeatherWeatherService {
	@WebMethod
    public int deleteByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int insert(@WebParam(name="weather") WeatherWeather weather);

	@WebMethod
    public int insertSelective(@WebParam(name="weather") WeatherWeather weather);

	@WebMethod
    public WeatherWeather selectByPrimaryKey(@WebParam(name="id") Long id);

	@WebMethod
    public int updateByPrimaryKeySelective(@WebParam(name="weather") WeatherWeather weather);

	@WebMethod
    public int updateByPrimaryKey(@WebParam(name="weather") WeatherWeather weather);
    /**
     * 查询数据总数
     * @return
     */
	@WebMethod
    public Long findAllCount(@WebParam(name="weather") WeatherWeather weather);
    
    /**
     * 分页查询
     * @param pageInfo
     * @param city
     * @return
     */
	@WebMethod
    public List<WeatherWeather> findAll(@WebParam(name="pageInfoHolder",mode=WebParam.Mode.INOUT) Holder<PageInfo> pageInfoHolder,@WebParam(name="weather") WeatherWeather weather);
  
    /**
     * 通过城镇Code查询天气
     * @param stationCode
     * @return
     */
	@WebMethod
	public WeatherWeatherBO selectByStationCode(@WebParam(name="stationCode") String stationCode);
    
    /**
     * 通过城镇名字查询天气（模糊查询）
     * @param stationName
     * @return
     */
	@WebMethod
	public List<WeatherWeatherBO> selectByStationName(@WebParam(name="stationName") String stationName);
	
	/**
	 * 通过城镇Code查询天气，RESTful接口
	 * @param stationCode
	 * @return
	 */
	@GET
	@Path("/getWeatherByStationCode")
	@Produces(MediaType.APPLICATION_JSON)
	public WeatherWeatherBO selectByStationCodeRest(@QueryParam("stationCode") String stationCode);
	
	/**
	 * 通过城镇名字查询天气（模糊查询），RESTful接口
	 * @param stationName
	 * @return
	 */
	@GET
	@Path("/getWeatherByStationName")
	@Produces(MediaType.APPLICATION_JSON)
	public List<WeatherWeatherBO> selectByStationNameRest(@QueryParam("stationName") String stationName);
}
