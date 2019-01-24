package com.gw.container;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gw.common.ActionMapUtil;
import com.gw.container.common.FilterHelper;
import com.gw.container.model.BaseResponse;
import org.springframework.context.ApplicationContext;

public class ServiceContainer {
    private SCContext scContext;

    public SCContext getScContext(SCContext scContext) {
        return scContext;
    }

    public BaseResponse service(ApplicationContext context) throws Exception {
        Object obj = context.getBean(scContext.getServiceName());
        if (obj != null) {
            //action
            //filter
            FilterHelper.getBeforeFilters().forEach(f -> {
                try {
                    f.getData().process(scContext);
                } catch (Exception e) {
                    System.out.println("Error Filter + " + f.getClass().getName());
                }
            });

            Object objResult = ActionMapUtil.invoke(scContext.getServiceName(), scContext.getActionName(), scContext.getRequest());
            if (objResult instanceof BaseResponse) {
                scContext.setResponse((BaseResponse) objResult);
            } else {
                scContext.setResponse(new BaseResponse<JSONObject>(JSON.parseObject(JSON.toJSONString(objResult))));
            }
            //filter
            FilterHelper.getAfterFilters().forEach(f -> {
                try {
                    f.getData().process(scContext);
                } catch (Exception e) {
                    System.out.println("Error Filter + " + f.getClass().getName());
                }
            });
            return scContext.getResponse();
        }
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setMessage("失败");
        return response;
    }


    public ServiceContainer(SCContext scContext) {
        this.scContext = scContext;
    }
}
