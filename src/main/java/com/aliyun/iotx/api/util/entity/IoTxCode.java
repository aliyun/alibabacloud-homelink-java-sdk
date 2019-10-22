/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.util.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * IoTx code struct
 *
 * @author zhuangyao.zy
 * @date 2017/3/17
 */
@Data
public class IoTxCode implements Serializable {

    private final int code;

    private final String message;

    /**
     * 新增本地化信息
     */
    private final String localizedMsg;

    public IoTxCode(int code, String message) {
        this.code = code;
        this.message = message;
        this.localizedMsg = null;
    }

    public IoTxCode(int code, String message, String localizedMsg) {
        this.code = code;
        this.message = message;
        this.localizedMsg = localizedMsg;
    }

}