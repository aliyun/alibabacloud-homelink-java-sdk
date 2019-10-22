package com.aliyun.iotx.api.sdk.business.homelink.dto.id2;

import lombok.Data;

/**
 * @author weishi.cc
 * @date 2019/5/16
 */
@Data
public class Id2VerifyResponseDTO {
    private String name;
    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 比对分数
     */
    private Integer score;

    private Integer result;

    private String resultText;
}
