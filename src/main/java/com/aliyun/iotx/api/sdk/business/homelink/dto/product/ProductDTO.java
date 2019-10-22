package com.aliyun.iotx.api.sdk.business.homelink.dto.product;

import lombok.Data;

import java.io.Serializable;

/**
 * @author weishi.cc
 * 设备的产品属性实体
 */
@Data
public class ProductDTO implements Serializable {

    /**
     * 设备名称
     */
    private String productName;

    /**
     * 设备productKey
     */
    private String productKey;

    /**
     * 产品设备型号
     */
    private String productModel;

    /**
     * 品类名称
     */
    private String categoryName;
    /**
     * 品类key
     */
    private String categoryKey;

    /**
     * 设备类型 GATEWAY DEVICE
     */
    private String nodeType;

    /**
     * 品牌名称
     */
    private String brand;

    /**
     * 产品昵称
     */
    private String nickName;

    /**
     * 产品图标
     */
    private String image;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productName='" + productName + '\'' +
                ", productKey='" + productKey + '\'' +
                ", productModel='" + productModel + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryKey='" + categoryKey + '\'' +
                ", nodeType='" + nodeType + '\'' +
                ", brand='" + brand + '\'' +
                ", nickName='" + nickName + '\'' +
            '}';
    }
}
