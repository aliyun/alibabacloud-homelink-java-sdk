/**
 * Copyright (c) 2019 Alibaba Group Holding Limited
 */
package com.aliyun.iotx.api.util.entity;

import com.aliyun.iotx.api.util.assertion.IoTxAssertions;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

import java.util.Objects;

import static java.util.Objects.isNull;


/**
 * {@link IoTxCode}增强版，支持动态参数。扩展了{@link IoTxCode}
 * ，会影响{@link IoTxAssertions}的处理行为，需要注意。
 * <p>
 * TODO 增加一个paramsSupplier方法，在需要的时候调用生成消息参数，避免运行时生成参数的消耗
 *
 * @author alibaba
 * @date 2018/11/4
 * @see IoTxAssertions
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class IoTxCodePro extends IoTxCode {

    private static final long serialVersionUID = 4811301151932591006L;

    /**
     * 动态参数
     */
    private Object[] params;

    /**
     * 消息模板
     */
    private String messageTpl;

    /**
     * 本地化消息模板
     */
    private String localizedMsgTpl;

    /**
     * 默认构造
     */
    public IoTxCodePro() {
        this(200, "success", null, null);

    }

    /**
     * 构造
     *
     * @param code    错误码
     * @param message 消息（模板）
     */
    public IoTxCodePro(int code, String message) {
        this(code, message, null, null);

    }

    /**
     * 构造
     *
     * @param code         错误码
     * @param message      消息（模板）
     * @param localizedMsg 本地化消息（模板）
     */
    public IoTxCodePro(int code, String message, String localizedMsg) {
        this(code, message, localizedMsg, null);
    }

    /**
     * 构造
     *
     * @param code         错误码
     * @param message      消息（模板）
     * @param localizedMsg 本地化消息（模板）
     * @param params       动态参数
     */
    public IoTxCodePro(int code, String message, String localizedMsg, Object[] params) {
        super(code, message, localizedMsg);
        this.params = params;
    }

    /**
     * 生成一个IoTxCode，不包含消息模板，所有消息完成参数化
     *
     * @return 的IoTxCode
     */
    public IoTxCode build() {
        return new IoTxCode(this.getCode(),
            getParameterizationMessage(),
            getParameterizationLocalizedMsg());
    }

    /**
     * 配置参数化消息
     */
    public IoTxCodePro message(String message) {
        return message(message, null);
    }

    /**
     * 配置参数化消息
     */
    public IoTxCodePro message(String messageTpl, String localizedMsgTpl) {
        return params(messageTpl, localizedMsgTpl, this.params);
    }

    /**
     * 链式API，动态配置参数
     *
     * @param messageArgs 消息参数
     * @return IoTxCodePro
     */
    public IoTxCodePro params(Object... messageArgs) {
        return params(this.messageTpl, this.localizedMsgTpl, messageArgs);
    }

    /**
     * 用当前的Code创建IoTxResult
     *
     * @param <T> Data类型
     * @return IoTxResult
     */
    public <T> IoTxResult<T> toIoTxResult() {
        return new IoTxResult<>(getCode(), getParameterizationMessage(), getParameterizationLocalizedMsg());
    }

    private String getParameterizationMessage() {
        return getMessageInternal(getMessage(), messageTpl, params);

    }

    private String getParameterizationLocalizedMsg() {
        return getMessageInternal(getLocalizedMsg(), localizedMsgTpl, params);
    }

    /**
     * 优先使用模板创建参数化消息，如果模板为空则使用原消息
     *
     * @param msg    原消息
     * @param msgTpl 参数化消息模板
     * @param params 参数
     * @return msg
     */
    private static String getMessageInternal(String msg, String msgTpl, Object[] params) {
        msg = Objects.nonNull(msgTpl) ? msgTpl : msg;
        if (isNull(msg)) {
            return null;
        }

        if (Objects.nonNull(params)) {
            return MessageFormatter.arrayFormat(msg, params).getMessage();
        } else {
            return msg;
        }
    }

    /**
     * 动态配置的内部实现
     * <p>
     * 思路是用当前配置生成新的IoTxCodePro实例，再恢复当前类的原值，以保证当前类不变。
     */
    private IoTxCodePro params(String messageTpl, String localizedMsgTpl, Object... messageArgs) {
        Object[] oldParams = this.params;
        String oldMsgTpl = this.messageTpl;
        String oldLocalizedMsgTpl = this.localizedMsgTpl;

        this.params = messageArgs;
        this.messageTpl = messageTpl;
        this.localizedMsgTpl = localizedMsgTpl;

        // 创建新对象的原因是不改变原对象值
        IoTxCodePro newPro = new IoTxCodePro(
            this.getCode(),
            this.getParameterizationMessage(),
            this.getParameterizationLocalizedMsg(),
            messageArgs);
        newPro.messageTpl = messageTpl;
        newPro.localizedMsgTpl = localizedMsgTpl;

        // 保证不改变原对象值，正常应该为null
        this.params = oldParams;
        this.messageTpl = oldMsgTpl;
        this.localizedMsgTpl = oldLocalizedMsgTpl;

        return newPro;
    }
}
