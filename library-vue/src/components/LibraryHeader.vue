<template>
  <!--头部导航-->

  <div class="home-header">
    <div class="logo">
      <img src="../assets/img/logo.png" width="100" height="100"/>
    </div>
    <div class="search">
      <div class="input">
        <input type="text" placeholder="Search" v-model="bookTitle">
        <button>
          <el-icon>
            <Search/>
          </el-icon>
          <span> 搜索 </span>
        </button>
      </div>
    </div>
    <div class="login">
      <el-dropdown v-if="!userInfo">
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
      <el-dropdown v-if="userInfo">
        <el-avatar class="pointer" fit="cover" shape="circle" :size="50"
                   :src="userInfo.profile"/>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="router.push('/info')">个人信息</el-dropdown-item>
            <span v-permission="'system:list'"><el-dropdown-item @click="router.push('/admin')">后台管理</el-dropdown-item></span>
            <el-dropdown-item @click="out()">注销</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
  <el-affix :offset="0">
    <el-menu
        class="el-menu-demo"
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


</template>

<script setup>
import {Search, Setting} from '@element-plus/icons-vue'
import {ref} from "vue";
import {storeToRefs} from "pinia/dist/pinia";
import {userStore} from "../store";
import {ElMessage} from "element-plus";
import router from "../router";
import {useRoute} from "vue-router";

const bookTitle=ref()

const route=useRoute()

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

</script>

<style lang="less" scoped>
.el-menu-demo {
  justify-content: center;
}
.home-header {
  height: 52px;
  padding: 20px 0;
  display: flex;
  justify-content: space-around;
  align-items: center;

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
}


</style>