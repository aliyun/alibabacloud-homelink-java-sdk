package com.aliyun.iotx.api.util.context;

import com.aliyun.iotx.api.util.assertion.IoTxAssertions;
import com.aliyun.iotx.api.util.command.ApiEnvironment;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

import static com.aliyun.iotx.api.util.Constant.MESSAGE_ERROR;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.API_TAG_PRE;


/**
 * @date 2019/07/09
 */
public final class ApiContext {

    /**
     * 默认运行环境
     */
    @Getter
    private static ApiEnvironment defaultEnv;

    /**
     * MAP
     */
    private static Map<String, ApiEnvironment> envMap = Maps.newHashMap();

    /**
     * 默认请求超时
     */
    private static final Long DEFAULT_REQUEST_TIMEOUT = 3000L;

    /**
     * 创建一个运行环境
     *
     * @param name           环境名称
     * @param host           主机
     * @param appKey         appKey
     * @param appSecret      appSecret
     * @param requestTimeout 默认请求超时
     * @param isDefault      是否设置为默认环境（最后一次设置的生效）
     */
    public static void setEnv(String name, String host, String appKey, String appSecret, long requestTimeout,
                              boolean isDefault) {
        IoTxAssertions.assertNotBlank(name, MESSAGE_ERROR.params("name不能为空"));
        IoTxAssertions.assertNotBlank(host, MESSAGE_ERROR.params("host不能为空"));
        IoTxAssertions.assertNotBlank(appKey, MESSAGE_ERROR.params("appSecret不能为空"));
        IoTxAssertions.assertNotBlank(appSecret, MESSAGE_ERROR.params("appSecret不能为空"));

        if (requestTimeout < 0) {
            requestTimeout = DEFAULT_REQUEST_TIMEOUT;
        }

        ApiEnvironment build = ApiEnvironment.builder()
            .name(name)
            .appKey(appKey)
            .appSecret(appSecret)
            .host(host)
            .requestTimeout(requestTimeout)
            .build();
        envMap.put(build.getName(), build);
        if (isDefault) {
            defaultEnv = build;
        }
    }

    public static void setEnv(ApiEnvironment env, boolean isDefault) {
        setEnv(env.getName(), env.getHost(), env.getAppKey(), env.getAppSecret(), env.getRequestTimeout(), isDefault);
    }

    /**
     * 设备为预发环境
     *
     * @param isPre true：预发环境, false：生产环境
     */
    public static void setPre(boolean isPre) {
        if (isPre) {
            System.setProperty(API_TAG_PRE, "PRE");
        } else {
            System.getProperties().remove(API_TAG_PRE);
        }
    }

    /**
     * 取运行环境
     *
     * @param name 环境名称，见{@link ApiEnvironment#getName()}
     * @return 服务运行环境
     */
    public static ApiEnvironment get(String name) {
        ApiEnvironment apiEnvironment = envMap.get(name);
        IoTxAssertions.assertNonNull(apiEnvironment, MESSAGE_ERROR.params("没有这个环境的配置信息 name:" + name));
        return apiEnvironment;
    }

}
