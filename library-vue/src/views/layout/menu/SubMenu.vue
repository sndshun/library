<template>
  <template v-for="item in children">

    <template v-if="!item.hidden&&item.children">

      <el-menu-item v-if="!item.hidden&&item.children.length===1" :index="item.children[0].path">
        <span>{{ item.children[0].meta.title }}</span>
      </el-menu-item>

      <el-sub-menu v-else :index="item.path">
        <template #title>
          <span>{{ item.meta.title }}</span>
        </template>
        <sub-menu :children="item.children"/>
      </el-sub-menu>

    </template>



      <el-menu-item v-else-if="!item.hidden" :index="item.path">
        <span>{{ item.meta.title }}</span>
      </el-menu-item>

  </template>
</template>

<script setup>
defineProps({
  children:Array
})
</script>

