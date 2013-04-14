先声网Web服务API
==============
1 简介
------
先声网Web服务是先声网对外开放的校园资源接口。基于RESTful Web Services发布。

2 如何使用
----------
### 2.1 添加Maven依赖
添加资源库

    <repository>
        <id>herald-web-services-api-mvn-repo</id>
        <url>https://raw.github.com/HeraldStudio/herald-web-services-api/mvn-repo/</url>
    </repository>

添加工件依赖

    <dependency>
        <groupId>cn.edu.seu.herald</groupId>
        <artifactId>herald-web-services-api</artifactId>
        <version>1.0.ALPHA2</version>
    </dependency>

### 2.2 代码示例

    final String HERALD_WS_BASE_URI = "http://herald.seu.edu.cn/ws";
    HeraldWebServicesFactory factory = new HeraldWebServicesFactoryImpl(HERALD_WS_BASE_URI);
    CurriculumService curriculumService = factory.getCurriculumService();
    
    String cardNumber = "213000000";
    Curriculum curriculum = curriculumService.getCurriculum(cardNumber);

### 2.3 JavaDoc
[使用在线版](http://heraldstudio.github.io/herald-web-services-api/docs)

或使用Maven生成JavaDoc

    mvn javadoc:javadoc

### 2.5 Wiki
[点击进入Wiki页面](https://github.com/HeraldStudio/herald-web-services-api/wiki)

3 资源列表
----------
所有可用的资源均可以通过HeraldWebServicesFactory中获取。目前先声网Web服务发布以下资源

* 课程表资源（``` cn.edu.seu.herald.ws.api.CurriculumService ```）
    * 课程表（``` cn.edu.seu.herald.ws.api.Curriculum ```）
    * 时间表（``` cn.edu.seu.herald.ws.api.TimeTable ```）
    * 行程安排（``` cn.edu.seu.herald.ws.api.Schedule ```）
    * 下节课（``` cn.edu.seu.herald.ws.api.Attendance ```）

使用方法详见[wiki](https://github.com/HeraldStudio/herald-web-services-api/wiki)
（目前处于开发初期，敬请期待更多资源…）
