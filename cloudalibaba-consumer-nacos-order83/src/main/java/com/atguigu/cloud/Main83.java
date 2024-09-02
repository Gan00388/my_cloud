package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: Main83
 * Package: com.atguigu.cloud
 * Description:
 *
 * @Author GWM
 * @Create 2024/8/29 12:31
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Main83 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Main83.class, args);
    }
}
