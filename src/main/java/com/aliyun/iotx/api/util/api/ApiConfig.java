package com.aliyun.iotx.api.util.api;

import lombok.Data;
import lombok.ToString;


/**
 * API配置
 *
 * @author weishi.cc
 * @date 2019/4/11
 */
@Data
@ToString(exclude = {"module"})
public class ApiConfig {

    /**
     * api名称
     */
    private String name;

    /**
     * api url
     */
    private String url;

    /**
     * api版本
     */
    private String version;

    /**
     * api所属模块
     */
    private String module;
}
