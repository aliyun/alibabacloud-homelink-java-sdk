package com.aliyun.iotx.api.util.command;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


/**
 * API的运行环境配置（网关连接参数），应用在{@link ApiCommandHelper}和{@link ApiCommand#getApiEnv()}提供API的环境参数
 *
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2018/11/13
 */
@Data
@ToString
@Builder
public class ApiEnvironment {

    /**
     * API运行环境名，用于运行时快速找到配置信息
     */
    private final String name;

    /**
     * 主机，不带协议，不带根路径
     * <p>
     * 例："api-performance.aliplus.com"
     */
    private final String host;

    /**
     * 阿里云的appKey
     */
    private final String appKey;

    /**
     * 阿里云的appSecret
     */
    private final String appSecret;

    /**
     * 请求超时
     */
    private final Long requestTimeout;

    /**
     * debug模式
     */
    private final boolean debug;
}
