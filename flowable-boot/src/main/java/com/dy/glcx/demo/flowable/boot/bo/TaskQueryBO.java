package com.dy.glcx.demo.flowable.boot.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Copyright (C), 2020, 桂林市鼎耀信息科技有限公司
 * @ProjectName: Phantom
 * @FileName: TaskQueryBO
 * @Author: yannunlin
 * @Date: 2020/6/3 2:16 下午
 * @Description:
 * @email ==>> yanjunlin@hfpay.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Data
public class TaskQueryBO implements Serializable {

    private static final long serialVersionUID = 7248620857834506128L;

    /**
     * 代理人
     */
    private String assignee;
}
