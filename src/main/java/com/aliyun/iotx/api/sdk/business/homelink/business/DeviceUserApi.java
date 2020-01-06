package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.SingleIotIdDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.UserResultDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.user.DeviceUserRoleEnum;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityRoleDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;
import com.aliyun.iotx.api.util.api.ApiConfig;
import com.aliyun.iotx.api.util.api.ApiConfigLoader;
import com.aliyun.iotx.api.util.command.ApiCommand;

import java.util.HashMap;
import java.util.List;

import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.G_HOME_LINK;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiResultTypeConstants.RETURN_TYPE_SINGLE_IOT_ID;
import static com.aliyun.iotx.api.sdk.business.homelink.ApiUtil.createParamMap;
import static com.aliyun.iotx.api.util.command.ApiCommandHelper.getApiCommand;


/**
 * 设备与用户相关API
 *
 * @date 2019/07/25
 */
public class DeviceUserApi {

    /**
     * 绑定公区设备
     *
     * @param operator   操作员
     * @param iotId      设备ID
     * @param productKey 设备PK
     * @param deviceName 设备DN
     * @param target     绑定目标用户
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行
     */
    public static ApiCommand<SingleIotIdDTO> bindLpDevice(IdentityDTO operator,
                                                          String iotId,
                                                          String productKey,
                                                          String deviceName,
                                                          IdentityDTO target) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "lp_device_bind");

        HashMap<String, Object> params = createParamMap(operator, iotId, 8);
        params.put("productKey", productKey);
        params.put("deviceName", deviceName);
        params.put("target", target);

        return getApiCommand(apiConfig, params, RETURN_TYPE_SINGLE_IOT_ID);
    }

    /**
     * 绑定用户与设备
     *
     * @param operator   操作者
     * @param iotId      设备ID
     * @param targetList 要绑定用户账号列表
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<List<UserResultDTO>> bindUserDevice(IdentityDTO operator,
                                                                 String iotId,
                                                                 List<IdentityDTO> targetList) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_device_bind");

        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("targetList", targetList);

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_USER_RESULT);
    }

    /**
     * 解绑用户与设备
     *
     * @param operator   操作者
     * @param iotId      设备ID
     * @param targetList 要绑定用户账号列表
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<List<UserResultDTO>> unbindUserDevice(IdentityDTO operator,
                                                                   String iotId,
                                                                   List<IdentityDTO> targetList) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_device_unbind");

        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("targetList", targetList);

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_USER_RESULT);
    }

    /**
     * 查询用户的设备
     *
     * @param operator 操作者
     * @param target   查询的目标用户
     * @param pageNo   页码（min=1）
     * @param pageSize 页大小（min=1, max=100)
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<PageDTO<DeviceDTO>> queryUserDevices(IdentityDTO operator,
                                                                  IdentityDTO target,
                                                                  int pageNo,
                                                                  int pageSize) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_device_list");

        PageSearchDTO page = new PageSearchDTO();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        HashMap<String, Object> params = createParamMap(operator, 4);
        params.put("target", target);
        params.put("pageInfo", page);

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_DEVICE_PAGE);
    }

    /**
     * 查询设备的用户
     *
     * @param operator  操作者
     * @param iotId     设备ID
     * @param pageQuery 查询参数
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     * @see #queryDeviceUsersWithRole(IdentityDTO, String, PageSearchDTO)
     */
    @Deprecated
    public static ApiCommand<PageDTO<IdentityDTO>> queryDeviceUsers(IdentityDTO operator, String iotId,
                                                                    PageSearchDTO pageQuery) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_user_list");

        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("pageQuery", pageQuery);

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_IDENTITY_PAGE);
    }

    /**
     * 查询设备的用户(带用户角色)
     *
     * @param operator  操作者
     * @param iotId     设备ID
     * @param pageQuery 查询参数
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行
     */
    public static ApiCommand<PageDTO<IdentityRoleDTO>> queryDeviceUsersWithRole(IdentityDTO operator, String iotId,
                                                                                PageSearchDTO pageQuery) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "device_user_list_with_role");

        HashMap<String, Object> params = createParamMap(operator, iotId, 4);
        params.put("pageQuery", pageQuery);

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_IDENTITY_ROLE_PAGE);
    }

    /**
     * 授予用户对某设备的权限
     *
     * @param operator   操作者
     * @param iotId      一级（顶层）空间ID
     * @param targetList 用户ID列表
     * @param role       用户角色
     * @return 调用 {@link ApiCommand#executeAndGet()} 执行获取场景ID
     */
    public static ApiCommand<List<UserResultDTO>> setDeviceUserRole(IdentityDTO operator,
                                                                    String iotId,
                                                                    List<IdentityDTO> targetList,
                                                                    DeviceUserRoleEnum role) {
        ApiConfig apiConfig = ApiConfigLoader.get(G_HOME_LINK, "user_device_perm_auth");

        HashMap<String, Object> params = createParamMap(operator, iotId, 8);
        params.put("targetList", targetList);
        params.put("roleCode", role.name());

        return getApiCommand(apiConfig, params, ApiResultTypeConstants.RETURN_TYPE_USER_RESULT);
    }
}
