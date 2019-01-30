package com.gw.container;

import com.gw.container.common.ActionMapUtil;
import com.gw.container.model.BaseResponse;
import com.gw.container.starter.FilterHelperV2;
import org.springframework.context.ApplicationContext;

public class ServiceContainer {
    public static BaseResponse service(SCContext scContext, ApplicationContext context) throws Exception {
        Object obj = context.getBean(scContext.getServiceName());
        if (obj != null) {
            //action
            //filter
            if (FilterHelperV2.getBeforeFilters() != null) {
//                FilterHelperV2.getBeforeFilters().forEach(f -> f.getData().process(scContext));
                FilterHelperV2.getBeforeFilters().entrySet().forEach(f -> f.getValue().process(scContext));
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
            if (FilterHelperV2.getAfterFilters() != null) {
//                FilterHelperV2.getAfterFilters().forEach(f -> f.getData().process(scContext));
                FilterHelperV2.getAfterFilters().entrySet().forEach(f -> f.getValue().process(scContext));
            }
            return scContext.getResponse();
        }
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setMessage("失败");
        return response;
    }
}
