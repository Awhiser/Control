<template>

  <a-select 
  v-model:value="userId" 
  show-search  
  :placeholder="i18n.global.t('input.searchName') "  
  :options="userOpt" 
  :not-found-content="null"  
  @search="searchUser" >  </a-select>
   
</template>
  <script lang="ts"  setup>

   import { ref  } from 'vue'
   import userService from '@/api/userservice';
    import i18n from '@/i18n';
   const userId = defineModel("userId")
   const userOpt = defineModel("userOpt",{default:[]})
  
  function searchUser(name) {
    if(name == "") {
      return;
    }
    userId.value = null
    userService.getList({name:name}).then(res => {
        userOpt.value =  res.data.dataList.map(user=>({
            label: `${user.displayName} (${user.name})`,
             value: user.id,
        }))
    })
  

  }
  
  
  </script>
  