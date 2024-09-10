<template>
    <a-layout-header style="background-color: #1677ff;">

        <a-flex justify="space-between">
            <a-flex gap="80">
                <div style="font-size: 30px;color: white;">Control</div>

                <div>
                    <a-select  v-model:value="projectId" style="width: 120px"   :placeholder="$t('project.name') "  >
                          <a-select-option @click="changeProject(record.id)" v-for=" record of projectList" :value="record.id">{{record.name}}</a-select-option>
                   </a-select>

                </div>

            </a-flex>

            <div style="width: 300px;">
                <a-menu style="background-color: #1677ff;" theme="dark" mode="horizontal">
                    <!-- <a-menu-item key="1"> {{ $t('home.name') }} </a-menu-item> -->
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
import projectService from '@/api/projectservice';
import { ref, onMounted ,watch} from 'vue';
import { useRouter } from 'vue-router'
const router = useRouter();
const projectId = defineModel("projectId");

const projectList = ref([])
onMounted(() => {
    loadProjectList();
})

function changeProject(e) {
    router.push({ name: 'tasks', params: { projectId: e } })

}

function loadProjectList(){
    let userId = localStorage.getItem("userId");
    projectService.getProjectByUserId(userId).then(res => {
        if (res.data.length == 0) {
            return;
        }
        projectList.value = res.data
    })
}




</script>

<style></style>