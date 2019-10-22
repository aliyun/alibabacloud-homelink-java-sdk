package com.aliyun.iotx.api.util.api;

import lombok.Data;

import java.util.Map;


/**
 * API配置组
 *
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/07/09
 */
@SuppressWarnings("WeakerAccess")
@Data
public class ApiConfigGroup {

    /**
     * API组的配置信息
     */
    private Map<String, ApiConfig> apis;

    /**
     * API组的名称，可以使用这个名称从{@link ApiConfigLoader}中快速获取配置信息
     */
    private String name;
}
