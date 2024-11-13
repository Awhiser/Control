<template  >

<a-row> <a-button type="primary" @click="openCreateForm" > {{ $t('button.create') }} </a-button> </a-row>



<a-row style="margin-top:10px">

<a-col col="10">
  <a-input-search v-model:value="searchParams.name" :placeholder="i18n.global.t('input.searchName')" enter-button @search="onSearch" />
</a-col>

</a-row>



  <a-table ref="versionTable" :scroll="{ y: 700 }" :pagination="false" :key="new Date().getTime()"
    :row-key="record => record.id" :columns="columns" :data-source="data">
    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'name'">
       {{ record.versionDto.name }}
      </template>

      <template v-if="column.key === 'process'">
        {{   record.versionStatistic.completeTask }} / {{   record.versionStatistic.totalTask }}
      </template>

      <template v-if="column.key === 'status'">
       {{ record.versionDto.status }}
      </template>

      <template v-if="column.key === 'description'">
       {{ record.versionDto.description }}
      </template>

      <template v-if="column.key === 'operation'">
        <a-button type="primary" style="margin-left: 15px;"> {{ $t('button.edit') }} </a-button>
        <a-popconfirm :title="i18n.global.t('message.confirm')" @confirm="deleteVersion(record.id)">
          <a-button danger style="margin-left: 15px;"> {{ $t('button.delete') }} </a-button>
        </a-popconfirm>
      </template>

    </template>
  </a-table>

  <a-pagination v-model:current="current" v-model:page-size="pageSize" show-size-changer :total="total" @change="onChangePage" :show-total="total => i18n.global.t('page.total') + ` : ${total}`" />


  <a-modal v-model:open="createOpen"  @ok="create()"  :ok-text="i18n.global.t('button.create')" :title="i18n.global.t('button.create')" >
      <a-divider />
      <div style="height:400px;overflow-y:auto">
  
        <a-form  ref="createForm" :rules="createRules" :model="createVersion"  :label-col="{style: { width: '100px' }}" :wrapper-col="{span: 18}" layout="horizontal" style="max-width: 800px">
  
          <a-form-item :label="i18n.global.t('version.name')" name="name" >
            <a-input v-model:value="createVersion.name" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('version.status')" name="status" >
            <a-input v-model:value="createVersion.statusId" />
          </a-form-item>
        
          <a-form-item :label="i18n.global.t('version.description')" name="description" >
            <a-input v-model:value="createVersion.description" />
          </a-form-item>
          
        </a-form>
      </div>
  </a-modal>



  <a-modal v-model:open="editOpen"  @ok="create()"  :ok-text="i18n.global.t('button.create')" :title="i18n.global.t('button.create')" >
      <a-divider />
      <div style="height:400px;overflow-y:auto">
  
        <a-form  ref="createForm" :rules="createRules" :model="createVersion"  :label-col="{style: { width: '100px' }}" :wrapper-col="{span: 18}" layout="horizontal" style="max-width: 800px">
  
          <a-form-item :label="i18n.global.t('version.name')" name="name" >
            <a-input v-model:value="createVersion.name" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('version.status')" name="status" >
            <a-input v-model:value="createVersion.statusId" />
          </a-form-item>
        
          <a-form-item :label="i18n.global.t('version.description')" name="description" >
            <a-input v-model:value="createVersion.description" />
          </a-form-item>
          
        </a-form>
      </div>
  </a-modal>

</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { onMounted, watch } from 'vue'
import { useRoute } from 'vue-router';
import i18n from '@/i18n';
import versionService from '@/api/versionservice';
import type { Rule } from 'ant-design-vue/es/form';
import { message } from 'ant-design-vue';

const createForm = ref();
const createOpen = ref(false);
const createRules: Record<string, Rule[]> = {
    name: [{ required: true, message: '', trigger: 'change' }],
    status: [{ required: true, message: '', trigger: 'change' }],
    description: [{ required: true, message: '', trigger: 'change' }]
  };

const createVersion = ref({
  name:"",
  statusId:"",
  description:""
})

const editForm = ref();
const editOpen = ref(false);
const editVersion = ref({
  name:"",
  status:"",
  description:""
})


const columns = [
  {
    title: i18n.global.t('version.name'),
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: i18n.global.t('version.process'),
    dataIndex: 'process',
    key: 'process',
  },
  {
    title: i18n.global.t('version.status'),
    key: 'status',
    dataIndex: 'status',
  },
  {
    title: i18n.global.t('version.description'),
    key: 'description',
    dataIndex: 'description',
  },
  {
    title: i18n.global.t('table.operation'),
    key: 'operation',
    dataIndex: 'operation',
  }
];


const props = defineProps({
  projectId: String
})

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



const pageSize = ref(50)
const current = ref(1)
const total = ref(0)



//搜索参数
const searchParams = ref({
  name: null,
  status: []
})

onMounted(() => {
  loadData();
})

function loadData() {
  searchParams.value.pageSize = pageSize.value
  searchParams.value.pageIndex = current.value - 1
  searchParams.value.projectId = props.projectId;
  versionService.getDetailPage(searchParams.value).then(res => {
    data.value = res.data.dataList;
    total.value = res.data.totalElement;
  })
}

function onSearch(){
  current.value = 1;
  loadData();
}



function onChangePage() {
  loadData();
}

function deleteVersion(id){

}

function openCreateForm() {
  createVersion.value = {
  name:"",
  status:"",
  description:""
 }
  createOpen.value = true;

}

function create(){

  
   createForm.value
      .validate().then(()=>{
        createVersion.value.projectId = props.projectId;
        versionService.create(createVersion.value).then(res=>{

          message.success(i18n.global.t('message.success'),1,()=>{location.reload()})

        })

      })
  

  
}


</script>
