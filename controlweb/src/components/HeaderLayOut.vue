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
                                <a-menu-item v-for=" record of projectList" :key="record.id" :title="record.name">
                                {{record.name }}</a-menu-item>
                            </a-menu>
                        </template>
                    </a-dropdown>

                </div>

            </a-flex>

            <div style="width: 300px;">
                <a-menu style="background-color: #1677ff;" theme="dark" mode="horizontal">
                    <a-menu-item key="1"> {{ $t('home.name') }} </a-menu-item>
                    <a-menu-item key="2">
                        <RouterLink :to="{ name: 'sys' }">{{ $t('button.syssetting') }}</RouterLink>
                    </a-menu-item>

                    <a-menu-item key="3">{{ $t('button.usersetting') }} </a-menu-item>

                </a-menu>
            </div>


        </a-flex>
    </a-layout-header>
</template>

<script setup>
import { DownOutlined } from '@ant-design/icons-vue';
import projectService from '@/api/projectservice';
import { ref, onMounted, onBeforeMount ,watch} from 'vue';
import { useRouter } from 'vue-router'
const router = useRouter();
const props = defineModel({
    projectId: String
})

const projectName= ref(null)

const projectList = ref([])

onMounted(() => {
   // console.log(props.projectId)
    if (props.projectId == 'undefined') {
        return;
    }

    let userId = localStorage.getItem('userId');

    projectService.getProjectByUserId(userId).then(res => {
        if (res.data.length == 0) {
            return;
        }
        projectList.value = res.data
        for (let i = 0; i < res.data.length; i++) {
            if (res.data[i].id == props.projectId) {
                projectName.value = res.data[i].name;
                break;
            }
        } 
    })
})

function handleMenuClick(e) {
    projectName.value = e.item.title
    localStorage.setItem("projectId", e.key)
    router.push({ name: 'main', params: { projectId: e.key } })

}

watch(props, (value) => {
    console.log(value)
  // router.push({ name: 'tasks', params: { projectId: value.projectId } });
})


</script>

<style></style>