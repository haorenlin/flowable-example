package com.dy.glcx.demo.flowable.boot.service.impl;

import com.dy.glcx.demo.flowable.boot.bo.DealProcessBO;
import com.dy.glcx.demo.flowable.boot.bo.StartProcessBO;
import com.dy.glcx.demo.flowable.boot.bo.TaskQueryBO;
import com.dy.glcx.demo.flowable.boot.entity.Person;
import com.dy.glcx.demo.flowable.boot.repository.PersonRepository;
import com.dy.glcx.demo.flowable.boot.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Copyright (C), 2020, 桂林市鼎耀信息科技有限公司
 * @ProjectName: Phantom
 * @FileName: ProcessService
 * @Author: yannunlin
 * @Date: 2020/6/3 2:01 下午
 * @Description:
 * @email ==>> yanjunlin@hfpay.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service("processService")
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Override
    public void startProcess(StartProcessBO bo) {
        if (StringUtils.isNotBlank(bo.getBusinessKey())) {
            runtimeService.startProcessInstanceByKey(bo.getProcessKey(), bo.getBusinessKey(), bo.getParams());
        } else {
            runtimeService.startProcessInstanceByKey(bo.getProcessKey(), bo.getParams());
        }

        log.info("==========> start a process over, processKey is {}, busunessKey is {}",
                bo.getProcessKey(), bo.getBusinessKey());
    }

    @Override
    @Transactional
    public List<Task> getTasks(TaskQueryBO bo) {
        return taskService.createTaskQuery().taskAssignee(bo.getAssignee()).orderByTaskCreateTime().desc().list();
    }

    @Override
    public  List<ProcessDefinition>  processDefinitions() {
        return repositoryService.createProcessDefinitionQuery().list();
    }

    @Override
    public Task getTaskById(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        return task;
    }

    @Override
    public Task getTeskByBusinessKey(String businessKey) {
        return taskService.createTaskQuery()
                .includeProcessVariables()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
    }

    @Override
    public List<Task> getTeskListByBusinessKey(String businessKey) {
        return taskService.createTaskQuery()
                .includeProcessVariables()
                .processInstanceBusinessKey(businessKey)
                .list();
    }

    @Override
    public void dealProcess(DealProcessBO bo) {
        Task task = taskService.createTaskQuery().taskId(bo.getTaskId()).singleResult();
        taskService.complete(bo.getTaskId(), bo.getParams());
        log.info("==========> start a process over, taskId is {}, assignee is {}",
                bo.getTaskId(), task.getAssignee());
    }


    @Override
    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("zhangguorong", "zhang", "guorong", new Date()));
            personRepository.save(new Person("wangzuxian", "wang", "zuxiang", new Date()));
        }
    }

    @Override
    public InputStream processImage(String key)  {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(key)
                .singleResult();

        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream imageStream = repositoryService.getResourceAsStream(
                processDefinition.getDeploymentId(), diagramResourceName);

        return imageStream;
    }

    @Override
    public void genProcessDiagram(HttpServletResponse response, String processId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();

        if (pi == null) {
            log.info("This process is over!");
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();

        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(
                bpmnModel,
                "png",
                activityIds,
                true);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = response.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

}
