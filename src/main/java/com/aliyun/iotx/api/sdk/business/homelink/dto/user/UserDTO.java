package com.aliyun.iotx.api.sdk.business.homelink.dto.user;

import lombok.Data;

/**
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2018/12/04
 */
@Data
public class UserDTO {

    /**
     * 三方账号标识
     */
    private String openId;

    /**
     * AppKey
     */
    private String appKey;

    /**
     * 用户ID
     */
    private String identityId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 账户类型：IdentityDTO.hidType
     */
    private String userType;

    /**
     * 用户身份id
     */
    private String hid;

}
