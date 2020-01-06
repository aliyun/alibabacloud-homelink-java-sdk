package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;

/**
 * 设备控制执行结果
 * @author baobao.xq
 * @date 2018/12/17下午4:32
 */
@Data
public class CommandResult {

    /**
     * 结果状态码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 本地化错误信息
     */
    private String localizedMsg;

    /**
     * 请求参数
     */
    private CommandTypeDTO param;

    /**
     * 控制返回信息
     */
    private Object result;

}
