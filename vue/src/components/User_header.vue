<template>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      :ellipsis="false"
      style="margin-left: 5%;margin-right: 5%;user-select:none;"
      router
  >
    <el-menu-item index="">Convenience store</el-menu-item>
    <el-menu-item index="/user/main">首页</el-menu-item>
    <el-menu-item index="/user/accessory">市场</el-menu-item>
    <el-menu-item index="#">待</el-menu-item>
    <div class="flex-grow" />
    <el-menu-item index="4">我的订单</el-menu-item>
    <el-menu-item index="5">收藏夹</el-menu-item>
    <el-menu-item index="6">购物车</el-menu-item>
    <el-dropdown style="margin-top: 20px;margin-left: 13px">
              <span>
                <el-icon style="margin-right: 8px;" size="15"><UserFilled/></el-icon>
                <span v-text="user.username"></span>
              </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="myxx">个人信息</el-dropdown-item>
          <el-dropdown-item @click="upsd">修改密码</el-dropdown-item>
          <el-dropdown-item divided @click="exit">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </el-menu>
</template>

<script setup>
import {UserFilled} from '@element-plus/icons-vue'
import router from "../router/index.js";
import {ref} from "vue";

const user = localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{}

const activeIndex = ref('/user/main')

const exit = () => {
  localStorage.removeItem("user")
  router.push("/")
}
const myxx = () => {
  router.push("/" + user.status + "/myxx")
}
const upsd = () => {
  router.push("/" + user.status + "/upsd")
}</script>

<style>
.flex-grow {
  flex-grow: 1;
}
</style>