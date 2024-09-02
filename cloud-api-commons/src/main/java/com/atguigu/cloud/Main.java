package com.atguigu.cloud;

import java.time.ZonedDateTime;

/**
 * ClassName: Main
 * Package: com.atguigu.cloud
 * Description:
 *
 * @Author GWM
 * @Create 2024/8/28 15:08
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
    }
}
