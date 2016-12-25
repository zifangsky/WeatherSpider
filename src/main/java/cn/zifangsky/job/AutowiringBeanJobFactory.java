package cn.zifangsky.job;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component("autowiringBeanJobFactory")
public class AutowiringBeanJobFactory extends AdaptableJobFactory {
	@Autowired
	private AutowireCapableBeanFactory autowiringBeanJobFactory;
	
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		//创建jobInstance
		Object jobInstance = super.createJobInstance(bundle);
		//向Spring中注入这个bean
		autowiringBeanJobFactory.autowireBean(jobInstance);
		
		return jobInstance;
	}

}
