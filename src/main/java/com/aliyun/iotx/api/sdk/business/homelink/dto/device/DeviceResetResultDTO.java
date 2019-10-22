package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import com.alibaba.fastjson.annotation.JSONField;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author alibaba
 * @date 2019/01/11
 */
@Data
@EqualsAndHashCode
@SuppressWarnings("WeakerAccess")
public class DeviceResetResultDTO {

    @JSONField(ordinal = 10)
    private String iotId;

    /**
     * 设备类型
     */
    @JSONField(ordinal = 20)
    private String deviceType;

    @JSONField(ordinal = 30)
    private List<DeviceUserDTO> users;

    @JSONField(ordinal = 40)
    private List<DeviceSpaceDTO> spaces;

    @JSONField(ordinal = 50)
    private List<DeviceSceneDTO> scenes;

    @JSONField(ordinal = 60)
    private List<DeviceResetResultDTO> children;

    @Data
    public static class DeviceUserDTO {

        @JSONField(ordinal = 10)
        private IdentityDTO identity;

        @JSONField(ordinal = 20)
        private String role;
    }

    @Data
    public static class DeviceSceneDTO {

        @JSONField(ordinal = 10)
        private String id;
    }

    @Data
    public static class DeviceSpaceDTO {

        /**
         * 空间id
         */
        @JSONField(ordinal = 10)
        private String id;

        /**
         * 空间名称
         */
        @JSONField(ordinal = 20)
        private String name;

        /**
         * 根空间id
         */
        @JSONField(ordinal = 30)
        private String rootSpaceId;

        /**
         * 上级空间id
         */
        @JSONField(ordinal = 30)
        private String parentId;

        /**
         * 空间类型代码
         */
        @JSONField(ordinal = 40)
        private String typeCode;

        /**
         * 空间类型名称
         */
        @JSONField(ordinal = 50)
        private String typeName;

    }
}
