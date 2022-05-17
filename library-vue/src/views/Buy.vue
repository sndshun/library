<template>
  <library-header/>
  <div class="container">
    <el-row justify="center">
      <el-col :xs="24" :sm="24" :md="20" :lg="20" :xl="20">
        <div class="box">
          <div v-for="(item,index) in credentialsList" :key="index" class="buy-card">
            <h1>【{{ item.credentialsName }}】</h1>
            <div class="price">
              <p style="font-weight: bold;font-size: 22px">￥{{ item.credentialsPrice }}</p>
              <p style="font-size: 13px">押金</p>
            </div>
            <p>每次可借阅{{ item.borrowingCount }}本图书</p>
            <p>每次借阅{{ item.borrowingTime }}天</p>
            <div class="button">
              <el-button type="primary" @click="buy(item.id)">办理</el-button>
            </div>
          </div>
        </div>
      </el-col>

    </el-row>
  </div>
</template>

<script setup>
import LibraryHeader from '../components/LibraryHeader.vue'
import {onBeforeMount, ref} from "vue";
import {getCredentials} from "../api/credentials";

onBeforeMount(() => {
  init()
})
const credentialsList = ref([])
const init = () => {
  getCredentials().then(res => {
    credentialsList.value = res.data
  })
}

const buy=(id)=>{

}

</script>

<style scoped lang="less">

.box{
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;

}
.buy-card {
  margin: 10px 0;
  text-align: center;
  line-height: 30px;
  border-radius: 4px;
  border: 1px solid #e4e7ed;
  box-shadow: 0 2px 6px rgba(235, 235, 235, .4);

  h1{
    font-size: 18px;
    font-weight: bold;
    line-height: 50px;
  }
  .price {
    padding: 24px 0;
    background-color: #fafafa;
  }
  p{
    padding: 0 20px;
  }
  .button{
    padding: 10px 0;
    background-color: #fafafa;
  }
}

.buy-card:hover {
  box-shadow: 0 .5rem 2rem #d4dcec;
  transform: translateY(-2px);
  transition: .3s;
  opacity: 1;
}

</style>