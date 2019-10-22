package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;

import java.util.Date;


/**
 * @author weishi.cc
 * @date 2019/3/27
 */
@SuppressWarnings("unused")
@Data
public class DeviceStatusDTO {

    private String iotId;

    private String batchId;

    private String attribute;

    private Object value;

    private Date gmtModified;

}