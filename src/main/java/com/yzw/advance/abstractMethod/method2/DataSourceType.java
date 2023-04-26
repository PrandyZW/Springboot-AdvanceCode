package com.yzw.advance.abstractMethod.method2;

import com.yzw.advance.util.JackJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public enum DataSourceType {
    MYSQL("mysql", "MySQL");

    private final String value;

    private final String title;

    DataSourceType(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
    public static DataSourceType ofValue(String type) throws Exception {
        Assert.hasText(type, "数据源类型不能为空");
        for (DataSourceType value : values()) {
            if (type.equals(value.getValue())) {
                return value;
            }
        }
        throw new Exception(String.format("数据源类型【%s】不支持", type));
    }

    public ConnectionInfo getConnectionInfo(String dataSourceDetails) throws Exception {
        if (StringUtils.isBlank(dataSourceDetails)) {
            throw new Exception("数据源配置信息不能为空");
        }

        ConnectionInfo connectionInfo = null;
        switch (this){
            case MYSQL:
                connectionInfo = JackJsonUtil.jsonToObject(dataSourceDetails, ConnectionInfo.MySql.class);
                break;
            default:
                break;
        }

        return connectionInfo;
    }


}
