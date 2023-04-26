package com.yzw.advance.adapter.method2;

public class PayAdapter implements Payment{

    private AliPay aliPay;
    private WeChatPay weChatPay;
    private CreditCardPay creditCardPay;
    private String payType;

    public PayAdapter(String payType) {
        this.payType = payType;

        switch (payType){
            case "ali":
                aliPay = new AliPay();
                break;
            case "wechat":
                weChatPay = new WeChatPay();
                break;
            case "creditCard":
                creditCardPay = new CreditCardPay();
                break;
            default:
                System.out.println("不支持该类型支付");
                break;
        }

    }


    @Override
    public void pay(double money) {
        switch (payType){
            case "ali":
                aliPay.aliPay(money);
                break;
            case "wechat":
                weChatPay.weChatPay(money);
                break;
            case "creditCard":
                creditCardPay.creditCardPay(money);
                break;
            default:
                System.out.println("不支持该类型支付");
                break;
        }
    }
}
