package com.example.dao;

import com.example.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TypeDao {

    //全部类别数据
    List<Type> find_ftype();

    //类别数据总数
    int find_ftype_c();
    //查询指定类别数据
    List<Type> findPage_ftype(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    //条件类别数据总数
    int cfind_ftype_c(@Param("f_type") String f_type,@Param("s_type") String s_type,@Param("n_type") String n_type);
    //条件查询指定类别数据
    List<Type> cfindPage_ftype(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,@Param("f_type") String f_type,@Param("s_type") String s_type,@Param("n_type") String n_type);

    //插入类别
    int insert(Type type);
    //删除类别
    int delete(@Param("id") Integer id);

    //更新类别
    int update(@Param("f_type") String f_type,@Param("s_type") String s_type);

}
