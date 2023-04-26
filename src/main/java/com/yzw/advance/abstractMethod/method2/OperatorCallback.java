package com.yzw.advance.abstractMethod.method2;

import org.springframework.lang.Nullable;

public interface OperatorCallback<T>{
    @Nullable
    T doInOperation(ConnectionOperator operations) throws Exception;
}
