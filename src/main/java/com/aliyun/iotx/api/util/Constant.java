package com.aliyun.iotx.api.util;

import com.aliyun.iotx.api.util.entity.IoTxCodePro;


/**
 * @date 2018/12/17
 */
public class Constant {

    public static final int SUCCESS_CODE = 200;

    /**
     * request param error
     */
    public static IoTxCodePro API_REQUEST_ERROR = new IoTxCodePro(400, "API请求异常，请检查AppKey/AppSecret/HTTP Schema");

    public static IoTxCodePro REQUEST_PARAM_ERROR = new IoTxCodePro(460, "request parameter error.");

    public static IoTxCodePro COMMON_ERROR = new IoTxCodePro(16400, "request parameter error.| msg={}");

    public static IoTxCodePro MESSAGE_ERROR = new IoTxCodePro(16401, "{}");

    /**
     * success code
     */
    public static IoTxCodePro SUCCESS = new IoTxCodePro(SUCCESS_CODE, "success");
    // 错误码定义： 服务上下文  751开始

    /**
     * 断言错误定义：断言无数据
     */
    public static final IoTxCodePro ASSERT_NO_DATA = new IoTxCodePro(1701,
        "No data was found from the IoTxResult results. |ioTxResult={}",
        "没有从IoTxResult结果中找到数据");

    /**
     * 断言错误定义：空值
     */
    public static final IoTxCodePro PARAM_NPE = new IoTxCodePro(1702,
        "Parameter object is a null pointer.",
        "参数对象为空指针");

    /**
     * 全局错误定义，条件为假
     */
    public static final IoTxCodePro CONDITION_IS_FALSE = new IoTxCodePro(1703,
        "Parameter values should be True.",
        "参数值应该为True，实际是False");

    /**
     * 全局错误定义，字符串为空
     */
    public static final IoTxCodePro STRING_IS_BLANK = new IoTxCodePro(1704,
        "String parameters should not be empty.",
        "字符串参数不应为空");

    /**
     * 全局错误定义，参数对相同
     */
    public static final IoTxCodePro PARAMS_IS_EQUALS = new IoTxCodePro(1705,
        "The two values of the comparison should not be the same. |value1={}, value2={}",
        "对比的两个值不应该相同");

    /**
     * 全局错误定义，参数对不相同
     */
    public static final IoTxCodePro PARAMS_IS_NOT_EQUALS = new IoTxCodePro(1706,
        "The two values of the comparison should be the same. |value1={}, value2={}",
        "对比的两个值应该相同");

    /**
     * 全局错误定义，容器为空
     */
    public static final IoTxCodePro COLLECTION_IS_EMPTY = new IoTxCodePro(1707,
        "Container type parameters should not be empty. |class={}",
        "容器类型参数不应为空");

    /**
     * 断言错误定义：断言无数据
     */
    public static final IoTxCodePro ASSERT_NO_IOT_RESULT = new IoTxCodePro(1708,
        "No IoTxResult.",
        "没有IoTxResult");

    public static final IoTxCodePro ASSERT_NO_TYPE = new IoTxCodePro(1709,
        "No type definition..",
        "没有类型定义");

    public static final IoTxCodePro ASSERT_NOT_EXPECT_TYPE = new IoTxCodePro(1710,
        "Not expected type.",
        "不是期望的类型");

}
