package com.aliyun.iotx.api.sdk.business.homelink.dto.scene;

import lombok.Data;

import java.io.Serializable;


/**
 * 场景模板详细字段
 *
 * @author weishi.cc
 * @date 2018/12/6 11:36 AM
 */
@Data
public class SceneTemplateDesignDTO implements Serializable {

    private static final long serialVersionUID = 6462315394101985769L;

    /**
     * 场景名称
     */
    private String name;

    /**
     * 场景图标
     */
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
    private String triggers;

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
    private String conditions;

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
    private String actions;

    /**
     * 场景模板ID
     */
    private String sceneTemplateId;

    /**
     * 场景描述
     */
    private String description;

    /**
     * 空间ID
     */
    private String spaceId;

    /**
     * 空间模版ID
     */
    private String spaceTemplateId;
}
