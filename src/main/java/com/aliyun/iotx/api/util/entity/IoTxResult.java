/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.util.entity;

import lombok.Data;

import static com.aliyun.iotx.api.util.Constant.SUCCESS;
import static com.aliyun.iotx.api.util.Constant.SUCCESS_CODE;


/**
 * @author alibaba
 * @date 2017/3/17
 */
@Data
public class IoTxResult<DataType> extends IoTxCode {

    private static final String EMPTY_MESSAGE = "";

    private DataType data;

    /**
     * default result code is ${success}
     */
    public IoTxResult() {
        this(SUCCESS.getCode(), SUCCESS.getMessage(), SUCCESS.getLocalizedMsg(), null);
    }

    public IoTxResult(IoTxCode ioTxCode) {
        this(ioTxCode.getCode(), ioTxCode.getMessage(), ioTxCode.getLocalizedMsg(), null);
    }

    public IoTxResult(IoTxCode ioTxCode, String message, String localizedMsg) {
        this(ioTxCode.getCode(), message, localizedMsg, null);
    }

    public IoTxResult(IoTxCode iotxCode, DataType data) {
        this(iotxCode.getCode(), iotxCode.getMessage(), iotxCode.getLocalizedMsg(), data);
    }

    public IoTxResult(int code, String message, DataType data) {
        this(code, message, EMPTY_MESSAGE, data);
    }

    public IoTxResult(int code, DataType data) {
        this(code, EMPTY_MESSAGE, EMPTY_MESSAGE, data);
    }

    public IoTxResult(DataType data) {
        this(SUCCESS, data);
    }

    public IoTxResult(int code) {
        this(code, EMPTY_MESSAGE, EMPTY_MESSAGE, null);
    }

    public IoTxResult(int code, String message) {
        this(code, message, EMPTY_MESSAGE, null);
    }

    public IoTxResult(int code, String message, String localizedMsg) {
        this(code, message, localizedMsg, null);
    }

    public IoTxResult(int code, String message, String localizedMsg, DataType data) {
        super(code, message, localizedMsg);
        this.data = data;
    }

    /**
     * 返回结果是否成功，code==200
     */
    public boolean hasSucceeded() {
        return SUCCESS_CODE == getCode();
    }
}