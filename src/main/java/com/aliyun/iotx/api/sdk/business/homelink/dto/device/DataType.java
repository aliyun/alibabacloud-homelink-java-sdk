package com.aliyun.iotx.api.sdk.business.homelink.dto.device;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jingwei
 */

public enum DataType {
    /**
     * 未知字段
     */
    UNKNOWN(99, "unknown"),
    /**
     * 枚举型
     */
    ENUM(2, "enum"),
    /**
     * 布尔型
     */
    BOOL(3, "bool"),
    /**
     * 浮点型
     */
    FLOAT(1, "float"),
    /**
     * 整数型
     */
    INT(0, "int"),

    /**
     * 字符型
     */
    TEXT(4, "text"),
    /**
     * 时间型
     */
    DATE(5, "date"),
    /**
     * 对象型
     */
    STRUCT(6, "struct"),
    /**
     * 数组型
     */
    ARRAY(7, "array"),
    /**
     * 双精度浮点
     */
    DOUBLE(8,"double");

    private Integer code;
    private String name;

    DataType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean contains(String name) {
        boolean have = false;
        for (DataType val : DataType.values()) {
            if (val.getName().equals(name)) {
                have = true;
                break;
            }
        }
        return have;
    }

    public static DataType valueOf(Integer value) {
        for (DataType type : values()) {
            if (type.getCode().equals(value)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    private static DataType valueof(String name) {
        for (DataType type : values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static DataType fromStringLowerCase(String name) {
        for (DataType type : values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static boolean isIn(String type, String... collections) {
        if (!contains(type)) { return false; }
        DataType dataType = valueof(type);
        Set<DataType> dataTypeList = new HashSet<>();
        for (String item : collections) {
            if (!contains(item)) { continue; }
            dataTypeList.add(valueof(item));
        }
        return dataTypeList.contains(dataType);
    }
}
