package com.ethan.seata.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethan.seata.entity.Storage;
import com.ethan.seata.mapper.StorageMapper;
import com.ethan.seata.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@Service
public class StorageServiceImp implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public Storage storageWithCode(String commodityCode) {

        QueryWrapper<Storage> storageQueryWrapper = new QueryWrapper<>();
        storageQueryWrapper.eq("commodity_code", commodityCode);

        Storage storage = this.storageMapper.selectOne(storageQueryWrapper);

        if (storage == null){
            throw new RuntimeException("商品不存在");
        }

        return storage;
    }

    @Override
    public int deduct(String commodityCode, int count) {

        QueryWrapper<Storage> storageQueryWrapper = new QueryWrapper<>();
        storageQueryWrapper.eq("commodity_code", commodityCode);

        Storage storage = this.storageMapper.selectOne(storageQueryWrapper);

        if (storage == null){
            throw new RuntimeException("商品不存在");
        }

        storage.setCount(storage.getCount()-count);

        int res = this.storageMapper.updateById(storage);

        if (res <= 0) {
            throw new RuntimeException("库存扣减失败");
        }else {
            log.info("库存扣减成功");
        }

        return res;

    }
}
