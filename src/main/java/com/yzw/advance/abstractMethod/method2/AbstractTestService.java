package com.yzw.advance.abstractMethod.method2;

import org.springframework.stereotype.Service;

@Service
public class AbstractTestService {

    public void test(String type,String detail){
        try {
            DataSourceType dataSourceType = DataSourceType.ofValue(type);
            ConnectionInfo connectionInfo = dataSourceType.getConnectionInfo(detail);
            connectionInfo.executeOperation(ConnectionOperator::test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
