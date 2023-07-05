<template>
  <el-container class="layout-container" style="height: 100vh; width: 100%; ">
    <el-container>
      <el-header>
        <Header/>
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
import Header from '/src/components/Header.vue'
import Footer from '/src/components/Footer.vue'
import router from "../router/index.js";

const user = localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{}

console.log(user)
const fresh = () => {
  if(user.status === 'user' && user.username !== null){
    router.push("/user/main")
  }
}
fresh()

</script>

<style>

</style>