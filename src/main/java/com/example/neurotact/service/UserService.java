package com.example.neurotact.service;

import com.example.neurotact.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    // 新用户注册
    int userRegister(String[] param);

    // 检测邮箱是否被占用，如未被占用则为新用户，如被占用则提示不可再使用当前邮箱注册
    boolean isMailLogin(String email);

    // 根据邮箱查询用户信息
    List<User> findByMail(String email);
}
