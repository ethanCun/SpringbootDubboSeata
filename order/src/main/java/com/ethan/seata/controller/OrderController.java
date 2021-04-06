package com.ethan.seata.controller;

import com.ethan.seata.entity.Order;
import com.ethan.seata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order")
    Order create(String userId, String commodityCode, int orderCount){
        return this.orderService.create(userId, commodityCode, orderCount);
    }
}
