package com.dy.glcx.demo.flowable.boot;

import com.dy.glcx.demo.flowable.boot.service.ProcessService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Copyright (C), 2020, 桂林市鼎耀信息科技有限公司
 * @ProjectName: Phantom
 * @FileName: Application
 * @Author: yannunlin
 * @Date: 2020/6/3 1:15 上午
 * @Description:
 * @email ==>> yanjunlin@hfpay.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = { "com.dy"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner init(final RepositoryService repositoryService,
//                                  final RuntimeService runtimeService,
//                                  final TaskService taskService) {
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                try {
////                    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
////                            .deploymentId("oneTaskProcess")
////                            .singleResult();
//                    ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
//                    System.out.println("Number of process definitions : " + processDefinitionQuery.count());
//                    System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//                    processDefinitionQuery.list().forEach(it -> {
//                        System.out.println(it.getName()+ "["+it.getId()+"] task start******");
//                        runtimeService.startProcessInstanceByKey(it.getKey());
//                    });
//                    System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//    }

    @Bean
    public CommandLineRunner init(final ProcessService processService, final RepositoryService repositoryService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                processService.createDemoUsers();
            }
        };
    }
}

