<template>
  <home-menu/>

  <div class="container">
    <el-row :gutter="30">

      <!-- 移动端查询搜索 -->
      <el-col class="hidden-md-and-up">
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
      <el-col :span="2" class="hidden-sm-and-down"></el-col>
      <!-- 搜索结果 -->
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">

        <div v-for="(item,index) in books.list" :key="index" class="book">
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

        <el-empty description="还没有这方面的书籍" v-show="books.list.length === 0"/>

        <el-pagination class="hidden-sm-and-down"
                       hide-on-single-page
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
                       style="justify-content: flex-end"
        />

        <el-pagination class="hidden-md-and-up"
                       hide-on-single-page
                       :currentPage="books.current"
                       :page-size="books.size"
                       :page-sizes="[10, 50, 100, 300]"
                       :small="false"
                       :disabled="false"
                       :background="true"
                       layout="prev, pager, next"
                       :total="books.total"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       style="justify-content: center"
        />


      </el-col>
      <!-- 桌面端右侧 -->
      <el-col :span="8" class="hidden-sm-and-down">
        <el-card>
          <div style="height: 30px;">
            <span>热门标签</span>
          </div>
          <div class="tag-list">
            <el-space wrap>
              <el-tag
                  v-for="(item,index) in hotTagsData"
                  :key="index"
                  :effect="searchForm.tagId.indexOf(item.tagId) > -1 ? 'dark' : 'plain'"
                  @click="onChange(item.tagId,true)"
              >
                {{ item.tagName }}
              </el-tag>
            </el-space>
          </div>
        </el-card>

        <el-card style="margin: 10px 0;">
          <div style="line-height: 30px;">
            <span>isbn10</span>
          </div>
          <div class="book-isbn10">
            <el-input v-model="searchForm.isbn10"
                      placeholder="请输入isbn10"
                      autocomplete="off"
            />
          </div>
          <div style="line-height: 30px;">
            <span>isbn13</span>
          </div>
          <div class="book-isbn13">
            <el-input v-model="searchForm.isbn13"
                      placeholder="请输入isbn13"
                      autocomplete="off"
            />
          </div>
          <div style="line-height: 30px;">
            <span>作者</span>
          </div>
          <div class="book-author">
            <el-input v-model="searchForm.author"
                      placeholder="请输入作者名称"
                      autocomplete="off"
            />
          </div>
          <div style="line-height: 30px;">
            <span>丛书</span>
          </div>
          <div class="book-brand">
            <el-input v-model="searchForm.brand"
                      placeholder="请输入丛书"
                      autocomplete="off"
            />
          </div>
          <div style="line-height: 30px;">
            <span>分类</span>
          </div>
          <div class="book-type">
            <el-select placeholder="请选择分类"
                       v-model="searchForm.bookType"
            >
              <el-option value="">请选择</el-option>
              <el-option
                  v-for="item in bookTypeList"
                  :key="item.id"
                  :label="item.typeName"
                  :value="item.id"
              ></el-option>
            </el-select>
          </div>

          <div style="line-height: 50px;">
            <span>全部标签</span>
          </div>
          <el-scrollbar>
            <div class="all-tag" style="max-height: 200px">
              <el-space wrap>
                <el-tag
                    v-for="(item,index) in allTag"
                    :key="index"
                    :effect="searchForm.tagId.indexOf(item.id) > -1 ? 'dark' : 'plain'"
                    @click="onChange(item.id)"
                >
                  {{ item.tagName }}
                </el-tag>
              </el-space>
            </div>

          </el-scrollbar>


        </el-card>

        <el-card style="margin: 10px 0;">
          <div style="height: 30px;">
            <span>上月借阅排行</span>
          </div>
        </el-card>

      </el-col>
      <el-col :span="2" class="hidden-sm-and-down"></el-col>

    </el-row>
  </div>
  <div class="bg">

  </div>
</template>

<script setup>
import {Refresh, Search} from '@element-plus/icons-vue'
import {getAllTags, getBooks, getBookTagList, getBookTypeList, hotTags} from "../api/book";
import {onBeforeMount, reactive, ref} from "vue";
import HomeMenu from "../components/LibraryHeader.vue";

onBeforeMount(() => {
  init()
  getBookType()
  getHotTags()
  getAllTag()
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
  brand: null,
  isbn10: null,
  isbn13: null,
  bookType: null,
  tagId: [],
})
const resetSearch = (form) => {
  form.resetFields()
  init()
}
//--------------------------------------------- 标签
const hotTagsData = ref()
const getHotTags = () => {
  hotTags(10).then(res => {
    hotTagsData.value = res.data
  })
}
const allTag = ref([])
const getAllTag = () => {
  getAllTags().then(res => {
    allTag.value = res.data
  })
}

const onChange = (id, isSearch) => {
  //没有添加，有就删除
  let index = searchForm.tagId.indexOf(id)
  if (index > -1) {
    searchForm.tagId.splice(index, 1)
  } else {
    searchForm.tagId.push(id)
  }
  if (isSearch) {
    init()
  }
}

//----------------------------------------------------------------------------- 分页
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

.bg {
  position: fixed;
  top: 0;
  left: 0;
  z-index: -1;
  width: 100%;
  height: 100%;
  background: url("../assets/img/bg1.jpg") center top no-repeat;
  background-size: 70% auto;
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

    }
  }
}


</style>