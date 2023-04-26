package com.yzw.advance.adapter.method1;

public class AdapterService {
    public static void main(String[] args) {
        // 创建一个 AdapterInterface 接口的实现类对象
        AdapterInterface adapter = new AdapterImpl();
        // 创建一个适配器对象，将 AdapterInterface 接口转换成 TargetInterface 接口
        TargetInterface adaptor = new Adapter(adapter);
        // 调用适配器对象的 request() 方法，实际上会调用 AdapterInterface 接口的 specificRequest() 方法
        adaptor.request();
    }
}
