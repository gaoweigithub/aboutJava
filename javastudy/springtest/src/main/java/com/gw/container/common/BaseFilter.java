package com.gw.container.common;

import com.gw.container.SCContext;

public abstract class BaseFilter {
    private int sort;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    private SCContext scContext;

    public SCContext getScContext() {
        return scContext;
    }

    public void setScContext(SCContext scContext) {
        this.scContext = scContext;
    }

    public void process(SCContext scContext){
        setScContext(scContext);
        _process();
    }
    public abstract void _process();
}
