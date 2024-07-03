<template>
    <a-layout-header style="background-color: #1677ff;">

        <a-flex justify="space-between">
            <a-flex gap="80">
                <div style="font-size: 30px;color: white;">Control</div>


                <div>

                    <a-dropdown>
                        <span style="font-size: 20px;color: white;" @click.prevent>
                            {{ $t('project.name') }} - {{ projectName }}
                            <DownOutlined />
                        </span>
                        <template #overlay>
                            <a-menu @click="handleMenuClick">
                                <a-menu-item v-for=" record of projectList" :key="record.id" :title="record.name"  >{{ record.name }}</a-menu-item>
                            </a-menu>
                        </template>
                    </a-dropdown>

                </div>

            </a-flex>

            <div style="width: 300px;">
                <a-menu style="background-color: #1677ff;" theme="dark" mode="horizontal">
                    <a-menu-item key="1"  > {{ $t('home.name') }}</a-menu-item>
                    <a-menu-item key="2">
                        <RouterLink :to="{ name: 'sys' }">{{ $t('button.syssetting') }}</RouterLink>
                    </a-menu-item>

                    <a-menu-item key="3">{{ $t('button.usersetting') }} </a-menu-item>
                    <!-- <a-menu-item key="4"> <I18N></I18N> </a-menu-item> -->
                </a-menu>
            </div>


        </a-flex>
    </a-layout-header>
</template>

<script setup>
import { DownOutlined } from '@ant-design/icons-vue';
import projectService from '@/api/projectservice';
import { ref, onMounted, onBeforeMount } from 'vue';
import { useRouter  } from 'vue-router'
  const router = useRouter();
const props = defineProps({
    projectId: String
})

const projectName = ref(null)

const projectList = ref([])

onMounted(() => {
    
    projectService.getList({}).then(res => {

        if (res.data.totalElement == 0) {
            return;
        }
        projectList.value = res.data.dataList
      
        if (props.projectId == 'undefined') {
          return;
        }

        for (let i = 0; i < projectList.value.length; i++) {
            
         
            if (projectList.value[i].id == props.projectId) {
                projectName.value = projectList.value[i].name;
               
                break;
            }
        } 

        if(projectName.value == null) {
            router.push({name:'main',params:{projectId: projectList.value[0].id}})
        }


    })

})

function handleMenuClick(e) {
    projectName.value = e.item.title
    router.push({name:'main',params:{projectId: e.key}})
   
}
</script>

<style></style>