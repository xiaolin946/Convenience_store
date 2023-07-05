package com.example.dao;

import com.example.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {

    //分页查询未处理订单
    List<Order> findPage_Forder(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("acid") String acid, @Param("guser") String guser);
    //分页未处理订单个数
    int findPage_Forder_c(@Param("acid") String acid, @Param("guser") String guser);

    //分页查询已处理订单
    List<Order> findPage_Torder(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("acid") String acid, @Param("guser") String guser);
    //分页已处理订单个数
    int findPage_Torder_c(@Param("acid") String acid, @Param("guser") String guser);

    //分页查询已处理订单
    List<Order> findPage_Oorder(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("acid") String acid, @Param("guser") String guser);
    //分页已处理订单个数
    int findPage_Oorder_c(@Param("acid") String acid, @Param("guser") String guser);

}
