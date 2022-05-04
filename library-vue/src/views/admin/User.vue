<template>
  <el-card>
    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="searchForm" ref="search" class="form-inline">
          <el-form-item label="用户名" prop="name">
            <el-input v-model="searchForm.name"
                      placeholder="请输入用户名"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="角色" prop="roleId">
            <el-select v-model="searchForm.roleId">
              <el-option value="" label="请选择"></el-option>
              <el-option
                      v-for="item in roles"
                      :key="item.id"
                      :label="item.description"
                      :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="searchForm.sex">
              <el-option value="">请选择</el-option>
              <el-option value="1" label="男"></el-option>
              <el-option value="2" label="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="searchForm.email"
                      placeholder="请输入用户邮箱"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="searchForm.phone"
                      placeholder="请输入用户电话"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="searchForm.address"
                      placeholder="请输入用户地址"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :icon="Search" @click="init">搜索</el-button>
            <el-button :icon="Refresh" @click="resetSearch(search)">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <el-button type="danger" :icon="Delete" :disabled="pageData.batchDelete" plain @click="batchDel"
                   v-permission="'user:del'">删除&nbsp;
          <b v-show="selectList.length">
            ({{ selectList.length }})
          </b>
        </el-button>
      </el-col>
    </el-row>

    <el-table
        :data="users.list"
        style="width: 100%;margin: 10px 0;"
        :empty-text="pageData.emptyText"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>
      <el-table-column label="昵称" prop="name"/>
      <el-table-column label="性别">
        <template #default="scope">
          <span v-if="scope.row.sex==1">男</span>
          <span v-if="scope.row.sex==2">女</span>
        </template>
      </el-table-column>
      <el-table-column label="电话" prop="phone"/>
      <el-table-column label="邮箱" prop="email" show-overflow-tooltip/>
      <el-table-column label="角色" show-overflow-tooltip>
        <template #default="scope">
          <el-tag v-if="scope.row.id==1">系统管理员</el-tag>
          <el-tag v-for="(item,index) in scope.row.roles">{{ item.description }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="地址" prop="address" show-overflow-tooltip/>
      <el-table-column label="操作" fixed="right" width="220">
        <template #default="scope">
          <span v-if="scope.row.id!==1">
            <el-button size="small" @click="updRole(scope.row)" v-permission="'user>role:upd'">角色</el-button>
                      <el-button size="small" @click="edit(editForm,scope.row,updateUser)" v-permission="'user:upd'">编辑</el-button>
            <el-button size="small" type="danger" @click="del(scope.row.id)" v-permission="'user:del'">删除</el-button>
          </span>
        </template>
      </el-table-column>
    </el-table>

    <el-row>
      <el-col :span="24">
        <el-pagination
            :currentPage="users.current"
            :page-size="users.size"
            :page-sizes="[10, 50, 100, 300]"
            :small="false"
            :disabled="false"
            :background="true"
            layout="total, sizes, prev, pager, next, jumper"
            :total="users.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            style="display: flex;justify-content: flex-end"
        />
      </el-col>
    </el-row>

    <el-dialog v-model="pageData.roleDialog" width="60%" center>
      <el-space>
        <el-tag
            v-for="(item,index) in userRoles"
            :key="index"
            closable
            @close="delUserRole(item.id,index)"
        >
          {{ item.description }}
        </el-tag>
      </el-space>
      <el-divider/>
      <el-space>
        <el-select v-model="tempRole">
          <el-option
              v-for="item in roles"
              :key="item.id"
              :label="item.description"
              :value="item.id"
          ></el-option>
        </el-select>
        <el-button type="primary" :disabled="isAddRole" @click="addUserRole">添加</el-button>
      </el-space>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="pageData.roleDialog = false"
        >确定</el-button
        >
      </span>
      </template>
    </el-dialog>

    <el-dialog v-model="pageData.editDialog">
      <el-form :model="user"
               ref="editForm"
               label-width="80px"
               :label-position="'left'"
      >
        <el-form-item v-show="false">
          <el-input v-model="user.id" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="昵称" prop="name"
                      :rules="[
                        {required: true, message: '请输入昵称', trigger: 'blur'},
                        {min:3, message: '昵称最少3个字符', trigger: 'blur'},
                        {max: 11, message: '昵称长度过长', trigger: 'blur'}]"
        >
          <el-input v-model="user.name" auto-complete="off" placeholder="请输入昵称" clearable></el-input>

        </el-form-item>
        <el-form-item label="邮箱" prop="email"
                      :rules="[
                        {required: true, message: '请输入邮箱', trigger: 'blur'},
                        {pattern:/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/, message: '请输入正确的邮箱格式', trigger: 'blur'}]"
        >
          <el-input v-model="user.email" auto-complete="off" placeholder="请输入邮箱" clearable></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address"
        >
          <el-input v-model="user.address" auto-complete="off" placeholder="请输入地址" clearable></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone"
                      :rules="[{pattern:/^1[3456789]\d{9}$/, message: '请输入正确的电话格式', trigger: 'blur'}]"
        >
          <el-input v-model="user.phone" auto-complete="off" placeholder="请输入电话" clearable></el-input>
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
import {Delete, Refresh, Search} from '@element-plus/icons-vue'
import {addRoleToUser, delRoleToUser, delUser, getUserPage, roleList, updateUser} from '../../api/user'
import {computed, nextTick, onBeforeMount, reactive, ref} from "vue";
import {userStore} from "../../store";
import {ElMessage} from "element-plus";

const uid = userStore().userInfo.id


onBeforeMount(() => {
  init()
  getRoleList()
})

const pageData = reactive({
  emptyText: '暂无数据',
  batchDelete: true,
  roleDialog: false,
  editDialog: false
})

const users = reactive({
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
    ...searchForm
  }
  for (let key in params) {
    if (params[key] === '' || params[key] === null) {
      delete params[key]
    }
  }
  getUserPage(params).then(res => {
    users.list = res.data.list
    users.current = res.data.current
    users.pages = res.data.pages
    users.total = res.data.total
    users.size = res.data.size
  })
}


const roles=ref([]);
const getRoleList=()=>{
  roleList().then(res=>{
    roles.value=res.data
  })
}

let selectList = ref([]);
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

const search = ref(null)
const searchForm = reactive({
  name: '',
  roleId: '',
  sex:'',
  email:'',
  phone:'',
  address:''
})
const resetSearch = (form) => {
  form.resetFields()
  init()
}
const batchDel = () => {
  delUser(selectList.value).then(res => {
    if (res.data == true) {
      ElMessage.success('操作成功')
      init();
    } else {
      ElMessage.success('操作失败')
    }
  })
}


const user = ref({
  id: null,
  name: '',
  email:'',
  phone:'',
  address:'',
  updateTime: '',
  createTime: '',
  createBy: null,
  updateBy: null
})
const userRoles= ref([])
const updRole = (row) => {
  userRoles.value = row.roles
  user.value = row
  pageData.roleDialog = true
}

const tempRole = ref(null)
const isAddRole=computed(()=>{
  return tempRole.value===null||userRoles.value.some(item=>item.id===tempRole.value)
})
const addUserRole= () => {
  addRoleToUser({userId:user.value.id,roleId:tempRole.value,updateBy:uid}).then(res=>{
    if(res.data==true){
      ElMessage.success('操作成功')
      userRoles.value.push(roles.value.find(item=>item.id===tempRole.value))
    }else{
      ElMessage.error('操作失败')
    }
  })

}
const delUserRole=(id,index)=>{
  delRoleToUser(id).then(res=>{
    if(res.data==true){
      ElMessage.success('操作成功')
      userRoles.value.splice(index,1)
    }else{
      ElMessage.error('操作失败')
    }
  })
}


const editForm = ref(null)

const add = (form, add) => {
  temp = add
  user.value = {}
  pageData.editDialog = true
  if (form) {
    form.resetFields()
    form.clearValidate()
  }
  nextTick(() => {
    user.value.createBy = uid
  })
}

const edit = (form, row, upd) => {
  temp = upd
  pageData.editDialog = true
  if (form) {
    form.resetFields()
    form.clearValidate()
  }
  nextTick(() => {
    user.value.id = row.id
    user.value.name = row.name
    user.value.phone = row.phone
    user.value.email = row.email
    user.value.updateBy = uid
  })
}
let temp
const submit = (form) => {
  form.validate((valid) => {
    if (valid) {
      temp(user.value).then(res => {
        if (res.data == true) {
          ElMessage.success('操作成功')
          init();
          pageData.editDialog = false
        } else {
          ElMessage.success('操作失败')
        }
      })
    } else {
      return false
    }
  })
}
const del = (id) => {
  delUser([id]).then(res => {
    if (res.data == true) {
      ElMessage.success('操作成功')
      init();
    } else {
      ElMessage.error('操作失败')
    }
  })
}

</script>

<style scoped>

</style>