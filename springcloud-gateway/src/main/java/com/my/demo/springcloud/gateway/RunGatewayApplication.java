package com.my.demo.springcloud.gateway;

import com.my.demo.springcloud.gateway.filter.AccessUserFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by zhangzhile on 2018/2/23.
 */
@SpringCloudApplication
@EnableZuulProxy
//服务注册到ZK
@EnableDiscoveryClient
public class RunGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunGatewayApplication.class, args);
    }

    //过滤器创建时必须要有如下声明
    @Bean
    public AccessUserFilter accessUserFilter(){
        return new AccessUserFilter();
    }
}
