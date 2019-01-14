package com.my.demo.springcloud.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangzhile on 2018/2/23.
 */
@SpringBootApplication
@RestController
//服务注册到ZK
@EnableDiscoveryClient
@ComponentScan("com.my.demo.springcloud")
//@MapperScan("com.my.demo.springcloud.mapper.dao")
@ImportResource("spring-config-dao.xml")
@PropertySource({
        "classpath:test.properties"
})
public class RunServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunServiceApplication.class, args);
    }
}
