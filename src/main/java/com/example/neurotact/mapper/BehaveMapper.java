package com.example.neurotact.mapper;

import com.example.neurotact.entity.Behave;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

public interface BehaveMapper {

    /**
     * 保存用户行为
     */
    @Insert({
        "insert into user_behave",
        "(userid, c_btn1, s1_btn1, s1_btn2, front_label1, back_label1, front_label2, back_label2, score, isDel, gmt_create, gmt_modified)",
        "values (#{userId,jdbcType=BIGINT}, #{c_btn1,jdbcType=VARCHAR}, #{s1_btn1,jdbcType=VARCHAR}, #{s1_btn2,jdbcType=VARCHAR}, ",
                "#{front_label1,jdbcType=VARCHAR}, #{back_label1,jdbcType=VARCHAR}, ",
                "#{front_label2,jdbcType=VARCHAR}, #{back_label2,jdbcType=VARCHAR}, #{score,jdbcType=VARCHAR}, ",
                "#{isDel,jdbcType=CHAR}, #{gmtCreate,jdbcType=DATE}, #{gmtModified,jdbcType=DATE})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int save(Behave behave);
}
