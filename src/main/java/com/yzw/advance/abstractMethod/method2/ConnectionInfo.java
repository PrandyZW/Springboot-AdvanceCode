package com.yzw.advance.abstractMethod.method2;

import java.util.Optional;

public abstract class ConnectionInfo implements IConnectionInfo {
    private String connectionUrl;
    private String version;
    private String type;
    private String databaseName;
    private ConnectionOperator connectionOperator;

    @Override
    public String getConnectionUrl() {
        return connectionUrl;
    }

    @Override
    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * 创建一个操作对象
     *
     * @return
     */
    protected abstract Optional<ConnectionOperator> createOperator();

    /**
     * 初始化连接操作对象
     */
    protected void initConnectionOperator() throws Exception {
        Optional<ConnectionOperator> operatorOptional = createOperator();
        if (!operatorOptional.isPresent()) {
            throw new Exception(String.format("%s 未实现createOperator方法", this.getClass()));
        }
        connectionOperator = operatorOptional.get();
        connectionOperator.before();
    }

    /**
     * 结束连接操作对象
     */
    public void finishConnectionOperator() throws Exception {
        if (connectionOperator != null) {
            connectionOperator.after();
            connectionOperator = null;
        }
    }

    /**
     * 执行操作
     * <p>默认【会】结束掉连接操作对象</p>
     *
     * @param operatorCallback
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T executeOperation(OperatorCallback<T> operatorCallback) throws Exception {
        try {
            if (connectionOperator == null) {
                initConnectionOperator();
            }
            return operatorCallback.doInOperation(connectionOperator);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        } finally {
            finishConnectionOperator();
        }
    }

    public static abstract class withUsernameAndPassword extends ConnectionInfo implements IUsernameAndPassword {
        private String username;
        private String password;

        @Override
        public String getUsername() {
            return this.username;
        }

        @Override
        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public void setPassword(String password) {
            this.password = password;
        }
    }


    public static class MySql extends withUsernameAndPassword {

        @Override
        protected Optional<ConnectionOperator> createOperator() {
            return Optional.of(new MySqlConnectionOperator(this));
        }

        @Override
        public void validate() throws Exception {

        }
    }
}
