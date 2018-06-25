# WeatherSpider

天气爬虫（全国城镇天气自动定时抓取更新，并开放RESTful查询接口），附带代理IP池定时更新并检测其可用性

## 2018年6月更新说明：
这个项目本来是我两年前写的一个`Demo`项目，当时为了熟悉Java开发的常用框架，因此在项目中使用了一些没必要的框架，导致最后项目的框架依赖问题比较严重，且部分代码的写法较为繁琐。这两年来，因为这个项目还是获得了一些朋友的喜欢，加上自己对`Java`技术有了更多的了解，因此在这个6月我对项目代码进行了重构。项目的总体逻辑没有改变，只是优化了项目的架构、减少了技术依赖以及优化了部分代码的写法。

##### 特别提醒： #####

- 新代码地址：https://github.com/zifangsky/WeatherSpider
- 旧代码地址：https://github.com/zifangsky/WeatherSpider/tree/old

#### 技术依赖：
* `Spring Boot`：项目基础架构，包括提供`基本Web服务`和`定时调度服务`
* `Druid`：`Alibaba`开源的`JDBC`数据库连接池
* `MyBatis `：一款优秀的持久层框架 ，用于访问`MySQL`数据库
* `WebMagic`：轻量型爬虫框架，用于抓取每个城镇的天气以及抓取免费代理IP（*抓取到的代理IP没有在项目中使用*）
* `Spring Kafka`：用于在`Spring`应用中连接`Kafka集群`，在项目中主要通过`消息队列`的方式更新各个城市的天气以及检测爬取到的代理IP是否失效
* `ZooKeeper`：`Spring Kafka`环境需要的依赖

#### 环境依赖：
* `JDK8+`
* `MYSQL5.7+`
* `Kafka集群`

##### 特别提醒： #####

- 项目中使用到的SQL文件：[https://raw.githubusercontent.com/zifangsky/WeatherSpider/master/weatherspider.sql](https://raw.githubusercontent.com/zifangsky/WeatherSpider/master/weatherspider.sql)
- Linux环境下的Kafka集群的安装步骤可以参考：[https://www.zifangsky.cn/1227.html](https://www.zifangsky.cn/1227.html)

#### 项目结构： ####

![项目结构](https://raw.githubusercontent.com/zifangsky/WeatherSpider/master/%E9%A1%B9%E7%9B%AE%E7%BB%93%E6%9E%84.png)

#### 项目运行：

##### 1、下载源码：
项目地址：[https://github.com/zifangsky/WeatherSpider](https://github.com/zifangsky/WeatherSpider)

**PS：希望感兴趣的朋友给我来一波star，谢谢！**

##### 2、配置Kafka集群：
具体安装步骤可以参考上面给出的链接，然后创建两个项目中需要使用的`Topic`，它们分别是：`topic-proxyIp`和`topic-weather`

##### 3、配置数据库环境： #####

具体用到的SQL文件可以参考上面给出的链接

##### 4、修改项目的配置文件： #####

在这里只需要修改项目中`application-dev.properties`文件的相关配置即可，具体配置项的含义可以参考注释

##### 5、运行项目：
使用`Maven`编译项目，然后运行编译生成的`jar文件`（或者可以直接下载[Releases的jar包](https://github.com/zifangsky/WeatherSpider/releases/)）。在项目启动起来之后，就可以根据前面设置的定时任务、在指定时间执行天气更新任务、代理IP获取任务以及代理IP的可用性检测任务

#### 项目对外提供的RESTful接口：
本项目对外发布了4个RESTful风格的接口。它们分别是：

##### i）随机返回一个可用的代理IP：
`http://localhost:7080/proxyIp/selectRandomIP`

其输出报文如下：

```xml
{
  "id": 676,
  "ip": "101.37.79.125",
  "port": 3128,
  "type": "HTTPS",
  "addr": null,
  "used": false,
  "other": null
}
```

##### ii）返回当前所有可用的代理IP：
`http://localhost:7080/proxyIp/selectAll`

其输出报文如下：

```xml
[
  {
    "id": 667,
    "ip": "61.135.217.7",
    "port": 80,
    "type": "HTTP",
    "addr": "北京",
    "used": false,
    "other": null
  },
  {
    "id": 668,
    "ip": "122.114.31.177",
    "port": 808,
    "type": "HTTP",
    "addr": "河南郑州",
    "used": false,
    "other": null
  },
  {
    "id": 669,
    "ip": "221.228.17.172",
    "port": 8181,
    "type": "HTTPS",
    "addr": "江苏无锡",
    "used": false,
    "other": null
  },
  
  ...
  
]
```

##### iii）根据城镇CODE返回一个城镇天气：
`http://localhost:7080/weather/selectByStationCode/{stationCode}`

注意：这里的	CODE可以从数据库的`weather_station`表查询

其输出报文如下：

```xml
{
  "country": {
    "id": 1,
    "code": "101",
    "name": "中国",
    "description": null
  },
  "province": {
    "id": 18,
    "code": "10106",
    "countryId": 1,
    "name": "吉林",
    "description": null
  },
  "city": {
    "id": 175,
    "code": "1010604",
    "provinceId": 18,
    "name": "四平",
    "description": null
  },
  "station": {
    "id": 214,
    "code": "101060404",
    "cityId": 175,
    "name": "公主岭",
    "description": null
  },
  "weather": {
    "id": 172,
    "stationId": 214,
    "hour": "[\"21日08时,d01,多云,21℃,西南风,<3级,2\",\"21日11时,d00,晴,24℃,西南风,<3级,1\",\"21日14时,d00,晴,24℃,西南风,<3级,1\",\"21日17时,d00,晴,26℃,西南风,<3级,1\",\"21日20时,n00,晴,23℃,西南风,<3级,0\",\"21日23时,n00,晴,19℃,西南风,<3级,0\",\"22日02时,n00,晴,19℃,西南风,<3级,0\",\"22日05时,n00,晴,19℃,西南风,3-4级,0\",\"22日08时,d00,晴,24℃,西南风,3-4级,1\"]",
    "today": "21日（今天）,晴,27/19℃,西南风/西南风,<3级转3-4级",
    "nextday": "22日（明天）,晴,30/22℃,西南风/西南风,4-5级转3-4级",
    "next2day": "23日（后天）,小雨转晴,29/20℃,西南风/西南风,<3级",
    "next3day": "24日（周日）,晴,29/18℃,西南风/西南风,<3级转4-5级",
    "next4day": "25日（周一）,晴,28/17℃,西南风/西南风,4-5级转<3级",
    "next5day": "26日（周二）,小雨,25/16℃,西南风/西南风,<3级",
    "next6day": "27日（周三）,小雨转大雨,26/15℃,西南风/无持续风向,<3级",
    "t24situation": null,
    "other": null
  }
}
```



##### iv）根据城镇名称模糊查询，返回所有匹配的城镇天气：
`http://localhost:7080/weather/selectByStationName/{stationName}`

注意：这里的`stationName`可以是城市/地区的中文名称，比如：朝阳

其输出报文如下：

```xml
[
  {
    "country": {
      "id": 1,
      "code": "101",
      "name": "中国",
      "description": null
    },
    "province": {
      "id": 16,
      "code": "10107",
      "countryId": 1,
      "name": "辽宁",
      "description": null
    },
    "city": {
      "id": 144,
      "code": "1010712",
      "provinceId": 16,
      "name": "朝阳",
      "description": null
    },
    "station": {
      "id": 13,
      "code": "101071201",
      "cityId": 144,
      "name": "朝阳",
      "description": null
    },
    "weather": {
      "id": 105,
      "stationId": 13,
      "hour": "[\"21日08时,d00,晴,24℃,西北风,<3级,2\",\"21日11时,d00,晴,32℃,西北风,<3级,2\",\"21日14时,d00,晴,33℃,西北风,<3级,2\",\"21日17时,d00,晴,33℃,西北风,<3级,2\",\"21日20时,n00,晴,26℃,西北风,3-4级,0\",\"21日23时,n00,晴,22℃,西南风,3-4级,0\",\"22日02时,n00,晴,21℃,西南风,3-4级,0\",\"22日05时,n00,晴,20℃,西南风,<3级,0\",\"22日08时,d00,晴,26℃,西南风,3-4级,2\"]",
      "today": "21日（今天）,晴,34/20℃,西北风/西南风,3-4级",
      "nextday": "22日（明天）,多云,34/22℃,西南风/西南风,4-5级",
      "next2day": "23日（后天）,多云,35/21℃,西南风/西北风,4-5级转3-4级",
      "next3day": "24日（周日）,多云转雷阵雨,34/21℃,东南风/西南风,4-5级转3-4级",
      "next4day": "25日（周一）,多云转小雨,33/20℃,西南风/西南风,4-5级转3-4级",
      "next5day": "26日（周二）,多云转晴,31/20℃,西南风/西南风,3-4级",
      "next6day": "27日（周三）,晴转小雨,33/19℃,西南风/无持续风向,3-4级转<3级",
      "t24situation": null,
      "other": null
    }
  },
  {
    "country": {
      "id": 1,
      "code": "101",
      "name": "中国",
      "description": null
    },
    "province": {
      "id": 24,
      "code": "10101",
      "countryId": 1,
      "name": "北京",
      "description": null
    },
    "city": {
      "id": 253,
      "code": "1010100",
      "provinceId": 24,
      "name": "北京",
      "description": null
    },
    "station": {
      "id": 803,
      "code": "101010300",
      "cityId": 253,
      "name": "朝阳",
      "description": null
    },
    "weather": {
      "id": 416,
      "stationId": 803,
      "hour": "[\"21日08时,d01,多云,25℃,东南风,<3级,1\",\"21日11时,d01,多云,33℃,东南风,<3级,1\",\"21日14时,d01,多云,34℃,东南风,<3级,3\",\"21日17时,d01,多云,33℃,东南风,<3级,3\",\"21日20时,n01,多云,32℃,东南风,<3级,0\",\"21日23时,n01,多云,24℃,东南风,<3级,0\",\"22日02时,n01,多云,24℃,东南风,<3级,0\",\"22日05时,n01,多云,23℃,东南风,<3级,0\",\"22日08时,d01,多云,23℃,东南风,<3级,1\"]",
      "today": "21日（今天）,多云,35/23℃,东南风/东南风,<3级",
      "nextday": "22日（明天）,雷阵雨转多云,32/21℃,南风/北风,<3级",
      "next2day": "23日（后天）,多云转晴,35/24℃,南风/南风,<3级",
      "next3day": "24日（周日）,多云,33/23℃,东南风/东风,<3级",
      "next4day": "25日（周一）,阴转雷阵雨,34/24℃,北风/北风,<3级",
      "next5day": "26日（周二）,多云转晴,34/22℃,西南风/西南风,<3级",
      "next6day": "27日（周三）,多云,36/24℃,西南风/西南风,3-4级转<3级",
      "t24situation": null,
      "other": null
    }
  }
]
```


## 项目开发和重构思路

1. 最原始的项目的开发思路：[https://www.zifangsky.cn/901.html](https://www.zifangsky.cn/901.html)
2. 本次的重构思路：[https://www.zifangsky.cn/1271.html](https://www.zifangsky.cn/1271.html)
