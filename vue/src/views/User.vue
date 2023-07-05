<template>
  <el-container class="layout-container" style="height: 100vh; width: 100%; ">
    <el-container>
      <el-header>
        <Header_user/>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
      <el-footer style="height: 5%">
        <Footer/>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script setup>
import Header_user from '/src/components/User_header.vue'
import Footer from '/src/components/Footer.vue'

const user = localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{}


</script>

<style scoped>

</style>