package cn.zifangsky.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 自定义Spring工具类
 * @author zifangsky
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
	
	/**
	 * 获取ApplicationContext对象
	 * @return
	 */
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	/**
	 * 根据bean的名称获取bean
	 * @param name
	 * @return
	 */
	public static Object getBeanByName(String name){
		return applicationContext.getBean(name);
	}
	
	/**
	 * 根据bean的class来查找对象
	 * @param <T>
	 * @param c
	 * @return
	 */
	public static <T> T getBeanByClass(Class<T> c){
		return applicationContext.getBean(c);
	}
	
	/**
	 * 根据bean的class来查找所有的对象（包括子类）
	 * @param <T>
	 * @param c
	 * @return
	 */
	public static <T> Map<String, T> getBeansByClass(Class<T> c){
		return applicationContext.getBeansOfType(c);
	}
	
	/**
	 * 获取HttpServletRequest
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return request;
	}
}
