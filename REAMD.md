# 参考文档

https://tkjohn.github.io/flowable-userguide


# Flowable

## 简介

Flowable是一个使用Java编写的轻量级业务流程引擎。

Flowable是Activiti原班主创人员从Activiti分离出来的一套工作流引擎，是一个业务流程管理(BPM)和工作流系统，适用于开发人员和系统管理员。其核心是超快速、稳定的BPMN2流程引擎，易于与 Spring集成使用。
Activiti7是 Salaboy团队开发的。进行维护activiti6以及activiti5代码。目前的activiti5以及activiti6代码还是原Tijs 

Rademakers原有团队开发的。Salaboy团队目前在开发activiti7框架。对于activiti6以及activiti5的代码官方已经宣称暂停维护了。activiti7就是噱头 内核使用的还是activiti6。并没有为引擎注入更多的新特性，只是在activiti之外的上层封装了一些应用。



## 所需软件

1、JDK 1.8+ 
2、IDE/Eclipse 
      图形化设计插件 actiBPM 、JBoss jBPM。（JBoss jBPM已经停止维护，其在新版本的Intellij下安装容易出问题）
      对于UI效果追求较高的同学可以试试其他bpmn设计工具：[flowable_ui](https://github.com/ArtIsLong/flowable-ui)、[visual-paradigm](https://www.visual-paradigm.com/features/bpmn-diagram-and-tools/)、[bpmn-js](https://github.com/bpmn-io/bpmn-js)（demo: https://demo.bpmn.io/）



## 数据表名说明

Flowable的所有数据库表都以ACT_开头。第二部分是说明表用途的两字符标示符。服务API的命名也大略符合这个规则。

ACT_RE_*: 'RE’代表repository。带有这个前缀的表包含“静态”信息，例如流程定义与流程资源（图片、规则等）。

ACT_RU_*: 'RU’代表runtime。这些表存储运行时信息，例如流程实例（process instance）、用户任务（user task）、变量（variable）、作业（job）等。Flowable只在流程实例运行中保存运行时数据，并在流程实例结束时删除记录。这样保证运行时表小和快。

ACT_HI_*: 'HI’代表history。这些表存储历史数据，例如已完成的流程实例、变量、任务等。

>详情可参考博客：https://blog.csdn.net/huangjun0210/article/details/84951615



## 使用一个工作流的大致步骤

创建流程引擎 --->  部署流程定义  --->  启动流程示例   ---> 查询与完成任务 ---> 结束



## Flowable Spring Boot stater的列表：

| Starter                                                      | 描述                                                         |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [flowable-spring-boot-starter-cmmn](https://github.com/flowable/flowable-engine/tree/master/modules/flowable-spring-boot/flowable-spring-boot-starters/flowable-spring-boot-starter-cmmn/pom.xml) | 提供以独立运行模式启动CMMN引擎的依赖                         |
| [flowable-spring-boot-starter-cmmn-rest](https://github.com/flowable/flowable-engine/tree/master/modules/flowable-spring-boot/flowable-spring-boot-starters/flowable-spring-boot-starter-cmmn-rest/pom.xml) | 提供以独立运行模式启动CMMN引擎，并提供其REST API的依赖。     |
| [flowable-spring-boot-starter-dmn](https://github.com/flowable/flowable-engine/tree/master/modules/flowable-spring-boot/flowable-spring-boot-starters/flowable-spring-boot-starter-dmn/pom.xml) | 提供以独立运行模式启动DMN引擎的依赖。                        |
| [flowable-spring-boot-starter-dmn-rest](https://github.com/flowable/flowable-engine/tree/master/modules/flowable-spring-boot/flowable-spring-boot-starters/flowable-spring-boot-starter-dmn-rest/pom.xml) | 提供以独立运行模式启动DMN引擎，并提供其REST API的依赖。      |
| [flowable-spring-boot-starter-process](https://github.com/flowable/flowable-engine/tree/master/modules/flowable-spring-boot/flowable-spring-boot-starters/flowable-spring-boot-starter-process/pom.xml) | 提供以独立运行模式启动流程引擎的依赖。                       |
| [flowable-spring-boot-starter-process-rest](https://github.com/flowable/flowable-engine/tree/master/modules/flowable-spring-boot/flowable-spring-boot-starters/flowable-spring-boot-starter-process-rest/pom.xml) | 提供以独立运行模式启动流程引擎，并提供其REST API的依赖。     |
| [flowable-spring-boot-starter](https://github.com/flowable/flowable-engine/tree/master/modules/flowable-spring-boot/flowable-spring-boot-starters/flowable-spring-boot-starter/pom.xml) | 提供启动所有Flowable引擎（流程，CMMN，DMN，Form，Content及IDM）的依赖。 |
| [flowable-spring-boot-starter-rest](https://github.com/flowable/flowable-engine/tree/master/modules/flowable-spring-boot/flowable-spring-boot-starters/flowable-spring-boot-starter-rest/pom.xml) | 提供启动所有Flowable引擎，并提供其REST API的依赖。           |
| [flowable-spring-boot-starter-actuator](https://github.com/flowable/flowable-engine/tree/master/modules/flowable-spring-boot/flowable-spring-boot-starters/flowable-spring-boot-starter-actuator/pom.xml) | 提供Spring Boot Actuator所需的依赖。                         |

## Flowable Spring Boot支持的配置参数列表：


```properties
# ===================================================================
# Common Flowable Spring Boot Properties
# 通用Flowable Spring Boot参数         
# 本示例文件只作为指导。请不要直接拷贝至你自己的应用中。
# ===================================================================

# Core (Process) FlowableProperties
# 核心（流程）
flowable.check-process-definitions=true # 是否需要自动部署流程定义。
flowable.custom-mybatis-mappers= # 需要添加至引擎的自定义Mybatis映射的FQN。
flowable.custom-mybatis-x-m-l-mappers= # 需要添加至引擎的自定义Mybatis XML映射的路径。
flowable.database-schema= # 如果数据库返回的元数据不正确，可以在这里设置schema用于检测/生成表。
flowable.database-schema-update=true # 数据库schema更新策略。
flowable.db-history-used=true # 是否要使用db历史。
flowable.deployment-name=SpringBootAutoDeployment # 自动部署的名称。
flowable.history-level= # 要使用的历史级别。
flowable.process-definition-location-prefix=classpath*:/processes/ # 自动部署时查找流程的目录。
flowable.process-definition-location-suffixes=**.bpmn20.xml,**.bpmn # 'processDefinitionLocationPrefix'路径下需要部署的文件的后缀（扩展名）。
```

>更加详细的配置参加[官方文档（5.7.8）](https://tkjohn.github.io/flowable-userguide/#springSpringBoot)：



# DEMO 说明



- flowable-javase		  基于java构建的命令行程序

- flowable-boot			 基于SpringBoot构建的简单demo

- flowable-complex      白嫖来的稍微复杂的流程示例

  

## flowable-boot 一个简单的操作示例

#### 开启一个流程：

``` http
curl --location --request POST 'http://localhost:9001/process/start_process' \
--header 'Content-Type: application/json' \
--data-raw '{
	"processKey": "oneTaskProcess",
    "businessKey": "123456",
	"params": {
		"money": "800",
		"taskUser": "1"
	}
		
}'
```

#### 查询报销申请人的任务列表

``` http
curl --location --request POST 'http://localhost:9001/process/tasks' \
--header 'Content-Type: application/json' \
--data-raw '{
	"assignee": "1"
}'
```

- 返回结果展示：

``` json
[
    {
        "assigneeUpdatedCount": 1,
        "assignee": "1",
        "name": "出差报销",
        "priority": 50,
        "createTime": "Jun 5, 2020 10:27:15 AM",
        "suspensionState": 1,
        "isIdentityLinksInitialized": false,
        "taskIdentityLinkEntities": [],
        "executionId": "11e7c1c2-a6d4-11ea-b68b-76d55d87e7da",
        "processInstanceId": "11e74c8f-a6d4-11ea-b68b-76d55d87e7da",
        "processDefinitionId": "submitExpenseAccount:1:e210e66d-a6d3-11ea-b68b-76d55d87e7da",
        "taskDefinitionKey": "fillTask",
        "isCanceled": false,
        "isCountEnabled": true,
        "variableCount": 0,
        "identityLinkCount": 0,
        "subTaskCount": 0,
        "tenantId": "",
        "forcedUpdate": false,
        "usedVariablesCache": {},
        "id": "11eaa7f6-a6d4-11ea-b68b-76d55d87e7da",
        "revision": 1,
        "isInserted": false,
        "isUpdated": false,
        "isDeleted": false,
        "originalPersistentState": {
            "processInstanceId": "11e74c8f-a6d4-11ea-b68b-76d55d87e7da",
            "processDefinitionId": "submitExpenseAccount:1:e210e66d-a6d3-11ea-b68b-76d55d87e7da",
            "suspensionState": 1,
            "priority": 50,
            "executionId": "11e7c1c2-a6d4-11ea-b68b-76d55d87e7da",
            "taskDefinitionKey": "fillTask",
            "subTaskCount": 0,
            "createTime": "Jun 5, 2020 10:27:15 AM",
            "name": "出差报销",
            "isCountEnabled": true,
            "variableCount": 0,
            "assignee": "1",
            "identityLinkCount": 0
        }
    }
]
```

- 使用processInstanceId 获取当前的流程状态图：

![开启一个流程](https://img-blog.csdnimg.cn/20200605095813650.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hhb3JlbmxpbjU5NDU=,size_16,color_FFFFFF,t_70#pic_center)



#### 申请人执行流程 （使用上述'id'）

``` http
curl --location --request POST 'http://localhost:9001/process/deal_process' \
--header 'Content-Type: application/json' \
--data-raw '{
	"taskId": "11eaa7f6-a6d4-11ea-b68b-76d55d87e7da"
		
}'
```

- 当前的流程状态图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200605103926514.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hhb3JlbmxpbjU5NDU=,size_16,color_FFFFFF,t_70#pic_center)

### 老板拒绝

- 获取老板的任务列表，得到该任务的id

``` http
curl --location --request POST 'http://localhost:9001/process/tasks' \
--header 'Content-Type: application/json' \
--data-raw '{
	"assignee": "老板"
}'
```

- 执行驳回

``` http
curl --location --request POST 'http://localhost:9001/process/deal_process' \
--header 'Content-Type: application/json' \
--data-raw '{
	"taskId": "70ab186d-a6d4-11ea-b68b-76d55d87e7da",
	"params": {
		"outcome": "驳回"
	}	
}'
```

- 当前的流程状态图：

![开启一个流程](https://img-blog.csdnimg.cn/20200605095813650.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hhb3JlbmxpbjU5NDU=,size_16,color_FFFFFF,t_70#pic_center)

老板驳回后，流程又回到报销申请人

### 申请人再次提交，并修改金额

``` http
curl --location --request POST 'http://localhost:9001/process/deal_process' \
--header 'Content-Type: application/json' \
--data-raw '{
	"taskId": "9f85ce92-a6d6-11ea-b68b-76d55d87e7da",
	"params": {
		"money": "488"
	}	
}'
```

- 当前的流程状态图：
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200605105332236.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hhb3JlbmxpbjU5NDU=,size_16,color_FFFFFF,t_70#pic_center)

#### 经理审批通过

``` http
curl --location --request POST 'http://localhost:9001/process/deal_process' \
--header 'Content-Type: application/json' \
--data-raw '{
	"taskId": "9c239b58-a6d7-11ea-b68b-76d55d87e7da",
	"params": {
		"outcome": "通过"
	}	
}'
```

- 流程结束

#### 说明

- 任务处理人标记的两种方式
  使用 'flowable:assignee' 指定

``` xml
<userTask id="fillTask" name="出差报销" flowable:assignee="${taskUser}">
```

使用 'class' 指定

``` xml
<userTask id="directorTak" name="经理审批">
      <extensionElements>
        <flowable:taskListener event="create" class="com.dy.glcx.demo.flowable.boot.flowable.listener.ManagerTaskHandler" />
      </extensionElements>
</userTask>
```

``` java
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
 
public class ManagerTaskHandler implements TaskListener {
 
    @Override
    public void notify(DelegateTask delegateTask) {
        // TODO 可做其他处理
        delegateTask.setAssignee("经理");
    }
 
}
```



#### 备注

- 使用demo前最好先从官方文档了解部分基础概念
- 本示例demo比较简单，如需流程较长的示例可参考 flowable-complex 模块
- 未完待续 ...