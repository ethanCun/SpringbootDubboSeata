package com.ethan.seata.controller;

import com.ethan.seata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/debit")
    public void debit(@RequestParam("userId") String userId,
                      @RequestParam("money") Integer money){

        this.userService.debit(userId, money);
    }
}
