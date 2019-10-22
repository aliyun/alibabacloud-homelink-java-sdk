/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.dto;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 分页查询参数
 * @author alibaba
 * @date 2018/12/13上午11:51
 */
@SuppressWarnings("all")
@Data
public class PageSearchDTO {

    private final int DEFAULT_PAGE_SIZE = 20;
    private final int MIN_PAGE_INDEX = 1;

    /**
     * 每页条数, 最大100条
     */
    private Integer pageSize = 20;

    /**
     * 当前页码，从1开始
     */
    @Min(value = 1, message = "pageNo must not be less than 1", groups = {PageSearchDTO.RangeCheck.class})
    private Integer pageNo = 1;

    /**
     * 排序字段，默认用创建时间
     */
    private OrderBy orderBy;

    public PageSearchDTO() {
        this.pageNo = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Integer getPageSize () {
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public Integer getPageNo () {
        return pageNo == null ? MIN_PAGE_INDEX : pageNo;
    }

    public Integer getOffset() {return (getPageNo() - 1) * pageSize;}

    public interface RangeCheck {
    }
}
