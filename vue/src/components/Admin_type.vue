<template>
  <div style="margin-bottom: 2%;text-align: center">

  </div>
  <div style="margin-bottom: 5.5%;width: 100%">
    <div style="float: left;width: 83%;.input-with-select .el-input-group__prepend {background-color: var(--el-fill-color-blank);}">
      <el-input
          v-model="statusData.find.n_type"
          placeholder="类别饰品数量"
          class="input-with-select"
      >
        <template #prepend>
          <el-select v-model="statusData.find.f_type" placeholder="饰品类别" style="width: 115px;" clearable @click="xf_type">
            <el-option
                v-for="item in statusData.f_type"
                :key="item.f_type"
                :label="item.f_type"
                :value="item.f_type"
            />
          </el-select>
          <el-select v-model="statusData.find.s_type" placeholder="饰品名称" style="width: 115px;margin-left: 20px" clearable @click="xs_type">
            <el-option
                v-for="item in statusData.s_type"
                :key="item"
                :label="item"
                :value="item"
            />
          </el-select>
        </template>
        <template #append>
          <el-button :icon="Search" @click="find_type"/>
        </template>
      </el-input>
    </div>
    <div  style="float: left;margin-left: 2%;">
      <el-button type="primary" round @click="insert_type">添加新类别</el-button>
      <el-button :icon="Refresh" circle @click="refresh"/>
    </div>

  </div>
  <el-dialog
      v-model="centerDialogVisible"
      title="添加类别"
      width="35%"
      align-center
  >
    <el-form
        :model="statusData.formData"
        :rules="statusData.formrules"
        ref="ruleFormRef"
        :label-position="'right'"
        label-width="100px"
        style="max-width: 460px;"
    >
      <el-form-item style="margin-bottom: 4vh; width: 50vh" label="一级类型" prop="type">
        <el-input
            v-model="statusData.formData.f_type"
            placeholder="Please input"

        >
          <template #append>
            <el-select v-model="statusData.formData.f_type" placeholder="已有类别" style="width: 115px;" clearable>
              <el-option
                  v-for="item in statusData.tableData"
                  :key="item.f_type"
                  :label="item.f_type"
                  :value="item.f_type"
              />
            </el-select>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item style="width: 50vh" label="二级类别" prop="name">
        <el-input v-model="statusData.formData.s_type" maxlength="10"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible=false">取消</el-button>
        <el-button type="primary" @click="iou">确认</el-button>
      </span>
    </template>
  </el-dialog>

  <el-table :data="statusData.tableData" style="height:72%; width: 100%; margin-bottom: 5px">
    <el-table-column fixed prop="id" label="ID" width="180" />
    <el-table-column prop="f_type" label="一级类别" width="220" />
    <el-table-column prop="s_type" label="二级类别" width="220" />
    <el-table-column prop="n_type" label="类别饰品数" width="320" />
    <el-table-column fixed="right" label="数据操作" width="160">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="update_type(scope.row)">更新数据</el-button>
        <el-popconfirm
            width="10"
            confirm-button-text="Yes"
            cancel-button-text="No"
            :hide-icon="true"
            title="确定删除该类别吗?"
            @confirm="delete_type(scope.row)"
            @cancel=""
        >
          <template #reference>
            <el-button link type="primary" size="small">删除类别</el-button>
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
  tableData: [],
  f_type: [],
  s_type: {},
  find: {
    f_type: '',
    s_type: '',
    n_type: ''
  },
  formData: {},
  formrules: {
    f_type: [
      {required: true, message: '请选择一级类别', trigger: 'blur'}
    ],
    s_type: [
      {required: true, message: '请输入二级类别', trigger: 'blur'},
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
  request.get("/type/findPage_ftype", {
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
    }
  }).then(res => {
    statusData.tableData = res.data.data
    total.value = res.data.total
    statusData.find = {
      f_type: '',
      s_type: '',
      n_type: ''
    }
  })
}
fresh()

const xf_type = () => {
  statusData.s_type = []
  statusData.find.s_type = ''
}

const xs_type = () => {
  for (let i = 0; i < statusData.f_type.length; i++) {
    if(statusData.find.f_type === statusData.f_type[i].f_type){
      statusData.s_type = statusData.f_type[i].s_type
      break
    }
  }
}

const find_type = () => {
  request.get("/type/cfindPage_ftype", {
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
      f_type: statusData.find.f_type==='' ? "%" : statusData.find.f_type,
      s_type: statusData.find.s_type==='' ? "%" : statusData.find.s_type,
      n_type: statusData.find.n_type==='' ? "%" : statusData.find.n_type
    }
  }).then(res => {
    statusData.tableData = res.data.data
    total.value = res.data.total
  })
}

const centerDialogVisible = ref(false)
const insert_type = () => {
  centerDialogVisible.value = true
  statusData.formData = {
    n_type: 0
  }
}
const update_type = (row) => {
  centerDialogVisible.value = true
  statusData.formData = JSON.parse(JSON.stringify(row))
}
const iou = () => {
  centerDialogVisible.value = false
  proxy.$refs.ruleFormRef.validate((valid) => {
    if(valid) {
      if (statusData.formData.id == null) {
        request.post('/type/insert', statusData.formData).then(res => {
          if (res.code === "200" && res.data === true) {
            ElNotification({
              title: '添加类别成功',
              type: 'success',
              message: "已添加类别 " + statusData.formData.f_type + " " + statusData.formData.s_type
            })
            fresh()
          }else{
            ElNotification({
              title: '添加类别失败',
              type: 'error',
              message: "数据格式有误或服务器异常"
            })
          }
        })
      }else{
        request.put('/type/update',statusData.formData).then(res => {
          if(res.code === "200" && res.data === true){
            ElNotification({
              title: '更新类别数据成功',
              type: 'success',
              message: statusData.formData.f_type + " 数据已更新"
            })
            fresh()
          }else{
            ElNotification({
              title: '更新类别数据失败',
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
    message: "已刷新类别数据"
  })
}
const delete_type = (row) => {
  request.delete('/type/delete', {
    params: {
      id: row.id
    }
  }).then(res => {
    if (res.code === "200" && res.data === true) {
      ElNotification({
        title: '删除类别成功',
        type: 'success',
        message: row.f_type + " " + row.s_type + " 数据已删除"
      })
      fresh()
    } else {
      ElNotification({
        title: '删除类别失败',
        type: 'error',
        message: "权限不足或服务器异常"
      })
    }
  })
}

const handleSizeChange = (val) => {
  pageSize.value = val
  if(statusData.find.f_type!=='' || statusData.find.s_type!=='' || statusData.find.n_type!==''){
    cfind_ftype()
  }else{
    fresh()
  }
}
const handleCurrentChange = (val) => {
  currentPage.value = val
  fresh()
}


</script>

<style scoped>

</style>