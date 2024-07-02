<script setup lang="ts">

import { useLangStore } from '@/stores/useLangStore'
import { getCurrentInstance } from 'vue'

const store = useLangStore()
const { proxy } = getCurrentInstance() as any
const handleCommand = (value: 'zhCn' | 'en') => {

    proxy.$i18n.locale = value
  sessionStorage.setItem('localeLang',value);
  console.log(value)
//   if (store.language === value) return
//   store.changeLang(value)
//   ElMessage.closeAll()
//   ElMessage.success(`${value === 'en' ? '英文' : '中文'}切换成功！`)
}
</script>

<template>
    <a-button @click="handleCommand('zhCn')" >简体中文</a-button> 
 <a-button @click="handleCommand('en')" >English</a-button>   ---- <a-button @click="handleCommand('zhCn')" >简体中文</a-button> 
</template>

<style scoped >

</style>