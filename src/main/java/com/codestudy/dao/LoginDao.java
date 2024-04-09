package com.codestudy.dao;

import com.codestudy.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginDao {

    @Select("select * from users where user_name =#{userName} and password=#{password}")
    User getByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);

    @Select("select count(*) from users where user_name=#{userName};")
    int select(User user);

    /**
     * 注册
     * @param user
     */
    @Insert("insert into users (user_name, password, account, gender, avatar, create_time, updata_time) values (#{userName},#{password},#{account},#{gender},#{avatar},#{createTime},#{updataTime});")
    void regedit(User user);
}
