package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author juanshi.yt
 * @date 2018/12/25 3:29 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DeviceTcaQueryDTO extends PageSearchDTO {
    /**
     * 1：trigger 2: condition 3: action
     */
    private Integer flowType;

    private String name;

    private String productKey;
}
