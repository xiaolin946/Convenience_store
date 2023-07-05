<template>
  <div style="height: 30%;margin-left: 5%;margin-right: 5%;">
    <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item v-for="i in imgs" :key="item">
        <img
            :src="i"
        style="height: 100%;width: 100%;margin-left: 5%;object-fit:contain;"
        height="895"
        width="370"/>
      </el-carousel-item>
    </el-carousel>
  </div>
  <div style="margin-left: 5%;margin-right: 5%;margin-top: 15%;margin-bottom: 3%;">
    <el-tabs class="demo-tabs" v-model="activeName">
      <el-tab-pane label="热门商品" name="first">
        <el-space :size="25" wrap>
          <el-col v-for="i in 10">
            <el-card :body-style="{ padding: '0px' }" shadow="hover" style="width: 28vh;margin-left: 8%;margin-right: 8%">
              <div style="height: 170px;width: 100%;background-image: url('https://buff.163.com/static/images/item_bg.png'); background-position:center;cursor: pointer;">
                <img
                    src="https://g.fp.ps.netease.com/market/file/5aa0c227aa49f1109b1e2342qFwi6740?fop=imageView/2/w/245/h/230"
                    style="height: 100%;width: 90%;margin-left: 5%;object-fit:contain;"
                    height="210"
                    width="138"
                />
              </div>
              <div style="height: 25px;width: 100%;background-color: #606266;margin-top: -20px">
                <a style="margin-left: 12px;margin-top: 5px;font-size: 12px;color: #CDD0D6">磨损: {{mdata.ms}}</a>
              </div>

              <div style="padding: 14px">
                <span>
                <el-text truncated>{{mdata.dname}}</el-text>
                </span>
                <div style="margin-top: 13px;line-height: 12px;display: flex;justify-content: space-between;align-items: center;">
                  <a style="font-size: 15px;color: #E6A23C;font-weight: bold;">¥ {{mdata.jg}}</a>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-space>
      </el-tab-pane>
      <el-tab-pane label="进入市场" name="second" @click="store"></el-tab-pane>
    </el-tabs>
  </div>
  <div style="margin-left: 5%;margin-right: 5%;margin-top: 5%;margin-bottom: 3%;">
    <el-tabs class="demo-tabs2" v-model="activeName2">
      <el-tab-pane label="最新上架" name="first2">
        <el-space :size="25" wrap>
          <el-col v-for="i in 10">
            <el-card :body-style="{ padding: '0px' }" shadow="hover" style="width: 28vh;margin-left: 8%;margin-right: 8%">
              <div style="height: 170px;width: 100%;background-image: url('https://buff.163.com/static/images/item_bg.png'); background-position:center;cursor: pointer;">
                <img
                    src="https://g.fp.ps.netease.com/market/file/5aa2b30c6f0494cffe147325LvWEQOCq?fop=imageView/2/w/245/h/230"
                    style="height: 100%;width: 90%;margin-left: 5%;object-fit:contain;"
                    height="210"
                    width="138"
                />
              </div>
              <div style="height: 25px;width: 100%;background-color: #606266;margin-top: -20px">
                <a style="margin-left: 12px;margin-top: 5px;font-size: 12px;color: #CDD0D6">磨损: 0.006727125495672226</a>
              </div>

              <div style="padding: 14px">
                <span>
                <el-text truncated>蝴蝶刀（★） | 渐变大理石 (崭新出厂)</el-text>
                </span>
                <div style="margin-top: 13px;line-height: 12px;display: flex;justify-content: space-between;align-items: center;">
                  <a style="font-size: 15px;color: #E6A23C;font-weight: bold;">¥ 18555</a>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-space>
      </el-tab-pane>
      <el-tab-pane label="进入市场" name="second2" @click="store"></el-tab-pane>
    </el-tabs>
  </div>
  <div style="margin-left: 5%;margin-right: 5%;margin-top: 5%;margin-bottom: 3%;">
    <el-tabs class="demo-tabs3" v-model="activeName3">
      <el-tab-pane label="限时优惠" name="first3">
        <el-space :size="25" wrap>
          <el-col v-for="i in 10">
            <el-card :body-style="{ padding: '0px' }" shadow="hover" style="width: 28vh;margin-left: 8%;margin-right: 8%">
              <div style="height: 170px;width: 100%;background-image: url('https://buff.163.com/static/images/item_bg.png'); background-position:center;cursor: pointer;">
                <img
                    src="https://spect.fp.ps.netease.com/file/6484edcaa368299f918480dfhWji0b6s04?fop=imageView/2/w/245/h/230"
                    style="height: 100%;width: 90%;margin-left: 5%;object-fit:contain;"
                    height="210"
                    width="138"
                />
              </div>
              <div style="height: 25px;width: 100%;background-color: #606266;margin-top: -20px">
                <a style="margin-left: 12px;margin-top: 5px;font-size: 12px;color: #CDD0D6">磨损: {{mdata.ms}}</a>
              </div>

              <div style="padding: 14px">
                <span>
                <el-text truncated>{{mdata.dname}}</el-text>
                </span>
                <div style="margin-top: 13px;line-height: 12px;display: flex;justify-content: space-between;align-items: center;">
                  <a style="font-size: 15px;color: #E6A23C;font-weight: bold;">¥ {{mdata.jg}}</a>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-space>
      </el-tab-pane>
      <el-tab-pane label="进入市场" name="second3" @click="store"></el-tab-pane>
    </el-tabs>
  </div>


</template>

<script setup>
import {reactive, ref} from 'vue'
import router from "../router/index.js";

const activeName = ref('first')
const activeName2 = ref('first2')
const activeName3 = ref('first3')


const imgs = reactive([
    "https://igstatic.igxe.cn/media/cms/20230612/8765b48ac8844f62bbdc467c73292e4c.jpg",
  "https://igstatic.igxe.cn/media/cms/20230615/005159a5a129421a9f78bc7da0d0d7b3.jpg",
  "https://igstatic.igxe.cn/media/cms/20220518/e9fe4c4aa5c145f387130f9818792e2c.png"])
const mdata = ref({
  dname: "AWP | 二西莫夫 (破损不堪)",
  ms: 0.385074257850647,
  jg: 895,
})

const store = () => {
  router.push("/store")
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}
</style>