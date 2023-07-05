package com.example.controller;

import com.example.common.Result;
import com.example.dao.TypeDao;
import com.example.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeDao typeDao;

    //全部类别数据
    @GetMapping("/find_ftype")
    public Result find_ftype(){
        Result result = new Result();
        return result.success(typeDao.find_ftype());
    }

    //分页查询类别数据
    @GetMapping("/findPage_ftype")
    public Result findPage_ftype(@RequestParam Integer currentPage,@RequestParam Integer pageSize){
        Result result = new Result();
        if(currentPage==null || pageSize==null){
            return result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        long total = typeDao.find_ftype_c();
        List<Type> types = typeDao.findPage_ftype(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("data", types);
        map.put("total", total);

        return result.success(map);
    }

    //分页条件查询类别数据
    @GetMapping("/cfindPage_ftype")
    public Result cfindPage_ftype(@RequestParam Integer currentPage,@RequestParam Integer pageSize,@RequestParam String f_type,@RequestParam String s_type,@RequestParam String n_type){
        Result result = new Result();
        if(currentPage==null || pageSize==null){
            return result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        long total = typeDao.cfind_ftype_c(f_type,s_type,n_type);
        List<Type> types = typeDao.cfindPage_ftype(pageNum, pageSize,f_type,s_type,n_type);
        Map<String, Object> map = new HashMap<>();
        map.put("data", types);
        map.put("total", total);

        return result.success(map);
    }

    //插入新类别
    @PostMapping("/insert")
    public Result insert(@RequestBody Type type){
        Result result = new Result();
        if(type.getF_type()==null){
            return result.error("参数错误");
        }
        return result.success(typeDao.insert(type)==1);
    }

    //删除类别
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        Result result = new Result();
        if(id==null){
            return result.error("参数错误");
        }
        return result.success(typeDao.delete(id)==1);
    }

    //更新类别
    @PutMapping("/update")
    public Result update(@RequestBody Type type){
        Result result = new Result();
        if(type.getF_type()==null || type.getS_type()==null){
            return result.error("参数错误");
        }
        List<Type> types = typeDao.find_ftype();
        for(int i=0;;i++){
            if(type.getF_type().equals(types.get(i).getF_type())){
                return result.success(typeDao.update(type.getF_type(), type.getS_type())==1);
            }
        }
    }

}
