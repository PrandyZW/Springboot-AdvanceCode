package com.yzw.advance.abstractMethod.method3;

/**
 * 利用模板模式设计了支付前，支付后，支付中的方法
 */
public abstract class AbstractPay implements Pay{

    //支付前的动作
    protected abstract void payBefore();

    //支付后的动作
    protected abstract void payAfter();

    /**
     * 支付功能
     * @param money
     * @return <T>
     */
    public <T> T pay(double money){
        payBefore();
        try {
            return doPay(money);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            payAfter();
        }
        return null;
    }
}
