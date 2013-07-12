先声网Web服务API
==============
1 简介
------
先声网Web服务是先声网对外开放的校园资源接口。基于Web Services发布。

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
        <version>1.0.ALPHA3</version>
    </dependency>

### 2.2 代码示例

    // Web服务地址
    final String HERALD_WS_BASE_URI = "http://herald.seu.edu.cn/ws";
    // 构造Web服务工厂
    HeraldWebServicesFactory factory = new HeraldWebServicesFactoryImpl(HERALD_WS_BASE_URI);
    // 获取某个特定的Web服务，如下是获取课程表服务的示例
    CurriculumService curriculumService = factory.getCurriculumService();
    
    // 调用课程表服务下的方法，如下是根据一卡通获得本学期课表的示例
    String cardNumber = "213000000";
    Curriculum curriculum = curriculumService.getCurriculum(cardNumber);

### 2.3 JavaDoc
[使用在线版](http://heraldstudio.github.io/herald-web-services-api/docs)

或使用Maven生成JavaDoc

    mvn javadoc:javadoc

### 2.5 Wiki
[点击进入Wiki页面](https://github.com/HeraldStudio/herald-web-services-api/wiki)

3 服务列表
----------
所有可用的资源均可以通过HeraldWebServicesFactory中获取。目前先声网Web服务发布以下资源

* 安卓客户端更新服务（``` cn.edu.seu.herald.ws.api.AndroidClientUpdateService ```）
* 校园咨询服务（``` cn.edu.seu.herald.ws.api.AaoInfoService ```）
* 教室服务（``` cn.edu.seu.herald.ws.api.ClassroomService ```）
* 课程表服务（``` cn.edu.seu.herald.ws.api.CurriculumService ```）
* 绩点服务（```cn.edu.seu.herald.ws.api.GradePointService```）
* 图书馆服务（```cn.edu.seu.herald.ws.api.LibraryService```）
* 跑操服务（```cn.edu.seu.herald.ws.api.MorningExerciseService```）

使用方法详见[wiki](https://github.com/HeraldStudio/herald-web-services-api/wiki)
