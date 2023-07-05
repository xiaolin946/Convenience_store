package com.example.controller;

import com.example.common.Result;
import com.example.dao.AdminDao;
import com.example.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Admin admin){
        Result result = new Result();
        if(admin.getStatus()==null || admin.getUsername()==null || admin.getPassword()==null){
            return result.error("参数错误");
        }
        if(adminDao.login(admin.getStatus(), admin.getUsername(), admin.getPassword())!=null){
            return result.success(adminDao.login(admin.getStatus(), admin.getUsername(), admin.getPassword()));
        }else{
            return result.error("密码错误");
        }
    }

    //个人信息----
    @GetMapping("/myxx")
    public Result myxx(@RequestParam String status,@RequestParam String username){
        Result result = new Result();
        if(status==null || username==null){
            return result.error("参数错误");
        }
        return result.success(adminDao.myxx(status, username));
    }

    //修改信息
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        Result result = new Result();
        if(admin.getId()==null || admin.getUsername()==null){
            return result.error("参数错误");
        }
        return result.success(adminDao.update(admin)==1);
    }

    //修改密码----
    @PutMapping("/uppsd")
    public Result uppsd(@RequestBody psd psd){
        Result result = new Result();
        if(psd.getUsername()==null || psd.getPassword()==null || psd.getNew_password()==null || psd.getNew_password2()==null){
            return result.error("参数错误");
        }
        return result.success(adminDao.uppsd(psd.getUsername(), psd.getNew_password2())==1);
    }
    @GetMapping("/mypsd")
    public Result mypsd(@RequestParam String status,@RequestParam String username){
        Result result = new Result();
        if(status==null || username==null){
            return result.error("参数错误");
        }
        return result.success(adminDao.mypsd(status, username));
    }

}
