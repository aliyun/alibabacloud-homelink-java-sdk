package com.aliyun.iotx.api.sdk.business.homelink.dto.scene;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastvalidator.constraints.ValidateBean;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceInfo;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 创建场景，及场景详情，场景不再限定到空间、人，而是一个独立的实体，唯一需要校验的是场景中的设备租户
 *
 * @author juanshi.yt
 * @date 2018/12/6 11:36 AM
 */
@Data
@ValidateBean
public class SceneDesignDTO implements Serializable {
    private static final long serialVersionUID = 6462315394101985769L;

    /**
     * 场景名称
     */
    @NotBlank
    private String name;

    /**
     * 场景图标
     */
    @NotBlank
    private String icon;
    /**
     * 数组格式
     * <pre>
     * [{
     *   "params": {
     *     "cron": "42 23 * * 1,2,3,4,5,6,7",
     *     "cronType": "linux",
     *     "timezoneID": "Asia/Shanghai"
     *   },
     *   "uri": "trigger/timer"
     * },
     * {
     *   "params": {
     *     "iotId": "xxxxxxxx",
     *     "propertyName": "switch",
     *     "compareType": ">, >=, ==, !=, in等",
     *     "compareValue": 1
     *   },
     *   "uri": "trigger/device/property"
     * },
     * {
     *   "params": {
     *     "iotId": "xxxxxxxx",
     *     "eventCode": "aaaaa",
     *     "propertyName": "switch",
     *     "compareType": ">, >=, ==, !=, in等",
     *     "compareValue": 1
     *   },
     *   "uri": "trigger/device/event"
     * }
     * ]
     * </pre>
     */
    private JSONArray triggers;
    /**
     * <pre>
     * [{
     *   "params": {
     *     "iotId": "xxxxxxxx",
     *     "propertyName": "switch",
     *     "compareType": ">, >=, ==, !=, in等",
     *     "compareValue": 1
     *   },
     *   "uri": "condition/device/property"
     * },
     * {
     *   "params": {
     *     "format": "HH:mm",
     *     "beginDate": "8:30",
     *     "endDate": "23:00",
     *     "timezoneID": "Asia/Shanghai",
     *     "repeat": "1,2,3"
     *   },
     *   "uri": "condition/timeRange"
     * }]
     * </pre>
     */
    private JSONArray conditions;
    /**
     * 数组格式
     * <pre>
     * [{
     *   "uri": "action/device/setProperty",
     *   "params": {
     *     "iotId": "xxxxxxxxxxx",
     *     "propertyItems": {
     *       "WorkMode": 4,
     *       "LightSwitch": 1
     *     },
     *     "propertyNamesItems": {
     *       "WorkMode": {
     *         "abilityName": "工作模式",
     *         "valueName": "生活",
     *         "valueType": "enum"
     *       },
     *       "LightSwitch": {
     *         "abilityName": "主灯开关",
     *         "valueName": "开启",
     *         "valueType": "enum"
     *       }
     *     }
     *   }
     * },
     * {
     *   "params": {
     *     "iotId": "xxxxxxxx",
     *     "serviceName": "online",
     *     "serviceArgs": {
     *       "WorkMode": 4,
     *       "LightSwitch": 1
     *     }
     *   },
     *   "uri": "action/device/invokeService"
     * },
     * {
     *   "uri": "action/automation/setSwitch",
     *   "params": {
     *     "automationRuleId": "string",
     *     "switchStatus": 0
     *   }
     * },
     * {
     *   "uri": "action/scene/trigger",
     *   "params": {
     *     "id": "string"
     *   }
     * }
     * ]
     * <pre/>
     */
    @NotNull(message = "actions can't be null")
    private JSONArray actions;
    /**
     * 创建的时候没有这个字段，更新的时候有
     */
    private String sceneId;

    /**
     * 场景模板ID
     */
    private String sceneTemplateId;

    /**
     * 表示场景生效
     */
    private Boolean enable;
    /**
     * 场景描述
     */
    private String description;
    /**
     * 场景状态
     */
    private Integer status;

    private String spaceId;

    private String spaceTemplateId;

    private SpaceInfo spaceInfo;
}
