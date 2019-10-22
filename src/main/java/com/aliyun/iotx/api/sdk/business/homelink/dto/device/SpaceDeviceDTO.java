package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;

import java.util.List;


/**
 * @author alibaba
 * @date 2019/08/06
 */
@Data
public class SpaceDeviceDTO extends DeviceBaseDTO {

    private String spaceId;

    private String spaceName;

    private String productImage;

    private String deviceName;

    private String nickName;

    private String productName;

    private Integer status;

    private Boolean isEdgeGateway;

    private List<Attribute> attributes;

    @Data
    public static class Attribute {

        private String attribute;

        private String value;

    }
}
