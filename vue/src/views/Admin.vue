<template>
  <el-container class="layout-container" style="height: 100vh; width: 100%; ">
    <el-container>
      <el-aside style="height: 100%; width: 20%; ">
        <Admin_aside />
      </el-aside>
      <el-container>
        <el-header style="height: 8%; width: 100%; text-align: right;">
          <Admin_header />
        </el-header>
        <el-main style="height: 100%; width: 100%;">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </el-container>

</template>

<script setup>
import Admin_aside from '/src/components/Admin_aside.vue'
import Admin_header from '/src/components/Admin_header.vue'



</script>

<style scoped>

</style>
