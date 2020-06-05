package com.golddishtech;

import com.golddishtech.common.ProcessDefinitionEnum;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Copyright (C), 2018, 上海金皿计算机科技有限公司
 * @ProjectName: Phantom
 * @FileName: DemoProcessTest
 * @Author: 曹旭楠
 * @Date: 2020-05-09 15:32
 * @Description:
 * @email ==>> caoxunan@midaigroup.com | caoxunan121@163.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Slf4j
public class DemoProcessTest extends FlowableGuideApplicationTests {

    @Test
    public void testDemoProcess() {

        final String processDefinitionKey = ProcessDefinitionEnum.DEMO_PROCESS.getProcessDefinitionId();

        // 生成一个业务主键
        String businessKey = UUID.randomUUID().toString();

        // 开启一个流程实例
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);

        String processInstanceId = processInstance.getId();

        log.info("查看流程实例ID, processInstanceId : " + processInstanceId);

        log.info("当前节点 TaskOne 同意 至 TaskTwo、TaskThree");
        this.taskService.complete(assertAndGetTask("1当前节点名称是TaskOne", "ONE", businessKey));


        log.info("当前节点 TaskTwo、TaskThree 同意 至 TaskFour");
        List<Task> tasks = assertAndGetTasks("1当前节点名称是TaskTwo 或者 TaskThree", new String[]{"TWO", "THREE"}, businessKey);

        tasks.forEach(task -> {

            log.info(String.format("%s 审批通过；", task.getTaskDefinitionKey()));
            this.taskService.complete(task.getId());
        });


        log.info("当前节点 TaskFour 同意 至 TaskFive");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskFour", "FOUR", businessKey), new HashMap<String, Object>() {{
            put("flow", "five");
        }});


        log.info("当前节点 TaskFive 同意至 TaskEight");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskFive", "FIVE", businessKey));


        log.info("当前节点 TaskEight 同意至 TaskNine、TaskTen");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskEight", "EIGHT", businessKey), new HashMap<String, Object>() {{
            put("flow", "yes");
        }});


        log.info("当前节点 TaskNine、TaskTen 同意至 TaskEleven");
        List<Task> tasks2 = assertAndGetTasks("1当前节点名称是 TaskNine、TaskTen", new String[]{"NINE", "TEN"}, businessKey);

        tasks2.forEach(task -> {

            log.info(String.format("%s 审批通过；", task.getTaskDefinitionKey()));
            this.taskService.complete(task.getId());
        });


        log.info("当前节点 TaskEleven 同意至 TaskTwelve");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskEleven", "ELEVEN", businessKey));


        log.info("当前节点 TaskTwelve 退回至 TaskEight");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskTwelve", "TWELVE", businessKey), new HashMap<String, Object>() {{
            put("flow", "eight");
        }});


        log.info("当前节点 TaskEight 直接同意至 TaskEleven");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskEight", "EIGHT", businessKey), new HashMap<String, Object>() {{
            put("flow", "no");
        }});


        log.info("当前节点 TaskEleven 同意至 TaskTwelve");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskEleven", "ELEVEN", businessKey));


        log.info("当前节点 TaskTwelve 退回至 TaskEleven");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskTwelve", "TWELVE", businessKey), new HashMap<String, Object>() {{
            put("flow", "eleven");
        }});


        log.info("当前节点 TaskEleven 同意至 TaskTwelve");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskEleven", "ELEVEN", businessKey));


        log.info("当前节点 TaskTwelve 同意 流程结束");
        this.taskService.complete(assertAndGetTask("3当前节点名称是TaskTwelve", "TWELVE", businessKey));
    }
}
