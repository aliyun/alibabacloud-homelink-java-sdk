package com.aliyun.iotx.api.util.command;

import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.iotx.api.client.IoTApiClientBuilderParams;
import com.aliyun.iotx.api.client.IoTApiRequest;
import com.aliyun.iotx.api.client.SyncApiClient;
import com.aliyun.iotx.api.sdk.business.homelink.DefaultAssertFunc;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.assertion.IoTxAssertions;
import com.aliyun.iotx.api.util.context.ApiContext;
import com.aliyun.iotx.api.util.exception.IoTxServiceException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import static com.aliyun.iotx.api.util.Constant.API_REQUEST_ERROR;
import static com.aliyun.iotx.api.util.Constant.MESSAGE_ERROR;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


/**
 * {@link ApiCommand}的帮助类
 *
 * @date 2018/11/12
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Slf4j
public class ApiCommandHelper {

    public static final String API_TAG_PRE = "X-ALIYUN-API-GW-ENV";

    /**
     * 正常访问返回码
     */
    static final int HTTP_STATUS_CODE_OK = 200;

    /**
     * 网关访问错误码
     */
    private static final int SERVICE_ERROR_CODE = 400;

    /**
     * 映射表
     */
    private static ConcurrentMap<ApiEnvironment, SyncApiClient> clientMap = Maps.newConcurrentMap();

    private static final int HEADER_MAP_INIT_SIZE = 4;

    /**
     * 根据环境参数构造api-gateway的客户端{@link SyncApiClient}
     *
     * @param apiEnv 运行环境
     * @return SyncApiClient
     */
    public static SyncApiClient getSyncApiClient(ApiEnvironment apiEnv) {
        IoTxAssertions.assertNonNull(apiEnv, MESSAGE_ERROR.params("没有可用的运行环境"));
        final SyncApiClient apiExecutor = clientMap.computeIfAbsent(apiEnv, k -> {
            IoTApiClientBuilderParams b = new IoTApiClientBuilderParams();
            b.setAppKey(apiEnv.getAppKey());
            b.setAppSecret(apiEnv.getAppSecret());
            b.setReadTimeout(apiEnv.getRequestTimeout());
            return new SyncApiClient(b);
        });
        Preconditions.checkNotNull(apiExecutor);
        return apiExecutor;
    }

    /**
     * 执行命令
     *
     * @param api API
     * @return 调用结果
     */
    public static <T> JSONObject execute(ApiCommand<T> api) {
        try {
            IoTApiRequest request = new IoTApiRequest();
            request.setApiVer(api.getApiVer());

            for (Map.Entry<String, Object> entry : api.getParamSupplier().get().entrySet()) {
                request.putParam(entry.getKey(), entry.getValue());
            }
            ApiEnvironment apiEnv = api.getApiEnv();
            if (isNull(apiEnv)) {
                apiEnv = ApiContext.getDefaultEnv();
            }

            // 添加headers，指明是测试环境还是预发环境
            Map<String, String> headers = new HashMap<>(HEADER_MAP_INIT_SIZE);
            if (System.getProperties().containsKey(API_TAG_PRE)) {
                headers.put("X-CA-STAGE", "PRE");
            }

            ApiResponse response = getSyncApiClient(apiEnv)
                .postBody(apiEnv.getHost(), api.getUrl(), request, api.getHttps(), headers);

            if (log.isDebugEnabled()) {
                log.debug("apiName={}, request={}, response={}, host={}", api.getUrl(),
                    JSON.toJSONString(request, false),
                    JSON.toJSONString(response, false),
                    apiEnv.getHost());
            }

            if (response.getCode() == SERVICE_ERROR_CODE) {
                throw new IoTxServiceException(API_REQUEST_ERROR);
            }

            //noinspection CharsetObjectCanBeUsed
            return JSON.parseObject(new String(response.getBody(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("call api error. url={}", api.getUrl());
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建API调用服务
     *
     * @param <T>           返回数据类型
     * @param apiConfig     配置信息
     * @param params        参数
     * @param typeReference 返回类型typeReference
     * @return ApiCommand
     */
    public static <T> ApiCommand<T> getApiCommand(ApiConfig apiConfig,
                                                  HashMap<String, Object> params,
                                                  TypeReference<T> typeReference) {
        return new ApiCommand<T>(apiConfig)
            .paramSupplier(() -> params)
            .assertFunc(DefaultAssertFunc::assertIt)
            .convertFunc(json -> {
                if (log.isDebugEnabled()) {
                    log.debug("result:{}", nonNull(json) ? json.toJSONString() : "[EMPTY BODY]");
                }
                //System.out.println(JSON.toJSONString(json, true));
                return nonNull(json)
                    ? json.toJavaObject(typeReference)
                    : null;
            });
    }

}
