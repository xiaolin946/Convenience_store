package com.example.dao;

import com.example.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AccessoryDao {

    //全部数据
    List<Accessory> find_Aac();

    //数据个数
    int findPage_Aac_c();
    //分页全部数据
    List<Accessory> findPage_Aac(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    //条件数据个数
    int cfindPage_Aac_c(@Param("type") String type, @Param("name") String name, @Param("skin") String skin);
    //条件分页全部数据
    List<Accessory> cfindPage_Aac(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("type") String type, @Param("name") String name, @Param("skin") String skin);

    //插入饰品
    int insert(Accessory accessory);
    //删除饰品
    int delete(@Param("id") Integer id);

    //更新饰品
    int update(Accessory accessory);

}
