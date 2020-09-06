package com.huiaong.common.client.fallback;

import com.google.common.base.Throwables;
import com.huiaong.common.client.UserClient;
import com.huiaong.common.dto.LoginUser;
import com.huiaong.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserClientFallback implements UserClient {

    @Autowired
    private Throwable cause;

    @Override
    public Response<LoginUser> info(String token) {
        log.error("/user/info fallback, cause:{}", Throwables.getStackTraceAsString(cause));
        return Response.fail("user client fallback");
    }

    @Override
    public Response<Boolean> refreshToken() {
        log.error("/user/refreshToken fallback, cause:{}", Throwables.getStackTraceAsString(cause));
        return Response.fail("user client fallback");
    }
}
