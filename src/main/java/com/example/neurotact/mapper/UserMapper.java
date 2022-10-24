package com.example.neurotact.mapper;

import com.example.neurotact.entity.User;
import com.example.neurotact.mapper.SqlProvider.UserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    /**
     * 用户注册(返回最近一次注册的用户id)
     */
    @Insert({
        "Insert into user ",
        "(name, birth, gender, email, ",
        "isDel, gmt_create, gmt_modified)",
        "values (#{name,jdbcType=VARCHAR}, #{birth,jdbcType=VARCHAR}, ",
                "#{gender,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
                "#{isDel,jdbcType=CHAR}, #{gmtCreate,jdbcType=DATE}, #{gmtModified,jdbcType=DATE})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int register(User user);

    /**
     * 返回当前邮箱查询的数量(以邮箱作为唯一登陆判别，每次登录需要检查邮箱是否存在并且唯一)
     */
    @SelectProvider(type = UserSqlProvider.class, method = "countByMail")
    long countByMail(String email);

    /**
     * 根据邮箱查询当前用户的信息
     */
    @Select({
        "Select * from user",
        "where isDel = 0 and email=#{email,jdbcType=VARCHAR}"
    })
    List<User> isMailLogin(String email);
}
