

<template>
   <a-config-provider :locale="locale">
  <RouterView />
  </a-config-provider>
  <!-- <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header> -->

  
</template>

<script setup lang="ts" >
  import dayjscn from 'dayjs/locale/zh-cn';
  import dayjsen from 'dayjs/locale/en';
  import zhCN from 'ant-design-vue/es/locale/zh_CN';
  import en from 'ant-design-vue/es/locale/en_US';
  import { onBeforeMount,getCurrentInstance } from 'vue'
  import dayjs from 'dayjs';
  
  const { proxy } = getCurrentInstance() as any
  let locale = zhCN
  onBeforeMount(() => {
    let lang = sessionStorage.getItem('localeLang') ?? 'zhCn'
    proxy.$i18n.locale = lang
    if(lang == 'zhCn'){
      locale = zhCN
      dayjs.locale(dayjscn);
    }else{
      locale=en
      dayjs.locale(dayjsen);
    }


  })




</script>


<style>

</style>
