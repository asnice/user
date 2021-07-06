package com.example.user.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shuixianbin
 * @date 2021/06/15
 */
@Configuration
@EnableConfigurationProperties(ZookeeperProperties.class)
public class ZookeeperAutoConfiguration {

    @Bean
    public CuratorFramework curatorFramework(ZookeeperProperties zp) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zp.getElapsedTimeMs(), zp.getRetryCount());
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                zp.getConnectString(),
                zp.getSessionTimeoutMs(),
                zp.getConnectionTimeoutMs(),
                retryPolicy);
        client.start();
        return client;
    }
}
