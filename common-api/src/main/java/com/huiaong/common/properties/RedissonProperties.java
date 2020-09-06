package com.huiaong.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonProperties {

    private String host;

    private String port;

    private String password;

}
