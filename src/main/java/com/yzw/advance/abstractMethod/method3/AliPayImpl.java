package com.yzw.advance.abstractMethod.method3;

import com.yzw.advance.util.JackJsonUtil;

public class AliPayImpl extends AbstractPay{
    @Override
    protected void payBefore() {
        System.out.println("获取支付宝支付地址");
    }

    @Override
    protected void payAfter() {
        System.out.println("关闭支付宝支付，返回执行信息");
    }

    @Override
    public AliPayResult doPay(double money) {
        AliPayResult aliPayResult = new AliPayResult("支付宝支付成功:"+money,200);
        System.out.println(JackJsonUtil.toJson(aliPayResult));
        return aliPayResult;
    }
}
