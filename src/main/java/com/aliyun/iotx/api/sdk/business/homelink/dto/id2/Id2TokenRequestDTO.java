package com.aliyun.iotx.api.sdk.business.homelink.dto.id2;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weishi.cc
 * @date 2019/5/16
 */
@Data
public class Id2TokenRequestDTO {
    /**
     * 接口版本
     */
    @NotBlank(message = "apiVersion不能为空")
    private String apiVersion;

    /**
     * 空发产品标识
     */
    @NotBlank(message = "productKey不能为空")
    private String productKey;

    /**
     * 用户ID
     */
    @NotBlank(message = "uid不能为空")
    String uid;
}
