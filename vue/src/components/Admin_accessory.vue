<template>
  <div style="margin-bottom: 2%;text-align: center">

  </div>
  <div style="margin-bottom: 5.5%;width: 100%">
    <div style="float: left;width: 83%;.input-with-select .el-input-group__prepend {background-color: var(--el-fill-color-blank);}">
      <el-input
          v-model="statusData.find1.skin"
          placeholder="饰品皮肤"
          class="input-with-select"
      >
        <template #prepend>
          <el-select v-model="statusData.find1.type" placeholder="饰品类别" style="width: 115px;" clearable @click="xf_type">
            <el-option
                v-for="item in statusData.f_type"
                :key="item.f_type"
                :label="item.f_type"
                :value="item.f_type"
            />
          </el-select>
          <el-select v-model="statusData.find1.name" placeholder="饰品名称" style="width: 115px;margin-left: 20px" clearable @click="xs_type">
            <el-option
                v-for="item in statusData.s_type"
                :key="item"
                :label="item"
                :value="item"
            />
          </el-select>
        </template>
        <template #append>
          <el-button :icon="Search" @click="cfind_Aac"/>
        </template>
      </el-input>
    </div>
    <div  style="float: left;margin-left: 2%;">
      <el-button type="primary" round @click="insert_ac">添加新饰品</el-button>
      <el-button :icon="Refresh" circle @click="refresh"/>
    </div>

  </div>
  <el-dialog
      v-model="centerDialogVisible"
      title="饰品信息"
      width="30%"

  >
    <el-form
        :model="statusData.formData"
        :rules="statusData.formrules"
        ref="ruleFormRef"
        :label-position="'right'"
        label-width="100px"
        style="max-width: 460px;"
    >
      <el-form-item style="margin-bottom: 4vh; width: 40vh" label="饰品类型" prop="type">
        <el-select v-model="statusData.formData.type" placeholder="选择类型" @click="ff_type">
          <el-option
              v-for="item in statusData.f_type"
              :key="item.f_type"
              :label="item.f_type"
              :value="item.f_type"
          />
        </el-select>
      </el-form-item>
      <el-form-item style="width: 40vh" label="饰品名称" prop="name">
        <el-select v-model="statusData.formData.name" placeholder="选择名称" @click="fs_type">
          <el-option
              v-for="item in statusData.s_type"
              :key="item"
              :label="item"
              :value="item"
          />
        </el-select>
      </el-form-item>
      <el-form-item style="width: 40vh" label="饰品皮肤" prop="skin">
        <el-input v-model="statusData.formData.skin" maxlength="20" autosize/>
      </el-form-item>
      <el-form-item style="width: 40vh" label="饰品品质" prop="quality">
        <el-input v-model="statusData.formData.quality" maxlength="3" autosize/>
      </el-form-item>
      <el-form-item style="width: 40vh" label="饰品图片" prop="img">
        <el-upload
            ref="uploadRef"
            class="upload-demo"
            action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
            :auto-upload="false"
        >
          <template #trigger>
            <el-button type="primary">select file</el-button>
          </template>
        </el-upload>
      </el-form-item>
      <el-form-item style="width: 40vh" label="图案模板" prop="paint_seed">
        <el-input v-model="statusData.formData.paint_seed" maxlength="3"/>
      </el-form-item>
      <el-form-item style="width: 40vh" label="皮肤编号" prop="paint_index">
        <el-input v-model="statusData.formData.paint_index" maxlength="3"/>
      </el-form-item>
      <el-form-item label="外观" prop="appearance" style="width: 40vh;">
        <el-select v-model="statusData.formData.appearance" placeholder="选择外观">
          <el-option label="崭新出厂" value="崭新出厂" />
          <el-option label="略有磨损" value="略有磨损" />
          <el-option label="久经沙场" value="久经沙场" />
          <el-option label="战痕累累" value="战痕累累" />
          <el-option label="破损不堪" value="破损不堪" />
        </el-select>
      </el-form-item>
      <el-form-item style="width: 40vh" label="磨损" prop="abrasion">
        <el-input v-model="statusData.formData.abrasion" maxlength="20"/>
      </el-form-item>
      <el-form-item style="width: 40vh" label="属性描述" prop="attribute_description">
        <el-input v-model="statusData.formData.attribute_description" type="textarea"/>
      </el-form-item>
      <el-form-item style="width: 40vh" label="价格" prop="price">
        <el-input v-model="statusData.formData.price"/>
      </el-form-item>
      <el-form-item label="饰品状态" prop="status">
        <el-radio-group v-model="statusData.formData.status">
          <el-radio label="1">上架</el-radio>
          <el-radio label="0">下架</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible=false">取消</el-button>
        <el-button type="primary" @click="iou">确认</el-button>
      </span>
    </template>
  </el-dialog>

  <el-table :data="statusData.tableData" style="height:85%; width: 100%; margin-bottom: 5px">
    <el-table-column fixed prop="id" label="ID" width="60" />
    <el-table-column prop="type" label="饰品类型" width="80" />
    <el-table-column prop="name" label="饰品名称" width="80" />
    <el-table-column prop="skin" label="饰品皮肤" width="90" />
    <el-table-column prop="quality" label="饰品品质" width="60" />
    <el-table-column prop="img" label="饰品图片" width="60" />
    <el-table-column prop="paint_seed" label="图案模板" width="60" />
    <el-table-column prop="paint_index" label="皮肤编号" width="60" />
    <el-table-column prop="appearance" label="外观" width="100" />
    <el-table-column prop="abrasion" label="磨损" width="90" />
    <el-table-column prop="attribute_description" label="属性描述" width="160">
      <template #default="scope">
        <el-popover
            placement="bottom"
            title="属性描述"
            :width="500"
            trigger="click"
            :content="scope.row.attribute_description"
        >
          <template #reference>
            <el-button>查看描述内容</el-button>
          </template>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column prop="price" label="价格" width="80" />
    <el-table-column prop="status" label="状态" width="60" />
    <el-table-column fixed="right" label="数据操作" width="160">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="update_ac(scope.row)">更新数据</el-button>
        <el-popconfirm
            width="10"
            confirm-button-text="Yes"
            cancel-button-text="No"
            :hide-icon="true"
            title="确定删除该饰品吗?"
            @confirm="delete_ac(scope.row)"
            @cancel=""
        >
          <template #reference>
            <el-button link type="primary" size="small">删除饰品</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[1, 3, 5, 10]"
      :small="false"
      :disabled="false"
      :background="false"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />
</template>

<script setup>
import {getCurrentInstance, reactive, ref} from "vue";
import {Refresh,Search} from "@element-plus/icons-vue";
import {ElNotification} from "element-plus";
import request from "../request.js";
const {proxy} = getCurrentInstance();





const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)

const statusData = reactive({
  find1: {
    type: '',
    name: '',
    skin: ''
  },
  f_type: [],
  s_type: {},
  tableData: [],
  formData: {},
  formrules: {
    type: [
      {required: true, message: '请选择饰品类型', trigger: 'blur'}
    ],
    name: [
      {required: true, message: '请输入用户名', trigger: 'blur'},
      {max: 20, message: '超出最大限制', trigger: 'blur'}
    ],
    appearance: [
      {required: true, message: '请选择外观', trigger: 'blur'},
    ],
    price: [
      {required: true, message: '请输入价格', trigger: 'blur'},
    ],
    status: [
      {required: true, message: '请选择状态', trigger: 'blur'},
    ],
  },
})

const fresh = () =>{
  request.get("/type/find_ftype").then(res => {
    statusData.f_type = res.data
    for (let i = 0; i < statusData.f_type.length; i++) {
      statusData.f_type[i].s_type = statusData.f_type[i].s_type.split(';')
    }
  })
  request.get("/accessory/findPage_Aac", {
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
    }
  }).then(res => {
    statusData.tableData = res.data.data
    total.value = res.data.total
  })

}
fresh()

const xf_type = () => {
  statusData.s_type = []
  statusData.find1.name = ''
}

const xs_type = () => {
  for (let i = 0; i < statusData.f_type.length; i++) {
    if(statusData.find1.type === statusData.f_type[i].f_type){
      statusData.s_type = statusData.f_type[i].s_type
      break
    }
  }
}

const ff_type = () => {
  statusData.s_type = []
  statusData.formData.name = ''
}
const fs_type = () => {
  for (let i = 0; i < statusData.f_type.length; i++) {
    if(statusData.formData.type === statusData.f_type[i].f_type){
      statusData.s_type = statusData.f_type[i].s_type
      break
    }
  }
}


const centerDialogVisible = ref(false)
const insert_ac = () => {
  centerDialogVisible.value = true
  statusData.formData = {
    id: '',
    type: '',
    name: '',
    skin: '',
    quality: '',
    img: '',
    paint_seed: '',
    paint_index: '',
    appearance: '',
    abrasion: '',
    attribute_description: '',
    price: '',
    status: ''
  }
}

const cfind_Aac = () => {
  request.get("/accessory/cfindPage_Aac", {
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
      type: statusData.find1.type==='' ? "%" : statusData.find1.type,
      name: statusData.find1.name==='' ? "%" : statusData.find1.name,
      skin: statusData.find1.skin==='' ? "%" : statusData.find1.skin
    }
  }).then(res => {
    statusData.tableData = res.data.data
    total.value = res.data.total
  })
}

const update_ac = (row) => {
  centerDialogVisible.value = true
  statusData.formData = JSON.parse(JSON.stringify(row))
}
const iou = () => {
  centerDialogVisible.value = false
  proxy.$refs.ruleFormRef.validate((valid) => {
    if(valid) {
      if (statusData.formData.id !== null) {
        request.post('/accessory/insert', statusData.formData).then(res => {
          if (res.code === "200" && res.data === true) {
            ElNotification({
              title: '添加饰品成功',
              type: 'success',
              message: "已添加 " + statusData.formData.type + " 饰品: " + statusData.formData.name
            })
            fresh()
          }else{
            ElNotification({
              title: '添加饰品失败',
              type: 'error',
              message: "数据格式有误或服务器异常"
            })
          }
        })
      }else{
        request.put('/accessory/update',statusData.formData).then(res => {
          if(res.code === "200" && res.data === true){
            ElNotification({
              title: '更新饰品数据成功',
              type: 'success',
              message: statusData.formData.type + " 饰品: " + statusData.formData.name +" 数据已更新"
            })
            fresh()
          }else{
            ElNotification({
              title: '更新饰品数据失败',
              type: 'error',
              message: "数据格式有误或服务器异常"
            })
          }
        })
      }
    }else{
      ElNotification({
        title: '操作数据失败',
        type: 'error',
        message: "数据格式有误或服务器异常"
      })
    }
  })
}
const refresh = () => {
  fresh()
  ElNotification({
    title: '刷新成功',
    type: 'success',
    message: "已刷新饰品数据"
  })
}
const delete_ac = (row) => {
  request.delete('/accessory/delete', {
    params: {
      id: row.id
    }
  }).then(res => {
    if (res.code === "200" && res.data === true) {
      ElNotification({
        title: '删除饰品成功',
        type: 'success',
        message: row.type + " 饰品: " + row.name + " 数据已删除"
      })
      fresh()
    } else {
      ElNotification({
        title: '删除饰品失败',
        type: 'error',
        message: "权限不足或服务器异常"
      })
    }
  })
}

const handleSizeChange = (val) => {
  pageSize.value = val
  if(statusData.find1.type!=='' || statusData.find1.name!=='' || statusData.find1.skin!==''){
    cfind_Aac()
  }else{
    fresh()
  }
}
const handleCurrentChange = (val) => {
  currentPage.value = val
  if(statusData.find1.type!=='' || statusData.find1.name!=='' || statusData.find1.skin!==''){
    cfind_Aac()
  }else{
    fresh()
  }
}


</script>

<style scoped>

</style>