package com.example.neurotact.service.Impl;

import com.example.neurotact.entity.User;
import com.example.neurotact.mapper.UserMapper;
import com.example.neurotact.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public int userRegister(String[] param) {
        String email = param[3];
        if (userMapper.countByMail(email) == 0) {
            User user = new User();
            user.setName(param[0]);
            user.setBirth(param[1]);
            user.setGender(param[2]);
            user.setEmail(email);
            user.setIsDel("0");
            Date nowTime = new Date();
            user.setGmtCreate(nowTime);
            user.setGmtModified(nowTime);
            return userMapper.register(user);
        } else {
            return -1;
        }
    }

    @Override
    public boolean isMailLogin(String email) {
        if (userMapper.countByMail(email) == 0) {
//            System.out.println("当前邮箱未被注册");
            return true;    // 返回true表示当前邮箱可以注册
        } else {
//            System.out.println("当前邮箱已被注册");
            return false;   // 返回true表示当前邮箱不可以注册
        }
    }

    @Override
    public List<User> findByMail(String email) {
        return userMapper.isMailLogin(email);
    }
}
