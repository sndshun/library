<template>
  <div class="container">
    <el-row :gutter="30">
      <el-col :md="8" :lg="8" :xl="8">
        <el-collapse>
          <el-collapse-item title="查询" name="1">
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
                  <el-form-item>
                    <el-button type="primary" :icon="Search" @click="init">搜索</el-button>
                    <el-button :icon="Refresh" @click="resetSearch(search)">重置</el-button>
                  </el-form-item>
                </el-form>
              </el-col>
            </el-row>
          </el-collapse-item>
        </el-collapse>
      </el-col>

      <el-col :xs="24" :sm="24" :md="16" :lg="16" :xl="16">

          <div :span="24" v-for="(item,index) in books.list" :key="index" class="book">
            <el-card shadow="hover">
              <div class="book-main">
                <div class="book-img">
                  <a :href="item.dataHref" target="_blank"><img :src="item.imgUrl" :alt="item.imgDesc"/></a>
                </div>
                <div class="book-content">
                  <h2 class="pointer">{{ item.title }}</h2>
                  <h2>{{ item.originTitle }}</h2>
                  <h3>{{ item.subTitle }}</h3>
                  <p>{{ item.author }}</p>
                  <p>{{ item.publisher }}/{{ $filters.formatDate(item.pubdate) }}</p>
                  <p>{{ item.price }}元</p>
                  <p>{{ item.likes }}人推荐</p>
                  <p>可借阅数量：{{ item.currentNumber }}/总数量：{{ item.total }}</p>
                  <div class="button">
                    <el-button>借阅</el-button>
                  </div>
                </div>
              </div>
            </el-card>
          </div>

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

        <el-row>
          <el-col :span="24">
          </el-col>
        </el-row>
      </el-col>


    </el-row>
  </div>

</template>

<script setup>
import {Refresh,Search} from '@element-plus/icons-vue'
import { getBooks, getBookTagList, getBookTypeList} from "../api/book";
import {onBeforeMount, reactive} from "vue";
import {ref} from "vue";
import {ElMessage} from "element-plus";

onBeforeMount(() => {
  init()
  getBookType()
})
const book = ref({
  author: "",
  borrows: null,
  currentNumber: null,
  id: null,
  likes: null,
  price: null,
  publisher: "",
  subTitle: "",
  title: "",
  total: null,
  typeName: "",
  imgDesc: "",
  imgUrl: "",
  dataHref: "",
  pubdate: "",
})
let page = {
  current: 1,
  size: 10,
}

const books = reactive({
  current: 1,
  list: [],
  size: 0,
  total: 0,
  pages: 0
})
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
  getBooks(params).then(res => {
    books.list = res.data.list;
    books.size = res.data.size;
    books.total = res.data.total;
    books.pages = res.data.pages;
    books.current = res.data.current;
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
})
const resetSearch = (form) => {
  form.resetFields()
  init()
}

const handleSizeChange = (val) => {
  page.size = val
  init()
}
const handleCurrentChange = (val) => {
  page.current = val
  init()
}

</script>

<style lang="less" scoped>
.container {
  padding: 20px 20px;
  max-width: 2500px; /* 容器最大宽度 */
  margin: 0 auto;
}


.book {
  margin-bottom: 20px;
  img {
    height: 200px;
    width: 142px;
  }

  .book-main {
    display: flex;

    .book-content {
      margin-left: 10px;

      h2 {
        font-size: 18px;
        font-weight: bolder;
        margin-bottom: 10px;
      }

      h3 {
        font-size: 14px;
        margin-bottom: 10px;
      }

      p {
        font-size: 12px;
        margin-bottom: 10px;
      }

      .button {
        //固定在底部

      }
    }
  }
}


</style>