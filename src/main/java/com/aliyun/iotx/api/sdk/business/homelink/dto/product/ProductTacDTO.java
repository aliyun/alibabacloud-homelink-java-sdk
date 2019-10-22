package com.aliyun.iotx.api.sdk.business.homelink.dto.product;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品TCA属性
 */
@Data
public class ProductTacDTO implements Serializable {
    /**
     * 功能主键Id
     */
    private Long abilityId;
    /**
     * 品类名称
     */
    private String categoryType;
    /**
     *功能类型 PROPERTY 属性,SERVICE 服务,EVENT 事件
     */
    private String abilityType;
    /**
     *名称
     */
    private String name;
    /**
     * 标识符
     */
    private String identifier;
    /**
     * 是否为标准功能
     */
    private Boolean isStd;
    /**
     * 标准功能是否必选
     */
    private Boolean required;
    /**
     * 读写功能 READ_WRITE 读写，READ_ONLY 只读，WRITE_ONLY 只写
     */
    private String rwType;
}
