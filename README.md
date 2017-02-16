# WeatherSpider

天气爬虫（全国城镇天气自动定时抓取更新，并开放RESTful查询接口），附带代理IP池定时更新并检测其可用性

## 简介
我在之前的某个项目中需要用到天气接口，但是遍观网上的天气API要么是收费的要么有使用次数或者频率的限制。因此我决定根据网上的专业天气网站结合爬虫技术自己开发一套天气自动定时抓取更新的API接口

### （1）技术依赖：
* `SSM（Spring+Spring MVC+Mybatis）`：项目基本架构
* `WebMagic`：轻量型爬虫框架，用于抓取每个城镇的天气以及抓取免费代理IP
* `ActiveMQ`：消息中间件，用于在定时更新全国城镇天气时将每个城镇天气的更新任务压入消息队列中，之后再使用多个消费者去消费这些消息（PS：多个消息消费者同时更新这些城镇的天气）
* `Quartz`：定时调度，用于设置每隔多久更新一次天气；每隔多久抓取一次代理IP；每隔多久检测一下数据库中的代理IP是否仍然有效
* `Apache CXF`：用于对外发布SOAP风格和RESTFul风格的接口

### （2）环境依赖：
* JDK7及以上
* Tomcat7及以上
* ActiveMQ-5.14.1及以上
* MYSQL5及以上

注：项目中使用到的SQL文件：[https://raw.githubusercontent.com/zifangsky/WeatherSpider/master/weatherspider.sql](https://raw.githubusercontent.com/zifangsky/WeatherSpider/master/weatherspider.sql)

### （3）项目运行：
#### i）源码下载：
全部代码已经开源，项目地址是：[https://github.com/zifangsky/WeatherSpider](https://github.com/zifangsky/WeatherSpider)

**PS：希望感兴趣的朋友给我来一波star，谢谢！**

#### ii）配置依赖环境：
首先编译源代码，生成war包并将war包放到Tomcat中。接着分别启动运行MYSQL和ActiveMQ

修改配置文件中对应的MYSQL和ActiveMQ的连接参数以及定时任务的更新频率，配置文件的路径是：`src/main/env/dev/`

#### iii）运行项目：
启动Tomcat之后，根据前面设置的定时任务将会在指定时间开始执行天气更新任务、代理IP获取任务以及代码IP的可用性检测任务

### （4）RESTful接口：
除了一些基本的SOAP风格的接口之外，我还对外发布了4个RESTful风格的接口。它们分别是：

#### i）随机返回一个可用的代理IP：
`http://localhost:7080/WeatherSpider/services/rest/proxyIpService/getRandomOne`

#### ii）返回当前所有可用的代理IP：
`http://localhost:7080/WeatherSpider/services/rest/proxyIpService/getAll`

其效果如下：
![https://www.zifangsky.cn/wp-content/uploads/2017/02/20170216163914.png](https://www.zifangsky.cn/wp-content/uploads/2017/02/20170216163914.png)

注：可以在这个网站对json字符串进行格式化：http://json.cn/

#### iii）根据城镇CODE返回一个城镇天气：
`http://localhost:7080/WeatherSpider/services/rest/weatherService/getWeatherByStationCode?stationCode=101060404`

其效果如下：
![https://www.zifangsky.cn/wp-content/uploads/2017/02/20170216164251.png](https://www.zifangsky.cn/wp-content/uploads/2017/02/20170216164251.png)

#### iv）根据城镇名称模糊查询，返回所有匹配的城镇天气：
`http://localhost:7080/WeatherSpider/services/rest/weatherService/getWeatherByStationName?stationName=朝阳`

其效果如下：
![https://www.zifangsky.cn/wp-content/uploads/2017/02/20170216164524.png](https://www.zifangsky.cn/wp-content/uploads/2017/02/20170216164524.png)

## 项目开发思路
关于这个项目的开发思路以及完整的开发流程可以参考我的这篇文章：https://www.zifangsky.cn/901.html
