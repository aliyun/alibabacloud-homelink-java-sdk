package com.aliyun.iotx.api.sdk.business.homelink.dto.user;

/**
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/07/25
 */
public enum DeviceUserRoleEnum {
    /**
     * 设备的管理员，有删除设备、修改设备备注名的权限
     */
    DEVICE_MANAGER,

    /**
     * 设备的使用者，可以看到和使用设备
     */
    DEVICE_MEMBER
}
