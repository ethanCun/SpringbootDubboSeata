package com.ethan.seata.service;

import com.ethan.seata.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    Order create(String userId, String commodityCode, int orderCount);
}
