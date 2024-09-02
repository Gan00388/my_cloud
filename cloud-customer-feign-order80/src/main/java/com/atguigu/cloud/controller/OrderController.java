package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ClassName: OrderController
 * Package: com.atguigu.cloud.controller
 * Description:
 *
 * @Author GWM
 * @Create 2024/8/19 16:54
 * @Version 1.0
 */
@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.add(payDTO);
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {

        ResultData resultData = null;

        try {
            System.out.println("调用feign接口" + DateUtil.now());
            resultData = payFeignApi.getById(id);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用feign接口结束" + DateUtil.now());
            resultData = ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }

        return resultData;
    }

    @GetMapping("/feign/pay/info")
    public String getInfoByFeign() {
        return payFeignApi.mylb();
    }
}
