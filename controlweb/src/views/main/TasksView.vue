<template  >
 


    <a-table   ref="taskTable" :scroll="{ y: 850 }" :pagination="false" :key="new Date().getTime()" :row-key="record => record.id"  :columns="columns" :data-source="data">

<template #headerCell="{ column }">
  <template v-if="column.key === 'title'">
    <span >
      <smile-outlined />
      title
    </span>
  </template>
</template>

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
      :show-total="total => `Total ${total} items`"
    /> 




  <TaskDetails v-model:show="openTaskDetails"  v-model:taskId="taskId" > </TaskDetails>
  
  
</template>
<script lang="ts" setup>
import { SmileOutlined } from '@ant-design/icons-vue';
import { ref } from 'vue'
import taskService from '@/api/taskservice';
import { onMounted } from 'vue' 
import TaskDetails from '@/components/TaskDetails.vue'


const props = defineProps({
    projectId: String
  })

const openTaskDetails = ref(false);
const taskId =ref('')
const columns = [
  {
    title: 'title',
    dataIndex: 'title',
    key: 'title',
  },
  {
    title: 'type',
    dataIndex: 'type',
    key: 'type',
  },

  {
    title: 'priority',
    key: 'priority',
    dataIndex: 'priority',
  },
  {
    title: 'assignee',
    key: 'assignee',
    dataIndex: 'assignee',
  },
  {
    title: 'tags',
    key: 'tags',
    dataIndex: 'tags',
  },
 
  {
    title: 'status',
    key: 'status',
    dataIndex: 'status',
  }
//   ,
//   {
//     title: 'duedate',
//     key: 'duedate',
//     dataIndex: 'duedate',
//   },
//   {
//     title: 'description',
//     key: 'description',
//     dataIndex: 'description',
//   },
//   {
//     title: 'updateTime',
//     key: 'updateTime',
//     dataIndex: 'updateTime',
//   },
//   {
//     title: 'createTime',
//     key: 'createTime',
//     dataIndex: 'createTime',
//   },

];

const pageSize = ref(50)
const  current = ref(1)
const total = ref(100)





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
      taskService.getTaskList({projectId:'S'}).then(res => {
        console.log(res.data.dataList[0])
        for(let i = 0 ; i < 50 ;i++ ){
            data.value.push(...res.data.dataList) ;
        }
         
        total.value = data.value.length;
      })
   

     // taskTable.$forceUpdate()
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