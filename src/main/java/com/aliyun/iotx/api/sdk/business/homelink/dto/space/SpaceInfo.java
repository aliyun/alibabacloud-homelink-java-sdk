package com.aliyun.iotx.api.sdk.business.homelink.dto.space;

import com.alibaba.fastvalidator.constraints.ValidateBean;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;


/**
 * 空间实例的结构
 *
 * @author  alibaba
 * @date 2018/12/08
 */
@ValidateBean
@Data
public class SpaceInfo {

    /**
     * 空间id
     */
    private String id;

    /**
     * 空间名称
     */
    @NotBlank(message = "name can't be blank")
    @Length(max = 128, message = "name length must not be greater than 128")
    private String name;

    /**
     * 根空间id
     */
    private String rootSpaceId;

    /**
     * 空间用途代码
     */
    private String usesCode;

    /**
     * 空间属性 json string
     **/
    private String attribute;

    /**
     * 上级空间id
     */
    private String parentId;

    /**
     * 路径ID列表
     *
     * @since 1.0.3
     */
    private List<String> pathIds;

    /**
     * 空间描述
     */
    private String description;

    /**
     * 空间类型代码
     */
    private String typeCode;

    /**
     * 空间类型名称
     */
    private String typeName;

    /**
     * 是否有下级空间节点
     */
    private Boolean hasChild;

    /**
     * 空间是否包含设备
     */
    private Boolean hasDeviceChild;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 创建时间
     */
    private String gmtModified;
}
