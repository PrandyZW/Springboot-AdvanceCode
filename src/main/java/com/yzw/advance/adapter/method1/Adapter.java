package com.yzw.advance.adapter.method1;

public class Adapter implements TargetInterface{
    private AdapterInterface adapterInterface;

    public Adapter(AdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }

    @Override
    public void request() {
        adapterInterface.specificRequest();
    }
}
