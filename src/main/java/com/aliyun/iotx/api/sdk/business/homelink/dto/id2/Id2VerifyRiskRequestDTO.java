package com.aliyun.iotx.api.sdk.business.homelink.dto.id2;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author weishi.cc
 * @date 2019/5/21
 */
@Data
public class Id2VerifyRiskRequestDTO {
    /**
     * 版本号
     */
    @NotBlank(message = "apiVersion不能为空")
    private String apiVersion;

    /**
     * 阿里云账户
     */
    @NotBlank(message = "uid不能为空")
    private String uid;

    /**
     * 组织机构代码
     */
    @NotBlank(message = "businessCode不能为空")
    private String businessCode;

    /**
     * 风险返回结果的表达方式，缺省为输出风险等级：
     *
     * 0 - 输出等级
     *
     * 1 - 输出分数
     */
    private String riskMode;

    /**
     * 人员信息
     */
    @NotNull(message = "person不能为null")
    private Id2VerifyPersonDTO person;
}
