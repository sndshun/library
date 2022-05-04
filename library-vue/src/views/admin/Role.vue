<template>
  <el-card>

    <el-row>
      <el-col :span="24">
        <el-button type="primary" :icon="Plus" plain @click="add(editForm,addRole)" v-permission="'role:add'">添加</el-button>
      </el-col>
    </el-row>

    <el-table
        :data="roles.list"
        style="width: 100%;margin: 10px 0;"
        :empty-text="pageData.emptyText"
        @selection-change="handleSelectionChange"
    >
      <el-table-column label="角色名" prop="roleName"/>
      <el-table-column label="描述" prop="description"/>
      <el-table-column label="操作" fixed="right" width="220">
        <template #default="scope">
          <el-button size="small" @click="edit(editForm,scope.row,updRole)" v-permission="'role:upd'">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)" v-permission="'role:del'">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row>
      <el-col :span="24">
        <el-pagination
            :currentPage="roles.current"
            :page-size="roles.size"
            :page-sizes="[10, 50, 100, 300]"
            :small="false"
            :disabled="false"
            :background="true"
            layout="total, sizes, prev, pager, next, jumper"
            :total="roles.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            style="display: flex;justify-content: flex-end"
        />
      </el-col>
    </el-row>


    <el-dialog v-model="pageData.editDialog">
      <el-form :model="role"
               ref="editForm"
               label-width="80px"
               :label-position="'left'"
      >
        <el-form-item v-show="false">
          <el-input v-model="role.id" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="角色名" prop="roleName" :rules="{required: true,trigger: 'blur',message:'角色名不能为空'}">
          <el-input v-model="role.roleName"
                    placeholder="请输入角色名"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="描述" prop="description" :rules="{required: true,trigger: 'blur',message:'角色描述不能为空'}">
          <el-input v-model="role.description"
                    placeholder="请输入角色描述"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit(editForm)">提交</el-button>
          <el-button @click="editForm.resetFields()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </el-card>

</template>

<script setup>
import {Plus} from '@element-plus/icons-vue'
import {addRole, delRole, getRolePage, updRole} from "../../api/permiss";
import {nextTick, onBeforeMount, reactive, ref} from "vue";
import {userStore} from "../../store";
import {ElMessage} from "element-plus";

const uid=userStore().userInfo.id



onBeforeMount(() => {
  init()
})

const pageData = reactive({
  emptyText: '未设置角色',
  batchDelete: true,
  detailsDialog: false,
  editDialog: false
})

const roles = reactive({
  current: 1,
  list: [],
  size: 0,
  total: 0,
  pages: 0
})
let page = {
  current: 1,
  size: 10,
}
const init = () => {
  let params = {
    ...page,
  }
  for (let key in params) {
    if (params[key] === ''||params[key] === null) {
      delete params[key]
    }
  }
  getRolePage(params).then(res => {
    roles.list = res.data.list
    roles.current = res.data.current
    roles.pages = res.data.pages
    roles.total = res.data.total
    roles.size = res.data.size
  })
}

let selectList=ref([]);
const handleSelectionChange = (val) => {
  selectList.value = val.map(item => {
    return item.id
  })
  console.log(selectList.value.length)
  pageData.batchDelete = !selectList.value.length > 0
}

const handleSizeChange = (val) => {
  page.size = val
  init()
}
const handleCurrentChange = (val) => {
  page.current = val
  init()
}




const role = ref({
  id: null,
  roleName: '',
  description: '',
  updateTime: '',
  createTime: '',
  createBy:null,
  updateBy:null
})


const editForm = ref(null)

const add=(form,add)=>{
  temp=add
  role.value={}
  pageData.editDialog=true
  if (form) {
    form.resetFields()
    form.clearValidate()
  }
  nextTick(()=>{
    role.value.createBy=uid
  })
}

const edit=(form,row,upd)=>{
  temp=upd
  pageData.editDialog=true
  if (form) {
    form.resetFields()
    form.clearValidate()
  }
  nextTick(()=>{
    role.value.id=row.id
    role.value.roleName=row.roleName
    role.value.description=row.description
    role.value.updateBy=uid
  })
}
let temp
const submit=(form)=>{
  form.validate((valid) => {
    if (valid) {
      temp(role.value).then(res=>{
        if (res.data == true) {
          ElMessage.success('操作成功')
          init();
          pageData.editDialog=false
        } else {
          ElMessage.success('操作失败')
        }
      })
    } else {
      return false
    }
  })
}
const del=(id)=>{
  delRole(id).then(res=>{
    if (res.data == true) {
      ElMessage.success('操作成功')
      init();
    } else {
      ElMessage.success('操作失败')
    }
  })
}

</script>

<style scoped>

</style>