package com.gw.starter;

public abstract class BaseStarter {
    public void process() {
        try {
            _process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void _process();
}
