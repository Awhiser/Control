<template>
  <a-row style="margin-bottom:10px">
    <CreateProject></CreateProject>
  </a-row>


  <a-row style="margin-bottom:10px">

    <a-col col="10">
      <a-input-search v-model:value="searchName" :placeholder="i18n.global.t('input.searchName')" enter-button @search="onSearch" />
    </a-col>

  </a-row>



  <a-table :scroll="{ y: 850 }" :pagination="false" :key="new Date().getTime()" :row-key="record => record.id"
    :columns="columns" :data-source="data">


    <template #bodyCell="{ column, record }">

      <template v-if="column.key === 'name'">
        <a @click="showTaskDetails(record.id)">
          {{ record.name }}
        </a>
      </template>

      <template v-if="column.key === 'leader'">
        <a @click="showTaskDetails(record.id)">
          {{ record.leader.displayName }}
        </a>
      </template>

      <template v-if="column.key === 'operation'">

        <a-button @click="showEdit(record)" >{{$t('button.edit') }}</a-button>

        <a-button type="primary" style="margin-left:15px" @click="showProjectMember(record.id)"  >  {{ $t('button.member') }} </a-button>

        <a-popconfirm :title="$t('message.confirm')"  @confirm="deleteProject(record.id)" >
          <a-button danger style="margin-left: 15px;"> {{ $t('button.delete') }} </a-button>
        </a-popconfirm>

      </template>


    </template>
  </a-table>


  <div style="float: right; margin-top: 10px;">

    <a-pagination v-model:current="current" v-model:page-size="pageSize" show-size-changer :total="total"
      @change="onChangePage" :show-total="total => i18n.global.t('page.total') + ` : ${total}`" />


  </div>

  <EditProject v-model:open="editShow" v-model:projectModel="editModel" ></EditProject>

  <ProjectMemberView v-model:open="showProjectMemberModal" :projectId="selectProjectId" > </ProjectMemberView>
</template>


<script setup>

import { ref, onMounted } from 'vue';
import i18n from '@/i18n/index';
import CreateProject from '@/components/sys/project/CreateProject.vue';
import EditProject from '@/components/sys/project/EditProject.vue';
import ProjectMemberView from '@/components/sys/project/ProjectMemberView.vue';
import projectService from '@/api/projectservice';
const columns = [
  {
    title: i18n.global.t('project.name'),
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: i18n.global.t('project.leader'),
    key: 'leader',
    dataIndex: 'leader',
  }
  ,
  {
    title: i18n.global.t('table.operation'),
    key: 'operation',
    dataIndex: 'operation',
  }

];


let data = ref([]);
const searchName = ref(null)
const pageSize = ref(20)
const current = ref(1)
const total = ref(0)


const editShow = ref(false);
const editModel = ref({})


const showProjectMemberModal = ref(false);
const selectProjectId = ref('');
onMounted(() => {
  loadData();
})

function loadData() {
  projectService.getProjectPage({ pageSize: pageSize.value, pageIndex: current.value - 1, extLeader: true, name: searchName.value }).then(res => {
    data.value = res.data.dataList;

    total.value = res.data.totalElement;
  })
}

function onSearch() {
  loadData();
}
function onChangePage() {
  loadData();
}

function deleteProject(id) {
    projectService.delete(id).then(res => {
      location.reload()
    });
  }
  function showEdit(value) {
    editModel.value = value;
    editShow.value = true;
  }

  function showProjectMember(projectId){
    selectProjectId.value = projectId;
    showProjectMemberModal.value = true;
  }

</script>


<style></style>