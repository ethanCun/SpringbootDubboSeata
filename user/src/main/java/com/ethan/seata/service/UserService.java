package com.ethan.seata.service;

import com.ethan.seata.entity.User;

public interface UserService {

    //根据userId查用户
    User userWithUserId(String userId);

    /**
     * 从用户账户中借出
     */
    int debit(String userId, int money);
}
