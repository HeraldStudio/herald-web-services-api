先声网Web服务API
==============
简介
---
先声网Web服务是先声网对外开放的校园资源接口。基于RESTful Web Services发布。

接口使用
-------
代码示例：

    final String HERALD_WS_BASE_URI = "http://herald.seu.edu.cn/ws";
    HeraldWebServicesFactory factory = new HeraldWebServicesFactoryImpl(HERALD_WS_BASE_URI);
    CurriculumService curriculumService = factory.getCurriculumService();
    
    String cardNumber = "213000000";
    Curriculum curriculum = curriculumService.getCurriculum(cardNumber);

资源列表
-------
所有可用的资源均可以通过HeraldWebServicesFactory中获取。目前先声网Web服务发布以下资源

* 课程表资源

（目前处于开发初期，敬请期待更多资源…）

资源接口描述
----------
以下为资源接口的RESTful Web Services接口描述。资源调用方式以在该API中提供参考实现。如需要对API扩展，可供参考。

### 课程表资源
#### 获取课程表
#### 请求

    GET http://herald.seu.edu.cn/ws/curriculum?cardNumber={一卡通号}&term={学期}
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
        <td>query</td>
        <td>N/A</td>
    </tr>
    <tr>
        <td>term</td>
        <td>query</td>
        <td>当前学期</td>
    </tr>
</table>

#### 响应

    200 OK HTTP/1.1
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

关于返回的XML的Schema，详见项目下```/src/test/resources/cn/edu/seu/herald/ws/api/curriculum.xsd```或先声网下[curriculum.xsd](http://herald.seu.edu.cn/ws/curriculum.xsd)。
