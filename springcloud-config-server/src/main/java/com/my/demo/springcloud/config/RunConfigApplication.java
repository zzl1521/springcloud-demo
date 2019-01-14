package com.my.demo.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by zhangzhile on 2018/2/23.
 */
//服务注册到ZK
@EnableDiscoveryClient
//配置中心注解
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class RunConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunConfigApplication.class, args);
    }
}
