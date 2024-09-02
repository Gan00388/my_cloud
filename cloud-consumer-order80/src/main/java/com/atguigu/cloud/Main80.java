package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

/**
 * ClassName: Main80
 * Package: com.atguigu.cloud
 * Description:
 *
 * @Author GWM
 * @Create 2024/8/19 16:44
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main80 {
    public static void main(String[] args)
    {
        ApplicationContext context = SpringApplication.run(Main80.class, args);
    }
}
