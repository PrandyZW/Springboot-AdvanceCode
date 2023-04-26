package com.yzw.advance.abstractMethod.method3;

import com.yzw.advance.util.JackJsonUtil;

public class CreditCardPayImpl extends AbstractPay{
    @Override
    protected void payBefore() {
        System.out.println("获取信用卡支付地址");
    }

    @Override
    protected void payAfter() {
        System.out.println("关闭信用卡支付，返回执行信息");
    }

    @Override
    public CreditCardResult doPay(double money) {
        CreditCardResult creditCardResult = new CreditCardResult("信用卡支付成功:"+money,200);
        System.out.println(JackJsonUtil.toJson(creditCardResult));
        return creditCardResult;
    }
}
