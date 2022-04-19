<template>
  <div v-loading="loading"
       element-loading-text="拼命加载中"
       class="login-container">
    <el-form :model="form"
             status-icon
             ref="loginForm"
             :size="'large'"
             class="login-page">
      <h3 class="title">系统登录</h3>
      <el-form-item>
      </el-form-item>
      <el-form-item prop="username"
                    :rules="[
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        {min:3, message: '账户最少3个字符', trigger: 'blur'},
                        {max: 11, message: '账户长度过长', trigger: 'blur'}
                    ]"
      >
        <el-input type="text" v-model="form.username" auto-complete="off" placeholder="用户名" clearable></el-input>
      </el-form-item>
      <el-form-item prop="password"
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
            @keypress.enter.native="handleSubmit"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="rememberMe" class="rememberMe">记住我</el-checkbox>
        <el-button type="primary" style="width: 100%" @click="handleSubmit">登录</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script setup>
import {useRouter} from 'vue-router'
import {reactive, ref} from "vue";
import {login} from "../api/login";
import {clearRememberMeLibrary, setLibrary, setRememberMeLibrary} from "../utils/auth";

const router = useRouter()

const loading = ref(false)
const form = reactive({
  username: 'admin',
  password: '123456',
})

const rememberMe = ref(false)


//表单提交
const loginForm = ref(null)

function handleSubmit() {
  loginForm.value.validate((valid) => {
    if (valid) {
      login(form).then(res=>{

        if (!res.data) {
          return
        }
        setLibrary(res.data)

        if (rememberMe.value) {
          //点击记住我保存cookie
          setRememberMeLibrary(res.data);
        } else {
          clearRememberMeLibrary()
        }

        router.push('/');

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

.login-page {
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.rememberMe {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>