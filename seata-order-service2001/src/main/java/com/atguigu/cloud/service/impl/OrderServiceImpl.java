package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * ClassName: OrderSerivceImpl
 * Package: com.atguigu.cloud.service.impl
 * Description:
 *
 * @Author GWM
 * @Create 2024/9/1 23:17
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageFeignApi storageFeignApi;

    @Resource
    private AccountFeignApi accountFeignApi;
    @Override
    @GlobalTransactional(name = "gwm-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        String xid = RootContext.getXID();
        log.info("xid:{},开始新建订单",xid);
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        Order orderFromDb = null;

        if(result > 0) {
            orderFromDb = orderMapper.selectOne(order);
            log.info("order:{}",orderFromDb);
            storageFeignApi.decrease(orderFromDb.getProductId(),orderFromDb.getCount());
            log.info("扣减库存完成");
            accountFeignApi.decrease(orderFromDb.getUserId(),orderFromDb.getMoney());
            log.info("扣减账户余额完成");
            log.info("修改订单状态开始");
            orderFromDb.setStatus(1);
            Example whereCondition = new Example(Order.class);
            whereCondition.createCriteria()
                    .andEqualTo("userId",orderFromDb.getUserId())
                    .andEqualTo("status",0);

            int updateResult = orderMapper.updateByExampleSelective(orderFromDb, whereCondition);
            log.info("修改订单状态结果:{}",updateResult);
        }
        log.info("新建订单完成");
    }
}
