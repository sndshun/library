<template>
  <!--头部导航-->
    <el-menu
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        @select="handleSelect"
    >
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="2">我的借阅</el-menu-item>
      <el-menu-item index="3">个人信息</el-menu-item>
      <el-menu-item index="4">Orders</el-menu-item>
    </el-menu>

</template>

<script setup>
//----------------------------------------------------------------------------- 菜单
const handleSelect = (key, keyPath) => {
  console.log(key, keyPath)
}
</script>

<style scoped>

</style>