/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.business.homelink.business.util;

import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceInfo;
import com.aliyun.iotx.api.sdk.business.homelink.dto.user.UserDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;

import static java.util.Objects.isNull;


/**
 * 摘要工具
 *
 * @author alibaba
 * @date 2019/09/19
 */
@SuppressWarnings("WeakerAccess")
public class SummaryUtil {

    public static String getPrefix(int deep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deep; i++) {
            sb.append('\t');
        }
        return sb.toString();
    }

    public static String getSpaceSummary(SpaceInfo space) {
        if (isNull(space)) {
            return "";
        }
        return "# " + space.getName() + " [" + space.getId() + "] " + space.getTypeCode();
    }

    public static String getDeviceSummary(DeviceDTO dev) {
        if (isNull(dev)) {
            return "";
        }
        return "* " + dev.getIotId() + " - " + dev.getProductName()
            + " [" + dev.getProductKey() + "/" + dev.getDeviceName() + "]";
    }

    public static String getUserSummary(UserDTO user) {

        if (isNull(user)) {
            return "";
        }

        if (!user.getIdentityId().equals(user.getOpenId())) {
            return "& " + user.getIdentityId() + " - " + user.getOpenId();
        } else {
            return "& " + user.getIdentityId();
        }
    }

    public static String getIdentitySummary(IdentityDTO item) {

        if (isNull(item)) {
            return "";
        }
        return "& " + item.getHid() + " - " + item.getHidType();
    }
}
