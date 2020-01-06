package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;

import java.util.List;

/**
 * 设备控制返回结果
 * @author baobao.xq
 * @date 2018/12/17下午4:34
 */
@Data
public class DeviceControlResultDTO {

    /**
     * 操作的设备id
     */
    private String targetId;

    /**
     * 命令操作结果
     */
    private List<CommandResult> commandResults;
}
