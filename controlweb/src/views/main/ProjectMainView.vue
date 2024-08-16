<template>
  <a-layout>
    <HeaderLayOut :projectId="projectId" > </HeaderLayOut>
    <a-layout>
      <a-layout-sider width="200">
        <a-menu v-model:selectedKeys="selectedKeys1" v-model:openKeys="openKeys" mode="inline"
          :style="{ height: '100%', borderRight: 0 }">
          <a-sub-menu key="sub1">
            <template #title>
              <span>
                subnav 1
              </span>
            </template>
            <a-menu-item key="1">
              <RouterLink :to="{ name: 'tasks', params: { projectId: props.projectId } }">Tasks</RouterLink>
            </a-menu-item>
            <a-menu-item key="2">
              <RouterLink :to="{ name: 'about', params: { projectId: 'X' } }">About</RouterLink>
            </a-menu-item>
            <a-menu-item key="3">option3</a-menu-item>
            <a-menu-item key="4">option4</a-menu-item>
          </a-sub-menu>

        </a-menu>
      </a-layout-sider>

      <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb style="margin: 10px 0">
          <a-breadcrumb-item>
            <SmileOutlined :style="{ fontSize: '22px', color: '#08c' }"> </SmileOutlined>
          </a-breadcrumb-item>

        </a-breadcrumb>
        <a-layout-content :style="{ background: '#FFF', padding: '24px', margin: 0, minHeight: '1000px' }">
          <RouterView />
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>


  <CreateTask></CreateTask>
</template>
<script lang="ts" setup>
import { SmileOutlined } from '@ant-design/icons-vue';
import { ref, onMounted, watch } from 'vue';
import CreateTask from '@/components/CreateTask.vue'
import HeaderLayOut from '@/components/HeaderLayOut.vue';
import { useRouter } from 'vue-router'
import projectService from '@/api/projectservice';
const router = useRouter();
const props = defineProps({
  projectId: String
})


const selectedKeys1 = ref(['1']);
const openKeys = ref(['sub1']);

onMounted(() => {
  // console.log(props.projectId)
  // if (props.projectId == 'undefined') {
  //   let userId = localStorage.getItem('userId');
  //   projectService.getProjectByUserId(userId).then(res => {
  //     if (res.data.length == 0) {
  //       return;
  //     }
  //     selectedProjectName.value = res.data[0].name;
  //     //跳转到tasks页面
  //     router.push({ name: 'main', params: { projectId: res.data[0].id} });
  //   })
  // }

})

watch(props, (value) => {
  // router.push({ name: 'tasks', params: { projectId: value.projectId } });
})






</script>
<style scoped></style>