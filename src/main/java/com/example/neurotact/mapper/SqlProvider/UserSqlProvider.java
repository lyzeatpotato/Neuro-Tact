package com.example.neurotact.mapper.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String countByMail(String email) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("user").WHERE("email = #{email,jdbcType=VARCHAR}");
        return sql.toString();
    }
}