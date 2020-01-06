package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;

/**
 * @author baobao.xq
 * @date 2019/2/21下午8:52
 */
@Data
public class DeviceCommandDTO {

    /**
     * 设备id
     */
    private String iotId;


    /**
     * 入参
     */
    private String commandString;

}
