package com.gw.container;

import com.gw.container.common.ActionMapUtil;
import com.gw.container.common.FilterHelper;
import com.gw.container.model.BaseResponse;
import org.springframework.context.ApplicationContext;

public class ServiceContainer {
    public static BaseResponse service(SCContext scContext, ApplicationContext context) throws Exception {
        Object obj = context.getBean(scContext.getServiceName());
        if (obj != null) {
            //action
            //filter
            if (FilterHelper.getBeforeFilters() != null) {
                FilterHelper.getBeforeFilters().forEach(f -> f.getData().process(scContext));
            }

            scContext.getRequestObj();

            Object objResult = ActionMapUtil.invoke(scContext.getServiceName()
                    , scContext.getActionName()
                    , scContext.getRequestData()
                    , scContext.getHeader());

            if (objResult instanceof BaseResponse) {
                scContext.setResponse((BaseResponse) objResult);
            } else {
                scContext.setResponse(new BaseResponse<>(objResult));
            }

            //filter
            if (FilterHelper.getAfterFilters() != null) {
                FilterHelper.getAfterFilters().forEach(f -> f.getData().process(scContext));
            }
            return scContext.getResponse();
        }
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setMessage("失败");
        return response;
    }
}
