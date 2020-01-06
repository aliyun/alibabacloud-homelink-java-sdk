package com.aliyun.iotx.api.sdk.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 身份DTO，需要鉴权的接口统一使用作为第1个参数
 *
 * @date 2018/12/04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IdentityRoleDTO extends IdentityDTO {

    /**
     * 角色
     */
    private String role;

}
