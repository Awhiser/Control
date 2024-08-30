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
          <a-button @click="showEditForm(record)" > {{ $t('button.edit') }} </a-button>
          <a-popconfirm :title="i18n.global.t('message.confirm')" @confirm="deleteType(record.id)">
            <a-button danger style="margin-left: 15px;"> {{ $t('button.delete') }} </a-button>
          </a-popconfirm>
        </template>
      </template>
    </a-table>
  
    <a-modal v-model:open="openCreateForm" :ok-text="i18n.global.t('button.create')" @ok="create"
      :title="i18n.global.t('button.create')">
      <a-divider />
      <div style="height:200px;overflow-y:auto">
        <a-form ref="createForm" :rules="formRules" layout="horizontal" :model="taskLinkType" style="max-width: 800px">
          <a-form-item :label="i18n.global.t('taskLinkType.name')" name="name">
            <a-input v-model:value="taskLinkType.name" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('taskLinkType.out')" name="outName">
            <a-input v-model:value="taskLinkType.outName" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('taskLinkType.in')" name="inName">
            <a-input v-model:value="taskLinkType.inName" />
          </a-form-item>

        </a-form>
      </div>
    </a-modal>


    <a-modal v-model:open="openEditForm" :ok-text="i18n.global.t('button.edit')" @ok="edit()"
      :title="i18n.global.t('button.edit')">
      <a-divider />
      <div style="height:200px;overflow-y:auto">
        <a-form ref="editForm" :rules="formRules" layout="horizontal" :model="editData" style="max-width: 800px">
          <a-form-item :label="i18n.global.t('taskLinkType.name')" name="name">
            <a-input v-model:value="editData.name" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('taskLinkType.out')" name="outName">
            <a-input v-model:value="editData.outName" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('taskLinkType.in')" name="inName">
            <a-input v-model:value="editData.inName" />
          </a-form-item>

        </a-form>
      </div>
    </a-modal>


  </template>
     
  
  
    
  <script lang="ts" setup>
  
  import { ref, onBeforeMount } from 'vue';
  import type { Rule } from 'ant-design-vue/es/form';
  import i18n from '@/i18n/index';
  import taskLinkTypeService from '@/api/tasklinktypeservice';
  import { message } from 'ant-design-vue';
  const columns = [
    {
      title: i18n.global.t('taskLinkType.name'),
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: i18n.global.t('taskLinkType.out'),
      dataIndex: 'outName',
      key: 'outName',
    },
    {
      title: i18n.global.t('taskLinkType.in'),
      dataIndex: 'inName',
      key: 'inName',
    },
    {
      title: i18n.global.t('table.operation'),
      key: 'operation',
      dataIndex: 'operation',
    }
  
  ];
  const createForm = ref();
  const editForm = ref();
  const formRules: Record<string, Rule[]> = {
    name: [{ required: true, message: '', trigger: 'change' }],
    outName: [{ required: true, message: '', trigger: 'change' }],
    inName: [{ required: true, message: '', trigger: 'change' }]
  
  };

  const data = ref([]);
  
  const taskLinkType = ref({
    name: '',
    inName:"",
    outName:""
  });
  
  const openCreateForm = ref(false)
  
  
  onBeforeMount(() => {
    loadData();
  })
  
  function loadData() {
    taskLinkTypeService.getAll().then(res => {
      data.value = res.data;
    });
  }
  
  function deleteType(id) {
    taskLinkTypeService.delete(id).then(res => {
      data.value = data.value.filter(i => i.id != id);
    });
  }
  
  
  function create(e) {
    createForm.value
      .validate().then(() => {
        taskLinkTypeService.create({name:taskLinkType.value.name,
          inName:taskLinkType.value.inName,
          outName:taskLinkType.value.outName
        }).then(res => {
          message.success(i18n.global.t('message.success'), 1, () => {
            openCreateForm.value = false;
            loadData();
          })
        });
      })
  };
  
  function openCreate() {
    taskLinkType.value = {};
    openCreateForm.value = true;
  }
  
  const editData = ref({ name: '',
    inName:"",
    outName:""});
  const openEditForm = ref(false);
  function showEditForm(record){
    editData.value = {
      id: record.id,
      name: record.name,
      outName: record.outName,
      inName :  record.inName
    }
    openEditForm.value = true;
  }

  function edit(){
    editForm.value
      .validate().then(() => {
        taskLinkTypeService.update(editData.value).then(res => {
            message.success(i18n.global.t('message.success'), 1, () => {
              openEditForm.value = false;
              loadData();
            })
        });
      })

  }
  
  
  </script>
    
    
  <style></style>