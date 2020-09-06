package com.huiaong.common.client.interceptor;

import com.huiaong.common.utils.UserUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class ClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-token", UserUtil.getToken());
    }
}
