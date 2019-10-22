package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;

/**
 * @author 天何
 * @date 2019/9/21
 */
@Data
public class GatewayDeviceDTO extends DeviceBaseDTO {

    /**
     * 同deviceName，用于兼容API出参名称
     */
    private String name;

}
