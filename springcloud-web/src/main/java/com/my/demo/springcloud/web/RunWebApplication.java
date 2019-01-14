package com.my.demo.springcloud.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

/**
 * Created by zhangzhile on 2018/2/23.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// 启用REST 客户端
@EnableFeignClients
// 启用客户端负载均衡
//@RibbonClient(name = "my", configuration = RibbonConfig.class)
// 启用服务发现
@EnableDiscoveryClient
// 启用断路器
@EnableHystrix
//@ImportResource(value = "spring_mvc.xml")
@ComponentScan("com.my.demo.springcloud.web")
public class RunWebApplication {

   /* @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(RunWebApplication.class, args);
    }

     //Spring JDBC
    /*@Bean
    public JdbcTemplate primaryJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }*/

     //TOMCAT DataSourcePool
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
