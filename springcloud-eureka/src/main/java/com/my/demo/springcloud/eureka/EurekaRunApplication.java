package com.my.demo.springcloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by zhangzhile on 2018/2/24.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaRunApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRunApplication.class, args);
    }
}
