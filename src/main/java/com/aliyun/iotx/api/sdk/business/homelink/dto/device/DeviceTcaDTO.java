package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;

/**
 *
 * @author juanshi.yt
 * @date 2018/12/25 3:05 PM
 */
@Data
public class DeviceTcaDTO {
    /**
     * 设备ID
     */
    private String iotId;

    /**
     * 设备DN
     */
    private String deviceName;

    /**
     * 产品PK
     */
    private String productKey;

    /**
     * 设备昵称
     */
    private String nickName;

    /**
     * 品类图标URL
     */
    private String image;

    /**
     * 在线状态(在获取子设备列表操作时有效）
     * 0 - 未激活， 1 - 在线， 3 - 离线， 8 - 禁用
     */
    private Byte status;
}
