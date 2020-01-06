package com.aliyun.iotx.api.sdk.business.homelink.dto.scene;

import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用于查询场景详情，删除场景
 *
 * @author juanshi.yt
 * @date 2018/03/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SceneSearchDTO extends PageSearchDTO {
    /**
     * 场景模板名称
     */
    private String name;

    /**
     * 空间id
     */
    private String spaceId;
    /**
     * 查场景日志的结束时间戳
     */
    private Long nowTime;
    /**
     * 失败日志的logId，由查询日志列表接口拉出
     */
    private String logId;
    /**
     * 场景日志对应的场景id
     */
    private String sceneId;
    /**
     * 失败日志的日志时间，由查询日志列表接口拉出
     */
    private Long time;
}
