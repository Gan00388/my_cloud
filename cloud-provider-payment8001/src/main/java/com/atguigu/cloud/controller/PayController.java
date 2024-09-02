package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: PayController
 * Package: com.atguigu.cloud.controller
 * Description:
 *
 * @Author GWM
 * @Create 2024/8/19 12:02
 * @Version 1.0
 */
@RestController
@Slf4j
@Tag(name = "支付接口", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
    public ResultData<String> add(@RequestBody Pay pay) {
        log.info("添加支付信息：" + pay);
        int i = payService.add(pay);
        return i > 0 ? ResultData.ok("新增成功") : ResultData.fail("405", "新增失败");
    }

    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<String> delete(@PathVariable("id") Integer id) {
        log.info("删除支付信息：" + id);
        int i = payService.delete(id);
        return i > 0 ? ResultData.ok("删除成功") : ResultData.fail("405", "删除失败");
    }

    @PutMapping("/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> update(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        log.info("修改支付信息：" + pay);
        int i = payService.update(pay);
        return i > 0 ? ResultData.ok("修改成功") : ResultData.fail("405", "修改失败");
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "按id查询",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        log.info("查询支付信息：" + id);
        Pay pay = payService.getById(id);

        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return pay != null ? ResultData.ok(pay) : ResultData.fail("405", "查询失败");
    }

    @GetMapping("/pay/getAll")
    public ResultData<Object> getAll() {
        log.info("查询所有支付信息");
        List<Pay> payList = payService.getAll();
        return payList != null ? ResultData.ok(payList) : ResultData.fail("405", "查询失败");
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/pay/get/info")
    public String getInfoByConsul(@Value("${atguigu.info}") String info ) {
        return "端口号：" + port + "，信息：" + info;

    }
}
