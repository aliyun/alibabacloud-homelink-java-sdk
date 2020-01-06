package com.aliyun.iotx.api.sdk.business.homelink.dto.space;

import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @date 2019/07/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VirtualDeviceQuery extends PageSearchDTO {

    private String productKey;
}
