<template>

    <a-row  style="margin-bottom:10px" >
         <CreateProject></CreateProject>
    </a-row>



<a-table   ref="taskTable" :pagination="false" :key="new Date().getTime()" :row-key="record => record.id"  :columns="columns" :data-source="data">
    

    <template #bodyCell="{ column, record }">

    <template v-if="column.key === 'name'">
        <a @click="showTaskDetails(record.id)" >
        {{ record.name}}
        </a>
    </template>

    <template v-if="column.key === 'operation'">

       <a-button> Edit</a-button> 

       <a-button style="margin-left:10px" > Member</a-button> 

       <a-button style="margin-left:10px"  > Delete</a-button>
    </template>
   

    </template>
</a-table>


















</template>


<script setup>

import { ref,onMounted } from 'vue';
import i18n from '@/i18n/index';
import CreateProject from '@/components/sys/CreateProject.vue';

const columns = [
  {
    title: i18n.global.t('project.name') ,
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


let data = ref([
  {
    id: 'X-1',
    name: 'Tasks',
    leader: 'FFF'
  }

]);

</script>


<style>


</style>