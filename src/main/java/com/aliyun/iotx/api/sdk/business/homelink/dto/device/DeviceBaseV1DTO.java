package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import com.aliyun.iotx.api.sdk.business.homelink.business.SpaceApi;
import lombok.Data;


/**
 * 用于 绑定虚拟设备和空间 {@link SpaceApi#bindSpaceVirtualDevice}接口的返回结果
 *
 * @author alibaba
 * @date 2019/07/25
 */
@Data
public class DeviceBaseV1DTO {

    /**
     * 设备id
     */
    private String iotId;

    /**
     * 设备ProductKey
     */
    private String productKey;

    /**
     * 设备类型
     */
    private String thingType;

    /**
     * 备注名
     */
    private String nickName;

}
