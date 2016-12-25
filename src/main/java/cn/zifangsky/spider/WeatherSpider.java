package cn.zifangsky.spider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.zifangsky.model.WeatherWeather;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

public class WeatherSpider implements PageProcessor{
	
	private Site site = Site.me().setTimeOut(20000).setRetryTimes(3)
			.setSleepTime(2000).setCharset("UTF-8");
	
	@Override
	public Site getSite() {
		Set<Integer> acceptStatCode = new HashSet<>();
		acceptStatCode.add(200);
		site = site.setAcceptStatCode(acceptStatCode).addHeader("Accept-Encoding", "/")
				.setUserAgent(UserAgentUtils.radomUserAgent());
		
		return site;
	}

	@Override
	public void process(Page page) {
		//最近7天天气
		Selectable sevenStr = page.getHtml().xpath("//div[@id='7d']/ul[@class='t clearfix']");
		//分时段天气
		Selectable hourStr = page.getHtml().xpath("//div[@id='7d']/script");
		//最近24小时整体情况
//		Selectable t24Str = page.getHtml().xpath("//div[@class='left fl']/script");
		
		WeatherWeather weather = new WeatherWeather();
		weather.setHour(handleHourStr(hourStr));;
		
		List<String> list = handleSevenDays(sevenStr);
		if(list != null && list.size() == 7){
			weather.setToday(list.get(0));
			weather.setNextday(list.get(1));
			weather.setNext2day(list.get(2));
			weather.setNext3day(list.get(3));
			weather.setNext4day(list.get(4));
			weather.setNext5day(list.get(5));
			weather.setNext6day(list.get(6));
		}
		page.putField("weather", weather);
		page.putField("stationCode", page.getUrl().regex("(\\d+).shtml",1));
	}

	/**
	 * 处理分时段天气
	 * @param hourStr
	 * @return 
	 */
	private String handleHourStr(Selectable hourStr) {
		String result = hourStr.regex("1d.*?(\\[.*?\\])",1).replace("&quot;", "\"").toString();
		
		if(result != null){
			return result;
		}else{
			return "";
		}
	}

	/**
	 * 处理最近7天天气
	 * @param sevenStr
	 * @return 最近7天天气格式化之后的集合
	 */
	private List<String> handleSevenDays(Selectable sevenStr) {
		List<String> sevenDays = sevenStr.xpath("//ul[@class='t clearfix']/li").all();
		List<String> result = new ArrayList<>();
		
		if(sevenDays != null && sevenDays.size() > 0){
			for(String day : sevenDays){
				Html temp = Html.create(day);
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(temp.xpath("//h1/text()").toString());
				stringBuffer.append("," + temp.xpath("//p[@class='wea']/text()").toString());
				stringBuffer.append("," + temp.xpath("//p[@class='tem']/allText()").toString());
				
				List<String> windList = temp.xpath("//p[@class='win']/em/span").all();
				String windStr = ",";
				if(windList !=null && windList.size() > 0){
					for(String win : windList){
						Html winHtml = Html.create(win);
						windStr = windStr + winHtml.xpath("//span/@title") + "/";
					}
				}
				stringBuffer.append(windStr.substring(0, windStr.length()-1));
				stringBuffer.append("," + temp.xpath("//p[@class='win']/i/text()").toString());

				result.add(stringBuffer.toString());
			}
		}
		
		return result;
	}
}
