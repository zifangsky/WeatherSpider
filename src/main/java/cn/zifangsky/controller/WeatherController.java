package cn.zifangsky.controller;

import cn.zifangsky.manager.WeatherWeatherManager;
import cn.zifangsky.model.bo.WeatherWeatherBO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 天气相关的对外RESTful接口
 *
 * @author zifangsky
 * @date 2018/6/20
 * @since 1.0.0
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Resource(name="weatherWeatherManager")
	private WeatherWeatherManager weatherManager;

    /**
     * 根据一个城镇CODE返回对应天气
     * @author zifangsky
     * @date 2018/6/21 10:37
     * @since 1.0.0
     * @param stationCode 中国天气网里的城镇CODE
     * @return cn.zifangsky.model.bo.WeatherWeatherBO
     */
    @RequestMapping(value = "/selectByStationCode/{stationCode}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public WeatherWeatherBO selectByStationCode(@PathVariable String stationCode){
        return weatherManager.selectByStationCode(stationCode);
    }

    /**
     * 根据城镇名称模糊查询，返回所有匹配的城镇天气
     * @author zifangsky
     * @date 2018/6/21 10:37
     * @since 1.0.0
     * @param stationName 查询关键字，比如：朝阳
     * @return java.util.List<cn.zifangsky.model.bo.WeatherWeatherBO>
     */
    @RequestMapping(value = "/selectByStationName/{stationName}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<WeatherWeatherBO> selectByStationName(@PathVariable String stationName){
        return weatherManager.selectByStationName(stationName);
    }

}
