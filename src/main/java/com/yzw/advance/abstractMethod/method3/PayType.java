package com.yzw.advance.abstractMethod.method3;

public enum PayType {

    ALI,WECHAT,CREDIT_CARD;

    PayType() {
    }

    AbstractPay getPay(){
        AbstractPay pay;
        switch (this){
            case ALI:
                pay = new AliPayImpl();
                break;
            case WECHAT:
                pay = new WeChatPayImpl();
                break;
            case CREDIT_CARD:
                pay = new CreditCardPayImpl();
                break;
            default:
                throw new IllegalStateException("暂不支持该类型支付：" + this);
        }
        return pay;
    }
}
