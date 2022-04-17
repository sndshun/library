<template>

  <el-upload
      class="avatar-uploader"
      :action="'/api/attachment/bookCoverUpload?uid='+uid"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
      drag
  >
    <img v-if="url" :src="url" class="avatar"/>
    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
    <div class="el-upload__text">
      拖拽上传或者 <em>点击上传</em>
    </div>
  </el-upload>
</template>

<script setup>
import {UploadFilled} from '@element-plus/icons-vue'

import {ElMessage} from "element-plus";
import {onBeforeUpdate, onUnmounted, onUpdated, ref, watch} from "vue";
import {userStore} from "../store";

const uid = ref()
uid.value=userStore().userInfo.id

const props=defineProps({
  imgUrl: {
    type: String,
    default: '',
  },
})

const emit=defineEmits(['update:attachmentId'])



const url = ref(props.imgUrl)
const handleAvatarSuccess = (response, uploadFile) => {
  url.value = response.data.imgUrl
  emit("update:attachmentId", response.data.id)
}

const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('可上传的图片格式为 jpg/png/jpeg')
    return false
  } else if (rawFile.size / 1024 > 500) {
    ElMessage.error('图片大小不能超过500kb')
    return false
  }
  return true
}


watch(()=>props.imgUrl,(val)=>{
  url.value=val
})

</script>

<style scoped>

</style>