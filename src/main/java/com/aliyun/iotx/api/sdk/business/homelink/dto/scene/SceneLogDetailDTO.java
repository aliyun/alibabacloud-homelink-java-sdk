package com.aliyun.iotx.api.sdk.business.homelink.dto.scene;

import lombok.Data;

import java.io.Serializable;

/**
 * 场景日志详情
 *
 * @author juanshi.yt
 * @date 2018/04/11
 */
@Data
public class SceneLogDetailDTO implements Serializable {
    private static final long serialVersionUID = -7027966698249618640L;
    /**
     * 时间戳
     */
    private Long time;
    /**
     * 时间戳
     */
    private String timeZone;
    /**
     * 场景id
     */
    private String sceneId;
    /**
     * 日志id
     */
    private String id;
    /**
     * 场景名称
     */
    private String sceneName;
    /**
     * 执行结果 0 失败 1成功
     */
    private Integer result;

    private String icon;
}
