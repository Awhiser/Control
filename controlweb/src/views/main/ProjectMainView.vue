<template>
  <a-layout>
    <HeaderLayOut v-model:projectId="selectedProject" > </HeaderLayOut>
    <a-layout>
      <a-layout-sider width="200">
        <a-menu v-model:selectedKeys="selectedKeys1" v-model:openKeys="openKeys" mode="inline"
          :style="{ height: '100%', borderRight: 0 }">
          <a-sub-menu key="sub1">
            <template #title>
              <span>
              {{ i18n.global.t('task.name') }}
              </span>
            </template>
            <a-menu-item key="1">
              <RouterLink :to="{ name: 'tasks', params: { projectId: props.projectId } }">{{ i18n.global.t('task.name') }} </RouterLink>
            </a-menu-item>
          </a-sub-menu>

            <!-- <a-menu-item key="2">
              <RouterLink :to="{ name: 'about', params: { projectId: 'X' } }">About</RouterLink>
            </a-menu-item>
           -->
          <a-sub-menu key="sub2">
            <template #title>
              <span>
                {{ i18n.global.t('project.setting') }}
              </span>
            </template>
            <a-menu-item key="3">  <RouterLink :to="{ name: 'version', params: { projectId: props.projectId } }">{{i18n.global.t('version.name')}}  </RouterLink> </a-menu-item>
            <a-menu-item key="4">{{i18n.global.t('user.name')}}</a-menu-item>
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
import { ref, onMounted, watch,onUpdated } from 'vue';
import CreateTask from '@/components/task/CreateTask.vue'
import HeaderLayOut from '@/components/HeaderLayOut.vue';
import { useRouter,useRoute } from 'vue-router'
import projectService from '@/api/projectservice';
import i18n from '@/i18n';
const router = useRouter();
const props = defineProps({
  projectId: String
})

const route = useRoute()
const selectedKeys1 = ref(['1']);
const openKeys = ref(['sub1']);
const selectedProject = ref(null);

onMounted(() => {
  selectedProject.value = route.params.projectId
 // console.log(props.projectId)
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

onUpdated(()=>{
 // selectedProject.value = route.params.projectId
 

})








</script>
<style scoped></style>