package com.aliyun.iotx.api.util.exception;

import com.aliyun.iotx.api.util.entity.IoTxCode;
import com.aliyun.iotx.api.util.entity.IoTxCodePro;
import com.aliyun.iotx.api.util.entity.IoTxResult;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


/**
 * IoTx业务异常定义，主要是包装了IoTxResult或IoTxCode参数，使得AOP可以捕获并返回异步的错误码和消息
 * <p>
 * 封装了IoTxResult，可以取出后返回
 *
 * @date 2018/10/11
 */
@SuppressWarnings("unused")
@Getter
public class IoTxServiceException extends RuntimeException {

    private static final long serialVersionUID = 9157990158576936893L;

    private IoTxResult result;

    /**
     * Constructs a new runtime exception with {@code null} as its detail message.  The cause is not initialized, and
     * may subsequently be initialized by a call to {@link #initCause}.
     */
    public IoTxServiceException(@NonNull IoTxResult result) {
        super(result.getMessage());
        this.result = result;
    }

    public IoTxServiceException(@NonNull IoTxCode code) {
        super(code.getMessage());
        setResult(code);
    }

    /**
     * Constructs a new runtime exception with the specified detail message. The cause is not initialized, and may
     * subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()}
     *                method.
     */
    public IoTxServiceException(String message, @NonNull IoTxResult result) {
        super(result.getMessage());
        this.result = result;
    }

    public IoTxServiceException(String message, @NonNull IoTxCode code) {
        super(code.getMessage());
        setResult(code);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and cause.  <p>Note that the detail message
     * associated with {@code cause} is <i>not</i> automatically incorporated in this runtime exception's detail
     * message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method).  (A
     *                <tt>null</tt>
     *                value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @since 1.4
     */
    public IoTxServiceException(String message, Throwable cause, @NonNull IoTxResult result) {
        super(result.getMessage(), cause);
        this.result = result;
    }

    public IoTxServiceException(String message, Throwable cause, @NonNull IoTxCode code) {
        super(message + "|" + code.getMessage(), cause);
        setResult(code);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a detail message of <tt>(cause==null ? null :
     * cause.toString())</tt> (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).  (A <tt>null</tt>
     *              value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @since 1.4
     */
    public IoTxServiceException(Throwable cause, @NonNull IoTxResult result) {
        super(result.getMessage(), cause);
        this.result = result;
    }

    /**
     * Constructs a new runtime exception with the specified detail message, cause, suppression enabled or disabled, and
     * writable stack trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted, and indicates that the cause is
     *                           nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     * @since 1.7
     */
    public IoTxServiceException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace,
                                @NonNull IoTxResult result) {
        super(result.getMessage(), cause, enableSuppression, writableStackTrace);
        this.result = result;
    }

    public void setResult(IoTxCode code) {
        if (code instanceof IoTxCodePro) {
            this.result = new IoTxResult(((IoTxCodePro)code).build());
        } else {
            this.result = new IoTxResult(code);
        }
    }

    @Override
    public String toString() {
        String msg = "IoTxServiceException{" + "code=" + result.getCode()
            + ", msg=" + result.getMessage() + ", localizedMsg=" + result.getLocalizedMsg() + '}';
        //return msg;
        return msg.replace("\r\n", "").replace("\n", "");
    }
}
