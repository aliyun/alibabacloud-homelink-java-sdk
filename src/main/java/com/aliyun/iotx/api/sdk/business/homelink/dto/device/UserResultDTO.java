package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.util.entity.IoTxResult;
import lombok.Data;


/**
 * @author jingwei
 */
@Data
public class UserResultDTO {

    private IdentityDTO identity;

    private IoTxResult result;
}
