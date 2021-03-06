package com.huiaong.common.client;

import com.huiaong.common.client.fallback.UserClientFallback;
import com.huiaong.common.client.interceptor.ClientInterceptor;
import com.huiaong.common.dto.LoginUser;
import com.huiaong.common.response.Response;
import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ninetabes-user", configuration = {UserClient.ClientConfiguration.class, ClientInterceptor.class}, fallback = UserClientFallback.class)
public interface UserClient {

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/user/info")
    Response<LoginUser> info(@RequestParam("token") String token);

    /**
     * 刷新Token
     *
     * @return
     */
    @GetMapping("/user/refresh-token")
    Response<Boolean> refreshToken();

    class ClientConfiguration {
        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }
    }
}
