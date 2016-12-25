package cn.zifangsky.test.spider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestProxyIP {
	
	@Test
	public void testSurvival() throws Exception{
		Set<String> ipSet = new HashSet<>();  //待测ip地址
		
		URL url = new URL("http://www.66ip.cn/nmtq.php?getnum=100&isp=0&anonymoustype=3&start=&ports=&export=&ipaddress=&area=1&proxytype=0&api=66ip");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setReadTimeout(3000);
		connection.setConnectTimeout(3000);
		connection.setRequestMethod("GET");
		
		if(connection.getResponseCode() == 200){
			InputStream inputStream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = "";
			Pattern pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+");
			Matcher matcher = null;
			
			while((line = reader.readLine()) != null){
				matcher = pattern.matcher(line);
				while(matcher.find()){
					ipSet.add(matcher.group());
				}		
			}
			reader.close();
			inputStream.close();
		}
		connection.disconnect();
		
		System.out.println("待测数目： " + ipSet.size());
		Set<String> successIPSet = new HashSet<>();
		for(String proxyIP : ipSet){
			URL tmpURL = new URL("https://www.sogou.com");
			//代理服务器
			InetSocketAddress proxyAddr = new InetSocketAddress(proxyIP.split(":")[0], Integer.valueOf(proxyIP.split(":")[1]));
			Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddr);
			HttpURLConnection conn = (HttpURLConnection) tmpURL.openConnection(proxy);
			conn.setReadTimeout(5000);
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			
			if(conn.getResponseCode() == 200){
				successIPSet.add(proxyIP);
			}
		
		}
		
		System.out.println("*************************");
		System.out.println("可用数目： " + successIPSet.size());
		for(String proxyIP : successIPSet){
			System.out.println(proxyIP);
		}
		
	}
}
