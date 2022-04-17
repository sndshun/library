<template>
  <el-card>
    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="searchForm" ref="search" class="form-inline">
          <el-form-item label="书籍" prop="title">
            <el-input v-model="searchForm.title"
                      placeholder="请输入书籍名称"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="作者" prop="author">
            <el-input v-model="searchForm.author"
                      placeholder="请输入作者名称"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="丛书" prop="author">
            <el-input v-model="searchForm.brand"
                      placeholder="请输入丛书"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="isbn10" prop="isbn10">
            <el-input v-model="searchForm.isbn10"
                      placeholder="请输入isbn10"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="isbn13" prop="isbn13">
            <el-input v-model="searchForm.isbn13"
                      placeholder="请输入isbn13"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="分类" prop="bookType">
            <el-select placeholder="请选择分类" v-model="searchForm.bookType">
              <el-option
                  v-for="item in bookTypeList"
                  :key="item.id"
                  :label="item.typeName"
                  :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="标签" prop="tagId">
            <el-select
                multiple
                filterable
                remote
                :remote-method="remoteMethod"
                placeholder="请选择标签"
                :loading="tagLoading"
                v-model="searchForm.tagId">
              <el-option
                  v-for="item in bookTagOptions"
                  :key="item.id"
                  :label="item.tagName"
                  :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="入库人" prop="createName">
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
        <el-button type="primary" :icon="Plus" plain @click="add(editForm,addBook)">添加</el-button>
        <el-button type="danger" :icon="Delete" :disabled="pageData.batchDelete" plain @click="batchDel">删除&nbsp;
          <b v-show="selectList.length">
            ({{ selectList.length }})
          </b>
        </el-button>
      </el-col>
    </el-row>

    <el-table
        :data="books.list"
        style="width: 100%;margin: 10px 0;"
        :empty-text="pageData.emptyText"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>
      <el-table-column label="书籍" prop="title" show-overflow-tooltip/>
      <el-table-column label="分类" prop="typeName"/>
      <el-table-column label="借阅人数" prop="borrows"/>
      <el-table-column label="书籍总量" prop="total"/>
      <el-table-column label="未出借数量" prop="currentNumber"/>
      <el-table-column label="入库人" prop="createName"/>
      <el-table-column label="操作" fixed="right" width="280">
        <template #default="scope">
          <el-button size="small" @click="details(scope.row.id)">详情</el-button>
          <el-button size="small" @click="edit(editForm,scope.row,updBook)">编辑</el-button>
          <el-button size="small" type="success" @click="tagEdit(scope.row.id)">标签</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row>
      <el-col :span="24">
        <el-pagination
            :currentPage="books.current"
            :page-size="books.size"
            :page-sizes="[10, 50, 100, 300]"
            :small="false"
            :disabled="false"
            :background="true"
            layout="total, sizes, prev, pager, next, jumper"
            :total="books.total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            style="display: flex;justify-content: flex-end"
        />
      </el-col>
    </el-row>

    <el-dialog v-model="pageData.detailsDialog" width="60%" center>

      <el-descriptions
          title="书籍详情"
          :column="4"
      >
        <el-descriptions-item label="封面:" :span="4">
          <el-image :src="book.imgUrl" :alt="book.imgDesc" fit="fill"/>
        </el-descriptions-item>
        <el-descriptions-item label="书名:" :span="2">{{ book.title }}</el-descriptions-item>
        <el-descriptions-item label="副标题:" :span="2">{{ book.subTitle }}</el-descriptions-item>
        <el-descriptions-item label="源标题:" :span="2">{{ book.originTitle }}</el-descriptions-item>
        <el-descriptions-item label="装订方式:" :span="2">{{ book.bindng }}</el-descriptions-item>
        <el-descriptions-item label="isbn10:" :span="2">{{ book.isbn10 }}</el-descriptions-item>
        <el-descriptions-item label="isbn13:" :span="2">{{ book.isbn13 }}</el-descriptions-item>
        <el-descriptions-item label="推荐人数:" :span="1">{{ book.likes }}</el-descriptions-item>
        <el-descriptions-item label="借阅人数:" :span="1">{{ book.borrows }}</el-descriptions-item>
        <el-descriptions-item label="分类:" :span="1">{{ book.typeName }}</el-descriptions-item>
        <el-descriptions-item label="丛书:" :span="1">{{ book.brand }}</el-descriptions-item>
        <el-descriptions-item label="标签:" :span="4">
          <el-space wrap>
            <el-tag
                v-for="item in book.tags"
                :key="item.id"
                type="success"
                effect="dark"
            >
              {{ item.tagName }} {{ item.tagLikes }}
            </el-tag>
          </el-space>
        </el-descriptions-item>
        <el-descriptions-item label="价格:" :span="1">{{ book.price }}</el-descriptions-item>
        <el-descriptions-item label="页数:" :span="1">{{ book.pages }}</el-descriptions-item>
        <el-descriptions-item label="总数:" :span="1">{{ book.total }}</el-descriptions-item>
        <el-descriptions-item label="未借阅数量:" :span="1">{{ book.currentNumber }}</el-descriptions-item>
        <el-descriptions-item label="作者:" :span="4">{{ book.author }}</el-descriptions-item>
        <el-descriptions-item label="出版社:" :span="2">{{ book.publisher }}</el-descriptions-item>
        <el-descriptions-item label="出版时间:" :span="2">{{
            $filters.formatUTC_DateTime(book.pubdate)
          }}
        </el-descriptions-item>
        <el-descriptions-item label="书籍简介:" :span="4">
          <div class="summary">
            <wang-editor :content="book.summary" :readonly="true"></wang-editor>
          </div>
        </el-descriptions-item>
        <el-descriptions-item label="原文摘录:" :span="4">
          <div class="summary">
            <wang-editor :content="book.originalTexts" :readonly="true"></wang-editor>
          </div>
        </el-descriptions-item>
        <el-descriptions-item label="入库人:" :span="2">{{ book.createName }}</el-descriptions-item>
        <el-descriptions-item label="创建时间:" :span="2">{{ $filters.formatUTC_DateTime(book.createTime) }}
        </el-descriptions-item>
        <el-descriptions-item label="修改人:" :span="2">{{ book.updateName }}</el-descriptions-item>
        <el-descriptions-item label="修改时间:" :span="2">{{ $filters.formatUTC_DateTime(book.updateTime) }}
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

    <el-dialog
        width="60%"
        :title="pageData.editType"
        @close="pageData.addBookDialog=false"
        v-model="pageData.editDialog">

      <el-form :model="book"
               ref="editForm"
               :label-position="'top'"
      >
        <el-form-item v-show="false">
          <el-input v-model="book.id" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="isbn10" prop="isbn10">
          <el-input v-model="book.isbn10" autocomplete="off"/>
          <el-button v-show="isIsbn10"
                     :disabled="pageData.addBookDialog"
                     @click="testAddBook(book.isbn10)"
                     class="animate__animated animate__fadeIn">试试一键添加
          </el-button>
        </el-form-item>
        <el-form-item label="isbn13" prop="isbn13">
          <el-input v-model="book.isbn13" autocomplete="off"/>
          <el-button
              v-show="isIsbn13"
              :disabled="pageData.addBookDialog"
              @click="testAddBook(book.isbn13)"
              class="animate__animated animate__fadeIn">试试一键添加
          </el-button>
        </el-form-item>
        <el-form-item label="图书标题" prop="title" :rules="{required: true,trigger: 'blur',message:'书籍名不能为空'}">
          <el-input v-model="book.title"
                    placeholder="请输入书籍名称"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="副标题" prop="subTitle">
          <el-input v-model="book.subTitle"
                    placeholder="请输入副标题"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="源标题" prop="originTitle">
          <el-input v-model="book.originTitle"
                    placeholder="请输入源标题(国外源标题)"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="封面" prop="imgUrl">
          <cover-upload :imgUrl="book.imgUrl" v-model:attachmentId="book.attachmentId"/>
        </el-form-item>
        <el-form-item label="作者" prop="author"
                      :rules="{required: true,trigger: 'blur',message:'作者不能为空'}"
        >
          <el-input v-model="book.author"
                    placeholder="请输入[国]作者-[译]作者"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="丛书" prop="brand">
          <el-input v-model="book.brand"
                    placeholder="请输入丛书"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="分类" prop="bookType"
                      :rules="{required: true,trigger: 'blur',message:'分类不能为空'}"
        >
          <el-select placeholder="请选择分类" v-model="book.bookType">
            <el-option
                v-for="item in bookTypeList"
                :key="item.id"
                :label="item.typeName"
                :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="装订方式" prop="bindng"
                      :rules="{required: true,trigger: 'blur',message:'装订方式不能为空'}"
        >
          <el-input v-model="book.bindng"
                    placeholder="请输入装订方式"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="页数" prop="pages"
                      :rules="[
                           {required: true,trigger: 'blur',message:'页数不能为空'},
                           {type: 'number',trigger: 'blur', message: '请输入数字' },
                      ]"
        >
          <el-input-number v-model="book.pages"
                           :min="1"
                           placeholder="请输入"
                           autocomplete="off"
          />
          &nbsp;&nbsp;&nbsp;
          <el-slider v-model="book.pages" :max="16000"/>
        </el-form-item>
        <el-form-item label="封面" prop="imgUrl"
        >

        </el-form-item>
        <el-form-item label="出版社" prop="publisher"
                      :rules="{required: true,trigger: 'blur',message:'出版社不能为空'}">
          <el-input v-model="book.publisher"
                    placeholder="请输入出版社"
                    autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="出版日期" prop="pubdate"
                      :rules="{required: true,trigger: 'blur',message:'出版日期不能为空'}">
          <el-date-picker v-model="book.pubdate"
                          type="date"
                          placeholder="请选择出版日期"
                          autocomplete="off"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input-number v-model="book.price"
                           :min="0"
                           :precision="2"
                           placeholder="请输入价格"
                           autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="总量" prop="total"
                      :rules="{required: true,trigger: 'blur',message:'总量不能为空'}">
          <el-input-number v-model="book.total"
                           :min="0"
                           placeholder="请输入总量"
                           autocomplete="off"
          />
          &nbsp;&nbsp;&nbsp;
          <el-slider v-model="book.total" :step="10" :max="1000"/>
        </el-form-item>
        <el-form-item label="书籍简介" prop="summary">
          <div class="summary">
            <wang-editor :content="book.summary" v-model:content="book.summary"></wang-editor>
          </div>
        </el-form-item>
        <el-form-item label="原文摘录" prop="originalTexts">
          <div class="summary">
            <wang-editor :content="book.originalTexts" v-model:content="book.originalTexts"></wang-editor>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit(editForm)">提交</el-button>
          <el-button @click="editForm.resetFields()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog v-model="pageData.tabDialog" :title="book.title" width="60%" center>
      <el-space wrap>
        <el-tag
            v-for="item in book.tags"
            :key="item.id"
            type="success"
            effect="dark"
            closable
            @close="delBookTag(item.bookTagsId)"
        >
          {{ item.tagName }} {{ item.tagLikes }}
        </el-tag>
      </el-space>
      <el-empty v-show="pageData.tagIsEmpty" description="还没有添加标签"/>
      <el-divider />
      <el-space>
        <div>添加以有的标签：</div>
        <el-select
            filterable
            remote
            :remote-method="remoteMethod"
            placeholder="请选择标签"
            :loading="tagLoading"
            v-model="tag">
          <el-option
              v-for="item in bookTagOptions"
              :key="item.id"
              :label="item.tagName"
              :value="item.id"
          ></el-option>
        </el-select>
        <el-button type="primary" :disabled="isAddTag" @click="addTagToBook">添加</el-button>
      </el-space>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="pageData.tabDialog = false">确定</el-button>
      </span>
      </template>
    </el-dialog>
  </el-card>

</template>

<script setup>
import {Search, Refresh, Plus, Delete,} from '@element-plus/icons-vue'
import {
  getBookList,
  addBook,
  updBook,
  delBook,
  getBookTagList,
  getBookTypeList,
  getBookDetail, getTestBook, getBookTags, addBookTags, delBookTags,
} from "../../api/book";
import {computed, nextTick, onBeforeMount, reactive, ref} from "vue";
import {userStore} from "../../store";
import {ElMessage} from "element-plus";
import WangEditor from "../../components/WangEditor.vue";
import CoverUpload from "../../components/CoverUpload.vue";

//-----------------------------------------------------------------------------生命周期钩子
onBeforeMount(() => {
  init()
  getBookType()
})


//-----------------------------------------------------------------------------公共方法、属性
const uid = userStore().userInfo.id

const pageData = reactive({
  emptyText: '未设置书籍分类',
  editType: '',
  batchDelete: true,
  detailsDialog: false,
  editDialog: false,
  addBookDialog: false,
  tabDialog: false,
  tagIsEmpty: true,
})
const book = ref({
  id: null,
  title: '',
  subTitle: '',
  originTitle: '',
  author: '',
  bookType: null,
  bindng: '',
  pages: null,
  attachmentId: null,
  imgUrl: '',
  imgDesc: '',
  brand:'',
  isbn10: '',
  isbn13: '',
  borrows: null,
  summary: '',
  originalTexts: '',
  price: null,
  total: null,
  currentNumber: null,
  publisher: '',
  pubdate: '',
  likes: null,
  typeName: '',
  tags: [],
  updateTime: '',
  createTime: '',
  createName: '',
  updateName: '',
  createBy: null,
  updateBy: null
})


//-----------------------------------------------------------------------------表格
const books = reactive({
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
  getBookList(params).then(res => {
    books.list = res.data.list
    books.current = res.data.current
    books.pages = res.data.pages
    books.total = res.data.total
    books.size = res.data.size
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
const batchDel = () => {
  delBook(selectList.value).then(res => {
    if (res.data == true) {
      ElMessage.success('操作成功')
      init();
    } else {
      ElMessage.success('操作失败')
    }
  })
}


//----------------------------------------------------------------------------- 查询条件
const tagLoading = ref(false)
const bookTagOptions = ref([])
const remoteMethod = (query) => {
  tagLoading.value = true
  if (query) {
    // bookTagOptions.value = bookTagList.value.filter((item) => {
    //   return item.tagName.toLowerCase().includes(query.toLowerCase())
    // })
    getBookTagList({tagName: query}).then(res => {
      bookTagOptions.value = res.data
      tagLoading.value = false
    })
  } else {
    bookTagOptions.value = []
    tagLoading.value = false
  }
}
const bookTypeList = ref([])
const getBookType = () => {
  getBookTypeList().then(res => {
    bookTypeList.value = res.data
  })
}
const search = ref(null)
const searchForm = reactive({
  title: null,
  author: null,
  brand:null,
  isbn10: null,
  isbn13: null,
  bookType: null,
  tagId: [],
  createName: null
})
const resetSearch = (form) => {
  form.resetFields()
  init()
}


//-----------------------------------------------------------------------------图书详情
const details = async (id) => {
  const res = await getBookDetail(id)
  pageData.detailsDialog = true
  await nextTick(() => {
    book.value = res.data
  })
}


//-----------------------------------------------------------------------------添加修改公用
const editForm = ref(null)
//暂存请求方法，控制添加或修改
let temp
const submit = (form) => {
  form.validate((valid) => {
    if (valid) {
      temp(book.value).then(res => {
        if (res.data == true) {
          ElMessage.success('操作成功')
          //这里需要随便给个数据，不然上传组件监听不到值的改变
          book.value.imgUrl= '111'
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



//-----------------------------------------------------------------------------添加图书
//计算属性
const isIsbn10 = computed(() => {
  let reg = /^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$/
  return reg.test(book.value.isbn10) && pageData.editType == '添加图书'
})
const isIsbn13 = computed(() => {
  let reg = /^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$/
  return reg.test(book.value.isbn13) && pageData.editType == '添加图书'
})
const add = (form, add) => {
  pageData.editType = '添加图书'
  temp = add
  book.value = {}
  pageData.editDialog = true
  if (form) {
    form.resetFields()
    form.clearValidate()
  }
  nextTick(() => {
    book.value.createBy = uid
  })
}
function testAddBook(isbn) {
  pageData.addBookDialog = true
  getTestBook(isbn).then(res => {
    let temp = res.data
    if (res.ret == 0) {
      book.value.title = temp.name
      book.value.subTitle = temp.subname
      book.value.brand = temp.brand
      book.value.author = temp.author + (temp.translator ? '-[译]' + temp.translator : '')
      book.value.publisher = temp.publishing
      book.value.pubdate = new Date(temp.published).getFullYear()
      book.value.bindng = temp.designed
      book.value.pages = parseInt(temp.pages)
      book.value.price = parseFloat(temp.price)
      book.value.summary = "<p>" + temp.description + (temp.authorIntro ? '</p><p><br></p><p><br></p><p>' + temp.authorIntro + "</p>" : '')
    } else {
      ElMessage.error(res.msg)
    }
  })
}


//-----------------------------------------------------------------------------编辑图书
const edit = async (form, row, upd) => {
  pageData.editType = '修改信息'
  temp = upd
  const res = await getBookDetail(row.id)
  pageData.editDialog = true
  if (form) {
    form.resetFields()
    form.clearValidate()
  }
  await nextTick(() => {
    book.value = res.data
    book.value.updateBy = uid
  })
}
//-----------------------------------------------------------------------------删除图书
const del = (id) => {
  delBook([id]).then(res => {
    if (res.data == true) {
      ElMessage.success('操作成功')
      init();
    } else {
      ElMessage.success('操作失败')
    }
  })
}
//-----------------------------------------------------------------------------图书标签编辑
const isAddTag=computed(()=>{
  if(tag.value==null){
    return true
  }
  let ids=book.value.tags.map(item=>{
    return item.id
  })
  if (ids.indexOf(tag.value) > -1) {
    return true
  }
  return false;
})

const bookTags=(id)=>{
  getBookTags(id).then(res => {
    book.value = res.data
    if (book.value.tags.length > 0) {
      pageData.tagIsEmpty = false;
    } else {
      pageData.tagIsEmpty = true
    }
  })
}

const tag = ref(null)
const tagEdit = (id) => {
  pageData.tabDialog = true
  bookTags(id)
}
const addTagToBook=()=>{
  let params={
    bookId:book.value.id,
    tagId:tag.value
  }
  addBookTags(params).then(res=>{
    if(res.data==true){
      ElMessage.success('操作成功')
      bookTags(book.value.id)
    }else{
      ElMessage.error('操作失败')
    }
  })
}
const delBookTag=(id)=>{
  delBookTags(id).then(res=>{
    if(res.data==true){
      ElMessage.success('操作成功')
      bookTags(book.value.id)
    }else{
      ElMessage.error('操作失败')
    }
  })
}


</script>

<style scoped>
.summary {
  height: 400px;
}

:deep(.el-form-item__content) {
  flex-wrap: nowrap;
}
</style>