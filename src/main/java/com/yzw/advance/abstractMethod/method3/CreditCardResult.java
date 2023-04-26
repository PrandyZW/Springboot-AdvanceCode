package com.yzw.advance.abstractMethod.method3;

public class CreditCardResult {

    public CreditCardResult(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
