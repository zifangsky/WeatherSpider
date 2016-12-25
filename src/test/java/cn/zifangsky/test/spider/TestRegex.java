package cn.zifangsky.test.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import cn.zifangsky.spider.UserAgentUtils;
import us.codecraft.webmagic.selector.Html;

public class TestRegex {
	
	@Test
	public void test1(){
		
		String line = "{\"showapi_res_code\":0,\"showapi_res_error\":\"\",\"showapi_res_body\":{\"ret_code\":0,\"pagebean\":{\"allPages\":1,\"contentlist\":[{\"port\":8118,\"speed\":4.443,\"anoy\":\"透明\",\"checkTime\":\"2016-12-08 15:23:31.637\",\"pos\":\"\",\"ip\":\"119.118.33.137\"},{\"port\":80,\"speed\":3.384,\"anoy\":\"透明\",\"checkTime\":\"2016-12-08 15:23:30.578\",\"pos\":\"\",\"ip\":\"119.254.92.52\"},";
		Pattern pattern = Pattern.compile("\"port\":(\\d+),\"speed.*?(\\d+\\.\\d+\\.\\d+\\.\\d+)\"");
		Matcher matcher = pattern.matcher(line);
		while(matcher.find()){
			System.out.println(matcher.group(1) + ":" + matcher.group(2));
		}
	}
	
	@Test
	public void test2(){
//		String str = "<img src=\"http://fs.xicidaili.com/images/flag/cn.png\" alt=\"Cn\" /> 183.164.128.219 8118  ";
//		Html html = Html.create(str);
//		
//		String res = html.xpath("//body/text(1)").toString();
//		System.out.println(res);
		
		String str = "121.204.165.216 8118    高匿 HTTP       3小时 16-12-08 17:55";
		String[] xx = str.split("\\s+");
		System.out.println(xx);
	}
	
	@Test
	public void test3(){
		String dataStr = " <body> 121.232.144.69 9000 高匿名 HTTP 中国 江苏省 镇江市 电信 3秒 2016-12-14 17:36:19 ";
		Pattern pattern = Pattern.compile("HTTPS?\\s(.*)?\\s\\d秒");
		Matcher matcher = pattern.matcher(dataStr);
		if(matcher.find()){
			System.out.println(matcher.group(1));
		}
	
	}
}
