package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import lombok.Data;


/**
 * 品类
 *
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/08/27
 */
@Data
public class CategoryDTO {

    private String categoryName;

    private String categoryKey;

    private String imageUrl;

    private Integer state;

    private Long superId;

    private Long categoryId;

}
