package cn.zifangsky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
@MapperScan("cn.zifangsky.mapper")
public class WeatherSpiderApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WeatherSpiderApplication.class);

        //关闭Banner
//		application.setBannerMode(Mode.OFF);
        //控制台输出
		application.setBannerMode(Banner.Mode.CONSOLE);
		application.run(args);
	}
}
