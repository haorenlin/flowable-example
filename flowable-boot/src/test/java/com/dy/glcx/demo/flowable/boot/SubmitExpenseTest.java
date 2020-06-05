package com.dy.glcx.demo.flowable.boot;

import com.dy.glcx.demo.flowable.boot.bo.DealProcessBO;
import com.dy.glcx.demo.flowable.boot.bo.StartProcessBO;
import com.dy.glcx.demo.flowable.boot.bo.TaskQueryBO;
import com.dy.glcx.demo.flowable.boot.repository.PersonRepository;
import com.dy.glcx.demo.flowable.boot.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * @Copyright (C), 2020, 桂林市鼎耀信息科技有限公司
 * @ProjectName: Phantom
 * @FileName: SubmitExpenseTest
 * @Author: yannunlin
 * @Date: 2020/6/4 11:09 上午
 * @Description:
 * @email ==>> yanjunlin@hfpay.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@Slf4j
public class SubmitExpenseTest extends MyJunitTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProcessService processService;

    @Autowired
    private TaskService taskService;

    /** 查询已经部署的流程模版*/
    @Test
    public void processDefinitions() {
        processService.processDefinitions().stream().forEach(System.out::println);
    }

    /** 开启一个流程引擎*/
    @Test
    public void startProcess() {
        StartProcessBO bo = new StartProcessBO();
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskUser", 1);
        map.put("money", 800);
        bo.setProcessKey("submitExpenseAccount");
        /*
         * 功能描述: <br>
         * 要指定业务标识businessKey 方便利用业务标示查询任务
         * 不定业务标识businessKey 也可根据任务办理人查询相应任务
         */
        bo.setBusinessKey("123456");
        bo.setParams(map);
        processService.startProcess(bo);
    }

    /** 获取用户名下的流程*/
    @Test
    public void taskList() {
        TaskQueryBO bo = new TaskQueryBO();
        bo.setAssignee("经理");
        List<Task> tasks = processService.getTasks(bo);
        if (tasks.isEmpty()) {
            log.info("no task record");
            return;
        }
        tasks.forEach( it -> {
            System.out.println(gson.toJson(it));
        });

    }

    @Test
    public void getLastRecentlyTask() {
        String businessKey = "123456";
//        System.out.println(processService.getTeskByBusinessKey(businessKey));
        System.out.println(processService.getTeskListByBusinessKey(businessKey));
    }

    /**
     * 处理流程
     */
    @Test
    public void dealProcessStart() {
        HashMap<String, Object> map = new HashMap<>();
        DealProcessBO bo = new DealProcessBO();
        bo.setTaskId("11e74c8f-a6d4-11ea-b68b-76d55d87e7da");
        processService.dealProcess(bo);
    }

    /**
     * 处理流程
     */
    @Test
    public void dealProcess() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "通过");
        DealProcessBO bo = new DealProcessBO();
        bo.setTaskId("70b56572-a697-11ea-b6b8-76d55d87e7da");
        bo.setParams(map);
        processService.dealProcess(bo);
    }

    @Test
    public void dealProcessRefuse() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "驳回");
        DealProcessBO bo = new DealProcessBO();
        bo.setTaskId("4feaf096-a69b-11ea-ac69-76d55d87e7da");
        bo.setParams(map);
        processService.dealProcess(bo);
    }

}
