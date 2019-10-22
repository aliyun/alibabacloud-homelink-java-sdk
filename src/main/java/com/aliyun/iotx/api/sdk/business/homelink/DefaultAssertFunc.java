package com.aliyun.iotx.api.sdk.business.homelink;

import com.aliyun.iotx.api.util.assertion.IoTxAssertions;
import com.aliyun.iotx.api.util.entity.IoTxResult;
import lombok.extern.slf4j.Slf4j;


/**
 * 默认验证方法实现
 *
 * @author zhangjingwei.zjw@alibaba-inc.com
 * @date 2019/09/20
 */
@Slf4j
public class DefaultAssertFunc {

    /**
     * 参数校验错误码
     */
    private static final int VALIDATE_ERROR_CODE = 460;

    public static <T> void assertIt(Integer code,
                                    String msg,
                                    String localizedMsg,
                                    @SuppressWarnings("unused")
                                        T data) {
        IoTxResult<Object> res = new IoTxResult<>(code, msg, localizedMsg);
        IoTxAssertions.assertIoTxResult(res);

        if (VALIDATE_ERROR_CODE == code) {
            log.info("调用API失败，错误码={}。这个错误通常由接口请求参数错误引起，参考提示信息：{}({})，或者查找API文档确认参数是否正确。",
                code, msg, localizedMsg);
        }
        IoTxAssertions.assertIoTxResult(res);
    }
}
