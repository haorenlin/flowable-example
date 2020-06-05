package com.golddishtech.common;

import lombok.Getter;

/**
 * @Copyright (C), 2018, 上海金皿计算机科技有限公司
 * @ProjectName: Phantom
 * @FileName: ProcessDefinitionEnum
 * @Author: 曹旭楠
 * @Date: 2020-05-09 15:36
 * @Description:
 * @email ==>> caoxunan@midaigroup.com | caoxunan121@163.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Getter
public enum ProcessDefinitionEnum {

    /**
     * 示例流程
     */
    DEMO_PROCESS("demoProcess"),
    ;
    private String processDefinitionId;

    ProcessDefinitionEnum(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }
}
