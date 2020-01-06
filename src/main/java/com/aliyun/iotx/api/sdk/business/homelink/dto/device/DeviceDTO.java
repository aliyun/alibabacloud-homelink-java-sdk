package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 设备信息
 *
 * @author 天何
 * @date 2018/12/11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceDTO extends DeviceBaseDTO {

    /**
     * 设备的产品图片
     */
    private String productImage;

    /**
     * 用户对设备设置的昵称
     */
    private String nickName;

    /**
     * 设备的产品名称
     */
    private String productName;

    /**
     * 设备的类型:"VIRTUAL", "WEB", "APP","DEVICE"
     */
    private String thingType;

    /**
     * 设备的状态
     */
    private Integer status;

    /**
     * 设备所属空间路径名称
     */
    private String spaceNamePath;

    /**
     * 设备最后上线时间
     */
    private String lastOnlineTime;

    /**
     * 设备属性列表
     */
    private List<DeviceStatusDTO> attributeList;

}
