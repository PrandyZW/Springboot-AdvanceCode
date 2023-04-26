package com.yzw.advance.abstractMethod.method2;

public interface ConnectionOperator {

    /**
     * 操作初始化
     *
     * @throws Exception 返回通用的错误
     */
    void before() throws Exception;

    /**
     * 操作释放资源
     *
     * @throws Exception
     */
    void after() throws Exception;

    /**
     * 操作可用性测试
     *
     * @throws Exception
     */
    boolean test();



}
