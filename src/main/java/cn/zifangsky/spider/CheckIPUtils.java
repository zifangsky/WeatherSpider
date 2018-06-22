package cn.zifangsky.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.MessageFormat;

public class CheckIPUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckIPUtils.class);
	
	/**
	 * 校验代理IP的有效性，测试地址为：http://www.ip138.com
	 * @param ip 代理IP地址
	 * @param port  代理IP端口
	 * @return  此代理IP是否有效
	 */
	public static boolean checkValidIP(String ip,Integer port){
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL("http://www.ip138.com");
			//代理服务器
			InetSocketAddress proxyAddr = new InetSocketAddress(ip, port);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddr);
			connection = (HttpURLConnection) url.openConnection(proxy);
			connection.setReadTimeout(4000);
			connection.setConnectTimeout(4000);
			connection.setRequestMethod("GET");

			if(connection.getResponseCode() == 200){
				return true;
			}
			
		} catch (Exception e) {
            LOGGER.error(MessageFormat.format("代理IP[{0} {1}]不可用", ip,port));
		}finally {
            if(connection != null){
                connection.disconnect();
            }
        }
		return false;
	}
}
