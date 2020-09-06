package com.huiaong.user;


import com.huiaong.common.client.UserClient;
import com.huiaong.common.interception.LoginUserInterceptor;
import com.huiaong.user.config.RedissonConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@Configuration
@EnableTransactionManagement
@Import({RedissonConfig.class})
@MapperScan("com.huiaong.user.dao")
@ComponentScan({"com.huiaong.common.aspect"})
public class UserConfiguration implements WebMvcConfigurer {

    @Value("${license.path}")
    private List<String> licensePath;
    @Value("${un-license.path}")
    private List<String> unLicensePath;
    @Autowired
    private UserClient userClient;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginUserInterceptor()) // 添加拦截器
                .addPathPatterns(licensePath) // 添加拦截路径
                .excludePathPatterns(unLicensePath)// 添加排除拦截路径
                .order(1);//执行顺序
        log.info("interceptor has been load, licensePath:{}, unLicensePath:{}", licensePath, unLicensePath);
    }

    @Bean
    public LoginUserInterceptor loginUserInterceptor() {
        return new LoginUserInterceptor(userClient);
    }
}



