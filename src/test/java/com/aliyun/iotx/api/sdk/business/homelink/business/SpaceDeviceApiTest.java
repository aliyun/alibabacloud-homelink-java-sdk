package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.TestInit;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import org.junit.Before;
import org.junit.Test;


/**
 * @date 2019/09/20
 */
public class SpaceDeviceApiTest {

    private static IdentityDTO operator;

    @Before
    public void setUp() {
        TestInit.init();
        operator = TestInit.getOperator();
    }

    @Test
    public void bindDeviceSpace() {
    }

    @Test
    public void unbindDeviceSpace() {
    }

    @Test
    public void getDeviceSpace() {
    }

    @Test
    public void querySpaceDevices() {
    }

    @Test
    public void bindSpaceVirtualDevice() {
    }

    @Test
    public void deleteSpaceVirtualDevice() {
    }

    @Test
    public void querySpaceVirtualDevices() {
    }

    @Test
    public void replaceSpaceVirtualDevice() {
    }
}