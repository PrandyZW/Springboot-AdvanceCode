package com.yzw.advance.abstractMethod.method2;

public class MySqlConnectionOperator extends AbstractJdbcConnectionOperator<ConnectionInfo.MySql> implements ConnectionOperator{

    public MySqlConnectionOperator(ConnectionInfo.MySql connectionInfo) {
        super(connectionInfo);
    }


}
