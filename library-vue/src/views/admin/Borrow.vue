<template>
  <el-card>

    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="searchForm" ref="search" class="form-inline">
          <el-form-item label="书名" prop="tagName">
            <el-input v-model="searchForm.title"
                      placeholder="请输入书名"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="借阅人" prop="createName">
            <el-input v-model="searchForm.name"
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
    <el-table
        :data="borrows.list"
        style="width: 100%;margin: 10px 0;"
        :empty-text="pageData.emptyText"
    >
      <el-table-column label="书籍" prop="book.title"/>
      <el-table-column label="借阅人" prop="name"/>
      <el-table-column label="借阅时间">
        <template #default="scope">
          {{ $filters.formatUTC_DateTime(scope.row.startTime) }}
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="220">
        <template #default="scope">
          <el-select v-model="scope.row.state" @change="updBorrow(scope.row)">
            <el-option :value="1" label="已归还"/>
            <el-option :value="0" label="未归还"/>
          </el-select>
        </template>
      </el-table-column>
    </el-table>

    <el-row justify="end">
      <el-col :span="24">
        <el-pagination
            :currentPage="borrows.current"
            :page-size="borrows.size"
            :page-sizes="[10, 50, 100, 300]"
            :small="false"
            :disabled="false"
            :background="true"
            layout="total, sizes, prev, pager, next, jumper"
            :total="borrows.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            style="display: flex;justify-content: flex-end"
        />
      </el-col>
    </el-row>
  </el-card>
</template>

<script setup>
import {pageBorrow, retBooks} from "../../api/user";
import {onBeforeMount, reactive} from "vue";
import {ref} from "vue";
import {Refresh,Search} from "@element-plus/icons-vue"
import {userStore} from "../../store";
import {ElMessage} from "element-plus";


onBeforeMount(()=>{
  init()
})

const pageData = reactive({
  emptyText:'无借阅数据'
})

const borrows = reactive({
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
  pageBorrow(params).then(res=>{
    borrows.list=res.data.list
    borrows.size=res.data.size
    borrows.total=res.data.total
    borrows.pages=res.data.pages
    borrows.current=res.data.current
  })
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
  title: '',
  name: ''
})

const resetSearch = (form) => {
  form.resetFields()
  init()
}

const uid=userStore().userInfo.id
const updBorrow= (row) => {
  let obj=Object.assign({updateBy:uid},row)
  retBooks(obj).then(res=>{
    if(res.code==200){
      ElMessage.success('操作成功')
      init()
    }
  })
}


</script>

<style scoped>

</style>