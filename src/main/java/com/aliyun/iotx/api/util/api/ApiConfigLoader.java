package com.aliyun.iotx.api.util.api;

import com.alibaba.fastjson.JSON;
import com.aliyun.iotx.api.util.assertion.IoTxAssertions;
import com.aliyun.iotx.api.util.exception.IoTxServiceException;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.aliyun.iotx.api.util.Constant.COMMON_ERROR;
import static java.util.Objects.nonNull;


/**
 * API配置加载器，用于加载并创建服务接口的基础配置信息
 *
 * @date 2019/07/09
 */
@SuppressWarnings("WeakerAccess")
@Slf4j
public final class ApiConfigLoader {

    /**
     * 配置缓存, Key是加载路径
     */
    private final static Map<String, ApiConfigGroup> CONFIG_LOAD_CACHE = new ConcurrentHashMap<>(8);

    /**
     * 配置缓存，Key是API组的名称，见{@link ApiConfigGroup#getName()}
     */
    private final static Map<String, ApiConfigGroup> CONFIG_NAME_CACHE = new ConcurrentHashMap<>(8);

    static {
        load("/apiConfig.yml");
        load("/aepApiConfig.yml");
    }

    /**
     * 加载API配置
     *
     * @param path 加载路径
     * @return API组的配置信息
     */
    @SuppressWarnings("UnusedReturnValue")
    public static ApiConfigGroup load(String path) {
        return CONFIG_LOAD_CACHE.computeIfAbsent(path, s -> {
            ApiConfigGroup apiConfigGroup = load1(s);
            CONFIG_NAME_CACHE.put(apiConfigGroup.getName(), apiConfigGroup);

            return apiConfigGroup;
        });
    }

    /**
     * 取API组的配置
     *
     * @param groupName 组名
     * @return ApiConfigGroup
     */
    public static ApiConfigGroup getApiGroup(String groupName) {
        return CONFIG_NAME_CACHE.computeIfAbsent(groupName, ApiConfigLoader::load1);
    }

    /**
     * 取API配置
     *
     * @param groupName 组名
     * @param apiName   配置名
     * @return ApiConfig
     */
    public static ApiConfig get(String groupName, String apiName) {
        ApiConfig apiConfig = getApiGroup(groupName).getApis().get(apiName);
        if (nonNull(apiConfig)) {
            return apiConfig;
        }
        throw new IoTxServiceException("没有找到API定义. groupName=" + groupName + ", apiName=" + apiName, COMMON_ERROR);
    }

    /**
     * 从路径加载配置
     */
    private static ApiConfigGroup load1(String path) {
        log.trace("api configuration path: {}", path);
        IoTxAssertions.assertNotBlank(path, COMMON_ERROR.params("API配置路径不能为空"));

        InputStream resource = ApiConfigLoader.class.getResourceAsStream(path);
        IoTxAssertions.assertNonNull(resource, COMMON_ERROR.params("配置资源不存在"));

        ApiConfigGroup apiConfigGroup = new Yaml().loadAs(resource, ApiConfigGroup.class);
        //System.out.println(JSON.toJSONString(apiConfigGroup, true));

        return apiConfigGroup;
    }

}
