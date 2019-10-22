/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.business.homelink.business.util;

import com.aliyun.iotx.api.sdk.business.homelink.business.DeviceUserApi;
import com.aliyun.iotx.api.sdk.business.homelink.business.SpaceApi;
import com.aliyun.iotx.api.sdk.business.homelink.business.SpaceDeviceApi;
import com.aliyun.iotx.api.sdk.business.homelink.business.SpaceUserApi;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceTcaQueryDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceInfo;
import com.aliyun.iotx.api.sdk.business.homelink.dto.space.SpaceQueryDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.user.UserDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;

import static java.util.Objects.isNull;


/**
 * @author alibaba
 * @date 2019/09/19
 */
@SuppressWarnings("WeakerAccess")
public class SpacePrinter {

    public static void dumpTenantSpaces(IdentityDTO operator) {

        // 查询根空间
        List<SpaceInfo> spaceInfos = queryRoots(operator);
        //System.out.println(JSON.toJSONString(spaceInfos, true));

        spaceInfos.forEach(rootId -> {
            String summary = SummaryUtil.getSpaceSummary(rootId);
            System.out.println(summary);

            dumpSubSpaces(operator, rootId.getId(), 1);
        });
    }

    public static void dumpSubSpaces(IdentityDTO operator, String id, int deep) {
        SpaceQueryDTO query = new SpaceQueryDTO();
        query.setSpaceId(id);
        query.setPageNo(1);
        query.setPageSize(100);

        PageDTO<SpaceInfo> page;
        try {
            page = SpaceApi.getSubSpace(operator, query).executeAndGet();
        } catch (Exception e) {
            return;
        }
        List<SpaceInfo> list = page.getData();
        if (isNull(list) || list.isEmpty()) {
            return;
        }

        list.forEach(subSpace -> {
            String prefix = SummaryUtil.getPrefix(deep);
            String summary = SummaryUtil.getSpaceSummary(subSpace);
            System.out.println(prefix + summary);

            dumpSpaceDevice(operator, subSpace, deep + 1);
            dumpSpaceIdentities(operator, subSpace, deep + 1);
            dumpSubSpaces(operator, subSpace.getId(), deep + 1);
        });
    }

    public static void dumpSpaceIdentities(IdentityDTO operator, SpaceInfo subSpace, int deep) {
        if (Strings.isNullOrEmpty(subSpace.getRootSpaceId())) {
            return;
        }

        PageDTO<UserDTO> page = SpaceUserApi.querySpaceUsers(
            operator,
            subSpace.getId(), 1, 100).executeAndGet();

        List<UserDTO> list = page.getData();
        if (isNull(list) || list.isEmpty()) {
            return;
        }

        list.forEach(dev -> {
            String summary = SummaryUtil.getUserSummary(dev);
            String prefix = SummaryUtil.getPrefix(deep);
            System.out.println(prefix + summary);
        });
    }

    public static void dumpSpaceDevice(IdentityDTO operator, SpaceInfo subSpace, int deep) {
        if (Strings.isNullOrEmpty(subSpace.getRootSpaceId())) {
            return;
        }

        PageDTO<DeviceDTO> page = SpaceDeviceApi.querySpaceDevices(
            operator,
            subSpace.getRootSpaceId(),
            subSpace.getId(),
            false, 1, 100).executeAndGet();

        List<DeviceDTO> list = page.getData();
        if (isNull(list) || list.isEmpty()) {
            return;
        }

        list.forEach(dev -> {
            String summary = SummaryUtil.getDeviceSummary(dev);
            String prefix = SummaryUtil.getPrefix(deep);
            System.out.println(prefix + summary);

            dumpDeviceUsers(operator, dev, deep + 1);
        });
    }

    public static void dumpDeviceUsers(IdentityDTO operator, DeviceDTO dev, int deep) {
        if (isNull(dev)) {
            return;
        }

        DeviceTcaQueryDTO query = new DeviceTcaQueryDTO();
        PageDTO<IdentityDTO> page;
        try {
            page = DeviceUserApi.queryDeviceUsers(operator, dev.getIotId(), query).executeAndGet();
        } catch (Exception e) {
            System.out.println("查询设备的用户列表失败 iotId="+ dev.getIotId());
            return;
        }

        List<IdentityDTO> list = page.getData();
        if (isNull(list) || list.isEmpty()) {
            return;
        }

        list.forEach(item -> {
            String summary = SummaryUtil.getIdentitySummary(item);
            String prefix = SummaryUtil.getPrefix(deep);
            System.out.println(prefix + summary);
        });
    }

    public static List<SpaceInfo> queryRoots(IdentityDTO operator) {

        SpaceQueryDTO query = new SpaceQueryDTO();
        query.setPageNo(1);
        query.setPageSize(20);

        List<SpaceInfo> total = Lists.newLinkedList();

        while (true) {
            PageDTO<SpaceInfo> rootList = SpaceApi.getRootSpace(operator, query).executeAndGet();
            List<SpaceInfo> data = rootList.getData();
            if (data.isEmpty()) {
                break;
            }
            total.addAll(data);
            query.setPageNo(query.getPageNo() + 1);
        }

        return total;
    }
}
