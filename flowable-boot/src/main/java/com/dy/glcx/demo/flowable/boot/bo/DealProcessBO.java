package com.dy.glcx.demo.flowable.boot.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Copyright (C), 2020, 桂林市鼎耀信息科技有限公司
 * @ProjectName: Phantom
 * @FileName: StartProcessBO
 * @Author: yannunlin
 * @Date: 2020/6/3 2:08 下午
 * @Description:
 * @email ==>> yanjunlin@hfpay.com
 * @History: <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Data
public class DealProcessBO implements Serializable {

    private static final long serialVersionUID = 6658536421558795629L;

    /**
     * 流程ID
     */
    private String taskId;

    /**
     * 参数
     */
    private Map<String, Object> params;
}
