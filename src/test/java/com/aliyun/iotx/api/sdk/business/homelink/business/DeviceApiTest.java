package com.aliyun.iotx.api.sdk.business.homelink.business;

import com.alibaba.fastjson.JSON;
import com.aliyun.iotx.api.sdk.TestInit;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.DeviceDTO;
import com.aliyun.iotx.api.sdk.business.homelink.dto.device.SpaceDeviceDTO;
import com.aliyun.iotx.api.sdk.dto.IdentityDTO;
import com.aliyun.iotx.api.sdk.dto.PageDTO;
import com.aliyun.iotx.api.util.exception.IoTxServiceException;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @date 2019/09/20
 */
public class DeviceApiTest {

    private static IdentityDTO operator;

    @Before
    public void setUp() {
        TestInit.init();
        operator = TestInit.getOperator();
    }

    @Test
    public void resetDevice() {
    }

    @Test
    public void controlDevice_ErrNotExist() {
        Throwable throwable = Assertions.catchThrowable(() -> {
            DeviceApi
                .controlDevice(operator, "iotId_not_exist", Lists.newArrayList())
                .executeAndGet();
        });
        assertThat(throwable).isNotNull();
        throwable.printStackTrace();

        assertThat(throwable).isInstanceOf(IoTxServiceException.class);
        IoTxServiceException ise = (IoTxServiceException)throwable;

        assertThat(ise.getResult().getCode()).isEqualTo(62101);
        assertThat(ise.getResult().getMessage()).isEqualTo("Device not exist(device not found:6100)");
        assertThat(ise.getResult().getLocalizedMsg()).isEqualTo("设备不存在");
    }

    @Test
    public void updateDeviceNickName() {
    }

    @Test
    public void getDevice() {
        List<SpaceDeviceDTO> device = DeviceApi.getDevice(operator,
            Collections.singletonList("kvH36PwwtGa0CiQ5CJdl000100")).executeAndGet();
        System.out.println(JSON.toJSONString(device, true));

    }

    @Test
    public void getDeviceTsl() {
    }

    @Test
    public void queryDevices() {
        PageDTO<DeviceDTO> page = DeviceApi.queryDevices(operator, 1, 20).executeAndGet();

        List<DeviceDTO> devices = page.getData();
        System.out.println(JSON.toJSONString(devices, true));
    }

    @Test
    public void queryGatewayOfSubDevice() {
    }

    @Test
    public void queryGatewaySubDevices() {
    }
}