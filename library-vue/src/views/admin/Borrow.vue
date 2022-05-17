<template>
  <el-card>

    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="searchForm" ref="search" class="form-inline">
          <el-form-item label="订单号" prop="id">
            <el-input v-model="searchForm.id"
                      placeholder="请输入订单"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="书籍" prop="title">
            <el-input v-model="searchForm.title"
                      placeholder="请输入书籍"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="状态" prop="name">
            <el-select v-model="searchForm.state">
              <el-option label="全部" value=""/>
              <el-option :value="0" label="未归还"/>
              <el-option :value="1" label="以归还"/>
              <el-option :value="2" label="延时归还"/>
              <el-option :value="3" label="书籍损坏"/>
              <el-option :value="4" label="书籍丢失"/>
            </el-select>
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
      <el-table-column label="订单号" prop="id"/>
      <el-table-column label="书籍" prop="book.title" show-overflow-tooltip/>
      <el-table-column label="借阅人" prop="name"/>
      <el-table-column label="借阅时间">
        <template #default="scope">
          {{ $filters.formatUTC_DateTime(scope.row.startTime) }}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template #default="scope">
          <el-tag type="success" v-if="scope.row.state == 1">已归还</el-tag>
          <el-tag type="danger" v-if="scope.row.state == 0">未归还</el-tag>
          <el-tag type="info" v-if="scope.row.state == 2">延时归还</el-tag>
          <el-tag type="warning" v-if="scope.row.state == 3">书籍损坏</el-tag>
          <el-tag type="danger" v-if="scope.row.state == 4">书籍丢失</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="220">
        <template #default="scope">
          <el-popover trigger="click">
            <template #reference>
              <el-button :type="'danger'" size="small" v-show="scope.row.state===0">异常归还</el-button>
            </template>
            <el-select v-model="scope.row.state" @change="updBorrow(scope.row)">
              <el-option :value="0" label="未归还"/>
              <el-option :value="2" label="延时归还"/>
              <el-option :value="3" label="书籍损坏"/>
              <el-option :value="4" label="书籍丢失"/>
            </el-select>
          </el-popover>
          <el-button v-show="scope.row.state===0" :type="'success'" size="small" @click="returnBook(scope.row)">归还</el-button>
          <el-popover trigger="click">
            <template #reference>
              <el-button size="small" :type="'primary'" v-show="scope.row.state!==0">修改</el-button>
            </template>
            <el-select v-model="scope.row.state" @change="updBorrow(scope.row)">
              <el-option :value="0" label="未归还"/>
              <el-option :value="1" label="以归还"/>
              <el-option :value="2" label="延时归还"/>
              <el-option :value="3" label="书籍损坏"/>
              <el-option :value="4" label="书籍丢失"/>
            </el-select>
          </el-popover>
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
  id: '',
  title: '',
  state: ''
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
const returnBook= (row) => {
  let obj=Object.assign({updateBy:uid},row)
  obj.state=1
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