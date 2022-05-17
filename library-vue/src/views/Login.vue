<template>
  <div class="login-container">
    <el-row justify="center" align="middle">
      <el-col :xs="20" :sm="12" :md="10" :lg="8" :xl="8">
        <el-card>
          <el-form :model="form"
                   status-icon
                   ref="loginForm"
                   :size="'large'">
            <h3 class="title">系统登录</h3>
            <el-form-item>
            </el-form-item>
            <el-form-item prop="username"
                          :rules="[
                        {required: true, message: '请输入账号或注册邮箱', trigger: 'blur'},
                        {min:3, message: '账户最少3个字符', trigger: 'blur'},
                        {max: 22, message: '账户长度过长', trigger: 'blur'}
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
                <el-col :span="18">
                  <el-checkbox v-model="rememberMe" class="rememberMe">记住我</el-checkbox>
                </el-col>
                <el-col :span="6" class="toReg">
                  <span class="pointer" @click="router.push('/reg')">没有账号?</span>
                </el-col>
                <el-col>
                  <el-button type="primary" style="width: 100%" @click="handleSubmit">登录</el-button>
                </el-col>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>

</template>

<script setup>
import {useRoute, useRouter} from 'vue-router'
import {onBeforeMount, reactive, ref} from "vue";
import {login} from "../api/login";
import {clearRememberMeLibrary, setLibrary, setRememberMeLibrary} from "../utils/auth";

const router = useRouter()
const route=useRoute()

onBeforeMount(()=>{
  if(route.params.username){
    form.username=route.params.username
  }
  if(route.params.password){
    form.password=route.params.password
  }
})



const form = reactive({
  username: '',
  password: '',
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
  /*background: url('../assets/img/background.svg') no-repeat;*/
  /*background-size: cover;*/
  background: url('../assets/img/bg-opt1.png') no-repeat;
  background-size: auto 100%;
}


.rememberMe {
  margin: 0px 0px 15px;
  text-align: left;
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