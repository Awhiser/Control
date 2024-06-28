<template>
    <a-layout>
      <a-layout-header style="background-color: #1677ff;">

        <a-flex justify="space-between" >
            <div class="logo" > Control  </div>
            <a-menu style="background-color: #1677ff;"
              v-model:selectedKeys="selectedKeys1"
              theme="dark"
              mode="horizontal"
              :style="{ lineHeight: '64px' }"
            >
            <a-menu-item key="1"  >nav 1</a-menu-item>
              <a-menu-item key="2">nav 2</a-menu-item>
              <a-menu-item key="3">nav 3</a-menu-item>
            </a-menu>
        
        </a-flex>
      </a-layout-header>

      <a-layout>
        <a-layout-sider width="200" >
          <a-menu
            v-model:selectedKeys="selectedKeys1"
            v-model:openKeys="openKeys"
            mode="inline"
            :style="{ height: '100%', borderRight: 0 }"
          >
            <a-sub-menu key="sub1">
              <template #title>
                <span>
                  <user-outlined />
                  subnav 1
                </span>
              </template>
              <a-menu-item key="1"><RouterLink  :to="{name:'tasks',params:{projectId:'X'}}" >Tasks</RouterLink></a-menu-item>
              <a-menu-item key="2"><RouterLink :to="{name:'about',params:{projectId:'X'}}">About</RouterLink></a-menu-item>
              <a-menu-item key="3">option3</a-menu-item>
              <a-menu-item key="4">option4</a-menu-item>
            </a-sub-menu>
    
          </a-menu>
        </a-layout-sider>


        <a-layout style="padding: 0 24px 24px">
          <a-breadcrumb style="margin: 16px 0">
            <a-breadcrumb-item>Home</a-breadcrumb-item>
            <a-breadcrumb-item>List</a-breadcrumb-item>
            <a-breadcrumb-item>App</a-breadcrumb-item>
          </a-breadcrumb>
          <a-layout-content
            :style="{ background: '#FFF', padding: '24px', margin: 0, minHeight: '1000px' }"
          >
            <RouterView  />
          </a-layout-content>
        </a-layout>
      </a-layout>
    </a-layout>




<!-- <a-button type="primary" @click="showDrawer">Open</a-button> -->







<CreateTask></CreateTask>

  </template>
  <script lang="ts" setup>
  
  import { ref,onMounted } from 'vue';
  import CreateTask from '@/components/CreateTask.vue'

  import { useRouter  } from 'vue-router'

  const props = defineProps({
    projectId: String
  })

  const router = useRouter();
  const selectedKeys1 = ref(['1']);
  const openKeys = ref(['sub1']);

  onMounted(() => {
  //  console.log(props.projectId)
    router.push({name:'tasks',params:{projectId:props.projectId}});

   // taskTable.$forceUpdate()
  })

  

  


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