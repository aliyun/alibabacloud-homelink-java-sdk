package com.aliyun.iotx.api.sdk.business.homelink.dto.scene;

import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 用于查询场景详情，删除场景
 *
 * @author juanshi.yt
 * @date 2018/03/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SceneTemplateSearchDTO extends PageSearchDTO {
    /**
     * 查询用户信息
     */
    private IdentityDTO target;

    /**
     * 空间模板ID
     */
    private String spaceTemplateId;

    /**
     * 场景模板名称，支持模糊搜索
     */
    private String name;

    /**
     * 查询类型
     */
    @NotNull
    private SceneTemplateSearchTypeEnum searchType;
}
