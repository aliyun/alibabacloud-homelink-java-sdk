package com.aliyun.iotx.api.sdk.business.homelink.dto.id2;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weishi.cc
 * @date 2019/5/16
 */
@Data
public class Id2RequestDTO {
    /**
     * 接口版本
     */
    @NotBlank(message = "apiVersion不能为空")
    private String apiVersion;

    /**
     * token认证码
     */
    @NotBlank(message = "deviceAuthCode不能为空")
    String deviceAuthCode;
}
