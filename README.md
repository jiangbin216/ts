# ts 项目
本项目基于 http://git.oschina.net/shuzheng/zheng 修改，界面样式请参考原项目。

整合了`spring-boot`、`mybatis`、`mapper`、`Pagehelper`、`dubbo`、`redis`...分布式后台管理系统，提供整套公共微服务服务模块：内容管理、支付中心、用户管理、微信平台、存储系统、配置中心、日志分析、任务和通知等，支持服务治理、监控和追踪等。

### 模块介绍
> spring-boot-starter-dubbo

`spring-boot` 和 `dubbo` 整合，`dubbo` 的启动器
> ts-core  

`spring-boot` + `Mybatis` 框架集成公共模块，包括公共配置、通用 `BaseService`、Paging分页对象、其他工具类等。
> mybatis-generator

mybatis代码生成工具
> ts-rpc

dubbo rpc服务模块。只要是api、model、service等
> ts-web

用户通用权限控制系统
> ts-webjars

第三方 `css`、`js` 等文件

### 构建说明

使用的时候，只需要修改 `ts\ts-rpc\ts-rpc-upms-service\src\main\resources\` 和 `ts\ts-web\ts-web-upms\src\main\resources\` 下对应的application-xxx.yml中的 druid、dubbo、zookeeper、redis 配置就行了，java 代码不用调整

系统默认帐号密码：admin/123456，没记错的话

1. 下载后，进入 `ts` 目录
2. 安装、配置 `maven` 环境
3. 执行 mvn install -Dmaven.test.skip=true
4. 执行 mvn eclipse:eclise 或者 mvn idea:idea，然后再导入到 `eclipse` 或者 `idea`
5. 配置好 `zookeeper`、`redis`、`oracle` 等环境，oracle 建表语句在 ts\ts-web\ts-web-upms\src\main\resources\backup\upms-oracle.sql
6. 启动 ts\ts-rpc\ts-rpc-upms-service\src\main\java\cn\ts\rpc\upms\RpcUpmsServiceApplication.java 的 `dubbo` 提供者服务
7. `tomcat`、`jetty` 等容器部署 ts\ts-web\ts-web-upms 项目

由于 oracle 的官方驱动包没有上传到 maven，所以要自己导入到本地仓库
```bash
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.1.0.7.0 -Dpackaging=jar -Dfile=D:/ts/ojdbc6-11.1.0.7.0.jar
```

