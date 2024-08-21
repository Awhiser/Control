<template>
  <a-select v-model:value="userId" show-search :placeholder="i18n.global.t('input.searchName')" :options="userOpt"
    :not-found-content="null" @search="searchUser"> </a-select>
</template>
<script lang="ts"  setup>

import userService from '@/api/userservice';
import projectService from '@/api/projectservice';
import i18n from '@/i18n';
const userId = defineModel("userId")
const userOpt = defineModel("userOpt", { default: [] })

const onlyProjectUser = defineModel("onlyProjectUser", { default: false })
const projectId =  defineModel("projectId", { default: null })

function searchUser(name) {
  if (name == "") {
    return;
  }
  userId.value = null

  if(onlyProjectUser.value){
    if(!projectId.value){
      return;
    }
    projectService.getProjectMemberList({projectId:projectId.value,name:name}).then(res=>{
        userOpt.value = res.data.dataList.map(item => ({
        label: `${item.user.displayName} (${item.user.name})`,
        value: item.user.id,
      }))

    });

    return;
  }


  userService.getList({ name: name }).then(res => {
    userOpt.value = res.data.dataList.map(user => ({
      label: `${user.displayName} (${user.name})`,
      value: user.id,
    }))
  })


}


</script>
  