<template>
  <div id="bgk" style="text-align: center; height: 70vh; width: 60vh;">
    <el-form :model="user" :rules="rules" ref="ruleFormRef">
      <h1 style="user-select:none;margin-bottom: 10px;"><a style="font-size: 35px">管理员登录</a></h1>
      <el-form-item prop="username" style=" text-align: center;margin-bottom: 5vh; width: 60vh;" type="text">
        <el-input v-model="user.username" maxlength="15" :prefix-icon=User></el-input>
      </el-form-item>
      <el-form-item prop="password" style="margin-bottom: 5vh; width: 60vh;">
        <el-input type="password" v-model="user.password" maxlength="18" show-password :prefix-icon="Lock"></el-input><br>
      </el-form-item>
      <el-form-item>
        <el-button style="width: 60vh" type="primary" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
  import {User,Lock} from '@element-plus/icons-vue'
  import {ref, reactive, getCurrentInstance} from 'vue'
  const {proxy} = getCurrentInstance();
  import {ElNotification} from 'element-plus'
  import request from "../request.js";
  import router from "../router/index.js";


  const user = reactive({
    status: 'admin'
  })

  const rules = reactive({
    username: [
      {required: true, message: '请输入用户名', trigger: 'blur'},
      {min: 5, message: '用户名最少为5位', trigger: 'blur'},
    ],
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'},
      {min: 5, message: '密码最少为5位', trigger: 'blur'}
    ]
  })

  const login = () => {
    proxy.$refs.ruleFormRef.validate((valid) => {
      if(valid){
        //Promise
        //发送请求给后台
        //返回数据格式: {code: "200", "msg": "", "data": null}   code: 200 请求成功
        request.post('/admin/login',user).then(res => {
          if(res.code === "200"){
            ElNotification({
              title: '登录成功',
              type: 'success',
              message: "欢迎 " + " 管理员: " + res.data.username
            })
            localStorage.setItem("user", JSON.stringify(res.data))
            router.push('/Admin/tip')
          }else{
            ElNotification({
              title: 'Error',
              type: 'error',
              message: '用户名或密码错误'
            })
          }
        })
      }else{
        //弹窗显示错误
        ElNotification({
          title: 'Error',
          type: 'error',
          message: '登录失败'
        })
      }
    })
  }
</script>

<style>
#bgk {
  margin: -80px auto;
  display: flex;
  place-items: center;
  min-width: 320px;
  min-height: 100vh;
}
h1 {
  font-size: 3.2em;
  line-height: 1.1;
}
</style>