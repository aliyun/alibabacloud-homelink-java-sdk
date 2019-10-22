package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.aliyun.iotx.api.sdk.TestInit;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/09/20
 */
public class SceneDeviceApiTest {

    private static IdentityDTO operator;

    @Before
    public void setUp() {
        TestInit.init();
        operator = TestInit.getOperator();
    }

    @Test
    public void querySceneDevices() {
    }

    @Test
    public void queryUserDevicesOnTca() {
    }
}