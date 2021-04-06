package com.ethan.seata.service.imp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethan.seata.entity.Order;
import com.ethan.seata.entity.Storage;
import com.ethan.seata.entity.User;
import com.ethan.seata.mapper.OrderMapper;
import com.ethan.seata.service.OrderService;
import com.ethan.seata.service.StorageService;
import com.ethan.seata.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Service
public class OrderServiceImp implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Reference
    private UserService userService;

    @Reference
    private StorageService storageService;

//    @Transactional(rollbackFor = RuntimeException.class)
    @GlobalTransactional(timeoutMills = 120000)
    @Override
    public Order create(String userId, String commodityCode, int orderCount) {

        //下单 扣除商品 扣除用户余额
        //用户
        User user = this.userService.userWithUserId(userId);
        if (user == null) throw new RuntimeException("用户不存在");

        //商品
        Storage storage = this.storageService.storageWithCode(commodityCode);
        if (storage == null) throw new  RuntimeException("商品不存在");
        if (storage.getCount() < orderCount) throw new RuntimeException("商品库存不足");

        //下单
        Order order = new Order();
        order.setId(0);
        order.setUserId(user.getUserId());
        order.setCommodityCode(storage.getCommodityCode());
        order.setCount(orderCount);
        order.setMoney(orderCount);

        int res = this.orderMapper.insert(order);

        if (res <= 0){
            throw new RuntimeException("下单失败");
        }

        //扣除库存
        int deductRes = this.storageService.deduct(storage.getCommodityCode(), orderCount);
        if (deductRes <= 0) throw new RuntimeException("下单失败， 扣除库存失败");

        //扣减余额
        int debitRes = this.userService.debit(user.getUserId(), order.getMoney());
        if (debitRes <= 0) throw new RuntimeException("下单失败, 扣除余额失败");

        return order;
    }
}
