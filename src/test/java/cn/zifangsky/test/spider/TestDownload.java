package cn.zifangsky.test.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestDownload {
	
	@Test
	public void get() throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.weather.com.cn/weather/101060101.shtml");
		
		CloseableHttpResponse response = httpClient.execute(httpGet);
		if(response.getStatusLine().getStatusCode() == 200){
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
//			System.out.println(EntityUtils.toString(entity));
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = "";
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
		}

	}
	
	@Test
	public void get2() throws Exception{
		URL url = new URL("http://www.weather.com.cn/weather/101060101.shtml");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setReadTimeout(3000);
		connection.setConnectTimeout(3000);
		connection.setRequestMethod("GET");
		
		if(connection.getResponseCode() == 200){
			InputStream inputStream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = "";
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
			
			
		}
	}
	
	/**
	 * 测试代理IP的可用性
	 * @throws Exception
	 */
	@Test
	public void testConn() throws Exception{
		URL url = new URL("https://www.sogou.com");
		//代理服务器
		InetSocketAddress proxyAddr = new InetSocketAddress("42.202.39.7", 8118);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
		connection.setReadTimeout(4000);
		connection.setConnectTimeout(4000);
		connection.setRequestMethod("GET");
		
		
		if(connection.getResponseCode() == 200){
			System.out.println("可用");
		}else{
			System.out.println("不可用");
		}
		
		
	}
	
	@Test
	public void testConn2() throws Exception{
		URL url = new URL("http://www.ip138.com");
		//代理服务器
		InetSocketAddress proxyAddr = new InetSocketAddress("112.87.145.167", 8118);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddr);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
		connection.setReadTimeout(3000);
		connection.setConnectTimeout(3000);
		connection.setRequestMethod("GET");
		
		if(connection.getResponseCode() == 200){
			InputStream inputStream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = "";
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
		}else{
			System.out.println("不可用");
		}
	}
}
