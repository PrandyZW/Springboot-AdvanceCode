package com.yzw.advance.abstractMethod.method3;

public class PayService {

    public static void main(String[] args) {
        String type = "WECHAT";
        PayType payType = PayType.valueOf(type);
        AbstractPay pay = payType.getPay();
        pay.pay(1);
    }
}
