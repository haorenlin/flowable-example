package com.golddishtech;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.*;
import org.flowable.task.api.Task;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class FlowableGuideApplicationTests {

    @Autowired
    public RuntimeService runtimeService;
    @Autowired
    public TaskService taskService;
    @Autowired
    public RepositoryService repositoryService;
    @Autowired
    public HistoryService historyService;
    @Autowired
    public ManagementService managementService;

    /**
     * 断言并且返回任务
     * @param message       断言信息
     * @param expected      期待值
     * @param businessKey   业务主键
     * @return  返回任务id
     */
    public String assertAndGetTask(String message, String expected, String businessKey) {

        Task task = getLastRecentlyTask(businessKey);

        log.info("log TaskId: {}", task.getId());
        Assert.assertEquals(message, expected, task.getTaskDefinitionKey());
        return task.getId();
    }

    /**
     * 查询最近的一个任务
     *
     * @param businessKey 业务主键
     * @return 任务Task
     */
    public Task getLastRecentlyTask(String businessKey) {
        return this.taskService.createTaskQuery()
                .includeProcessVariables()
                .processInstanceBusinessKey(businessKey)
                // singleResult()只会返回当前流程实例中最后一个激活的任务节点，list()返回当前流程实例中所有激活的任务节点
                .singleResult();
    }

    public List<Task> assertAndGetTasks(String message, String[] expected, String businessKey) {

        List<Task> lastRecentlyTasks = getLastRecentlyTasks(businessKey);

        lastRecentlyTasks.forEach((task) -> {

            log.info("log TaskId: {}", task.getId());
        });

        List<String> taskDefinitionKeys = lastRecentlyTasks.stream().map(Task::getTaskDefinitionKey).collect(Collectors.toList());

        Assert.assertArrayEquals(message, expected, taskDefinitionKeys.toArray(new String[]{}));

        return lastRecentlyTasks;
    }

    public List<Task> getLastRecentlyTasks(String businessKey) {
        return this.taskService.createTaskQuery()
                .includeProcessVariables()
                .processInstanceBusinessKey(businessKey)
                .list();
    }

}
