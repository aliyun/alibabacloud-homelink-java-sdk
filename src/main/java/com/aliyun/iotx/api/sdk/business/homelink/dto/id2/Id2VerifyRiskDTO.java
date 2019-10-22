package com.aliyun.iotx.api.sdk.business.homelink.dto.id2;

import lombok.Data;

/**
 * @author weishi.cc
 * @date 2019/5/21
 */
@Data
public class Id2VerifyRiskDTO {
    private String name;
    private String idNumber;
    private String phone;
    private String riskValue;
}
