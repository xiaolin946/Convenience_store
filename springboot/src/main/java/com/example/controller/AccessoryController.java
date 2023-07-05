package com.example.controller;

import com.example.common.Result;
import com.example.dao.AccessoryDao;
import com.example.dao.TypeDao;
import com.example.entity.Accessory;
import com.example.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accessory")
public class AccessoryController {

    @Autowired
    private AccessoryDao accessoryDao;


    //全部类别数据
    @GetMapping("/find_Aac")
    public Result find_Aac(){
        Result result = new Result();
        return result.success(accessoryDao.find_Aac());
    }

    //分页查询饰品数据
    @GetMapping("/findPage_Aac")
    public Result findPage_Aac(@RequestParam Integer currentPage,@RequestParam Integer pageSize){
        Result result = new Result();
        if(currentPage==null || pageSize==null){
            return result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        long total = accessoryDao.findPage_Aac_c();
        List<Accessory> types = accessoryDao.findPage_Aac(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("data", types);
        map.put("total", total);

        return result.success(map);
    }

    //分页条件查询饰品数据
    @GetMapping("/cfindPage_Aac")
    public Result cfindPage_Aac(@RequestParam Integer currentPage,@RequestParam Integer pageSize,@RequestParam String type,@RequestParam String name,@RequestParam String skin){
        Result result = new Result();
        if(currentPage==null || pageSize==null){
            return result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        long total = accessoryDao.cfindPage_Aac_c(type,name,skin);
        List<Accessory> accessories = accessoryDao.cfindPage_Aac(pageNum, pageSize,type,name,skin);
        Map<String, Object> map = new HashMap<>();
        map.put("data", accessories);
        map.put("total", total);

        return result.success(map);
    }

    //插入新饰品
    @PostMapping("/insert")
    public Result insert(@RequestBody Accessory accessory){
        Result result = new Result();
        if(accessory.getType()==null||accessory.getName()==null||accessory.getQuality()==null||accessory.getAppearance()==null||accessory.getPrice()==null){
            return result.error("参数错误");
        }
        return result.success(accessoryDao.insert(accessory)==1);
    }

    //删除饰品
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        Result result = new Result();
        if(id==null){
            return result.error("参数错误");
        }
        return result.success(accessoryDao.delete(id)==1);
    }

    //更新饰品
    @PutMapping("/update")
    public Result update(@RequestBody Accessory accessory){
        Result result = new Result();
        if(accessory.getType()==null||accessory.getName()==null||accessory.getQuality()==null||accessory.getAppearance()==null||accessory.getPrice()==null){
            return result.error("参数错误");
        }
        return result.success(accessoryDao.update(accessory)==1);
    }




}
