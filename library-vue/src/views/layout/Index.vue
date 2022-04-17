<template>
  <el-scrollbar>

    <div id="layout">
      <el-container>
        <el-aside>
          <el-scrollbar>
            <routerMenu/>
          </el-scrollbar>
        </el-aside>
        <el-container>
          <el-header>
            <Header/>
          </el-header>


          <el-main>
            <el-scrollbar>
              <div>
                <router-view v-slot="{ Component }">
                  <transition mode="out-in"
                              :name="mainAni"
                  >
                    <keep-alive>
                      <component :is="Component"/>
                    </keep-alive>
                  </transition>
                </router-view>
              </div>
            </el-scrollbar>

          </el-main>


        </el-container>
      </el-container>
    </div>
  </el-scrollbar>

</template>

<script setup>
import RouterMenu from "./menu/RouterMenu.vue";
import Header from "./Header.vue";
import {sysStore} from "../../store";
import {storeToRefs} from "pinia";

const {mainAni} = storeToRefs(sysStore())


</script>

<style scoped>
.left-enter-active, .left-leave-active {
  animation: fadeInLeft;
  animation-duration: .2s;
}

.left-enter, .left-leave-to {
  animation: fadeOutRight;
  animation-duration: .3s;
}

.right-enter-active, .right-leave-active {
  animation: fadeInRight;
  animation-duration: .2s;
}

.right-enter, .right-leave-to {
  animation: fadeOutLeft;
  animation-duration: .3s;
}
</style>
