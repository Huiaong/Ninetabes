package com.huiaong.common.interception;

import com.huiaong.common.client.UserClient;
import com.huiaong.common.dto.LoginUser;
import com.huiaong.common.response.Response;
import com.huiaong.common.utils.UserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
public class LoginUserInterceptor extends HandlerInterceptorAdapter {
    private final UserClient userClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("X-Token");
        if (StringUtils.isBlank(token)) {
            return false;
        }

        Response<LoginUser> loginResp = userClient.info(token);
        if (!loginResp.isSuccess()) {
            log.error("token:{} has been expired", token);
            return false;
        }
        LoginUser loginUser = loginResp.getResult();
        UserUtil.putCurrentUser(loginUser);

        Duration between = Duration.between(loginUser.getCacheTime(), LocalDateTime.now());
        if (between.toMillis() * 2 > loginUser.getExpireTime()) {
            this.refreshToken(token);
        }

        return true;
    }

    private void refreshToken(String token) {
        Response<Boolean> refreshResp = userClient.refreshToken();
        if (!refreshResp.isSuccess()) {
            log.error("failed to refresh token:{}", token);
        }
    }
}
