package com.aliyun.iotx.api.util.log;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


/**
 * 通用log工具，统一日志格式，便于监控、排查问题
 *
 * @date 2018/3/28
 */
@SuppressWarnings("WeakerAccess")
@Slf4j
public class LoggerUtil {

    public static final Logger ERROR_LOGGER = LoggerFactory.getLogger("error");

    private static final Logger expLogger = LoggerFactory.getLogger("exception-logger");

    /**
     * 缩进计数
     */
    private static ThreadLocal<AtomicLong> logIndent = ThreadLocal.withInitial(() -> new AtomicLong(0));

    /**
     * 调用顺序
     */
    private static ThreadLocal<AtomicLong> logSeq = ThreadLocal.withInitial(() -> new AtomicLong(0));

    /**
     * 增加日志缩进级别
     */
    public static void indent() {
        MDC.put("logIndent", String.valueOf(logIndent.get().incrementAndGet()));
        MDC.put("logSeq", String.valueOf(logSeq.get().incrementAndGet()));
    }

    /**
     * 返回当前序列
     */
    public static long getAndIncrementLogSeq() {
        long seq = logSeq.get().getAndIncrement();
        MDC.put("logSeq", String.valueOf(logSeq.get().get()));
        return seq;
    }

    /**
     * 生成日志的调用序号
     */
    public static long getLogSeq() {
        return logSeq.get().getAndIncrement();
    }

    /**
     * 更新日志的调用序号
     */
    public static void updateLogSeq(long seq) {
        MDC.put("logSeq", String.valueOf(seq));
    }

    /**
     * 减少日志缩进级别。有维护能力，当缩进为0时删除ThreadLocal。
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static void outdent() {
        long v = logIndent.get().decrementAndGet();
        MDC.put("logIndent", String.valueOf(v));
        if (0 >= v) {
            logIndent.remove();
            logSeq.remove();
        }
    }

    public static void logException(Throwable e) {
        if (Objects.isNull(e)) {
            return;
        }
        logException(e, null);
    }

    public static void logException(Throwable e, String moreInfos, Object... args) {
        if (Objects.isNull(e)) {
            return;
        }
        if (Objects.isNull(moreInfos)) {
            moreInfos = "";
        }

        String traceId = MDC.get("traceId");
        final String traceMsg;
        if (Strings.isNullOrEmpty(traceId)) {
            traceMsg = "";
        } else {
            traceMsg = "(traceId: " + traceId + ")";
        }

        if (nonNull(args) && 0 < args.length) {
            log.error(traceMsg + moreInfos + e.getMessage() + ". (Find More detail from error.log)", args);
            expLogger.error(e.getMessage(), args, e);
        } else {
            log.error(traceMsg + moreInfos + e.getMessage() + ". (Find More detail from error.log)");

            List<StackTraceElement> stackTraceElements = filterStack(Lists.newArrayList(e.getStackTrace()));
            e.setStackTrace(stackTraceElements.toArray(new StackTraceElement[0]));

            expLogger.error(e.getMessage() + traceMsg, e);
        }
    }

    /**
     * 过滤异常栈，避免生成异常消息过大，不容易查看。可以通过Diamond增加动态配置。
     *
     * @param ste 异常栈
     * @return 过滤后的栈
     */
    public static List<StackTraceElement> filterStack(List<StackTraceElement> ste) {
        return ste.stream()
            .filter(c -> !c.getClassName().startsWith("org.springframework"))
            .filter(c -> !c.getClassName().startsWith("com.sun.proxy"))
            .filter(c -> !c.getClassName().startsWith("sun.reflect"))
            .filter(c -> !c.getClassName().startsWith("java.lang.reflect"))
            .filter(c -> !c.getClassName().startsWith("com.sun.proxy"))
            .filter(c -> !c.getClassName().startsWith("com.taobao.hsf"))
            .filter(c -> !c.getClassName().startsWith("com.aliyun.iotx.digital.community.Log"))
            .filter(c -> !c.getClassName().startsWith("com.alibaba.fastvalidator.core.spring"))
            .filter(c -> !c.getClassName().startsWith("com.taobao.csp.sentinel.entrypoint"))
            .filter(c -> !c.getClassName().contains("FastClassBySpringCGLIB"))
            .filter(c -> !c.getClassName().contains("EnhancerBySpringCGLIB"))
            .collect(Collectors.toList());
    }

    /**
     * 把异常信息生成一行文本，用于日志记录
     *
     * @param throwable t
     * @return text in line
     */
    public static String createStacksTextInLine(Throwable throwable) {
        if (isNull(throwable)) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder(throwable.toString());
        StackTraceElement[] ste = throwable.getStackTrace();

        // 下面逻辑把IoTxResultUtil的assert方法跳过
        int i = 0;
        for (; i < ste.length; i++) {
            if (!"com.aliyun.iotx.digital.community.common.util.IoTxResultUtil".equals(ste[i].getClassName())) {
                break;
            }
        }
        final List<StackTraceElement> stackTraceElements = (0 == i)
            ? Lists.newArrayList(ste)
            : Lists.newArrayList(ste).subList(i, ste.length - 1);

        List<StackTraceElement> stacks = filterStack(stackTraceElements);

        stringBuilder.append(". ");
        for (StackTraceElement st : stacks) {
            stringBuilder.append(st.getClassName()).append(".").append(st.getMethodName());
            stringBuilder.append("(").append(st.getFileName());
            stringBuilder.append(":").append(st.getLineNumber());
            stringBuilder.append(")|");
        }
        return stringBuilder.toString();
    }
}
