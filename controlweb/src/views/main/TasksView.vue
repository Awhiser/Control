<template  >
 


    <a-table   ref="taskTable" :scroll="{ y: 850 }" :pagination="false" :key="new Date().getTime()" :row-key="record => record.id"  :columns="columns" :data-source="data">



<template #bodyCell="{ column, record }">
  <template v-if="column.key === 'title'">
    <a @click="showTaskDetails(record.id)" >
      {{ record.title}}
    </a>
  </template>
  <template v-else-if="column.key === 'tags'">
    <span>
      <a-tag v-for="tag in record.tags" :key="tag" >
        {{tag}}
      </a-tag>
    </span>
  </template>

</template>
</a-table>


 <a-pagination
      v-model:current="current"
      v-model:page-size="pageSize"
      show-size-changer
      :total="total"
      :show-total="total => i18n.global.t('page.total') + ` : ${total}`"
    /> 




  <TaskDetails v-model:show="openTaskDetails"  v-model:taskId="taskId" > </TaskDetails>
  
  
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import taskService from '@/api/taskservice';
import { onMounted ,onUpdated} from 'vue' 
import TaskDetails from '@/components/TaskDetails.vue'
import i18n from '@/i18n';

const props = defineProps({
    projectId: String
  })

const openTaskDetails = ref(false);
const taskId =ref('')


const pageSize = ref(50)
const  current = ref(1)
const total = ref(100)


const columns = [
  {
    title: i18n.global.t('task.title'),
    dataIndex: 'title',
    key: 'title',
  },
  {
    title: i18n.global.t('task.type'),
    dataIndex: 'type',
    key: 'type',
  },

  {
    title: i18n.global.t('task.priority'),
    key: 'priority',
    dataIndex: 'priority',
  },
  {
    title: i18n.global.t('task.assignee'),
    key: 'assignee',
    dataIndex: 'assignee',
  },
  {
    title: i18n.global.t('task.tags'),
    key: 'tags',
    dataIndex: 'tags',
  },
 
  {
    title: i18n.global.t('task.status'),
    key: 'status',
    dataIndex: 'status',
  }

];


let data = ref([
//   {
//     id: 'X-1',
//     title: 'Tasks',
//     type: 'Task',
//     priority: 'Easy',
//     tags: ['nice', 'developer'],
//     status: '处理中',
//   }

]);


   onMounted(() => {
   
  })

  onUpdated(()=>{
    //console.log(props.projectId)
   // data.value = [];
    taskService.getTaskList({projectId:props.projectId}).then(res => {
          data.value = res.data.dataList ;
          total.value = data.value.length;
     })
  })

  function changeTable( pag,c,a){
    console.log(pag)
   // pagination.value.current = pag.current;
   // pagination.value.pageSize = pag.pageSize;

  }

  function showTaskDetails(id){
    //console.log(id)
    openTaskDetails.value = true;
    taskId.value = id;
  }

</script>