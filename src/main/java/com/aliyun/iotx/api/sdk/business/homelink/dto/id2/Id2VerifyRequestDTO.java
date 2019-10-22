package com.aliyun.iotx.api.sdk.business.homelink.dto.id2;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weishi.cc
 * @date 2019/5/16
 */
@Data
public class Id2VerifyRequestDTO {
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
     * ID2标识
     */
    @NotBlank(message = "id2不能为空")
    private String id2;

    /**
     * 用户ID
     */
    @NotBlank(message = "uid不能为空")
    private String uid;

    /**
     * 客户端认证码
     */
    @NotBlank(message = "authCode不能为空")
    private String authCode;

    /**
     * 人员名称
     */
    @NotBlank(message = "name不能为空")
    private String name;

    /**
     * 身份证号码
     */
    @NotBlank(message = "idNumber不能为空")
    private String idNumber;

    /**
     * 人员照片
     */
    @NotBlank(message = "image不能为空")
    private String image;

    /**
     * 照片签名信息
     */
    @NotBlank(message = "token不能为空")
    private String token;

    /**
     * 组织机构代码
     */
    @NotBlank(message = "businessCode不能为空")
    private String businessCode;
}
