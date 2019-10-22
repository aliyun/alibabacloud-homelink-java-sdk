package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.alibaba.fastjson.JSON;
import com.aliyun.iotx.api.sdk.TestInit;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityRoleDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;
import org.junit.Before;
import org.junit.Test;


/**
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/09/20
 */
public class DeviceUserApiTest {

    private static IdentityDTO operator;

    @Before
    public void setUp() {
        TestInit.init();
        operator = TestInit.getOperator();
    }

    @Test
    public void bindUserDevice() {
    }

    @Test
    public void bindUserDeviceV2() {
        PageSearchDTO query = new PageSearchDTO();
        PageDTO<IdentityRoleDTO> users = DeviceUserApi.queryDeviceUsersWithRole(operator, "r804eDzKVksZBjosaCBh000101", query).executeAndGet();
        System.out.println(JSON.toJSONString(users, true));
    }

    @Test
    public void unbindUserDevice() {
    }

    @Test
    public void queryUserDevices() {
        IdentityDTO target = new IdentityDTO();
        target.setHid("wanping.test.1");
        target.setHidType("OPEN");
        PageDTO<DeviceDTO> page = DeviceUserApi.queryUserDevices(operator, target, 1, 20)
            .executeAndGet();

        System.out.println(JSON.toJSONString(page, true));
    }

    @Test
    public void queryDeviceUsers() {
    }

    @Test
    public void setDeviceUserRole() {
    }
}