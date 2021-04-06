package com.ethan.seata.service;

import com.ethan.seata.entity.Storage;

public interface StorageService {

    //根据商品编号查商品
    Storage storageWithCode(String commodityCode);

    /**
     * 扣除存储数量
     */
    int deduct(String commodityCode, int count);
}
