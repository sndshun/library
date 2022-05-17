<template>
  <el-card>
    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="searchForm" ref="search" class="form-inline">
          <el-form-item label="凭证名" prop="typeName">
            <el-input v-model="searchForm.credentialsName"
                      placeholder="请输入凭证名"
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
        <el-button type="primary" :icon="Plus" plain @click="add(editForm,addCredentials)" v-permission="'credentials:add'">添加</el-button>
        <el-button type="danger" :icon="Delete" :disabled="pageData.batchDelete" plain @click="batchDel" v-permission="'credentials:del'">删除&nbsp;
          <b v-show="selectList.length">
            ({{selectList.length}})
          </b>
        </el-button>
      </el-col>
    </el-row>

    <el-table
        :data="credentialsList.list"
        style="width: 100%;margin: 10px 0;"
        :empty-text="pageData.emptyText"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>
      <el-table-column label="凭证名" prop="credentialsName"/>
      <el-table-column label="押金" prop="credentialsPrice"/>
      <el-table-column label="可借阅数量" prop="borrowingCount"/>
      <el-table-column label="可借阅时间（天）" prop="borrowingTime"/>
      <el-table-column label="操作" fixed="right" width="220">
        <template #default="scope">
          <el-button size="small" @click="details(scope.row)">详情</el-button>
          <el-button size="small" @click="edit(editForm,scope.row,updCredentials)" v-permission="'credentials:upd'">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)" v-permission="'credentials:del'">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row>
      <el-col :span="24">
        <el-pagination
            :currentPage="credentialsList.current"
            :page-size="credentialsList.size"
            :page-sizes="[10, 50, 100, 300]"
            :small="false"
            :disabled="false"
            :background="true"
            layout="total, sizes, prev, pager, next, jumper"
            :total="credentialsList.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            style="display: flex;justify-content: flex-end"
        />
      </el-col>
    </el-row>

    <el-dialog v-model="pageData.detailsDialog" width="60%" center>

      <el-descriptions
          title="凭证详情"
          :column="4"
          border
      >
        <el-descriptions-item label="凭证名" :span="4">{{ credentials.credentialsName }}</el-descriptions-item>
        <el-descriptions-item label="押金" :span="2">{{ credentials.credentialsPrice }}（元）</el-descriptions-item>
        <el-descriptions-item label="可借阅数量" :span="1">{{ credentials.borrowingCount }}</el-descriptions-item>
        <el-descriptions-item label="可借阅天数" :span="1">{{ credentials.borrowingTime }}</el-descriptions-item>
        <el-descriptions-item label="创建时间" :span="2">{{ $filters.formatUTC_DateTime(credentials.createTime) }}
        </el-descriptions-item>
        <el-descriptions-item label="修改时间" :span="2">{{ $filters.formatUTC_DateTime(credentials.updateTime) }}
        </el-descriptions-item>
      </el-descriptions>

      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="pageData.detailsDialog = false"
        >确定</el-button
        >
      </span>
      </template>
    </el-dialog>

    <el-dialog v-model="pageData.editDialog">
      <el-form :model="credentials"
               ref="editForm"
               label-width="80px"
               :label-position="'left'"
      >
        <el-form-item v-show="false">
          <el-input v-model="credentials.id" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="凭证名"
                      prop="credentialsName"
                      :rules="{required: true,trigger: 'blur',message:'凭证名不能为空'}"
        >
          <el-input v-model="credentials.credentialsName"
                    placeholder="请输入凭证名"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="押金"
                      prop="credentialsPrice"
                      :rules="{required: true,trigger: 'blur',message:'押金不能为空'}"
        >
          <el-input-number v-model="credentials.credentialsPrice"
                    placeholder="请输入押金"
                    autocomplete="off"
                           :precision="2"
                           :min="1"
          />
        </el-form-item>
        <el-form-item label="借阅数量"
                      prop="borrowingCount"
                      :rules="{required: true,trigger: 'blur',message:'借阅数量不能为空'}"
        >
          <el-input-number v-model="credentials.borrowingCount"
                           placeholder="请输入借阅数量"
                           autocomplete="off"
                           :min="1"
          />
        </el-form-item>
        <el-form-item label="借阅天数"
                      prop="borrowingTime"
                      :rules="{required: true,trigger: 'blur',message:'借阅天数不能为空'}"
        >
          <el-input-number v-model="credentials.borrowingTime"
                           placeholder="请输入借阅天数"
                           autocomplete="off"
                           :min="1"
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
import {Delete, Plus, Refresh, Search} from '@element-plus/icons-vue'
import {addCredentials, delCredentials, getCredentialsPage, updCredentials} from "../../api/credentials";
import {nextTick, onBeforeMount, reactive, ref} from "vue";
import {userStore} from "../../store";
import {ElMessage} from "element-plus";

const uid=userStore().userInfo.id



onBeforeMount(() => {
  init()
})

const pageData = reactive({
  emptyText: '未设置书籍分类',
  batchDelete: true,
  detailsDialog: false,
  editDialog: false
})

const credentialsList = reactive({
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
    if (params[key] === ''||params[key] === null) {
      delete params[key]
    }
  }
  getCredentialsPage(params).then(res => {
    credentialsList.list = res.data.list
    credentialsList.current = res.data.current
    credentialsList.pages = res.data.pages
    credentialsList.total = res.data.total
    credentialsList.size = res.data.size
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

const search = ref(null)
const searchForm = reactive({
  credentialsName: '',
})
const resetSearch = (form) => {
  form.resetFields()
  init()
}
const batchDel=()=>{
  delCredentials(selectList.value).then(res=>{
    if (res.data == true) {
      ElMessage.success('操作成功')
      init();
    } else {
      ElMessage.success('操作失败')
    }
  })
}


const credentials = ref({
  id: null,
  credentialsName: '',
  credentialsPrice: '',
  borrowingCount: '',
  borrowingTime: '',
  updateTime: '',
  createTime: '',
  createName: '',
  updateName: '',
  createBy:null,
  updateBy:null
})
const details = (row) => {
  credentials.value = row
  pageData.detailsDialog = true
}

const editForm = ref(null)

const add=(form,add)=>{
  temp=add
  credentials.value={}
  pageData.editDialog=true
  if (form) {
    form.resetFields()
    form.clearValidate()
  }
  nextTick(()=>{
    credentials.value.createBy=uid
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
    credentials.value.id=row.id
    credentials.value.credentialsName=row.credentialsName
    credentials.value.credentialsPrice=row.credentialsPrice
    credentials.value.borrowingCount=row.borrowingCount
    credentials.value.borrowingTime=row.borrowingTime
    credentials.value.updateBy=uid
  })
}
let temp
const submit=(form)=>{
  form.validate((valid) => {
    if (valid) {
      temp(credentials.value).then(res=>{
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
  delCredentials([id]).then(res=>{
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