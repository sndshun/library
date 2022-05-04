<template>
  <el-card>
    <el-card shadow="never">
      <h2>修改权限会清除登录用户缓存，请勿频繁修改</h2>
    </el-card>
    <el-table
        :data="roles"
        style="width: 100%"
    >
      <el-table-column type="expand">
        <template #default="props">
          <div v-for="menu in props.row.menus" :key="menu.menuCode">
              <span
                  v-text="menu.menuName"
                  style="display:inline-block;width: 200px;margin: 2px 0;text-indent: 20px"
              />
            <el-tag
                v-for="per in menu.permissions"
                :key="per.pid"
                v-text="per.permissionName"
                style="margin: 2px 5px"
            />
          </div>
          <el-empty v-if="null==props.row.menus||props.row.menus==''" :description="pageData.emptyText"/>
        </template>
      </el-table-column>
      <el-table-column label="角色" prop="roleName"/>
      <el-table-column label="角色描述" prop="description"/>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">变更权限</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="roleForm.dialogFormVisible" title="权限编辑">
      <el-form :model="roleForm.role">
        <el-form-item label="角色名称:" :label-width="roleForm.formLabelWidth">
          <el-input v-model="roleForm.role.description" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="角色权限:" :label-width="roleForm.formLabelWidth">
          <div
              v-for="menu in roleForm.menus"
              :key="menu.menuCode"
              style="width: 100%;"
          >
            <div style="display: flex;">
            <span
                v-text="menu.menuName"
                style="flex-basis: 100px;flex-shrink: 0;text-align: left"
            />
              <div style="flex-grow: 3; display: flex; flex-wrap: wrap;">
                <el-tag
                    v-for="per in menu.permissions"
                    :type="tag(per.id)"
                    :key="per.id"
                    v-text="per.permissionName"
                    @click="editPer(per.id)"
                    style="margin: 2px 5px;cursor:pointer"
                />
              </div>

            </div>

          </div>
          <el-empty v-if="null==roleForm.menus||roleForm.menus==''" :description="pageData.emptyText"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="roleForm.dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submit">提交</el-button>
      </span>
      </template>
    </el-dialog>
  </el-card>

</template>

<script setup>
import {getRoleIsMenuPer, updRolePer} from "../../api/permiss";
import {getAllPer} from "../../api/permiss";
import {computed, onBeforeMount, onMounted, reactive, ref} from "vue";
import {userStore} from "../../store";
import {ElMessage} from "element-plus";

const user = userStore();
const uid=user.userInfo.id

onBeforeMount(()=>{
  init()
})

onMounted(() => {
  menuInit()
})

const pageData = reactive({
  emptyText: '未分配权限'
})
const roles = ref()
const init=()=>{
  getRoleIsMenuPer().then(res => {
    roles.value = res.data
  })
}



const roleForm = reactive({
  dialogFormVisible: false,
  formLabelWidth: '120',
  role: {},
  menus: [],
  permissions: []
})
const menuInit = () => {
  getAllPer().then(res => {
    roleForm.menus = res.data
  })
}

function handleEdit(row) {
  roleForm.permissions=[]
  roleForm.dialogFormVisible = true
  row.menus.map(item => {
    for (let key in item.permissions) {
      roleForm.permissions.push(item.permissions[key].pid)
    }
  })
  roleForm.role = row
}
const tag=computed(()=>{
  return function (pid){
    return roleForm.permissions.indexOf(pid)>-1?'':'info'
  }
})
const editPer=(pid)=>{
  const i=roleForm.permissions.indexOf(pid)
  if (i > -1) {
    roleForm.permissions.splice(i,1)
  } else {
    roleForm.permissions.push(pid)
  }
}

function submit() {
  roleForm.dialogFormVisible = false
  const {role,permissions}=roleForm
  updRolePer(role.rid,role.description,uid,permissions).then(res=>{
    if (res.code == 200) {
      init();
      ElMessage.success('修改成功');
    }
  })
}

</script>

<style scoped>

</style>