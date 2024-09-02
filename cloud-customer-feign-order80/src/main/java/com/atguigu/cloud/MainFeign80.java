package com.atguigu.cloud;

import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: MainFeign80
 * Package: com.atguigu.cloud
 * Description:
 *
 * @Author GWM
 * @Create 2024/8/20 17:55
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MainFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(MainFeign80.class, args);
    }
}
