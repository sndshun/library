<template>
  <div style="border: 1px solid #ccc;z-index: 2030">
    <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editorRef"
        :defaultConfig="toolbarConfig"
        :mode="mode"
    />
    <Editor
        style="height: 320px; overflow-y: hidden;"
        v-model="valueHtml"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="handleCreated"
        @onChange="handleChange"
    />
  </div>
</template>

<script setup>
import '@wangeditor/editor/dist/css/style.css' // 引入 css

import {onBeforeUnmount, ref, shallowRef, onMounted, watch, nextTick} from 'vue'
import {Editor, Toolbar} from '@wangeditor/editor-for-vue'
import {getLibrary, getRememberMeLibrary} from "../utils/auth";
import {userStore} from "../store";
import {ElMessage} from "element-plus";


const uid = ref()
uid.value=userStore().userInfo.id

const props = defineProps({
  content: {
    type: String,
    default: '',
  },
  readonly: {
    type: Boolean,
    default: false,
  },
})

const emit = defineEmits(['update:content'])

function handleChange(opt) {
  emit('update:content', opt.getHtml())
}


const mode = ref('default') // 或 'simple'
// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()

// 内容 HTML
let temp = props.content
let read = props.readonly
const valueHtml = ref(temp)

// 模拟 ajax 异步获取内容
// onMounted(() => {
//   // nextTick(() => {
//   //   valueHtml.value = temp
//   // })
//   // setTimeout(() => {
//   //   valueHtml.value = '<p>模拟 Ajax 异步设置内容</p>'
//   // }, 1500)
// })

const toolbarConfig = {
  excludeKeys: [
    'uploadVideo',
  ]
}
let library=getLibrary()||getRememberMeLibrary()

const editorConfig = {
  placeholder: '请输入内容...',
  readOnly: read,
  MENU_CONF: {}
}

editorConfig.MENU_CONF['uploadImage'] = {
  server: '/api/attachment/bookContentUpload?uid='+uid.value,
  // form-data fieldName ，默认值 'wangeditor-uploaded-image'
  fieldName: 'file',
  // 单个文件的最大体积限制，默认为 2M
  maxFileSize:500*1024, // 500kb
  // 最多可上传几个文件，默认为 100
  maxNumberOfFiles: 10,
  // 选择文件时的类型限制，默认为 ['image/*'] 。如不想限制，则设置为 []
  allowedFileTypes: ['image/*'],
  // 自定义上传参数，例如传递验证的 token 等。参数会被添加到 formData 中，一起上传到服务端。
  meta: {
    library: library,
  },
  // 将 meta 拼接到 url 参数中，默认 false
  metaWithUrl: false,
  // 自定义增加 http  header
  // headers: {
  //   Accept: 'text/x-json',
  //   otherKey: 'xxx'
  // },
  // 跨域是否传递 cookie ，默认为 false
  withCredentials: false,
  // 超时时间，默认为 10 秒
  timeout: 5 * 1000, // 5 秒
  customInsert(res, insertFn) {
    // res 即服务端的返回结果
    // 从 res 中找到 url alt href ，然后插图图片
    if(res.code==200){
      insertFn(res.data.imgUrl, res.data.imgDesc, res.data.dataHref)
    }else {
      ElMessage.error(res.message)
    }
  },
}



// 组件销毁时，也及时销毁编辑器
// onBeforeUnmount(() => {
//   const editor = editorRef.value
//   if (editor == null) return
//   editor.destroy()
// })
let _editor
const handleCreated = (editor) => {
  editorRef.value = editor // 记录 editor 实例，重要！
  _editor = editor
  // console.log(editor.getAllMenuKeys())
}


watch(props, (val) => {
  valueHtml.value = val.content
  nextTick(() => {
    if (read) {
      _editor.disable()
    }
  })
})
</script>

<style scoped>

</style>