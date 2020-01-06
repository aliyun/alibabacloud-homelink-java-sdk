package com.aliyun.iotx.api.sdk.dto;

import lombok.Data;


/**
 * 身份DTO，需要鉴权的接口统一使用作为第1个参数
 *
 * @date 2018/12/04
 */
@Data
public class IdentityDTO {

    /**
     * 三方账号ID
     */
    public static final String TYPE_OPEN = "OPEN";

    /**
     * OA账号
     */
    public static final String TYPE_OA = "OA";

    /**
     * OA账号手机号
     */
    public static final String TYPE_OA_PHONE = "OA_PHONE";

    /**
     * IoT统一身份标识
     */
    public static final String TYPE_IOT = "IOT";

    /**
     * 人居用户标识，支持三方账号或自有OA账号
     */
    private String hid;

    /**
     * 自有账号体系
     */
    private String hidType;

    /**
     * AppKey
     */
    private String appKey;

}
