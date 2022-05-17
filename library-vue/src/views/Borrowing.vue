<template>
  <library-header/>
  <div class="container">
    <el-row v-if="borrowings.length!=0" justify="center">
      <el-col :xs="24" :sm="24" :md="20" :lg="20" :xl="20">
        <h1 style="line-height: 80px">归还图书</h1>
      </el-col>
      <el-col :xs="24" :sm="24" :md="20" :lg="20" :xl="20">
        <el-table :data="borrowing">
          <el-table-column label="图书名称" prop="book.title"/>
          <el-table-column label="借阅时间" prop="startTime">
            <template #default="scope">
              {{ $filters.formatUTC_DateTime(scope.row.startTime) }}
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button type="text" @click="returnBook(scope.row)">归还</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :xs="24" :sm="24" :md="20" :lg="20" :xl="20">
        <h1 style="line-height: 80px">借阅历史</h1>
      </el-col>
      <el-col :xs="24" :sm="24" :md="20" :lg="20" :xl="20">
        <el-timeline>
          <el-timeline-item
              v-for="(item, index) in borrowings"
              :key="index"
              :timestamp="item.startTime"
              hide-timestamp
              :type="item.state!=1?'danger':'success'"
          >
            <el-card v-if="item.state==0">
              <div>
                <span>{{ $filters.formatUTC_DateTime(item.startTime) }}借出</span>
              </div>
              <div>
                <span>{{ item.book.title }}</span>
              </div>
              <div>
                <span>尚未归还</span>
              </div>
            </el-card>
            <el-card v-if="item.state==1">
              <div>
                <span>{{ $filters.formatUTC_DateTime(item.startTime) }}借出</span>
              </div>
              <div>
                <span>{{ item.book.title }}</span>
              </div>
              <div>
                <span>{{ $filters.formatUTC_DateTime(item.endTime) }}归还</span>
              </div>
            </el-card>
            <el-card v-if="item.state==2">
              <div>
                <span>{{ $filters.formatUTC_DateTime(item.startTime) }}借出</span>
              </div>
              <div>
                <span>{{ item.book.title }}</span>
              </div>
              <div>
                <span>{{ $filters.formatUTC_DateTime(item.endTime) }}延时归还</span>
              </div>
            </el-card>
            <el-card v-if="item.state==3">
              <div>
                <span>{{ $filters.formatUTC_DateTime(item.startTime) }}借出</span>
              </div>
              <div>
                <span>{{ item.book.title }}</span>
              </div>
              <div>
                <span>{{ $filters.formatUTC_DateTime(item.endTime) }}损坏归还</span>
              </div>
            </el-card>
            <el-card v-if="item.state==4">
              <div>
                <span>{{ $filters.formatUTC_DateTime(item.startTime) }}借出</span>
              </div>
              <div>
                <span>{{ item.book.title }}</span>
              </div>
              <div>
                <span>{{ $filters.formatUTC_DateTime(item.endTime) }}书籍丢失</span>
              </div>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-col>

    </el-row>

    <el-dialog v-model="pageData.detailsDialog" width="200px" center>
      <el-row justify="center">
        <el-col>
          <h1>借阅凭证</h1>
        </el-col>
        <el-col>
          <qrcode-vue :value="pageData.id" :size="128" level="H" />
        </el-col>
        <el-col>
          <h1>{{ pageData.id }}</h1>
        </el-col>
      </el-row>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="pageData.detailsDialog = false"
        >确定</el-button
        >
      </span>
      </template>
    </el-dialog>
    <el-empty v-if="borrowings.length==0" :description="'尚未借阅图书'"></el-empty>
  </div>
</template>

<script setup>
import LibraryHeader from '../components/LibraryHeader.vue'
import {borrowingBooks} from "../api/user";
import {computed, onBeforeMount, reactive, ref} from "vue";
import {userStore} from "../store";
import QrcodeVue from 'qrcode.vue'

const pageData = reactive({
  detailsDialog: false,
  id: '',
})

const uid = userStore().userInfo.id

const borrowing = ref([]);

const borrowings = ref([])
onBeforeMount(() => {
  init()
});

const init = () => {
  borrowingBooks(uid).then(res => {
    borrowings.value = res.data
    //筛选出未归还的
    borrowing.value = res.data.filter(item => item.state == 0)
  })
}

const returnBook = (row) => {
  // row.state=1
  // let obj=Object.assign({updateBy:uid},row)
  // retBooks(obj).then(res=>{
  //   if(res.code==200){
  //     ElMessage.success('归还成功')
  //     init()
  //   }
  // })
  pageData.detailsDialog = true
  pageData.id = row.id.toString()
}

</script>

<style scoped>

</style>