<template>
  <el-card>
    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="searchForm" ref="search" class="form-inline">
          <el-form-item label="分类名" prop="typeName">
            <el-input v-model="searchForm.typeName"
                      placeholder="请输入书籍分类"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="创建者" prop="createName">
            <el-input v-model="searchForm.createName"
                      placeholder="请输入用户姓名"
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
        <el-button type="primary" :icon="Plus" plain @click="add(editForm,addBookType)">添加</el-button>
        <el-button type="danger" :icon="Delete" :disabled="pageData.batchDelete" plain @click="batchDel">删除&nbsp;
          <b v-show="selectList.length">
            ({{selectList.length}})
          </b>
        </el-button>
      </el-col>
    </el-row>

    <el-table
        :data="bookTypes.list"
        style="width: 100%;margin: 10px 0;"
        :empty-text="pageData.emptyText"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>
      <el-table-column label="书籍分类" prop="typeName"/>
      <el-table-column label="创建人" prop="createName"/>
      <el-table-column label="操作" fixed="right" width="220">
        <template #default="scope">
          <el-button size="small" @click="details(scope.row)">详情</el-button>
          <el-button size="small" @click="edit(editForm,scope.row,updBookType)">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row>
      <el-col :span="24">
        <el-pagination
            :currentPage="bookTypes.current"
            :page-size="bookTypes.size"
            :page-sizes="[10, 50, 100, 300]"
            :small="false"
            :disabled="false"
            :background="true"
            layout="total, sizes, prev, pager, next, jumper"
            :total="bookTypes.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            style="display: flex;justify-content: flex-end"
        />
      </el-col>
    </el-row>

    <el-dialog v-model="pageData.detailsDialog" width="60%" center>

      <el-descriptions
          title="分类详情"
          :column="4"
          border
      >
        <el-descriptions-item label="分类名" :span="4">{{ bookType.typeName }}</el-descriptions-item>
        <el-descriptions-item label="创建人" :span="2">{{ bookType.createName }}</el-descriptions-item>
        <el-descriptions-item label="创建时间" :span="2">{{ $filters.formatUTC_DateTime(bookType.createTime) }}
        </el-descriptions-item>
        <el-descriptions-item label="修改人" :span="2">{{ bookType.updateName }}</el-descriptions-item>
        <el-descriptions-item label="修改时间" :span="2">{{ $filters.formatUTC_DateTime(bookType.updateTime) }}
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
      <el-form :model="bookType"
               ref="editForm"
               label-width="80px"
               :label-position="'left'"
      >
        <el-form-item v-show="false">
          <el-input v-model="bookType.id" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="分类名" prop="typeName" :rules="{required: true,trigger: 'blur',message:'分类名不能为空'}">
          <el-input v-model="bookType.typeName"
                    placeholder="请输入书籍分类"
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
import {Search, Refresh, Plus, Delete} from '@element-plus/icons-vue'
import {getBookTypePage, addBookType, updBookType, delBookType, batchDelBookType} from "../../api/book";
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

const bookTypes = reactive({
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
  getBookTypePage(params).then(res => {
    bookTypes.list = res.data.list
    bookTypes.current = res.data.current
    bookTypes.pages = res.data.pages
    bookTypes.total = res.data.total
    bookTypes.size = res.data.size
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
  typeName: '',
  createName: ''
})
const resetSearch = (form) => {
  form.resetFields()
  init()
}
const batchDel=()=>{
  batchDelBookType(selectList.value).then(res=>{
    if (res.data == true) {
      ElMessage.success('操作成功')
      init();
    } else {
      ElMessage.success('操作失败')
    }
  })
}


const bookType = ref({
  id: null,
  typeName: '',
  updateTime: '',
  createTime: '',
  createName: '',
  updateName: '',
  createBy:null,
  updateBy:null
})
const details = (row) => {
  bookType.value = row
  pageData.detailsDialog = true
}

const editForm = ref(null)

const add=(form,add)=>{
  temp=add
  bookType.value={}
  pageData.editDialog=true
  if (form) {
    form.resetFields()
    form.clearValidate()
  }
  nextTick(()=>{
    bookType.value.createBy=uid
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
    bookType.value.id=row.id
    bookType.value.typeName=row.typeName
    bookType.value.updateBy=uid
  })
}
let temp
const submit=(form)=>{
  form.validate((valid) => {
    if (valid) {
      temp(bookType.value).then(res=>{
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
  delBookType(id).then(res=>{
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