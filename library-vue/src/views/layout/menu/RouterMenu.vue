<template>
    <el-menu
        :default-active="route.path"
        :router="true"
    >
      <template v-for="item in routerList" :key="item.path">

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

    </el-menu>

</template>

<script setup>
import SubMenu from './SubMenu.vue'
import {userStore} from "../../../store";
import {useRoute} from "vue-router";

const route=useRoute()
const store=userStore()

const routerList=store.userMenu

</script>

<style scoped>

</style>