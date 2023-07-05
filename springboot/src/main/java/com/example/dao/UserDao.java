package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserDao {

    //登录验证-----
    User login(@Param("status") String status, @Param("username") String username, @Param("password") String password);

    //个人信息
    User myxx(@Param("status") String status, @Param("username") String username);

    //修改信息
    int update(User user);

    //修改密码
    int uppsd(@Param("username") String username, @Param("password") String password);
    String mypsd(@Param("status") String status, @Param("username") String username);


}
