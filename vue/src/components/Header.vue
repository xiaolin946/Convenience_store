<template>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      :ellipsis="false"
      style="margin-left: 5%;margin-right: 5%;"
      router
  >
    <el-menu-item >Convenience store</el-menu-item>
    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/accessory">市场</el-menu-item>
    <div class="flex-grow" />
    <el-menu-item index="" @click="login_user">登录/注册</el-menu-item>
  </el-menu>

  <el-dialog
      v-model="centerDialogVisible"
      title="账号登录/注册"
      width="50vh"
      align-center
      center="true"
      style="text-align: center"
  >
    <el-form :model="statusData.loginData" :rules="statusData.loginrules" ref="ruleFormRef" size="large">
      <el-form-item prop="username" style=" text-align: center;margin-bottom: 3vh; width: 90%;margin-left: 5%;margin-right: 5%" type="text">
        <el-input v-model="statusData.loginData.username" maxlength="15" :prefix-icon=User></el-input>
      </el-form-item>
      <el-form-item prop="password" style="margin-bottom: 3vh; width: 90%;margin-left: 5%;margin-right: 5%">
        <el-input type="password" v-model="statusData.loginData.password" maxlength="18" show-password :prefix-icon="Lock"></el-input><br>
      </el-form-item>
      <el-form-item style="width: 90%;margin-left: 5%;margin-right: 5%">
        <el-checkbox v-model="loginx" label="我同意 《用户协议》(待完善)" @click="loginxx"/>
      </el-form-item>
      <el-form-item>
        <el-button style="width: 90%;margin-left: 5%;margin-right: 5%" type="primary" @click="login" :disabled="logint">登录/注册</el-button>
      </el-form-item>
    </el-form>
    <template style="width: 100%;height: 5vh;background-color: #DCDFE6;" #footer>
      <a style="font-size: 13px;margin-right: 5px">其他登录方式</a>
      <el-button type="info" size="small" :icon="Message" circle />
    </template>
  </el-dialog>

  <el-dialog
      v-model="centerDialogVisible2"
      title="账号注册"
      width="30%"
      align-center
      center="true"
  >

  </el-dialog>
</template>

<script setup>
import {User,Lock,Message} from '@element-plus/icons-vue'
import {getCurrentInstance, reactive, ref} from "vue";
import {ElNotification} from 'element-plus'
import router from "../router/index.js";
import request from "../request.js";

const {proxy} = getCurrentInstance();

const activeIndex = ref('/')

const loginx = ref(false)
const logint = ref(true)
const statusData = reactive({
  loginData: {
    status: "user",

  },
  loginrules: {
    username: [
      {required: true, message: '请输入用户名', trigger: 'blur'},
      {min: 5, message: '用户名最少为5位', trigger: 'blur'}
    ],
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'},
      {min: 2, message: '密码最少为5位', trigger: 'blur'}
    ],
  },
  formData: {
    status: "user",
  },
})

const centerDialogVisible = ref(false)
const centerDialogVisible2 = ref(false)


const login_user = () => {
  centerDialogVisible.value = true
}
const loginxx = () => {
  if(loginx.value){
    logint.value = true;
  }else{
    logint.value = false;
  }
}

const login = () => {
  proxy.$refs.ruleFormRef.validate((valid) => {
    if(valid){
      //Promise
      //发送请求给后台
      //返回数据格式: {code: "200", "msg": "", "data": null}   code: 200 请求成功
      request.post('/user/login',statusData.loginData).then(res => {
        centerDialogVisible.value = false
        if(res.code === "200"){
          ElNotification({
            title: '登录成功',
            type: 'success',
            message: "欢迎用户: " + res.data.username
          })
          localStorage.setItem("user", JSON.stringify(res.data))
          router.push('/user')
        }else{
          ElNotification({
            title: 'Error',
            type: 'error',
            message: '用户名不存在或密码错误'
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
.flex-grow {
  flex-grow: 1;
}
</style>