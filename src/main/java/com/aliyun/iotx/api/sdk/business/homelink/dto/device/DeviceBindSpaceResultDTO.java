package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DeviceBindSpaceResultDTO {
    /**
     * 成功绑定的设备数
     */
    private Integer successCount;
    /**
     * 每个设备的绑定结果
     */
    private List<Map<String,CommandResult>> batchResultMap;
}
