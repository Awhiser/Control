<template>




    <a-layout>
    <HeaderLayOut></HeaderLayOut>
    <a-layout>
      <a-layout-sider width="200" style="background: #fff">
        <a-menu
                mode="inline"
                :style="{ height: '100%', borderRight: 0 }"
            >
                <a-menu-item key="1"> <RouterLink to="/sys/project"  >  {{$t('button.projectManager') }} </RouterLink>  </a-menu-item>
                <!-- <a-menu-item key="2">option4</a-menu-item> -->



                
            <a-sub-menu >
                <template #title>
                 <span>
               用户管理
              </span>
            </template>
                <a-menu-item key="2">  <RouterLink to="/sys/user">  {{$t('button.userManager') }} </RouterLink>  </a-menu-item>
                <a-menu-item key="3">  <RouterLink to="/sys/userconnector">  {{$t('button.userConnectorManager') }} </RouterLink> </a-menu-item>
            </a-sub-menu>
        
            </a-menu>
      </a-layout-sider>
      <a-layout style="padding: 0 24px 24px">
        <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '1000px' }"
        >
        <RouterView  />
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>











</template>


<script setup >

  import HeaderLayOut from '@/components/HeaderLayOut.vue'


</script>

<style scoped>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>