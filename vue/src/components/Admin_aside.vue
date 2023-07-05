<template>
  <el-scrollbar style="user-select:none;">
      <el-menu
        :default-active="activeIndex"
        :default-openeds="['2','3','4']"
        router
      >
        <el-menu-item index="/Admin/tip"><template #title><el-icon><ElementPlus /></el-icon>后台系统首页</template></el-menu-item>
        <el-sub-menu index="2">
          <template #title><el-icon><ShoppingCart /></el-icon>商品管理</template>
          <el-menu-item index="/Admin/type">类别管理</el-menu-item>
          <el-menu-item index="/Admin/accessory">饰品管理</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3">
          <template #title><el-icon><Paperclip /></el-icon>订单管理</template>
          <el-menu-item index="/Admin/Forder/">未处理订单</el-menu-item>
          <el-menu-item index="/Admin/Torder/">已处理订单</el-menu-item>
          <el-menu-item index="/Admin/Oorder/">已完成订单</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="4">
          <template #title><el-icon><Tools /></el-icon>设置</template>
          <el-menu-item index="#">待开发~</el-menu-item>
          <el-menu-item index="#">待开发~</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-scrollbar>
</template>

<script setup>
import {Paperclip,Tools, ShoppingCart, ElementPlus} from '@element-plus/icons-vue'
import { ref } from 'vue'

const activeIndex = ref('/Admin/tip')
</script>

<style scoped>

</style>