<template>

  <el-card>

    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="searchForm" ref="search" class="form-inline">
          <el-form-item label="标签名" prop="tagName">
            <el-input v-model="searchForm.tagName"
                      placeholder="请输入书籍标签"
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
        <el-button type="primary" :icon="Plus" plain @click="add(editForm,addBookTag)">添加</el-button>
        <el-button type="danger" :icon="Delete" :disabled="pageData.batchDelete" plain @click="batchDel">删除&nbsp;
          <b v-show="selectList.length">
            ({{ selectList.length }})
          </b>
        </el-button>
      </el-col>
    </el-row>
    <el-table
        :data="bookTags.list"
        style="width: 100%;margin: 10px 0;"
        :empty-text="pageData.emptyText"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>
      <el-table-column label="书籍标签" prop="tagName"/>
      <el-table-column label="创建人" prop="createName"/>
      <el-table-column label="操作" fixed="right" width="220">
        <template #default="scope">
          <el-button size="small" @click="details(scope.row)">详情</el-button>
          <el-button size="small" @click="edit(editForm,scope.row,updBookTag)">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row justify="end">
      <el-col :span="24">
        <el-pagination
            :currentPage="bookTags.current"
            :page-size="bookTags.size"
            :page-sizes="[10, 50, 100, 300]"
            :small="false"
            :disabled="false"
            :background="true"
            layout="total, sizes, prev, pager, next, jumper"
            :total="bookTags.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            style="display: flex;justify-content: flex-end"
        />
      </el-col>
    </el-row>

    <el-dialog v-model="pageData.detailsDialog" width="60%" center>

      <el-descriptions
          title="标签详情"
          :column="4"
          border
      >
        <el-descriptions-item label="分类名" :span="4">{{ bookTag.tagName }}</el-descriptions-item>
        <el-descriptions-item label="创建人" :span="2">{{ bookTag.createName }}</el-descriptions-item>
        <el-descriptions-item label="创建时间" :span="2">{{ $filters.formatUTC_DateTime(bookTag.createTime) }}
        </el-descriptions-item>
        <el-descriptions-item label="修改人" :span="2">{{ bookTag.updateName }}</el-descriptions-item>
        <el-descriptions-item label="修改时间" :span="2">{{ $filters.formatUTC_DateTime(bookTag.updateTime) }}
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
      <el-form :model="bookTag"
               ref="editForm"
               label-width="80px"
               :label-position="'left'"
      >
        <el-form-item v-show="false">
          <el-input v-model="bookTag.id" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="标签名" prop="tagName" :rules="{required: true,trigger: 'blur',message:'标签名不能为空'}">
          <el-input v-model="bookTag.tagName"
                    placeholder="请输入书籍标签"
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
import {addBookTag, updBookTag, delBookTag, batchDelBookTag, getBookTagPage} from "../../api/book";
import {computed, nextTick, onBeforeMount, reactive, ref} from "vue";
import {userStore} from "../../store";
import {ElMessage} from "element-plus";

const uid = userStore().userInfo.id


onBeforeMount(() => {
  init()
})

const pageData = reactive({
  emptyText: '未设置书籍标签',
  batchDelete: true,
  detailsDialog: false,
  editDialog: false
})

const bookTags = reactive({
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
  getBookTagPage(params).then(res => {
    bookTags.list = res.data.list
    bookTags.current = res.data.current
    bookTags.pages = res.data.pages
    bookTags.total = res.data.total
    bookTags.size = res.data.size
  })
}

let selectList = ref([]);
const handleSelectionChange = (val) => {
  selectList.value = val.map(item => {
    return item.id
  })
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
  tagName: '',
  createName: ''
})

const resetSearch = (form) => {
  form.resetFields()
  init()
}
const batchDel = () => {
  batchDelBookTag(selectList.value).then(res => {
    if (res.data == true) {
      ElMessage.success('操作成功')
      init();
    } else {
      ElMessage.success('操作失败')
    }
  })
}


const bookTag = ref({
  id: null,
  tagName: '',
  updateTime: '',
  createTime: '',
  createName: '',
  updateName: '',
  createBy: null,
  updateBy: null
})
const details = (row) => {
  bookTag.value = row
  pageData.detailsDialog = true
}

const editForm = ref(null)

const add = (form, add) => {
  temp = add
  bookTag.value={}
  pageData.editDialog = true
  if (form) {
    form.resetFields()
    form.clearValidate()
  }
  nextTick(() => {
    bookTag.value.createBy = uid
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
    bookTag.value.id = row.id
    bookTag.value.tagName = row.tagName
    bookTag.value.updateBy = uid
  })
}
let temp
const submit = (form) => {
  form.validate((valid) => {
    if (valid) {
      temp(bookTag.value).then(res => {
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
  delBookTag(id).then(res => {
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