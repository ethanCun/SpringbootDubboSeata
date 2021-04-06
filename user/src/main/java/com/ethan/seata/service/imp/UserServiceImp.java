package com.ethan.seata.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ethan.seata.entity.User;
import com.ethan.seata.mapper.UserMapper;
import com.ethan.seata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Component
@Service //注册到buddo
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User userWithUserId(String userId) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);

        User user = this.userMapper.selectOne(userQueryWrapper);

        if (user == null){
            throw new RuntimeException("用户不存在");
        }

        return user;
    }

    @Override
    public int debit(String userId, int money) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", userId);

        User user = this.userMapper.selectOne(userQueryWrapper);

        if (user == null){
            throw new RuntimeException("用户不存在");
        }

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("user_id", userId)
                .set("money", user.getMoney()-money);


        int debit = this.userMapper.update(user, userUpdateWrapper);

        if (debit <= 0){
            throw new RuntimeException("用户账号扣款失败");
        }else {
            log.info("用户账户扣款成功");
        }

//        int i=10/0;

        return debit;
    }
}
