<template>
    <a-row style="margin-bottom:10px">
      <CreateUserConnector></CreateUserConnector>
    </a-row>
 
  
    <a-table :scroll="{ y: 850 }" ref="taskTable" :pagination="false" :key="new Date().getTime()"
      :row-key="record => record.id" :columns="columns" :data-source="data">
  
  
      <template #bodyCell="{ column, record }">
  
        <template v-if="column.key === 'name'">
            {{ record.name }}
        </template>
  
        <template v-if="column.key === 'operation'">
  
          <a-button @click="showEdit(record)"> {{ $t('button.edit') }} </a-button>
  
          <a-button @click="sync(record.id)"  style="margin-left: 15px;" > {{ $t('button.sync') }} </a-button>

          <a-popconfirm :title="i18n.global.t('message.confirm')"  @confirm="deleteUserConnector(record.id)" >
            <a-button danger style="margin-left: 15px;"> {{ $t('button.delete') }} </a-button>
          </a-popconfirm>
  
        </template>
  
  
      </template>
    </a-table>
  
  

  
  
  
    <EditUserConnector v-model:open="editShow" :userConnectorModel = "editModel"></EditUserConnector>
  </template>
  
  
  <script setup>
  
  import { ref, onMounted, onBeforeMount } from 'vue';
  import i18n from '@/i18n/index';
  import CreateUserConnector from '@/components/sys/user/CreateUserConnector.vue'
  import EditUserConnector from '@/components/sys/user/EditUserConnector.vue'
  import userService from '@/api/userservice.js'
  import { message } from 'ant-design-vue';
  const columns = [
    {
      title: i18n.global.t('userConnector.name'),
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: i18n.global.t('userConnector.url'),
      key: 'url',
      dataIndex: 'url',
    },
 
    {
      title: i18n.global.t('table.operation'),
      key: 'operation',
      dataIndex: 'operation',
    }
  
  ];
  
  
  const data = ref([
  
  
  ]);
  
  
  const editShow = ref(false);
  const editModel = ref({})
  


  
  onBeforeMount(() => {
    loadData();
  
  })
  
  function loadData() {
    userService.getUserConnectors().then(res => {
      data.value = res.data;
    });
  }

  function deleteUserConnector(id) {
    userService.deleteUserConnector(id).then(res => {
     location.reload()
    });
  }

  
  function showEdit(value) {
    editModel.value = value;
    editShow.value = true;
  }


  function sync(id){
    userService.syncUserConnector(id).then(res =>{
      message.success(i18n.global.t('message.success'))
    })
  }
  
  
  </script>
  
  
  <style></style>