<template>
  <div class="login-container">
    <el-row justify="center" align="middle">
      <el-col :xs="20" :sm="12" :md="10" :lg="8" :xl="8">
        <el-card>
          <el-form :model="form"
                   status-icon
                   ref="loginForm"
                   :size="'large'">
            <h3 class="title">系统注册</h3>
            <el-form-item>
            </el-form-item>
            <el-form-item prop="username" label="账户"
                          :rules="[
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        {min:3, message: '账户最少3个字符', trigger: 'blur'},
                        {max: 11, message: '账户长度过长', trigger: 'blur'}
                    ]"
            >
              <el-input type="text" v-model="form.username" auto-complete="off" placeholder="用户名" clearable></el-input>
            </el-form-item>
            <el-form-item prop="password" label="密码"
                          :rules="[
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min:6, message: '密码最少6位', trigger: 'blur'},
                        {max: 18, message: '密码长度过长', trigger: 'blur'}
                    ]"
            >
              <el-input
                  type="password"
                  v-model="form.password"
                  auto-complete="off"
                  placeholder="密码"
                  show-password
                  clearable
              ></el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="name"
                          :rules="[
                        {required: true, message: '请输入昵称', trigger: 'blur'},
                        {min:3, message: '昵称最少3个字符', trigger: 'blur'},
                        {max: 11, message: '昵称长度过长', trigger: 'blur'}]"
            >
              <el-input v-model="form.name" auto-complete="off" placeholder="请输入昵称" clearable></el-input>

            </el-form-item>
            <el-form-item label="邮箱" prop="email"
                          :rules="[
                        {required: true, message: '请输入邮箱', trigger: 'blur'},
                        {pattern:/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/, message: '请输入正确的邮箱格式', trigger: 'blur'}]"
            >
                <el-input v-model="form.email" auto-complete="off" placeholder="请输入邮箱" clearable></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="code"
                          :rules="[
                        {required: true, message: '请输入验证码', trigger: 'blur'}]">
              <el-col :span="12">
                <el-input v-model="form.code" auto-complete="off" placeholder="请输入验证码"
                          @keypress.enter.native="handleSubmit"
                          clearable></el-input>
              </el-col>
              <el-col :span="6" :offset="1">
                <el-button :disabled="!isEmail" type="primary" @click="sendEmail">{{pageData.msg}}</el-button>
              </el-col>
            </el-form-item>
            <el-form-item>
                <el-col :span="18">
                </el-col>
                <el-col :span="6" class="toReg">
                  <span class="pointer" @click="router.push('/login')">已有账户</span>
                </el-col>
                <el-col>
                  <el-button type="primary" style="width: 100%" @click="handleSubmit">注册</el-button>
                </el-col>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>

</template>

<script setup>
import {useRouter} from 'vue-router'
import {computed, reactive, ref} from "vue";
import {getCode, login, register} from "../api/login";
import {clearRememberMeLibrary, setLibrary, setRememberMeLibrary} from "../utils/auth";
import {ElMessageBox, ElNotification} from "element-plus";

const router = useRouter()

const pageData=reactive({
  msg:'获取验证码',
})

const form = reactive({
  username: '',
  password: '',
  name: '',
  email: '',
  code: '',
})

const isEmail=computed(()=>{
  return /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(form.email)
})
const sendEmail=()=>{
  if(!isEmail.value){
    return
  }
  if(pageData.msg!=='获取验证码'){
    ElNotification.error({
      title: '错误',
      message: '冷却中',
      position: 'top-right'
    })
    return
  }
  pageData.msg='发送中...'


  pageData.msg='发送成功'
  let time=60
  let temp= setInterval(
      ()=>{
        pageData.msg=`${time}s`
        time--
        if(time<0){
          clearInterval(temp)
          pageData.msg='获取验证码'
        }
      }, 1000)
    getCode(form.email).then(res=>{
      if(res.data){
        ElNotification.success({
          title: '成功',
          message: '发送成功',
          position: 'top-right'
        })
      }else{
        ElNotification.error({
          title: '错误',
          message: '发送失败',
          position: 'top-right'
        })
      }
    })
}
//表单提交
const loginForm = ref(null)

function handleSubmit() {
  loginForm.value.validate((valid) => {
    if (valid) {
      register(form).then(res=>{
        if(res.data){
          ElMessageBox.alert('是否跳转到登录页', '注册成功', {
            confirmButtonText: '确定',
            callback: (action) => {
                router.push({
                  name: '登录',
                  params: {
                    username: form.username,
                    password: form.password
                  }
                })
            },
          })
        }else{
          ElNotification.error({
            title: '错误',
            message: '注册失败',
            position: 'top-right'
          })
        }
      })
    }
  })
}

</script>

<style scoped>
.login-container {
  position: fixed;
  width: 100%;
  height: 100%;
  background: url('../assets/img/background.svg') no-repeat;
  background-size: cover;
}



.toReg{
  text-align: right;
  font-size: 12px;
  color: #cccccc;
}
.toReg:hover{
  text-decoration: underline;
}
.el-row{
  height: 100%;
}
</style>