package com.yzw.advance.abstractMethod.method3;

import com.yzw.advance.util.JackJsonUtil;

public class WeChatPayImpl extends AbstractPay{
    @Override
    protected void payBefore() {
        System.out.println("获取微信支付地址");
    }

    @Override
    protected void payAfter() {
        System.out.println("关闭微信支付，返回执行信息");
    }

    @Override
    public WeChatPayResult doPay(double money) {
        WeChatPayResult weChatPayResult = new WeChatPayResult("微信支付成功:"+money,200);
        System.out.println(JackJsonUtil.toJson(weChatPayResult));
        return weChatPayResult;
    }
}
