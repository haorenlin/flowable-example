package com.dy.glcx.demo.flowable.boot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Copyright (C), 2020, 桂林市鼎耀信息科技有限公司
 * @ProjectName: Phantom
 * @FileName: MyJunitTest
 * @Author: yannunlin
 * @Date: 2020/6/4 11:48 上午
 * @Description:
 * @email ==>> yanjunlin@hfpay.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MyJunitTest {

    public  static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }


    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}
