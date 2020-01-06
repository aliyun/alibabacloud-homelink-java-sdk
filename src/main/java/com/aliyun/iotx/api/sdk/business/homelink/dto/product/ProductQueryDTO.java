package com.aliyun.iotx.api.sdk.business.homelink.dto.product;

import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author weishi.cc 产品查询条件
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductQueryDTO extends PageSearchDTO implements Serializable {

    private static final long serialVersionUID = -4030152516309270481L;

    /**
     * 产品名称，模糊查询
     */
    private String productName;

    /**
     * 产品key
     */
    private String productKey;

    /**
     * 产品对应的品类key
     */
    private String categoryKey;
}
