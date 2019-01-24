package com.gw.container.filters;

import com.alibaba.fastjson.JSON;
import com.gw.annotations.BeforeFilter;
import com.gw.container.SCContext;
import com.gw.container.common.BaseFilter;

@BeforeFilter
public class GenerateRequestJsonObjFilter extends BaseFilter {
    @Override
    public void _process() {
        getScContext().setRequestObj(JSON.parseObject(JSON.toJSONString(getScContext().getRequest())));
    }
}
