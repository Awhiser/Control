<template>
  <a-select  style="width: 200px"   :filter-option="false" v-model:value="userId" :mode="mode" show-search label-in-value  :placeholder="i18n.global.t('input.searchName')" :options="userOpt"
    :not-found-content="null" @search="searchUser"> </a-select>

    <!-- <a-select v-if="mode=='multiple'" 
    style="width: 200px"
     v-model:value="userId" 
     mode="multiple" 
     :placeholder="i18n.global.t('input.searchName')"
      :options="userOpt"
      :filter-option="false"
    :not-found-content="fetching ? undefined : null"
    @search="searchUser"> </a-select> -->
</template>
<script lang="ts"  setup>
import { ref } from 'vue'
import userService from '@/api/userservice';
import projectService from '@/api/projectservice';
import i18n from '@/i18n';
const userId = defineModel("userId", { default: [] })
const userOpt = defineModel("userOpt", { default: [] })
const mode = defineModel("mode", { default: "combobox" }) 
const onlyProjectUser = defineModel("onlyProjectUser", { default: false })
const projectId =  defineModel("projectId", { default: null })

const fetching = ref(false)
function searchUser(name) {
  if (name == "") {
    return;
  }

  if(mode.value == 'combobox'){
    userId.value = null
  }

  fetching.value = true;
  if(onlyProjectUser.value){
    if(!projectId.value){
      return;
    }
    projectService.getProjectMemberList({projectId:projectId.value,name:name}).then(res=>{
        if(res.data.totalElement == 0) {
          return;
        }
        userOpt.value = res.data.dataList.map(item => ({
        label: `${item.user.displayName} (${item.user.name})`,
        value: item.user.id,
      }))

      console.log()
      fetching.value = false;
    });

    return;
  }


  userService.getList({ name: name }).then(res => {
    userOpt.value = res.data.dataList.map(user => ({
      label: `${user.displayName} (${user.name})`,
      value: user.id,
    }))
    fetching.value = false;
  })


}


</script>
  