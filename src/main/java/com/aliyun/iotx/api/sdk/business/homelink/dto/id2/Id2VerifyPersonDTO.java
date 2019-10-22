package com.aliyun.iotx.api.sdk.business.homelink.dto.id2;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weishi.cc
 * @date 2019/5/21
 */
@Data
public class Id2VerifyPersonDTO {
    /**
     * 姓名
     */
    @NotBlank(message = "name不能为空")
    private String name;

    /**
     * 身份证号码
     */
    @NotBlank(message = "idNumber不能为空")
    private String idNumber;

    /**
     * 手机号
     */
    @NotBlank(message = "phone不能为空")
    private String phone;

    /**
     * 业务区域
     */
    @NotBlank(message = "area不能为空")
    private String area;

    /**
     * 人员位置
     */
    @NotBlank(message = "location不能为空")
    private String location;

    /**
     * 业务码
     */
    @NotBlank(message = "business不能为空")
    private String business;
}
