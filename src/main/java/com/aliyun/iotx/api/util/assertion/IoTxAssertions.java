package com.aliyun.iotx.api.util.assertion;

import com.aliyun.iotx.api.util.entity.IoTxCode;
import com.aliyun.iotx.api.util.entity.IoTxCodePro;
import com.aliyun.iotx.api.util.entity.IoTxResult;
import com.aliyun.iotx.api.util.Constant;
import com.aliyun.iotx.api.util.exception.IoTxServiceException;
import com.aliyun.iotx.api.util.log.LoggerUtil;
import com.google.common.base.Strings;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.aliyun.iotx.api.util.Constant.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


/**
 * IoT断言工具类
 * <p>
 * <p>
 * 它是IoTxResult的帮助类，用来简化IoTxResult处理的模板代码，使开发人员在编码时更加关注业务流程。 省略掉的模板代码会降低开发人员阅读代码的难度，提高效率。
 * <p>
 * <p>
 * 设计思路：
 * <ol>
 * <li>简化大量重复的断言IoTxResult的模板代码</li>
 * <li>用异常来代替对IoTxResult断言式的逻辑判断</li>
 * </ol>
 * 除了对IoTxResult的断言式处理，还可以引申出对其它变量的断言处理。所以这部分也是断言工具支持的。
 * <p>
 * 在编码中，还会遇到运行一段代码，然后在失败时执行清理（或回滚）操作，再返回错误码。 清理（或回滚）动作无论失败与否都要完成，最后返回错误。 这类操作也有支持，主要是简化代码，提高可用性。
 * <p>
 * <p>
 * 识别断言方法：
 * <ul>
 * <li>assertIoTxResult[XXX] 系列方法是IoTxResult的断言方法，区别是否使用自定义错误码。没有设置错误码时，返回IoTxResult中的错误码</li>
 * <li>assertAndGet[XXX] 是IoTxResult的断言和取值的方法。方法先处理IoTxResult的断言（见上一条），再从结果中取值，无值时返回{@link
 * Constant#ASSERT_NO_DATA}。如果期望结果可能为空，可以调用{@link #assertAndGetNullable}方法</li>
 * <li>{@link #assertTrue}、{@link #assertNonNull}、{@link #assertEquals}、{@link #assertNotBlank}、{@link
 * #assertNotEquals}是对布尔、对象、字符串和窗器类型的断言断言方法</li>
 * </ul>
 *
 * @date 2018/10/31
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class IoTxAssertions {

    /**
     * 断言结果并取数据，数据不为空。失败时抛出封装参数指定failureCode的{@link IoTxServiceException}
     *
     * @param ioTxResult  被断言的服务返回结果
     * @param failureCode 失败后的状态码, 为空时使用ioTxResult中的状态码
     * @param noDataCode  无数据时的状态码
     * @param <T>         DataType
     * @return 数据，不为null
     */
    public static <T> T assertAndGet(IoTxResult<T> ioTxResult,
                                     IoTxCode failureCode,
                                     IoTxCode noDataCode) {
        return assertAndGet(ioTxResult, failureCode, noDataCode, null);
    }

    /**
     * 断言结果并取数据，数据不为空
     *
     * @param ioTxResult  被断言的服务返回结果
     * @param failureCode 失败后的状态码, 为空时使用ioTxResult中的状态码
     * @param noDataCode  无数据时的状态码
     * @param <T>         DataType
     * @return 数据，不为null
     */
    public static <T> T assertAndGet(IoTxResult<T> ioTxResult,
                                     IoTxCode failureCode,
                                     IoTxCode noDataCode,
                                     Consumer<IoTxCode> exe) {
        assertIoTxResult(ioTxResult, failureCode, exe);

        return Optional.ofNullable(ioTxResult.getData())
            .orElseThrow(() -> {
                IoTxCode pro = getCode(noDataCode);
                IoTxCode ioTxCode = nonNull(pro) ? pro : REQUEST_PARAM_ERROR;
                return new IoTxServiceException(ioTxCode);
            });
    }

    /**
     * 断言IoTxResult
     * <p>
     * ioTxResult.isSuccess()==false或null==ioTxResult.getData()时抛出封装{@link Constant#ASSERT_NO_DATA}的{@link
     * IoTxServiceException}
     *
     * @param ioTxResult ioTxResult
     * @param <T>        T
     * @return data
     */
    public static <T> T assertAndGet(IoTxResult<T> ioTxResult) {
        return assertAndGet(ioTxResult, null, Constant.ASSERT_NO_DATA, null);
    }

    /**
     * 断言结果并取数据，数据可为空
     *
     * @param ioTxResult 被断言的服务返回结果
     * @param <T>        DataType
     * @return Optional
     */
    public static <T> Optional<T> assertAndGetNullable(IoTxResult<T> ioTxResult) {
        assertIoTxResult(ioTxResult, null, null);
        return Optional.ofNullable(ioTxResult.getData());
    }

    /**
     * 断言结果并取数据，数据可为空
     *
     * @param ioTxResult  被断言的服务返回结果
     * @param failureCode 失败后的状态码
     * @param <T>         DataType
     * @return Optional
     */
    public static <T> Optional<T> assertAndGetNullable(IoTxResult<T> ioTxResult, IoTxCode failureCode) {
        assertIoTxResult(ioTxResult, failureCode, null);
        return Optional.ofNullable(ioTxResult.getData());
    }

    /**
     * 断言o1, o2相等。失败时抛出封装参数指定failureCode的{@link IoTxServiceException}
     *
     * @param o1          object1
     * @param o2          object2
     * @param failureCode 失败时的状态码
     */
    public static void assertEquals(@NonNull Object o1, Object o2, @NonNull IoTxCode failureCode) {
        assertTrue(o1.equals(o2), failureCode);
    }

    /**
     * 断言o1, o2相等。失败时抛出封装{@link Constant#PARAMS_IS_NOT_EQUALS}的{@link IoTxServiceException}
     *
     * @param o1 object1
     * @param o2 object2
     */
    public static void assertEquals(@NonNull Object o1, Object o2) {
        assertTrue(o1.equals(o2), Constant.PARAMS_IS_NOT_EQUALS.params(o1, o2));
    }

    /**
     * 断言结果，不断言数据。失败时抛出封装参数指定failureCode的{@link IoTxServiceException}
     *
     * @param ioTxResult  ioTxResult
     * @param failureCode 失败后的状态码, 为空时使用ioTxResult中的状态码
     * @param <T>         DataType
     */
    public static <T> void assertIoTxResult(IoTxResult<T> ioTxResult,
                                            IoTxCode failureCode) {
        assertIoTxResult(ioTxResult, failureCode, null);
    }

    /**
     * 断言结果，不断言数据。如果不成功，则调用exe回调函数，然后抛出异常。
     * <p>
     * 如果failureCode非空，用failureCode封装IoTxResult对象抛异常； 否则直接使用ioTxResult中的错误码抛异常；
     *
     * @param ioTxResult  ioTxResult
     * @param failureCode 失败后的状态码, 为空时使用ioTxResult中的状态码
     * @param exe         回调
     * @param <T>         DataType
     */
    public static <T> void assertIoTxResult(IoTxResult<T> ioTxResult,
                                            IoTxCode failureCode,
                                            Consumer<IoTxCode> exe) {
        assertNonNull(ioTxResult, nonNull(failureCode) ? failureCode : ASSERT_NO_IOT_RESULT);

        // 正常时返回
        if (ioTxResult.hasSucceeded()) {
            return;
        }

        failureCode = getCode(failureCode);

        IoTxCode code = wrapIoTxCode(ioTxResult, failureCode);

        // 错误时先执行回调
        try {
            if (nonNull(exe)) {
                exe.accept(code);
            }
        } catch (Throwable t) {
            LoggerUtil.logException(t);
        }

        throw new IoTxServiceException(code);
    }

    /**
     * IoTxResult断言方法，当ioTxResult.success()==false时，返回结果中自带的Code和Message
     *
     * @param ioTxResult result
     * @param <T>        T
     */
    public static <T> void assertIoTxResult(IoTxResult<T> ioTxResult) {
        assertIoTxResult(ioTxResult, null, null);
    }

    /**
     * 断言返回结果为true，否则抛错误
     *
     * @param ioTxResult  被断言的服务返回结果
     * @param failureCode 数据域错误时的错误码
     */
    public static void assertIoTxResultIsTrue(IoTxResult<Boolean> ioTxResult, IoTxCode failureCode) {
        assertIoTxResultIsTrue(ioTxResult, failureCode, null);
    }

    /**
     * 断言返回结果为true，否则抛错误
     *
     * @param ioTxResult  被断言的服务返回结果
     * @param failureCode 数据域错误时的错误码
     * @param consumer    断言失败时的回调
     */
    public static void assertIoTxResultIsTrue(IoTxResult<Boolean> ioTxResult,
                                              IoTxCode failureCode,
                                              Consumer<IoTxCode> consumer) {
        Boolean aBoolean = assertAndGet(ioTxResult, null, failureCode, consumer);
        if (!aBoolean && nonNull(consumer)) {
            consumer.accept(getCode(failureCode));
        }
        assertTrue(aBoolean);
    }

    /**
     * 断言返回结果为true，否则抛错误
     *
     * @param ioTxResult 被断言的服务返回结果
     */
    public static void assertIoTxResultIsTrue(IoTxResult<Boolean> ioTxResult) {
        assertIoTxResultIsTrue(ioTxResult, null, null);
    }

    /**
     * 断言返回结果，默认抛500
     *
     * @param ioTxResult 被断言的服务返回结果
     */
    public static <T> void assertIoTxResultNotEmpty(IoTxResult<List<T>> ioTxResult) {
        assertIoTxResultNotEmpty(ioTxResult, null);
    }

    /**
     * 针对结果是IoTxResult<Collection>的数据，检查返回是否成功
     *
     * @param ioTxResult      被断言的服务返回结果
     * @param invalidDataCode 数据域错误时使用的错误码
     */
    public static <T> void assertIoTxResultNotEmpty(IoTxResult<List<T>> ioTxResult,
                                                    IoTxCode invalidDataCode) {
        assertIoTxResultNotEmpty(ioTxResult, invalidDataCode, null);
    }

    /**
     * 针对结果是IoTxResult<Collection>的数据，检查返回是否成功
     *
     * @param ioTxResult  被断言的服务返回结果
     * @param failureCode 失败错误代码
     * @param consumer    失败回调
     */
    public static <T> void assertIoTxResultNotEmpty(IoTxResult<List<T>> ioTxResult,
                                                    IoTxCode failureCode,
                                                    Consumer<IoTxCode> consumer) {
        List<T> list = assertAndGet(ioTxResult, null, failureCode, consumer);
        assertTrue(!list.isEmpty(), failureCode);
    }

    /**
     * 断言参数非空，失败时抛出封装{@link Constant#PARAM_NPE}的{@link IoTxServiceException}
     *
     * @param object 断言参数
     */
    public static void assertNonNull(Object object) {
        assertNonNull(object, Constant.PARAM_NPE);
    }

    /**
     * 业务断言方法，验证对象非空
     *
     * @param object      断言对象
     * @param failureCode 失败时的错误码
     */
    public static void assertNonNull(Object object, @NonNull IoTxCode failureCode) {
        assertTrue(nonNull(object), failureCode);
    }

    /**
     * 业务断言方法，验证字符串不空。失败时抛出封装{@link Constant#STRING_IS_BLANK}的{@link IoTxServiceException}
     *
     * @param string 字符串
     */
    public static void assertNotBlank(String string) {
        assertNotBlank(string, Constant.STRING_IS_BLANK);
    }

    /**
     * 业务断言方法，验证字符串不空。失败时抛出封装参数指定failureCode的{@link IoTxServiceException}
     *
     * @param string      字符串
     * @param failureCode 失败时的错误码
     */
    public static void assertNotBlank(String string, @NonNull IoTxCode failureCode) {
        assertTrue(!Strings.isNullOrEmpty(string), failureCode);
    }

    /**
     * 业务断言方法，验证不是空容器。失败时抛出封装{@link Constant#COLLECTION_IS_EMPTY}的{@link IoTxServiceException}
     *
     * @param collection 容器
     */
    public static void assertNotEmpty(Collection collection) {
        assertNotEmpty(collection, Constant.COLLECTION_IS_EMPTY.params(collection.getClass().getName()));
    }

    /**
     * 业务断言方法，验证不是空容器。失败时抛出封装参数指定failureCode的{@link IoTxServiceException}
     *
     * @param collection  容器
     * @param failureCode 失败时的错误码
     */
    public static void assertNotEmpty(Collection collection, @NonNull IoTxCode failureCode) {
        assertTrue(nonNull(collection), failureCode);
        assertTrue(!collection.isEmpty(), failureCode);
    }

    /**
     * 断言o1, o2不相等。失败时抛出封装参数指定failureCode的{@link IoTxServiceException}
     *
     * @param o1 object1
     * @param o2 object2
     */
    public static void assertNotEquals(@NonNull Object o1, Object o2, @NonNull IoTxCode failureCode) {
        assertTrue(!o1.equals(o2), failureCode);
    }

    /**
     * 断言o1, o2不相等。失败时抛出封装{@link Constant#PARAMS_IS_EQUALS}的{@link IoTxServiceException}
     *
     * @param o1 object1
     * @param o2 object2
     */
    public static void assertNotEquals(@NonNull Object o1, Object o2) {
        assertTrue(!o1.equals(o2), Constant.PARAMS_IS_EQUALS.params(o1, o2));
    }

    /**
     * 断言参数为值，失败时抛出封装{@link Constant#CONDITION_IS_FALSE}的{@link IoTxServiceException}
     *
     * @param result 布尔型参数
     */
    public static void assertTrue(boolean result) {
        assertTrue(result, getCode(Constant.CONDITION_IS_FALSE));
    }

    /**
     * 业务断言方法，验证布尔型结果
     * <p>
     * 失败时抛出封装failureCode的{@link IoTxServiceException}
     *
     * @param result      Boolean
     * @param failureCode 失败后的状态码, 为空时使用ioTxResult中的状态码
     */
    public static void assertTrue(boolean result, @NonNull IoTxCode failureCode) {
        if (!result) {
            throw new IoTxServiceException(new IoTxResult(getCode(failureCode)));
        }
    }

    /**
     * 断言参数为假，失败时抛出封装{@link Constant#CONDITION_IS_FALSE}的{@link IoTxServiceException}
     *
     * @param result 布尔型参数
     */
    public static void assertFalse(boolean result) {
        assertTrue(!result, getCode(Constant.CONDITION_IS_FALSE));
    }

    /**
     * 业务断言方法，验证布尔型结果
     * <p>
     * 失败时抛出封装failureCode的{@link IoTxServiceException}
     *
     * @param result      Boolean
     * @param failureCode 失败后的状态码, 为空时使用ioTxResult中的状态码
     */
    public static void assertFalse(boolean result, @NonNull IoTxCode failureCode) {
        if (result) {
            throw new IoTxServiceException(new IoTxResult(getCode(failureCode)));
        }
    }

    /**
     * 业务断言方法，验证参数的类型
     *
     * @param value       变量
     * @param cls         类型
     * @param failureCode 验证失败时返回的错误
     * @param <T>         T
     * @see #assertType(Object, Class)
     */
    public static <T> void assertType(Object value, Class<T> cls, IoTxCode failureCode) {
        assertNonNull(value, failureCode);
        assertNonNull(cls, ASSERT_NO_TYPE);
        assertTrue(value.getClass().equals(cls), failureCode);
    }

    /**
     * 业务断言方法，验证参数的类型
     *
     * @param value 变量
     * @param cls   类型
     * @param <T>   T
     * @see #assertType(Object, Class, IoTxCode)
     */
    public static <T> void assertType(Object value, Class<T> cls) {
        assertType(value, cls, ASSERT_NOT_EXPECT_TYPE);
    }

    /**
     * 业务断言方法，验证参数的类型，并返回转换后的类型值
     *
     * @param value 变量
     * @param cls   类型
     * @param <T>   T
     * @return T
     * @see #assertTypeAndConvert(Object, Class)
     */
    public static <T> T assertTypeAndConvert(Object value, Class<T> cls, IoTxCode failureCode) {
        assertType(value, cls, failureCode);
        //noinspection unchecked
        return (T)value;
    }

    /**
     * 业务断言方法，验证参数的类型，并返回转换后的类型值
     *
     * @param value 变量
     * @param cls   类型
     * @param <T>   T
     * @return T
     * @see #assertTypeAndConvert(Object, Class, IoTxCode)
     */
    public static <T> T assertTypeAndConvert(Object value, Class<T> cls) {
        return assertTypeAndConvert(value, cls, ASSERT_NOT_EXPECT_TYPE);
    }

    /**
     * {@link IoTxCodePro}的转换方法
     * <p>
     * <p>
     * 如果类型是{@link IoTxCodePro}则调用{@link IoTxCodePro#build()}方法生成{@link IoTxCode}
     * <p>
     * 如果类型是{@link IoTxCode}则直接返回
     *
     * @param code {@link IoTxCode}及其子类
     * @return {@link IoTxCode}
     */
    static IoTxCode getCode(IoTxCode code) {
        if (nonNull(code) && code instanceof IoTxCodePro) {
            return ((IoTxCodePro)code).build();
        } else {
            return code;
        }
    }

    /**
     * 把原始消息封装成包装消息并返回
     *
     * @param originCode 原始消息
     * @param wrapCode   包装消息
     * @param <T>        T
     * @return IoTxCode
     */
    private static <T> IoTxCode wrapIoTxCode(@NonNull IoTxResult<T> originCode, IoTxCode wrapCode) {
        if (isNull(wrapCode)) {
            return new IoTxCode(originCode.getCode(), originCode.getMessage(), originCode.getLocalizedMsg());
        }
        final int code = originCode.getCode();
        final String msg = mergeMsg(code, originCode.getMessage(), wrapCode.getMessage());
        final String localizedMsg = mergeMsg(code, originCode.getLocalizedMsg(), wrapCode.getLocalizedMsg());
        return new IoTxCodePro(wrapCode.getCode(), msg, localizedMsg, null);
    }

    /**
     * 合并消息
     * <p>
     * 把原始的错误码和错误消息（通常是从基础PaaS返回的）包装到新消息中
     * <p>
     * 格式：wrapMsg(originMsg:code）
     *
     * @param code      原始错误码
     * @param originMsg 原始错误消息
     * @param wrapMsg   包装消息
     * @return 包装后的消息
     */
    private static String mergeMsg(int code, String originMsg, String wrapMsg) {
        if (Strings.isNullOrEmpty(originMsg)) {
            return wrapMsg;
        } else if (Strings.isNullOrEmpty(wrapMsg)) {
            return originMsg;
        }

        final String ps;
        final String ms;
        int idx = wrapMsg.indexOf('|');
        if (-1 < idx) {
            ms = wrapMsg.substring(0, idx - 1);
            ps = wrapMsg.substring(idx);
        } else {
            ms = wrapMsg;
            ps = "";
        }

        return String.format("%s(%s:%s)%s", ms, originMsg, code, ps);
    }
}
