package com.dy.glcx.demo.flowable.boot.controller;

import com.dy.glcx.demo.flowable.boot.bo.DealProcessBO;
import com.dy.glcx.demo.flowable.boot.bo.StartProcessBO;
import com.dy.glcx.demo.flowable.boot.bo.TaskQueryBO;
import com.dy.glcx.demo.flowable.boot.service.ProcessService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @Copyright (C), 2020, 桂林市鼎耀信息科技有限公司
 * @ProjectName: Phantom
 * @FileName: ProcessController
 * @Author: yannunlin
 * @Date: 2020/6/3 2:04 下午
 * @Description:
 * @email ==>> yanjunlin@hfpay.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RestController
@RequestMapping("process")
public class ProcessController  {

    @Autowired
    private ProcessService processService;

    /**
     * 功能描述: <br>
     * 获取已有的流程定义
     *
     */
    @GetMapping("processDefinitions")
    public List<ProcessDefinition> processDefinitions() {
        return processService.processDefinitions();
    }

    /**
     * 功能描述: <br>
     * 开启一个流程
     *
     */
    @PostMapping("start_process")
    public String startProcess(@RequestBody StartProcessBO bo) {
        processService.startProcess(bo);
        return "SUCCESS";
    }

    /**
     * 功能描述: <br>
     * 执行流程
     *
     */
    @PostMapping("deal_process")
    public String dealProcess(@RequestBody DealProcessBO bo) {
        processService.dealProcess(bo);
        return "SUCCESS";
    }

    /**
     * 功能描述: <br>
     * 获取受理人任务
     *
     */
    @PostMapping("tasks")
    public List getTasks(@RequestBody TaskQueryBO bo) {
        return processService.getTasks(bo);
    }

    /**
     * 功能描述: <br>
     * 根据流程ID获取流程图
     *
     */
    @RequestMapping(value = "/processDiagram/{processId}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public void getImage(@PathVariable("processId") String processId, HttpServletResponse response) throws Exception {
        processService.genProcessDiagram(response, processId);
    }

    @RequestMapping(value = "/image/{key}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getImage(@PathVariable("key") String key) throws IOException {
        return ImageIO.read(processService.processImage(key));
    }



}
