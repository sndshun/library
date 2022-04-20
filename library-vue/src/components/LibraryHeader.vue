<template>
  <div>

    <!--头部导航-->
    <div class="home-header">
      <div class="logo">
        <img src="../assets/img/logo.png" width="100" height="100"/>
      </div>
      <div class="search hidden-sm-and-down">
        <div class="input">
          <input type="text" placeholder="Search" v-model="searchForm.title">
          <button @click="pushSearch">
            <el-icon>
              <Search/>
            </el-icon>
            <span> 搜索 </span>
          </button>
        </div>
      </div>
      <div class="login" style="display: flex;align-items: center">
      <span class="shopCar">
          <el-badge value="0" class="item">
            <el-icon :size="24"><shopping-cart-full/></el-icon>
          </el-badge>
      </span>
        <el-dropdown class="hidden-sm-and-down" v-if="!userInfo">
          <el-icon :size="24">
            <setting/>
          </el-icon>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="router.push('/login')">&nbsp;&nbsp; 登录 &nbsp;&nbsp;</el-dropdown-item>
              <el-dropdown-item>&nbsp;&nbsp; 注册 &nbsp;&nbsp;</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <el-dropdown class="hidden-sm-and-down" v-if="userInfo">
          <el-avatar class="pointer" fit="cover" shape="circle" :size="50"
                     :src="userInfo.profile"/>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="router.push('/info')">个人信息</el-dropdown-item>
              <span v-permission="'system:list'"><el-dropdown-item
                  @click="router.push('/admin')">后台管理</el-dropdown-item></span>
              <el-dropdown-item @click="out()">注销</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

        <span class="hidden-md-and-up" @click="drawer=true">
        <el-icon :size="24">
          <fold/>
        </el-icon>
      </span>
      </div>
    </div>
    <el-affix :offset="0">
      <el-menu
          class="el-menu-demo hidden-sm-and-down"
          mode="horizontal"
          background-color="#336699"
          text-color="#FFFFFF"
          active-text-color="#99CCFF"
          :default-active="route.path"
          :router="true"
      >
        <el-menu-item index="/">首页</el-menu-item>
        <el-menu-item index="/search">书籍检索</el-menu-item>
        <el-menu-item index="/hot">热门图书</el-menu-item>
        <el-menu-item index="/borrow">我的借阅</el-menu-item>
        <el-menu-item index="/info">个人信息</el-menu-item>
      </el-menu>

    </el-affix>
    <el-drawer
        custom-class="drawer"
        :with-header="false"
        v-model="drawer"
        size="70%"
    >
      <div class="drawer-header" v-if="userInfo">
       <span>
      欢迎您： {{ userInfo.name }}
      </span>
        <el-dropdown>
          <el-avatar class="pointer" fit="cover" shape="circle" :size="50"
                     :src="userInfo.profile"/>
          <template #dropdown>
            <el-dropdown-menu>
              <span v-permission="'system:list'"><el-dropdown-item
                  @click="router.push('/admin')">后台管理</el-dropdown-item></span>
              <el-dropdown-item @click="out()">注销</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>


      <div class="input">
        <input type="text" placeholder="请输入书名" v-model="searchForm.title">
        <button @click="pushForm">
          <el-icon>
            <Search/>
          </el-icon>
        </button>
      </div>
      <el-collapse>
        <el-collapse-item title="高级查询">
          <el-form :inline="true" :model="searchForm" ref="search" class="form-inline">
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
              <el-button :icon="Refresh" @click="resetSearch(search)">重置</el-button>
            </el-form-item>
          </el-form>
        </el-collapse-item>
      </el-collapse>
      <el-divider/>
      <el-menu
          class="el-menu-demo"
          background-color="#fff"
          text-color="#000000"
          active-text-color="#0080ff"
          @select="menuSelect"
          :default-active="route.path"
          :router="true"
      >
        <el-menu-item index="/">首页</el-menu-item>
        <el-menu-item index="/search">书籍检索</el-menu-item>
        <el-menu-item index="/hot">热门图书</el-menu-item>
        <el-menu-item index="/borrow">我的借阅</el-menu-item>
        <el-menu-item index="/info">个人信息</el-menu-item>
      </el-menu>
      <el-divider/>
      <el-collapse v-if="!userInfo">
        <el-collapse-item title="账号">
          <div class="account" @click="router.push('/login')">
            登录
          </div>
          <div class="account" @click="router.push('/login')">
            注册
          </div>
        </el-collapse-item>
      </el-collapse>

    </el-drawer>
  </div>

</template>

<script setup>
import {Fold, Search, Setting, ShoppingCartFull, Refresh} from '@element-plus/icons-vue'
import {onBeforeMount, ref, reactive} from "vue";
import {storeToRefs} from "pinia/dist/pinia";
import {userStore} from "../store";
import {ElMessage} from "element-plus";
import router from "../router";
import {useRoute} from "vue-router";
import {getBookTagList, getBookTypeList} from "../api/book";

onBeforeMount(() => {
  getBookType()
  searchForm.title=route.query.title
})

const emit = defineEmits(['update:title','update:searchForm','searchBook'])

const route = useRoute()

const {userInfo} = storeToRefs(userStore())
const {logout} = userStore()

async function out() {
  const flag = await logout()
  if (flag) {
    ElMessage.success('注销成功')
    await router.push({
      path: '/'
      , name: 'home'
    })
  }
}

const drawer = ref(false)

const pushSearch = () => {
  router.push({
    path: '/search'
    , name: '书籍搜索'
    , query: {
      title: searchForm.title
    }
  })
  emit('update:title', searchForm.title)
  emit('searchBook')
}
const pushForm = () => {
  router.push({
    path: '/search'
    , name: '书籍搜索'
  })
  emit('update:searchForm', searchForm)
  emit('searchBook')
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
}

const menuSelect = (index) => {
  drawer.value = false
}


</script>

<style lang="less" scoped>
.el-menu-demo {
  justify-content: center;
}

.home-header {
  height: 52px;
  padding: 20px 0;
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-around;
  align-items: center;
  background-color: #ffffff;
  overflow: hidden;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.2);

  .input {
    width: 380px;
    height: 38px;
    display: flex;

    input {
      border: 1px solid #dcdcdc;
      border-radius: 5px 0 0 5px;
      width: 77%;
      padding: 0;
      height: 36px;
      text-indent: 20px;
      outline: none;
      background: none;
    }

    button {
      border-radius: 0 5px 5px 0;
      background: #00aaff;
      width: 20%;
      height: 38px;
      padding: 0;
      border: none;
      text-align: center;
      outline: none;
      cursor: pointer;
      color: white;
    }

    button:hover {
      background: #004870;
      transition: .6s;
    }
  }

  .shopCar {
    display: inline-block;
    width: 48px;
    margin-right: 20px;
  }
}

.drawer {
  .drawer-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 36px;
  }

  .input {
    width: 100%;
    height: 38px;
    display: flex;
    margin-bottom: 20px;

    input {
      border: 1px solid #dcdcdc;
      border-radius: 5px 0 0 5px;
      width: 77%;
      padding: 0;
      height: 36px;
      text-indent: 20px;
      outline: none;
      background: none;
    }

    button {
      border-radius: 0 5px 5px 0;
      background: #00aaff;
      width: 20%;
      height: 38px;
      padding: 0;
      border: none;
      text-align: center;
      outline: none;
      cursor: pointer;
      color: white;
    }

    button:hover {
      background: #004870;
      transition: .6s;
    }
  }

  .account {
    width: 100%;
    line-height: 36px;
    font-size: 16px;
    text-indent: 20px;
  }
}

</style>