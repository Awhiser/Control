<template>

  <a-select 
  v-model:value="userId" 
  show-search  
  placeholder="input search text"  
  :options="userOpt" 
  :not-found-content="null"  
  @search="searchUser" >  </a-select>
   
</template>
  <script lang="ts"  setup>

   import { ref  } from 'vue'
   import userService from '@/api/userservice';

   const userId = defineModel("userId")
   const userOpt = defineModel("userOpt",{default:[]})
  
  function searchUser(name) {
    if(name == "") {
      return;
    }
    userService.getList({name:name}).then(res => {
        userOpt.value =  res.data.dataList.map(user=>({
            label: `${user.displayName} (${user.name})`,
             value: user.id,
        }))
    })

  }
  
  
  </script>
  