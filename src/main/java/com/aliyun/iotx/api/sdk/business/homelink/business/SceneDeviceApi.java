package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceTcaDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;

import java.util.HashMap;
import java.util.List;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.*;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 场景与设备相关API
 *
 * @date 2019/07/25
 */
public class SceneDeviceApi {

    /**
     * 获取符合TCA功能的设备列表
     *
     * @param operator 操作者
     * @param pageNo   页码
     * @param pageSize 页数
     * @param flowType 1:查trigger 2:查condition 3:查action
     * @param name     场景名称
     * @return 调用 {@link ApiCommand#executeAndGet()} ()}执行获取设备TCA分页
     */
    public static ApiCommand<PageDTO<DeviceTcaDTO>> querySceneDevices(IdentityDTO operator,
                                                                      int pageNo,
                                                                      int pageSize,
                                                                      int flowType,
                                                                      String name) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "scene_device_tenant_tca_list");

        HashMap<String, Object> params = createParamMap(operator, 8);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("flowType", flowType);
        params.put("name", name);

        return getApiCommand(apiConfig, params, RETURN_TYPE_DEVICE_TCA_PAGE);
    }

    /**
     * 查询符合TCA条件的用户设备
     *
     * @param operator 操作者
     * @param target   设备的归属用户
     * @param flowType 1:查trigger 2:查condition 3:查action
     * @return ApiCommand&lt;{@link List}&lt;{@link DeviceTcaDTO}>>，调用{@link ApiCommand#executeAndGet()} ()}执行
     */
    public static ApiCommand<List<DeviceTcaDTO>> queryUserDevicesOnTca(IdentityDTO operator,
                                                                       IdentityDTO target,
                                                                       Integer flowType) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_user_tca_list");

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("target", target);
        params.put("flowType", flowType);

        return getApiCommand(apiConfig, params, RETURN_TYPE_DEVICE_TCA_LIST);
    }

}
