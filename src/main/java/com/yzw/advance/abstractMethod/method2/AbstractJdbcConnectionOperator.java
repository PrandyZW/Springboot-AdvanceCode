package com.yzw.advance.abstractMethod.method2;

import java.sql.Connection;
import java.util.Objects;

public abstract class AbstractJdbcConnectionOperator <C extends IUsernameAndPassword> implements ConnectionOperator{
    protected final C connectionInfo;
    protected Connection connection;

    public AbstractJdbcConnectionOperator(C connectionInfo) {
        this.connectionInfo = Objects.requireNonNull(connectionInfo);
    }

    @Override
    public void before() throws Exception {
        //初始化链接对象

    }

    @Override
    public void after() throws Exception {
        //关闭连接对象
    }

    @Override
    public boolean test() {
        //测试方法
        return false;
    }
}
