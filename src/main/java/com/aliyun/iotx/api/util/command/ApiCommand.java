/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.util.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.iotx.api.client.SyncApiClient;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.exception.IoTxServiceException;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;


/**
 * API的命令定义
 * <p>
 * 定义了API的必要调用参数，采用流式风格API配置。
 * <p>
 * 封装{@link SyncApiClient}简化api-gateway的调用。
 * <p>
 * {@link SyncApiClient}的用法参考：<a href="https://github.com/aliyun/iotx-api-gateway-client">阿里云物联网一站式开发平台服务客户端以及调用实例</a>
 *
 * @author alibaba
 * @date 2018/11/12
 */
@Slf4j
public class ApiCommand<T> {

    /**
     * API
     */
    @Getter
    private final String url;

    /**
     * 运行的角色
     */
    private ApiAccount as;

    /**
     * 运行环境
     */
    private ApiEnvironment apiEnv;

    /**
     * API版本
     */
    @Getter
    private String apiVer = "1.0.0";

    /**
     * 参数提供方法
     */
    @Getter
    private Supplier<Map<String, Object>> paramSupplier;

    /**
     * 是否使用HTTPS
     */
    @Getter
    private Boolean https = Boolean.TRUE;

    /**
     * 验证方法
     */
    private ApiAssertFunc assertFunc;

    /**
     * 结果的转换方法
     */
    private Function<JSON, T> convertFunc;

    /**
     * 构造
     *
     * @param url API的URL（路径部分，不包括协议、主机和端口，以/开头）
     */
    public ApiCommand(String url) {
        this.url = url;
    }

    /**
     * 通过配置构造
     *
     * @param apiConfig API配置
     */
    public ApiCommand(ApiConfig apiConfig) {
        this.url = apiConfig.getUrl();
        apiVer(apiConfig.getVersion());
    }

    /**
     * 执行并返回原始的JSONObject
     * <p>
     * 不调用{@link #convertFunc}
     *
     * @return 结果
     */
    public JSONObject execute() {
        assertParam(url, paramSupplier);

        final JSONObject jsonObject = ApiCommandHelper.execute(this);

        if (nonNull(assertFunc)) {
            //noinspection unchecked
            assertFunc.assertIt(
                jsonObject.getInteger("code"),
                jsonObject.getString("message"),
                jsonObject.getString("localizedMsg"),
                null
            );
        }
        return jsonObject;
    }

    /**
     * 执行并在成功(code=200)时，调用 {@link #convertFunc}转换结果
     *
     * @return T 转换的结果
     */
    public T executeAndGet() {
        assertParam(url, paramSupplier);

        final JSONObject jsonObject = ApiCommandHelper.execute(this);
        Preconditions.checkNotNull(jsonObject, "接口没有返回任何数据，如果接口配置为透传，可能会丢失网关返回的信息。");

        final Integer code = jsonObject.getInteger("code");
        if (ApiCommandHelper.HTTP_STATUS_CODE_OK == code) {
            JSON data = getData(jsonObject);
            if (nonNull(assertFunc)) {
                //noinspection unchecked
                assertFunc.assertIt(
                    jsonObject.getInteger("code"),
                    jsonObject.getString("message"),
                    jsonObject.getString("localizedMsg"),
                    null
                );
            }
            return convertFunc.apply(data);
        } else {
            log.debug("response error, ignore result convert: {}", JSON.toJSONString(jsonObject, false));
            if (nonNull(assertFunc)) {
                //noinspection unchecked
                assertFunc.assertIt(
                    jsonObject.getInteger("code"),
                    jsonObject.getString("message"),
                    jsonObject.getString("localizedMsg"),
                    null
                );
            }
            return null;
        }
    }

    /**
     * 设置账号信息
     *
     * @param as 阿里云账号
     */
    /*public ApiCommand<T> as(AliYunAccount as) {
        this.as = as;
        return this;
    }*/

    /**
     * 设置参数的{@link Supplier}
     */
    public ApiCommand<T> paramSupplier(Supplier<Map<String, Object>> paramSupplier) {
        this.paramSupplier = paramSupplier;
        return this;
    }

    /**
     * 设置结果的校验方法
     */
    public ApiCommand<T> assertFunc(ApiAssertFunc<T> assertFunc) {
        this.assertFunc = assertFunc;
        return this;
    }

    /**
     * 设置结果的转换方法，在code=0时调用
     */
    public ApiCommand<T> convertFunc(Function<JSON, T> convertFunc) {
        this.convertFunc = convertFunc;
        return this;
    }

    /**
     * API的运行环境，将在{@link ApiCommand}中取出来构造
     */
    public ApiCommand<T> apiEnv(ApiEnvironment apiEnv) {
        this.apiEnv = apiEnv;
        return this;
    }

    /**
     * 设置是否HTTPS，true:https, false: http
     */
    public ApiCommand<T> https(Boolean https) {
        this.https = https;
        return this;
    }

    /**
     * 设置API版本，默认1.0.0
     */
    public ApiCommand<T> apiVer(String apiVer) {
        this.apiVer = apiVer;
        return this;
    }

    /**
     * 返回运行环境配置，当没有设置也没有默认环境配置时，抛出NPE
     * <p>
     * 使用{@link ApiCommand#apiEnv}设置运行环境配置
     * <p>
     * 或
     * <p>
     * 使用{@link ApiCommandHelper (ApiEnvironment)}设置默认的环境配置信息
     *
     * @return API的运行环境配置
     */
    ApiEnvironment getApiEnv() {
        /*if (isNull(apiEnv)) {
            apiEnv = ApiHelper.getDefaultApiEnvironment();
        }
        Preconditions.checkNotNull(apiEnv, "未配置API运行环境，使用ApiHelper.setDefaultApiEnvironment()或ApiCommand.apiEnv()设置");
        */
        return apiEnv;
    }

    /**
     * 返回账号信息，当没有设置也没有默认账号信息时，抛出NPE。
     * <p>
     * 使用{@link ApiCommand#apiEnv}设置账号信息
     * <p>
     * 或
     * <p>
     * 使用{@link ApiCommandHelper (AliYunAccount)}设置默认账号信息
     *
     * @return 账号
     */
    ApiAccount getAliYunAccount() {
        /*if (isNull(as)) {
            as = ApiHelper.getDefaultAliYunAccount();
        }
        Preconditions.checkNotNull(as, "未配置默认账号，使用ApiHelper.setDefaultAliYunAccount()或ApiCommand.apiEnv()设置");
        */
        return as;
    }

    private static void assertParam(String url, Supplier<Map<String, Object>> paramSupplier) {
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(paramSupplier);
    }

    private JSON getData(JSONObject jsonObject) {
        final String key = "data";
        try {
            return jsonObject.getJSONObject(key);
        } catch (Exception ignored) {}
        try {
            return jsonObject.getJSONArray(key);
        } catch (Exception ignored) {}

        String string = jsonObject.getString(key);
        JSONObject object = new JSONObject();
        object.put("value", string);
        return object;
    }

    /**
     * API接口调用结果的校验方法定义，在{@link ApiCommand}中调用
     *
     * @author alibaba
     */
    @FunctionalInterface
    public interface ApiAssertFunc<T> {

        /**
         * 验证方法
         *
         * @param data 数据
         */
        default void assertIt(T data) {
            // TODO 需要删除
            assertIt(200, "", "", data);
        }

        /**
         * 验证方法
         *
         * @param code         返回码
         * @param msg          消息
         * @param localizedMsg 本地化消息
         * @param data         数据
         */
        void assertIt(Integer code, String msg, String localizedMsg, T data);
    }

}
