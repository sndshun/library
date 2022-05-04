<template>
  <library-header/>
  <div class="container">
    <el-row justify="center">
      <el-col v-if="userInfo" :xs="24" :sm="24" :md="20" :lg="20" :xl="20">
        <el-form :model="userInfo" ref="infoForm">
          <el-form-item label="头像">
            <el-upload
                class="avatar-uploader"
                :action="'/api/attachment/profile?uid='+userInfo.id"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                drag
            >
              <img v-if="userInfo.profile" :src="userInfo.profile" class="avatar"/>
              <el-icon class="el-icon--upload">
                <upload-filled/>
              </el-icon>
              <div class="el-upload__text">
                拖拽上传或者 <em>点击上传</em>
              </div>
            </el-upload>
          </el-form-item>
          <el-form-item label="角色">
            <el-space>
              <el-tag v-for="(item,index) in userInfo.rolesName" :key="index">
                {{ item }}
              </el-tag>
            </el-space>
          </el-form-item>
          <el-form-item label="用户名" prop="name" :rules="[
                        {required: true, message: '请输入昵称', trigger: 'blur'},
                        {min:3, message: '昵称最少3个字符', trigger: 'blur'},
                        {max: 11, message: '昵称长度过长', trigger: 'blur'}]"
          >
            <el-input v-model="userInfo.name"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-radio v-model="userInfo.sex" :label="1">男</el-radio>
            <el-radio v-model="userInfo.sex" :label="2">女</el-radio>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="userInfo.email" disabled></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone"
            :rules="[
                {required: false, message: '请输入电话', trigger: 'blur'},
                {pattern: /^1[3456789]\d{9}$/, message: '请输入正确的电话', trigger: 'blur'}
            ]"
          >
            <el-input v-model="userInfo.phone"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="userInfo.address"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onsubmit">保存</el-button>
          </el-form-item>
        </el-form>

      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import {UploadFilled} from '@element-plus/icons-vue'
import LibraryHeader from '../components/LibraryHeader.vue'
import {storeToRefs} from "pinia";
import {userStore} from "../store";
import {ElMessage} from "element-plus";
import {updateUser} from "../api/user";
import {ref} from "vue";

const store = userStore()

const infoForm=ref(null)
const {userInfo} = storeToRefs(userStore())

let profileID = null
const handleAvatarSuccess = (response, uploadFile) => {
  store.userInfo.profile = response.data.imgUrl
  profileID = response.data.id
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
const onsubmit = () => {
  infoForm.value.validate((valid) => {
    if (valid) {
      updateUser({
        id: userInfo.value.id,
        name: userInfo.value.name,
        phone: userInfo.value.phone,
        sex:userInfo.value.sex,
        address:userInfo.value.address,
        email: userInfo.value.email,
        profile:profileID
      }).then(res => {
        if (res.code === 200) {
          ElMessage.success('修改成功')
        }
      })
    }
  })
}
</script>

<style scoped>

</style>