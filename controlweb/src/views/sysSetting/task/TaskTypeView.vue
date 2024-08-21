<template>
  <a-row style="margin-bottom:10px"> <a-button type="primary" @click="openCreate()"> {{ $t('button.create') }} </a-button>
  </a-row>

  <a-table :scroll="{ y: 850 }" ref="taskTable" :pagination="false" :key="new Date().getTime()"
    :row-key="record => record.id" :columns="columns" :data-source="data">


    <template #bodyCell="{ column, record }">

      <template v-if="column.key === 'name'">
        {{ record.name }}
      </template>

      <template v-if="column.key === 'operation'">

        <a-popconfirm :title="i18n.global.t('message.confirm')" @confirm="deleteType(record.id)">
          <a-button danger style="margin-left: 15px;"> {{ $t('button.delete') }} </a-button>
        </a-popconfirm>

      </template>


    </template>
  </a-table>

  <a-modal v-model:open="openCreateForm" :ok-text="i18n.global.t('button.create')" @ok="create"
    :title="i18n.global.t('button.create')">
    <a-divider />
    <div style="height:100px;overflow-y:auto">
      <a-form ref="createForm" :rules="formRules" layout="horizontal" :model="taskType" style="max-width: 800px">
        <a-form-item :label="i18n.global.t('taskType.name')" name="name">
          <a-input v-model:value="taskType.name" />
        </a-form-item>
      </a-form>
    </div>
  </a-modal>
</template>
   


  
<script lang="ts" setup>

import { ref, onBeforeMount } from 'vue';
import type { Rule } from 'ant-design-vue/es/form';
import i18n from '@/i18n/index';
import taskTypeService from '@/api/tasktypeservice.js';
import { message } from 'ant-design-vue';
const columns = [
  {
    title: i18n.global.t('taskType.name'),
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: i18n.global.t('table.operation'),
    key: 'operation',
    dataIndex: 'operation',
  }

];
const createForm = ref();
const formRules: Record<string, Rule[]> = {
  name: [{ required: true, message: '', trigger: 'change' }]

};

const data = ref([]);

const taskType = ref({
  name: ''
});



const openCreateForm = ref(false)


onBeforeMount(() => {
  loadData();

})

function loadData() {
  taskTypeService.getList().then(res => {
    data.value = res.data;
  });
}

function deleteType(id) {
  taskTypeService.delete(id).then(res => {
    data.value = data.value.filter(i => i.id != id);
  });
}


function create(e) {
  createForm.value
    .validate().then(() => {
      taskTypeService.create(taskType.value).then(res => {
        message.success(i18n.global.t('message.success'), 1, () => {
          openCreateForm.value = false;
          loadData();
        })
      });
    })
};

function openCreate() {
  taskType.value.name = "";
  openCreateForm.value = true;
}




</script>
  
  
<style></style>