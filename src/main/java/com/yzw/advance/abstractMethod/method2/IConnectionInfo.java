package com.yzw.advance.abstractMethod.method2;

public interface IConnectionInfo {

    String getConnectionUrl();

    void setConnectionUrl(String connectionUrl);

    String getVersion();

    void setVersion(String version);

    String getType();

    void setType(String type);

    String getDatabaseName();

    void setDatabaseName(String databaseName);

    void validate() throws Exception;
}
