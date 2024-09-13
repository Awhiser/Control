<template  >

   
  <a-row :gutter="16" id="taksFilter">
    <a-col> <a-textarea size="middle" v-model:value="searchParams.title"  :placeholder="i18n.global.t('task.inputTitleOrDesc')"  auto-size /> </a-col>  
    <a-col> {{ $t('task.type') }} : <a-select mode="multiple" style="width: 200px" :options="value"  ></a-select></a-col>  
    <a-col> {{ $t('task.priority') }} : <a-select mode="multiple" style="width: 200px" :options="value"  ></a-select></a-col>  
    <a-col> {{ $t('task.assignee') }} : <a-select mode="multiple" style="width: 200px" :options="value"  ></a-select></a-col>  
    <a-col> {{ $t('task.tags') }} : <a-select mode="tags" style="width: 200px"  ></a-select></a-col>  
    <a-col> {{ $t('task.status') }} : <a-select mode="multiple" style="width: 200px" :options="value"  ></a-select></a-col>  
    <a-col> <a-button type="primary" > {{$t('button.search')}} </a-button></a-col>  
  </a-row>

  <a-table ref="taskTable" :scroll="{ y: 700 }" :pagination="false" :key="new Date().getTime()"
    :row-key="record => record.id" :columns="columns" :data-source="data">
    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'title'">
        <a @click="showTaskDetails(record.id)">
          {{ record.title }}
        </a>
      </template>
      <template v-if="column.key === 'assignee'">
        {{ record.assignee.displayName }}
      </template>

      <template v-else-if="column.key === 'tags'">
        <span>
          <a-tag v-for="tag in record.tags" :key="tag">
            {{ tag }}
          </a-tag>
        </span>
      </template>

    </template>
  </a-table>


  <!-- <a-pagination
      v-model:current="current"
      v-model:page-size="pageSize"
      show-size-changer
      :total="total"
      :show-total="total => i18n.global.t('page.total') + ` : ${total}`"
    />  -->


  <a-pagination v-model:current="current" v-model:page-size="pageSize" show-size-changer :total="total"
    @change="onChangePage" :show-total="total => i18n.global.t('page.total') + ` : ${total}`" />

  <a-drawer v-model:open="openTaskDetails" :width="'50%'" placement="right" >
    <TaskDetails :taskId="taskId" ></TaskDetails>
  </a-drawer>

</template>
<script lang="ts" setup>
import { ref } from 'vue'
import taskService from '@/api/taskservice';
import { onMounted, watch } from 'vue'
import TaskDetails from '@/components/task/TaskDetails.vue'
import { useRoute } from 'vue-router';
import i18n from '@/i18n';
const route = useRoute();

const props = defineProps({
  projectId: String
})

const openTaskDetails = ref(false);
const taskId = ref(null)


const pageSize = ref(50)
const current = ref(1)
const total = ref(0)

const value = ref([{value:"Task"},{value:"Bug"},{value:"Story"},{value:"BreakDown"}]);

//搜索参数
const searchParams = ref({
   title:null,
   type:null,
   assignee:null,
   tags:null,
   priority:null,
   status:null
})


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
  loadData();
})

watch(() => route.params.projectId, (newId, oldId) => {
  loadData();
})

function loadData() {
  taskService.getTaskList({ pageSize: pageSize.value, pageIndex: current.value - 1, projectId: props.projectId }).then(res => {
    data.value = res.data.dataList;

    total.value = res.data.totalElement;
  })
}

function changeTable(pag, c, a) {
  console.log(pag)
  // pagination.value.current = pag.current;
  // pagination.value.pageSize = pag.pageSize;

}

function showTaskDetails(id) {
  //console.log(id)
  openTaskDetails.value = true;
  taskId.value = id;
}

function onChangePage() {
  loadData();
}


function search(){

  taskService.search().then(res=>{
    
  })

}

</script>