package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;

import java.util.HashMap;
import java.util.List;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 全屋边缘网关 API
 * <p>
 * 文档： https://homelink.iot.aliyun.com/doc#sor02x.html
 *
 * @date 2019/10/21
 */
@SuppressWarnings({"Duplicates", "unused"})
public class EdgeGatewayApi {

    /**
     * 查询空间的边缘网关
     *
     * @param operator 操作者
     * @param spaceId  空间ID
     * @param pageNo   页号
     * @param pageSize 页大小
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<PageDTO<String>> querySpaceGateway(IdentityDTO operator,
                                                                String spaceId,
                                                                int pageNo,
                                                                int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_edge_gateway_query");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("spaceId", spaceId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING_PAGE);
    }

    /**
     * 查询人的边缘网关
     *
     * @param operator 操作者
     * @param target   target 目标用户
     * @param pageNo   页号
     * @param pageSize 页大小
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<PageDTO<String>> queryUserGateway(IdentityDTO operator,
                                                               IdentityDTO target,
                                                               int pageNo,
                                                               int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_edge_gateway_query");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("target", target);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING_PAGE);
    }

    /**
     * 查询租户的边缘网关
     *
     * @param operator 操作者
     * @param pageNo   页号
     * @param pageSize 页大小
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<PageDTO<String>> queryGateway(IdentityDTO operator,
                                                           int pageNo,
                                                           int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_gateway_query");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING_PAGE);
    }

    /**
     * 推送配置到边缘网关设备
     *
     * @param operator  操作者
     * @param edgeIotId 设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> pushConfig(IdentityDTO operator, String edgeIotId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_config_push");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("edgeIotId", edgeIotId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 清空边缘网关上的配置
     *
     * @param operator  操作者
     * @param edgeIotId 设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> clearConfig(IdentityDTO operator, String edgeIotId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_config_clear");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("edgeIotId", edgeIotId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 移除边缘网关上的配置
     *
     * @param operator  操作者
     * @param edgeIotId 设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> deleteConfig(IdentityDTO operator, String edgeIotId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_config_delete");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("edgeIotId", edgeIotId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 向边缘网关配置中添加设备
     *
     * @param operator  操作者
     * @param iotIds    子设备列表
     * @param edgeIotId 设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> addDeviceToGateway(IdentityDTO operator,
                                                      List<String> iotIds,
                                                      String edgeIotId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_device_add");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("iotIds", iotIds);
        params.put("edgeIotId", edgeIotId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 从边缘网关配置中删除设备
     *
     * @param operator  操作者
     * @param iotIds    子设备列表
     * @param edgeIotId 设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> deleteDeviceFromGateway(IdentityDTO operator,
                                                           List<String> iotIds,
                                                           String edgeIotId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_device_delete");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("iotIds", iotIds);
        params.put("edgeIotId", edgeIotId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 查询已经加入到边缘网关配置下的子设备列表
     *
     * @param operator  操作者
     * @param edgeIotId 设备ID
     * @param pageNo    页号
     * @param pageSize  页大小
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<PageDTO<String>> queryDeviceInGateway(IdentityDTO operator,
                                                                   String edgeIotId,
                                                                   int pageNo,
                                                                   int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_device_query");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("edgeIotId", edgeIotId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING_PAGE);
    }

    /**
     * 查询边缘网关已连接但未加入到配置下的子设备列表
     *
     * @param operator  操作者
     * @param edgeIotId 设备ID
     * @param pageNo    页号
     * @param pageSize  页大小
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<PageDTO<String>> queryUnConfiguredDeviceInGateway(IdentityDTO operator,
                                                                               String edgeIotId,
                                                                               int pageNo,
                                                                               int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_unconfigured_device_query");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("edgeIotId", edgeIotId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING_PAGE);
    }

    /**
     * 向边缘网关配置中添加场景
     *
     * @param operator  操作者
     * @param edgeIotId 设备ID
     * @param sceneId   场景ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> addSceneToGateway(IdentityDTO operator,
                                                     String edgeIotId,
                                                     String sceneId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_scene_add");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("edgeIotId", edgeIotId);
        params.put("sceneId", sceneId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 从边缘网关配置中删除场景
     *
     * @param operator  操作者
     * @param edgeIotId 设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<Void> deleteSceneFromGateway(IdentityDTO operator,
                                                          String edgeIotId,
                                                          String sceneId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_scene_delete");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("edgeIotId", edgeIotId);
        params.put("sceneId", sceneId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 查询边缘网关配置中的场景
     *
     * @param operator  操作者
     * @param edgeIotId 设备ID
     * @param pageNo    页号
     * @param pageSize  页大小
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<PageDTO<String>> querySceneInGateway(IdentityDTO operator, String edgeIotId,
                                                                  int pageNo,
                                                                  int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "edge_scene_query");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("edgeIotId", edgeIotId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);

        return getApiCommand(apiConfig, params, RETURN_TYPE_STRING_PAGE);
    }

}
