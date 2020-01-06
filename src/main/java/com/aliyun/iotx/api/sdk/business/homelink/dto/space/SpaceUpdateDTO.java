package com.aliyun.iotx.api.sdk.business.homelink.dto.space;

import lombok.Data;

/**
 * @author weishi.cc
 * @date 2019/5/17
 */
@Data
public class SpaceUpdateDTO {

    private String spaceId;

    private String description;

    private String name;

    private String attribute;
}
