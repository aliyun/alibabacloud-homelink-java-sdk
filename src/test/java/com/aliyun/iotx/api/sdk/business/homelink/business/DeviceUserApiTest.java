/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.TestInit;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author alibaba
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
    public void unbindUserDevice() {
    }

    @Test
    public void queryUserDevices() {
    }

    @Test
    public void queryDeviceUsers() {
    }

    @Test
    public void setDeviceUserRole() {
    }
}