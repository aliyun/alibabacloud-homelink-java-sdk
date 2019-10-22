/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants;
import com.aliyun.iotx.api.sdk.business.homelink.DefaultAssertFunc;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceBaseV1DTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceBindSpaceResultDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceInfo;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.VirtualDeviceQuery;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * @author alibaba
 * @date 2019/07/25
 */
public class SpaceDeviceApi {

    /**
     * 绑定设备与空间
     *
     * @param operator    操作者
     * @param rootSpaceId 一级（顶层）空间ID
     * @param spaceId     需要做绑定的空间ID
     * @param iotIdList   设备IDList
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取绑定执行结果
     */
    @SuppressWarnings("Duplicates")
    public static ApiCommand<DeviceBindSpaceResultDTO> bindDeviceSpace(IdentityDTO operator,
                                                                       String rootSpaceId,
                                                                       String spaceId,
                                                                       List<String> iotIdList) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_space_bind");
        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("rootSpaceId", rootSpaceId);
        params.put("spaceId", spaceId);
        params.put("iotIdList", iotIdList);

        return new ApiCommand<DeviceBindSpaceResultDTO>(apiConfig)
            .paramSupplier(() -> params)
            .assertFunc(DefaultAssertFunc::assertIt)
            .convertFunc(json -> {
                JSONObject jo = (JSONObject)json;
                return parseDeviceBindSpaceResult(jo);
            });
    }

    /**
     * 解绑设备与空间
     *
     * @param operator    操作者
     * @param rootSpaceId 一级（顶层）空间ID
     * @param spaceId     空间ID
     * @param iotIdList   设备IDList
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取绑定执行结果
     */
    @SuppressWarnings("Duplicates")
    public static ApiCommand<DeviceBindSpaceResultDTO> unbindDeviceSpace(IdentityDTO operator,
                                                                         String rootSpaceId,
                                                                         String spaceId,
                                                                         List<String> iotIdList) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_space_unbind");
        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("rootSpaceId", rootSpaceId);
        params.put("spaceId", spaceId);
        params.put("iotIdList", iotIdList);

        return new ApiCommand<DeviceBindSpaceResultDTO>(apiConfig)
            .paramSupplier(() -> params)
            .assertFunc(DefaultAssertFunc::assertIt)
            .convertFunc(json -> {
                JSONObject jo = (JSONObject)json;
                return parseDeviceBindSpaceResult(jo);
            });
    }

    /**
     * 获取设备所在空间
     *
     * @param operator    操作者
     * @param rootSpaceId 一级（顶层）空间ID
     * @param iotId       设备ID
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取空间信息
     */
    public static ApiCommand<SpaceInfo> getDeviceSpace(IdentityDTO operator,
                                                       String rootSpaceId,
                                                       String iotId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_space_get");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("rootSpaceId", rootSpaceId);
        params.put("iotId", iotId);

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_SPACE_INFO);
    }

    /**
     * 查询空间的设备
     *
     * @param operator        操作者
     * @param rootSpaceId     一级（顶层）空间ID
     * @param spaceId         空间ID
     * @param includeSubSpace True 查询子空间 FALSE 不查询子空间
     * @param pageNo          pageNo
     * @param pageSize        pageSize
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取设备列表
     */
    public static ApiCommand<PageDTO<DeviceDTO>> querySpaceDevices(IdentityDTO operator,
                                                                   String rootSpaceId,
                                                                   String spaceId,
                                                                   boolean includeSubSpace,
                                                                   int pageNo,
                                                                   int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_device_list");

        PageSearchDTO page = new PageSearchDTO();
        page.setPageSize(pageSize);
        page.setPageNo(pageNo);

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("rootSpaceId", rootSpaceId);
        params.put("spaceId", spaceId);
        params.put("includeSubSpace", includeSubSpace);
        params.put("pageInfo", page);

        return getApiCommand(apiConfig, params, RETURN_TYPE_DEVICE_PAGE);
    }

    /**
     * 绑定虚拟设备和空间
     *
     * @param operator       操作者
     * @param spaceId        空间ID
     * @param deviceInfoList 设备信息列表
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取绑定执行结果
     */
    public static ApiCommand<List<DeviceDTO>> bindSpaceVirtualDevice(IdentityDTO operator,
                                                                     String spaceId,
                                                                     List<DeviceDTO> deviceInfoList) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_virtualdevice_bind");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("spaceId", spaceId);
        params.put("deviceInfoList", deviceInfoList);

        return getApiCommand(apiConfig, params, RETURN_TYPE_DEVICE_LIST);
    }

    /**
     * 删除空间的虚拟设备
     *
     * @param operator  操作者
     * @param spaceId   空间ID
     * @param iotIdList 设备IDList
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> deleteSpaceVirtualDevice(IdentityDTO operator, String spaceId,
                                                            List<String> iotIdList) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_virtualdevice_delete");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("spaceId", spaceId);
        params.put("iotIdList", iotIdList);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    /**
     * 查询空间虚拟设备
     *
     * @param operator 操作者
     * @param spaceId  空间ID
     * @param query    查询条件
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<PageDTO<DeviceBaseV1DTO>> querySpaceVirtualDevices(IdentityDTO operator,
                                                                                String spaceId,
                                                                                VirtualDeviceQuery query) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_virtualdevice_list");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("spaceId", spaceId);
        params.put("condition", query);

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_VIRTUAL_DEVICE_INFO_PAGE);
    }

    /**
     * 替换空间虚拟设备
     *
     * @param operator     操作者
     * @param iotId        设备ID
     * @param virtualIotId 虚拟设备
     * @param spaceId      空间ID
     * @return 调用 {@link ApiCommand#execute()} 执行
     */
    public static ApiCommand<Void> replaceSpaceVirtualDevice(IdentityDTO operator,
                                                             String iotId,
                                                             String virtualIotId,
                                                             String spaceId) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "space_virtualdevice_replace");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("iotId", iotId);
        params.put("virtualIotId", virtualIotId);
        params.put("spaceId", spaceId);

        return getApiCommand(apiConfig, params, RETURN_TYPE_VOID);
    }

    private static DeviceBindSpaceResultDTO parseDeviceBindSpaceResult(JSONObject jo) {
        DeviceBindSpaceResultDTO result = new DeviceBindSpaceResultDTO();
        Integer successCount = jo.getInteger("successCount");
        result.setSuccessCount(successCount);

        JSONArray batchResultMapList = jo.getJSONArray("batchResultMap");
        batchResultMapList.forEach(brm -> {
            Map<String, Object> map = ((JSONObject)brm).getInnerMap();

        });
        return result;
    }
}
