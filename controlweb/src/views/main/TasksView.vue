<template  >
 


    <a-table  @change="changeTable" ref="taskTable" :scroll="{ y: 800 }" :pagination="false" :key="new Date().getTime()" :row-key="record => record.id"  :columns="columns" :data-source="data">

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
    <a :href="record.id" >
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


 <!-- <a-pagination
      v-model:current="current"
      v-model:page-size="pageSize"
      :total="85"
      :show-total="total => `Total ${total} items`"
    />  -->
  
  
</template>
<script lang="ts" setup>
import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
import { ref } from 'vue'
import taskService from '@/api/taskservice';
import { onMounted,computed } from 'vue'
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

// const pagination = {
//       pageSize: ref(10),
//       current: ref(1),
//      showTotal: (total)=> `总共${total}页`
// };


let pagination = ref({
    current: 1,
    pageSize:50,
    total: 100,
    showTotal: () => `共 ${100} 条`
})

// const pagination = computed(() => ({
//   total: 200,
//   current: current.value,
//   pageSize: pageSize.value,
// }));

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
        for(let i = 0 ; i < 50 ;i++ ){
            data.value.push(...res.data.dataList) ;
        }
         
       
      })

     // taskTable.$forceUpdate()
  })

  function changeTable( pag,c,a){
    console.log(pag)
    pagination.value.current = pag.current;
    pagination.value.pageSize = pag.pageSize;

  }

</script>