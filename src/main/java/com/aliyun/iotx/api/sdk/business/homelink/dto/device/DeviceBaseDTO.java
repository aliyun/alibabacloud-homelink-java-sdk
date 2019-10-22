package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;


/**
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/07/25
 */
@Data
public class DeviceBaseDTO {

    /**
     * 设备id
     */
    private String iotId;

    /**
     * 设备ProductKey
     */
    private String productKey;

    /**
     * 设备DeviceName
     */
    private String deviceName;
}
