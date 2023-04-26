package com.yzw.advance.adapter.method2;

public class PayService {

    public static void main(String[] args) {
        PayAdapter payAdapter = new PayAdapter("ali");
        payAdapter.pay(1);

        payAdapter = new PayAdapter("wechat");
        payAdapter.pay(2);

        payAdapter = new PayAdapter("creditCard");
        payAdapter.pay(3);
    }
}
