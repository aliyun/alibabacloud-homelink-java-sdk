package com.aliyun.iotx.api.sdk.business.homelink;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.*;
import com.aliyun.iotx.api.sdk.business.homelink.dto.product.ProductDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.scene.SceneDesignDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceInfo;
import com.aliyun.iotx.api.sdk.business.homelink.dto.user.ThirdAccountListDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityRoleDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;

import java.util.List;


/**
 * 返回结果类型的定义
 *
 * @date 2019/07/25
 */
public final class ApiResultTypeConstants {

    /**
     * API分组：人居
     */
    public static final String G_HOME_LINK = "HomeLink";

    /**
     * API分组：SEP（三方账号接入）
     */
    public static final String G_BASE_SEP = "BaseSep";

    /**
     * API分组：锁
     */
    public static final String G_EXT_LOCK = "ExtLock";

    public static final TypeReference<Void>
        RETURN_TYPE_VOID = new TypeReference<Void>() {};

    public static final TypeReference<PageDTO<String>>
        RETURN_TYPE_STRING_PAGE = new TypeReference<PageDTO<String>>() {};

     public static final TypeReference<String>
        RETURN_TYPE_STRING = new TypeReference<String>() {};

    public static final TypeReference<DeviceControlResultDTO>
        RETURN_TYPE_DEVICE_CONTROL = new TypeReference<DeviceControlResultDTO>() {};

    public static final TypeReference<PageDTO<ProductDTO>>
        RETURN_TYPE_PRODUCT_LIST = new TypeReference<PageDTO<ProductDTO>>() {};

    public static final TypeReference<JSONObject>
        RETURN_TYPE_GET_TSL = new TypeReference<JSONObject>() {};

    public static final TypeReference<PageDTO<DeviceDTO>>
        RETURN_TYPE_DEVICE_PAGE = new TypeReference<PageDTO<DeviceDTO>>() {};

    public static final TypeReference<List<DeviceDTO>>
        RETURN_TYPE_DEVICE_LIST = new TypeReference<List<DeviceDTO>>() {};

    public static final TypeReference<DeviceBaseDTO>
        RETURN_TYPE_DEVICE_BASE = new TypeReference<DeviceBaseDTO>() {};

    public static final TypeReference<DeviceResetResultDTO>
        RETURN_TYPE_RESET_DEVICE = new TypeReference<DeviceResetResultDTO>() {};

    public static final TypeReference<GatewayDeviceDTO>
        RETURN_TYPE_DEVICE_GATEWAY = new TypeReference<GatewayDeviceDTO>() {};

    public static final TypeReference<PageDTO<DeviceBaseDTO>>
        RETURN_TYPE_GATEWAY_SUB_DEVICE = new TypeReference<PageDTO<DeviceBaseDTO>>() {};

    public static final TypeReference<List<UserResultDTO>>
        RETURN_TYPE_USER_RESULT = new TypeReference<List<UserResultDTO>>() {};

    public static final TypeReference<SingleIotIdDTO>
        RETURN_TYPE_SINGLE_IOT_ID = new TypeReference<SingleIotIdDTO>() {};

    public static final TypeReference<List<SpaceDeviceDTO>>
        RETURN_TYPE_SPACE_DEVICE = new TypeReference<List<SpaceDeviceDTO>>() {};

    public static final TypeReference<PageDTO<IdentityDTO>>
        RETURN_TYPE_IDENTITY_PAGE = new TypeReference<PageDTO<IdentityDTO>>() {};

    public static final TypeReference<List<IdentityDTO>>
        RETURN_TYPE_IDENTITY_LIST = new TypeReference<List<IdentityDTO>>() {};

    public static final TypeReference<PageDTO<IdentityRoleDTO>>
        RETURN_TYPE_IDENTITY_ROLE_PAGE = new TypeReference<PageDTO<IdentityRoleDTO>>() {};

    public static final TypeReference<com.aliyun.iotx.api.sdk.business.homelink.dto.user.IdentityDTO>
        RETURN_TYPE_IDENTITY = new TypeReference<com.aliyun.iotx.api.sdk.business.homelink.dto.user.IdentityDTO>() {};

    public static final TypeReference<SceneDesignDTO>
        RETURN_TYPE_SCENE_DESIGN = new TypeReference<SceneDesignDTO>() {};

    public static final TypeReference<PageDTO<SceneDesignDTO>>
        RETURN_TYPE_SCENE_DESIGN_PAGE = new TypeReference<PageDTO<SceneDesignDTO>>() {};

    public static final TypeReference<PageDTO<DeviceTcaDTO>>
        RETURN_TYPE_DEVICE_TCA_PAGE = new TypeReference<PageDTO<DeviceTcaDTO>>() {};

    public static final TypeReference<List<DeviceTcaDTO>>
        RETURN_TYPE_DEVICE_TCA_LIST = new TypeReference<List<DeviceTcaDTO>>() {};

    public static final TypeReference<List<SpaceInfo>>
        RETURN_TYPE_SPACE_INFO_LIST = new TypeReference<List<SpaceInfo>>() {};

    public static final TypeReference<PageDTO<SpaceInfo>>
        RETURN_TYPE_SPACE_INFO_PAGE = new TypeReference<PageDTO<SpaceInfo>>() {};

    public static final TypeReference<SpaceInfo>
        RETURN_TYPE_SPACE_INFO = new TypeReference<SpaceInfo>() {};

    public static final TypeReference<PageDTO<DeviceBaseV1DTO>>
        RETURN_TYPE_VIRTUAL_DEVICE_INFO_PAGE = new TypeReference<PageDTO<DeviceBaseV1DTO>>() {};

    public static final TypeReference<ThirdAccountListDTO>
        RETURN_TYPE_THIRD_ACCOUNT_LIST = new TypeReference<ThirdAccountListDTO>() {};

    public static final TypeReference<PageDTO<CategoryDTO>>
        RETURN_TYPE_CATEGORY_PAGE = new TypeReference<PageDTO<CategoryDTO>>() {};

}
