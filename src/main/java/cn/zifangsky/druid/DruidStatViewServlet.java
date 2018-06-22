package cn.zifangsky.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/druid/*",
		initParams={
			@WebInitParam(name="allow",value="127.0.0.1") //IP白名单 (没有配置或者为空，则允许所有访问)
			,@WebInitParam(name="loginUsername",value="admin")
			,@WebInitParam(name="loginPassword",value="admin")
			,@WebInitParam(name = "resetEnable", value = "false") //禁用HTML页面上的“Reset All”功能)
		})
public class DruidStatViewServlet extends StatViewServlet {
	private static final long serialVersionUID = 6128376524152847518L;

}
