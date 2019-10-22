/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.dto;

import lombok.Data;

/**
 * 查询排序参数
 * @author alibaba
 * @date 2018/12/13上午11:54
 */
@SuppressWarnings("WeakerAccess")
@Data
public class OrderBy {

    private String columnName;

    private String direction;
}
