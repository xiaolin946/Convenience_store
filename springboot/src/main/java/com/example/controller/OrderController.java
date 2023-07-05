package com.example.controller;

import com.example.common.Result;
import com.example.dao.OrderDao;
import com.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    //分页查询未处理订单数据
    @GetMapping("/findPage_Forder")
    public Result findPage_Forder(@RequestParam Integer currentPage, @RequestParam Integer pageSize,@RequestParam String acid,@RequestParam String guser){
        Result result = new Result();
        if(currentPage==null || pageSize==null){
            return result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        long total = orderDao.findPage_Forder_c(acid, guser);
        List<Order> orders = orderDao.findPage_Forder(pageNum, pageSize, acid, guser);
        Map<String, Object> map = new HashMap<>();
        map.put("data", orders);
        map.put("total", total);

        return result.success(map);
    }

    //分页查询已处理订单数据
    @GetMapping("/findPage_Torder")
    public Result findPage_Torder(@RequestParam Integer currentPage, @RequestParam Integer pageSize,@RequestParam String acid,@RequestParam String guser){
        Result result = new Result();
        if(currentPage==null || pageSize==null){
            return result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        long total = orderDao.findPage_Torder_c(acid, guser);
        List<Order> orders = orderDao.findPage_Torder(pageNum, pageSize, acid, guser);
        Map<String, Object> map = new HashMap<>();
        map.put("data", orders);
        map.put("total", total);

        return result.success(map);
    }

    //分页查询已处理订单数据
    @GetMapping("/findPage_Oorder")
    public Result findPage_Oorder(@RequestParam Integer currentPage, @RequestParam Integer pageSize,@RequestParam String acid,@RequestParam String guser){
        Result result = new Result();
        if(currentPage==null || pageSize==null){
            return result.error("参数错误");
        }
        Integer pageNum = (currentPage-1) * pageSize;
        long total = orderDao.findPage_Oorder_c(acid, guser);
        List<Order> orders = orderDao.findPage_Oorder(pageNum, pageSize, acid, guser);
        Map<String, Object> map = new HashMap<>();
        map.put("data", orders);
        map.put("total", total);

        return result.success(map);
    }

}
