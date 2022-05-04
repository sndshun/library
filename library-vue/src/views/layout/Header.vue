<template>
  <div class="header">
    <div class="header-main">
      <div style="margin-right: 20px">
        欢迎用户: <span>{{ userInfo.name }}</span>
      </div>
      <el-dropdown>
        <el-avatar class="pointer" fit="cover" shape="circle" :size="50"
                   :src="userInfo.profile"/>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="router.push('/')">主页</el-dropdown-item>
            <el-dropdown-item @click="router.push('/info')">个人信息</el-dropdown-item>
            <el-dropdown-item>系统设置</el-dropdown-item>
            <el-dropdown-item @click="out()">注销</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <div style="width: 30px;"></div>
    </div>

    <el-scrollbar>
      <div class="breadcrumb">

          <el-tag
              class="tag"
              v-for="(item, index) in breadcrumb"
              :key="index"
              :closable="!item.open"
              @close="delTag(index)"
              :effect="route.path === item.path ? 'dark' : 'plain'"
              @click="router.push(item.path)"
          >
            {{ item.name }}
          </el-tag>

      </div>
    </el-scrollbar>

  </div>
</template>

<script setup>
import {sysStore, userStore} from "../../store";
import {ElMessage} from "element-plus";
import router from "../../router";
import {storeToRefs} from "pinia";
import {useRoute} from "vue-router";


const {breadcrumb} = storeToRefs(sysStore())
const {userInfo} = storeToRefs(userStore())

const {logout} = userStore()
const {delBreadcrumb} = sysStore()

const route=useRoute()


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

const delTag = (index) => {
  delBreadcrumb(index)
}



</script>

<style scoped>
.header .header-main {
  height: 60px;
  line-height: 60px;
  background: #fff;
  border-bottom: 1px solid #e6e6e6;
  display: flex;
  align-items: center;
  justify-content: right;
}

.breadcrumb {
  background: #2c3e50;
  height: 30px;
  line-height: 30px;
  padding: 0 10px;
  color: #fff;
  font-size: 14px;
}


.tag{
  cursor: pointer;
  margin: 0 1px;
}


</style>
