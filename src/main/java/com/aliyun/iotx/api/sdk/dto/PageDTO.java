package com.aliyun.iotx.api.sdk.dto;

import lombok.Data;

import java.util.List;


/**
 * 分页定义
 *
 * @author zhenzhao.czz
 * @date 2018/4/2 分页查询
 */
@Data
public class PageDTO<T> {

    /**
     * 分页最大值
     */
    public static final Integer MAX_PAGE_SIZE = 100;

    /**
     * 分页最小值
     */
    public static final Integer MIN_PAGE_SIZE = 1;

    /**
     * 分页页码最小值
     */
    public static final Integer MIN_PAGE_INDEX = 1;

    /**
     * 默认分页大小
     */
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 具体数据集合
     */
    private List<T> data;

    /**
     * 每页条数, 最大1000条
     */

    private Integer pageSize;

    /**
     * 当前页码，从1开始
     */
    private Integer pageNo;

    /**
     * 排序字段，默认用创建时间
     */
    private Integer total;

    public PageDTO() {
        pageSize = DEFAULT_PAGE_SIZE;
        pageNo = MIN_PAGE_INDEX;
    }

    public PageDTO(Integer start, Integer size, Integer total, List<T> data) {
        this.pageNo = start;
        this.pageSize = size;
        this.total = total;
        this.data = data;
    }

    public PageDTO(PageDTO pageDTO) {
        this.pageNo = pageDTO.getPageNo();
        this.pageSize = pageDTO.getPageSize();
        this.total = pageDTO.getTotal();
        this.data = pageDTO.getData();
    }

    public PageDTO(Integer start, Integer size) {
        this.pageNo = start;
        this.pageSize = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize 每页大小不能超过1000，不能小于1
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize < MIN_PAGE_SIZE ? DEFAULT_PAGE_SIZE
            : pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo 页码不能小于1
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo < MIN_PAGE_INDEX ? MIN_PAGE_INDEX : pageNo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
            "data=" + data +
            ", pageSize=" + pageSize +
            ", pageNo=" + pageNo +
            ", total=" + total +
            '}';
    }
}
