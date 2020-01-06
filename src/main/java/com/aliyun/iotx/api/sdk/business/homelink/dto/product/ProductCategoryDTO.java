package com.aliyun.iotx.api.sdk.business.homelink.dto.product;

import java.io.Serializable;

/**
 * @author weishi.cc
 * 产品品类
 */
public class ProductCategoryDTO implements Serializable {

    private static final long serialVersionUID = -8097327041967612973L;
    /**
     * 品类名称
     */
    private String categoryName;
    /**
     * 品类key
     */
    private String categoryKey;
    /**
     * 对应图片
     */
    private String imageUrl;
    /**
     * 对应模版
     */
    private String panelTemplateId;

    public ProductCategoryDTO() {

    }

    public ProductCategoryDTO(String categoryName, String categoryKey, String imageUrl, String panelTemplateId) {
        this.categoryName = categoryName;
        this.categoryKey = categoryKey;
        this.imageUrl = imageUrl;
        this.panelTemplateId = panelTemplateId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPanelTemplateId() {
        return panelTemplateId;
    }

    public void setPanelTemplateId(String panelTemplateId) {
        this.panelTemplateId = panelTemplateId;
    }

    @Override
    public String toString() {
        return "ProductCategoryDTO{" +
            "categoryName='" + categoryName + '\'' +
            ", categoryKey='" + categoryKey + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", panelTemplateId='" + panelTemplateId + '\'' +
            '}';
    }
}
