package com.dy.glcx.demo.flowable.boot;

import com.dy.glcx.demo.flowable.boot.bo.StartProcessBO;
import com.dy.glcx.demo.flowable.boot.entity.Person;
import com.dy.glcx.demo.flowable.boot.repository.PersonRepository;
import com.dy.glcx.demo.flowable.boot.service.ProcessService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (C), 2020, 桂林市鼎耀信息科技有限公司
 * @ProjectName: Phantom
 * @FileName: HolidayRequestTest
 * @Author: yannunlin
 * @Date: 2020/6/2 10:51 上午
 * @Description:
 * @email ==>> yanjunlin@hfpay.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class HolidayRequestTest extends MyJunitTest{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProcessService processService;

    @Test
    public void startProcess() {

        Person person = personRepository.findById(1L).get();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("person", person);
        StartProcessBO bo = new StartProcessBO();
        bo.setProcessKey("holidayRequest");
        bo.setParams(variables);
        processService.processDefinitions();
    }



}
