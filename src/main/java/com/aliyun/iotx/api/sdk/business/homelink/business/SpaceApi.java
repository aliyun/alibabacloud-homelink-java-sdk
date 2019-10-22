package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceInfo;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceQueryDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceUpdateDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.assertion.IoTxAssertions;
import com.aliyun.iotx.api.util.command.ApiCommand;
import com.aliyun.iotx.api.util.entity.IoTxResult;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 空间相关API
 *
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/07/25
 */
@Slf4j
public class SpaceApi {

    /**
     * 创建顶层空间
     *
     * @param operator  操作人
     * @param spaceInfo 空间信息
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取新创建的空间ID
     */
    public static ApiCommand<String> createRootSpace(IdentityDTO operator, SpaceInfo spaceInfo) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_root_create");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("spaceInfo", spaceInfo);

        return new ApiCommand<String>(apiConfig)
            .paramSupplier(() -> params)
            .assertFunc((code, msg, localizedMsg, data) -> {
                IoTxResult<Object> res = new IoTxResult<>(code, msg, localizedMsg);
                IoTxAssertions.assertIoTxResult(res);
            }).convertFunc(json -> {
                JSONObject jo = (JSONObject)json;
                return jo.getString("value");
            });
    }

    /**
     * 创建子空间
     *
     * @param operator  操作人
     * @param spaceInfo 空间信息
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取新创建的空间ID
     */
    public static ApiCommand<String> createSubSpace(IdentityDTO operator, SpaceInfo spaceInfo) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_sub_create");

        return new ApiCommand<String>(apiConfig)
            .paramSupplier(() -> {
                HashMap<String, Object> params = createParamMap(operator, 8);
                params.put("spaceInfo", spaceInfo);
                return params;
            })
            .assertFunc((code, msg, localizedMsg, data) -> {
                IoTxResult<Object> res = new IoTxResult<>(code, msg, localizedMsg);
                IoTxAssertions.assertIoTxResult(res);
            }).convertFunc(json -> {
                JSONObject jo = (JSONObject)json;
                return jo.getString("value");
            });
    }

    /**
     * 删除空间
     *
     * @param operator 操作人
     * @param spaceId  空间ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Integer> deleteSpace(IdentityDTO operator, String spaceId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_delete");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("spaceId", spaceId);

        return new ApiCommand<Integer>(apiConfig)
            .paramSupplier(() -> params)
            .assertFunc((code, msg, localizedMsg, data) -> {
                IoTxResult<Object> res = new IoTxResult<>(code, msg, localizedMsg);
                IoTxAssertions.assertIoTxResult(res);
            }).convertFunc(json -> {
                JSONObject jo = (JSONObject)json;
                String value = jo.getString("value");
                log.info("删除空间: " + value);
                return Integer.parseInt(value);
            });
    }

    /**
     * 更新空间
     *
     * @param operator  操作人
     * @param spaceInfo 空间信息
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> updateSpace(IdentityDTO operator, SpaceUpdateDTO spaceInfo) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_update");

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("spaceInfo", spaceInfo);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 获取空间信息
     *
     * @param operator    操作人
     * @param spaceIdList 被查询的空间ID列表
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取空间信息列表
     */
    public static ApiCommand<List<SpaceInfo>> getSpace(IdentityDTO operator, List<String> spaceIdList) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_get");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("spaceIdList", spaceIdList);
        params.put("rootSpaceId", null);

        return getApiCommand(apiConfig, params, RETURN_TYPE_SPACE_INFO_LIST);
    }

    /**
     * 获取子空间信息
     *
     * @param operator   操作人
     * @param spaceQuery 子空间查询条件
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取空间信息分页数据
     */
    public static ApiCommand<PageDTO<SpaceInfo>> getSubSpace(IdentityDTO operator, SpaceQueryDTO spaceQuery) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_sub_list");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("spaceQuery", spaceQuery);

        return getApiCommand(apiConfig, params, RETURN_TYPE_SPACE_INFO_PAGE);
    }

    /**
     * 获取租户跟空间列表
     *
     * @param operator   操作人
     * @param spaceQuery 子空间查询条件
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取空间信息分页数据
     */
    public static ApiCommand<PageDTO<SpaceInfo>> getRootSpace(IdentityDTO operator, SpaceQueryDTO spaceQuery) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_root_list");

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("spaceQuery", spaceQuery);

        return getApiCommand(apiConfig, params, RETURN_TYPE_SPACE_INFO_PAGE);
    }

}
