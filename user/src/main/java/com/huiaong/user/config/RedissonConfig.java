package com.huiaong.user.config;

import com.huiaong.common.properties.RedissonProperties;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AllArgsConstructor
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonConfig {
    private final RedissonProperties redissonProperties;


    /**
     * 单机模式 redisson 客户端
     *
     * @return RedissonClient
     */
    @Bean
    RedissonClient redissonSingle() {
        Config config = new Config();

        String node = redissonProperties.getHost().concat(":").concat(redissonProperties.getPort());
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node);
        if (StringUtils.isNotEmpty(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }
        return Redisson.create(config);
    }

}
