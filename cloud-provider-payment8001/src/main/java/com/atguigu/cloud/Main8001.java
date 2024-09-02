package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * ClassName: Main8001
 * Package: com.atguigu.cloud
 * Description:
 *
 * @Author GWM
 * @Create 2024/8/18 19:14
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.atguigu.cloud.mapper")
@RefreshScope
public class Main8001 {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main8001.class, args);
    }
}
