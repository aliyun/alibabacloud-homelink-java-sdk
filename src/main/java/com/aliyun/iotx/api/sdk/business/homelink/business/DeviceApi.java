/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.*;
import com.aliyun.iotx.api.sdk.business.homelink.dto.product.ProductDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.product.ProductQueryDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;

import java.util.HashMap;
import java.util.List;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.util.assertion.IoTxAssertions.*;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;

/**
 * 设备相关API
 *
 * @author alibaba
 * @date 2019/07/09
 */
@SuppressWarnings("WeakerAccess")
public final class DeviceApi {

    /**
     * 重置设备
     *
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<DeviceResetResultDTO> resetDevice(IdentityDTO operator, String iotId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_reset");

        HashMap<String, Object> params = createParamMap(operator, iotId, 2);

        return getApiCommand(apiConfig, params, RETURN_TYPE_RESET_DEVICE);
    }

    /**
     * 控制设备
     *
     * @param operator 操作者
     * @param targetId 设备ID
     * @param commands 控制命令列表
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<DeviceControlResultDTO> controlDevice(IdentityDTO operator,
                                                                   String targetId,
                                                                   List<CommandTypeDTO> commands) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_control");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("targetId", targetId);
        params.put("commands", commands);

        return getApiCommand(apiConfig, params, RETURN_TYPE_DEVICE_CONTROL);
    }

    /**
     * 更新设备的备注名
     *
     * @param operator 操作者
     * @param iotId    设备ID
     * @param nickName 备注名
     * @return 调用 {@link ApiCommand#execute()} 执行获取场景ID
     */
    public static ApiCommand<Void> updateDeviceNickName(IdentityDTO operator,
                                                        String iotId,
                                                        String nickName) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_nickname_update");

        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("nickName", nickName);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 查询设备详情
     *
     * @param operator 操作者
     * @param iotIds   设备ID列表
     * @return ApiCommand，使用executeAndGet()方法执行
     */
    public static ApiCommand<List<SpaceDeviceDTO>> getDevice(IdentityDTO operator,
                                                       List<String> iotIds) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_detail_get");

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("iotIds", iotIds);

        return getApiCommand(apiConfig, params, RETURN_TYPE_SPACE_DEVICE);
    }

    /**
     * 获取设备TSL
     *
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<JSONObject> getDeviceTsl(IdentityDTO operator, String iotId) {

        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_tsl_get");

        HashMap<String, Object> params = createParamMap(operator, iotId, 2);

        return getApiCommand(apiConfig, params, RETURN_TYPE_GET_DEVICE_TSL);
    }

    /**
     * 查询设备
     * <p>
     * 查询租户接入的所有设备，以分页列表方式返回。
     * <p>
     * 查询的范围是所有完成了配网接入的、与用户产生关联的设备。
     *
     * @param operator 操作者
     * @param pageNo   页码（min=1）
     * @param pageSize 页大小（min=1, max=100)
     * @return ApiCommand，使用executeAndGet()方法执行
     */
    public static ApiCommand<PageDTO<DeviceDTO>> queryDevices(IdentityDTO operator,
                                                              int pageNo,
                                                              int pageSize) {

        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_list");

        PageSearchDTO page = new PageSearchDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        HashMap<String, Object> params = createParamMap(operator, 2);
        params.put("pageInfo", page);

        return getApiCommand(apiConfig, params, RETURN_TYPE_DEVICE_PAGE);
    }

    /**
     * 查询子设备网关
     *
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<DeviceBaseDTO> queryGatewayOfSubDevice(IdentityDTO operator, String iotId) {
        assertNonNull(operator);
        assertNotBlank(iotId);

        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_gateway");

        HashMap<String, Object> params = createParamMap(operator, iotId, 2);

        return getApiCommand(apiConfig, params, RETURN_TYPE_DEVICE_GATEWAY);
    }

    /**
     * 分页查询网关的子设备列表
     *
     * @param operator 操作者
     * @param iotId    设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<PageDTO<DeviceBaseDTO>> queryGatewaySubDevices(IdentityDTO operator,
                                                                            String iotId,
                                                                            int pageNo,
                                                                            int pageSize) {
        assertNonNull(operator);
        assertNotBlank(iotId);
        assertTrue(0 < pageNo);
        assertTrue(0 < pageSize);

        PageSearchDTO query = new PageSearchDTO();
        query.setPageNo(pageNo);
        query.setPageSize(pageSize);

        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_sub_list");

        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("pageQuery", query);

        return getApiCommand(apiConfig, params, RETURN_TYPE_GATEWAY_SUB_DEVICE);
    }
}
