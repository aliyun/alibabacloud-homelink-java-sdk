package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;

import java.util.Map;

/**
 * 设备控制命令格式
 * @author baobao.xq
 * @date 2018/12/17下午4:30
 */
@Data
public class CommandTypeDTO {
    /**
     * 命令类型集合
     */
    public static final String GET_PROPERTIES = "GET_PROPERTIES";
    public static final String INVOKE_SERVICE = "INVOKE_SERVICE";
    public static final String SET_PROPERTIES= "SET_PROPERTIES";


    /**
     * 命令类型
     */
    private String type;

    /**
     * 参数信息
     */
    private Map<String, Object> data;

    /**
     * 标记符
     */
    private String identifier;
}
