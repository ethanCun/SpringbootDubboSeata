package com.ethan.seata.controller;

import com.ethan.seata.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/debuct")
    public void deduct(String commodityCode, int count){

        this.storageService.deduct(commodityCode, count);
    }
}
