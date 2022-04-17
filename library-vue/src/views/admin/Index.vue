<template>
  <el-card>
    <h1>欢迎使用图书关联系统</h1>
    <p>
      当前登录用户：{{userInfo.name}}
    </p>
    <p>
      当前登录用户角色：<span v-for="(item,index) in userInfo.roles" :key="index">{{item}}</span>
    </p>
  </el-card>
</template>

<script setup>

import {storeToRefs} from "pinia";
import {userStore} from "../../store";

const {userInfo} = storeToRefs(userStore())
</script>

<style scoped>

</style>