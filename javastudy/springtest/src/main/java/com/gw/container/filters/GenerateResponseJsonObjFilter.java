package com.gw.container.filters;

import com.alibaba.fastjson.JSON;
import com.gw.annotations.BeforeFilter;
import com.gw.container.common.BaseFilter;

@BeforeFilter
public class GenerateResponseJsonObjFilter extends BaseFilter {
    @Override
    public void process() {
        getScContext().setResponseObj(JSON.parseObject(JSON.toJSONString(getScContext().getResponse())));
    }
}
