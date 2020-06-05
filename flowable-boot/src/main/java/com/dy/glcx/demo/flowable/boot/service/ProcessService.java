package com.dy.glcx.demo.flowable.boot.service;

import com.dy.glcx.demo.flowable.boot.bo.DealProcessBO;
import com.dy.glcx.demo.flowable.boot.bo.StartProcessBO;
import com.dy.glcx.demo.flowable.boot.bo.TaskQueryBO;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.flowable.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
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
public interface ProcessService {

    /**
     * 功能描述: <br>
     * 开启流程
     *
     * @param: [bo]
     *
     * @return: void
     * @throws:
     * @Version: 1.0.0
     * @Author: yanjunlin
     * @Date: 10:39 上午 2020/6/4
     */
    void startProcess(StartProcessBO bo);

    /**
     * 功能描述: <br>
     * 获取已经开启的任务
     *
     * @param: [bo]
     *
     * @return: void
     * @throws:
     * @Version: 1.0.0
     * @Author: yanjunlin
     * @Date: 10:39 上午 2020/6/4
     */
    List<Task> getTasks(TaskQueryBO bo);

    /**
     * 功能描述: <br>
     * 已经部署的流程模板
     *
     * @param: [bo]
     *
     * @return: void
     * @throws:
     * @Version: 1.0.0
     * @Author: yanjunlin
     * @Date: 10:39 上午 2020/6/4
     */
    List<ProcessDefinition>  processDefinitions();

    /**
     * 功能描述: <br>
     * 更具ID获取任务点
     *
     * @param: [bo]
     *
     * @return: void
     * @throws:
     * @Version: 1.0.0
     * @Author: yanjunlin
     * @Date: 10:39 上午 2020/6/4
     */
    Task getTaskById(String taskId);

    /**
     * 功能描述: <br>
     * 更具业务ID获取最会一个激活的任务点
     *
     * @param: [bo]
     *
     * @return: void
     * @throws:
     * @Version: 1.0.0
     * @Author: yanjunlin
     * @Date: 10:39 上午 2020/6/4
     */
    Task getTeskByBusinessKey(String businessKey);

    /**
     * 功能描述: <br>
     * 更具业务ID获取任务点
     *
     * @param: [bo]
     *
     * @return: void
     * @throws:
     * @Version: 1.0.0
     * @Author: yanjunlin
     * @Date: 10:39 上午 2020/6/4
     */
    List<Task> getTeskListByBusinessKey(String businessKey);

    /**
     * 功能描述: <br>
     * 处理流程
     *
     * @param: [bo]
     *
     * @return: void
     * @throws:
     * @Version: 1.0.0
     * @Author: yanjunlin
     * @Date: 10:39 上午 2020/6/4
     */
    void dealProcess(DealProcessBO bo);

    void createDemoUsers();

    InputStream processImage(String key);

    void genProcessDiagram(HttpServletResponse response, String processId) throws Exception;

}
