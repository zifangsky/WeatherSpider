package cn.zifangsky.spider;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.zifangsky.manager.WeatherStationManager;
import cn.zifangsky.manager.WeatherWeatherManager;
import cn.zifangsky.model.WeatherWeather;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 自定义Pipeline处理抓取的数据
 * @author zifangsky
 *
 */
@Component("customPipeline")
public class CustomPipeline implements Pipeline {
	@Autowired
	private WeatherStationManager stationManager;
	
	@Resource(name="weatherWeatherManager")
	private WeatherWeatherManager weatherManager;
	
	/**
	 * 保存数据
	 */
	@Override
	public void process(ResultItems resultItems, Task task) {
		WeatherWeather weather = resultItems.get("weather");
		Long stationId = stationManager.selectIdByCode(resultItems.get("stationCode").toString());
		if(stationId != null){
			weather.setStationId(stationId);
			WeatherWeather oldWeather = weatherManager.selectByStationId(stationId);
			if(oldWeather == null){
				weatherManager.insertSelective(weather);
			}else{
				weather.setId(oldWeather.getId());
				weatherManager.updateByPrimaryKeySelective(weather);
			}
			
		}
	
	}

}
