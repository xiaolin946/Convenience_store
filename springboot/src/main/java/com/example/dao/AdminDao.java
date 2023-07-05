package com.example.dao;

import com.example.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface AdminDao {

    //登录验证-----
    Admin login(@Param("status") String status, @Param("username") String username, @Param("password") String password);

    //个人信息
    Admin myxx(@Param("status") String status, @Param("username") String username);

    //修改信息
    int update(Admin admin);

    //修改密码
    int uppsd(@Param("username") String username, @Param("password") String password);
    String mypsd(@Param("status") String status, @Param("username") String username);


}
