先声网Web服务API
==============
1 简介
---
先声网Web服务是先声网对外开放的校园资源接口。基于RESTful Web Services发布。

2 如何使用
------
### 2.1 获取源码

    git clone git://github.com/HeraldStudio/herald-web-services-api.git
    cd herald-web-services-api
    mvn install

### 2.2 添加Maven依赖

    <dependency>
        <groupId>cn.edu.seu.herald</groupId>
        <artifactId>herald-web-services-api</artifactId>
        <version>{latest version}</version>
    </dependency>

### 2.3 代码示例

    final String HERALD_WS_BASE_URI = "http://herald.seu.edu.cn/ws";
    HeraldWebServicesFactory factory = new HeraldWebServicesFactoryImpl(HERALD_WS_BASE_URI);
    CurriculumService curriculumService = factory.getCurriculumService();
    
    String cardNumber = "213000000";
    Curriculum curriculum = curriculumService.getCurriculum(cardNumber);

### 2.4 JavaDoc
[使用在线版](http://heraldstudio.github.io/herald-web-services-api/docs)

或使用Maven生成JavaDoc

    mvn javadoc:javadoc


### 2.5 Wiki
[点击进入Wiki页面](https://github.com/HeraldStudio/herald-web-services-api/wiki)

3 资源列表
-------
所有可用的资源均可以通过HeraldWebServicesFactory中获取。目前先声网Web服务发布以下资源

* 课程表资源（``` cn.edu.seu.herald.ws.api.CurriculumService ```）
    * 课程表（``` cn.edu.seu.herald.ws.api.Curriculum ```）
    * 时间表（``` cn.edu.seu.herald.ws.api.TimeTable ```）
    * 行程安排（``` cn.edu.seu.herald.ws.api.Schedule ```）
    * 下节课（``` cn.edu.seu.herald.ws.api.Attendance ```）

（目前处于开发初期，敬请期待更多资源…）

4 资源接口描述
----------
以下为资源接口的RESTful Web Services接口描述。资源调用方式以在该API中提供参考实现。如需要对API扩展，可供参考。

### 4.1 课程表资源
#### 4.1.1 获取课程表
**请求：**

    GET http://herald.seu.edu.cn/ws/curriculum;cardNumber={一卡通号};term={学期}
    Accept: application/xml

参数列表说明

<table width="100%">
    <tr>
        <td><b>参数名称</b></td>
        <td><b>参数类型</b></td>
        <td><b>缺省</b></td>
    </tr>
    <tr>
        <td>cardNumber</td>
        <td>matrix</td>
        <td>N/A</td>
    </tr>
    <tr>
        <td>term</td>
        <td>matrix</td>
        <td>当前学期</td>
    </tr>
</table>

**响应：**

    HTTP/1.1 200 OK
    Content-Type: application/xml;charset=UTF-8
    
    <?xml version="1.0" encoding="UTF-8"?>
    <curriculum xmlns="http://herald.seu.edu.cn/ws/curriculum"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://herlad.seu.edu.cn/ws/curriculum curriculum.xsd"
        cardNumber="213100434"
        term="12-13-3">
        <courses>
            <course>
                <name>IT新技术讲座</name>
                <lecturer></lecturer>
                <credit>0.5</credit>
                <week from="1" to="16"/>
            </course>
            <course>
                <name>Web技术及其应用</name>
                <lecturer>Xiannong Meng</lecturer>
                <credit>2</credit>
                <week from="14" to="17"/>
            </course>
            <course>
                <name>XML技术</name>
                <lecturer>王伟</lecturer>
                <credit>2</credit>
                <week from="1" to="8"/>
            </course>
        </courses>
        <timeTable>
            <schedule day="MON">
                <attendance courseName="XML技术" place="九龙湖教四-303">
                    <period from="1" to="4"/>
                </attendance>
                <attendance courseName="IT新技术讲座" place="九龙湖教八-401">
                    <period from="6" to="7"/>
                </attendance>
            </schedule>
            <schedule day="TUE">
                <attendance courseName="Web技术及其应用" place="九龙湖教四-303" strategy="ODD">
                    <period from="1" to="4"/>
                </attendance>
                <attendance courseName="IT新技术讲座" place="九龙湖教八-401">
                    <period from="6" to="7"/>
                </attendance>
                <attendance courseName="XML技术" place="九龙湖教八-401">
                    <period from="8" to="9"/>
                </attendance>
            </schedule>
        </timeTable>
    </curriculum>

#### 4.1.2 获取时间表
时间表描述了一周的课程安排。

**请求：**

    GET http://herald.seu.edu.cn/ws/curriculum;cardNumber={一卡通号};term={学期}/timeTable
    Accept: application/xml

参数列表说明

<table width="100%">
    <tr>
        <td><b>参数名称</b></td>
        <td><b>参数类型</b></td>
        <td><b>缺省</b></td>
    </tr>
    <tr>
        <td>cardNumber</td>
        <td>matrix</td>
        <td>N/A</td>
    </tr>
    <tr>
        <td>term</td>
        <td>matrix</td>
        <td>当前学期</td>
    </tr>
</table>

**响应：**

    HTTP/1.1 200 OK
    Content-Type: application/xml;charset=UTF-8

    <?xml version="1.0" encoding="UTF-8"?>
    <timeTable xmlns="http://herald.seu.edu.cn/ws/curriculum"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://herlad.seu.edu.cn/ws/curriculum curriculum.xsd">
        <schedule day="MON">
            <attendance courseName="XML技术" place="九龙湖教四-303">
                <period from="1" to="4"/>
            </attendance>
            <attendance courseName="IT新技术讲座" place="九龙湖教八-401">
                <period from="6" to="7"/>
            </attendance>
        </schedule>
        <schedule day="TUE">
            <attendance courseName="Web技术及其应用" place="九龙湖教四-303" strategy="ODD">
                <period from="1" to="4"/>
            </attendance>
            <attendance courseName="IT新技术讲座" place="九龙湖教八-401">
                <period from="6" to="7"/>
            </attendance>
            <attendance courseName="XML技术" place="九龙湖教八-401">
                <period from="8" to="9"/>
            </attendance>
        </schedule>
    </timeTable>

#### 4.1.3 获取某天行程安排
获取某天要上的课

**请求：**

    GET http://herald.seu.edu.cn/ws/curriculum;cardNumber={一卡通号};term={学期}/timeTable/schedule;day={星期}
    Accept: application/xml

参数列表说明

<table width="100%">
    <tr>
        <td><b>参数名称</b></td>
        <td><b>参数类型</b></td>
        <td><b>缺省</b></td>
    </tr>
    <tr>
        <td>cardNumber</td>
        <td>matrix</td>
        <td>N/A</td>
    </tr>
    <tr>
        <td>term</td>
        <td>matrix</td>
        <td>当前学期</td>
    </tr>
    <tr>
        <td>day</td>
        <td>matrix</td>
        <td>今天的星期</td>
    </tr>
</table>

**响应：**

    HTTP/1.1 200 OK
    Content-Type: application/xml;charset=UTF-8

    <?xml version="1.0" encoding="UTF-8"?>
    <schedule xmlns="http://herald.seu.edu.cn/ws/curriculum"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://herlad.seu.edu.cn/ws/curriculum curriculum.xsd"
        day="MON">
        <attendance courseName="XML技术" place="九龙湖教四-303">
            <period from="1" to="4"/>
        </attendance>
        <attendance courseName="IT新技术讲座" place="九龙湖教八-401">
            <period from="6" to="7"/>
        </attendance>
    </schedule>

### 4.2 其他
关于返回的XML的Schema，详见项目下```/src/test/resources/cn/edu/seu/herald/ws/api/curriculum.xsd```或先声网下[curriculum.xsd](http://herald.seu.edu.cn/ws/curriculum.xsd)。
