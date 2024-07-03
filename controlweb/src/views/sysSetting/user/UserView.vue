<template>
  <a-row style="margin-bottom:10px">
    <CreateUser></CreateUser>
  </a-row>

  <a-row style="margin-bottom:10px">
  
   
    <a-col col="10">
        <a-input-search v-model:value="searchName" :placeholder="i18n.global.t('input.placeholder')" enter-button @search="onSearch" />
    </a-col>

  </a-row>

  <a-table :scroll="{ y: 850 }" ref="taskTable" :pagination="false" :key="new Date().getTime()"
    :row-key="record => record.id" :columns="columns" :data-source="data">


    <template #bodyCell="{ column, record }">

      <template v-if="column.key === 'name'">
        <a @click="showTaskDetails(record.id)">
          {{ record.name }}
        </a>
      </template>

      <template v-if="column.key === 'operation'">

        <a-button @click="showEdit(record)"> {{ $t('button.edit') }} </a-button>


        <a-popconfirm :title="i18n.global.t('message.confirm')"  @confirm="deleteUser(record.id)"  >
          <a-button danger style="margin-left: 15px;"> {{ $t('button.delete') }} </a-button>
        </a-popconfirm>

      </template>


    </template>
  </a-table>


  <div style="float: right; margin-top: 10px;">

    <a-pagination 
    v-model:current="current" 
    v-model:page-size="pageSize" 
    show-size-changer :total="total" 
    @change="onChangePage"
      :show-total="total => i18n.global.t('page.total') + ` : ${total}`" />


  </div>




  <EditUser v-model:open="editShow" :user="editUser"></EditUser>
</template>


<script setup>

import { ref, onMounted, onBeforeMount } from 'vue';
import i18n from '@/i18n/index';
import CreateUser from '@/components/sys/user/CreateUser.vue'
import EditUser from '@/components/sys/user/EditUser.vue'
import userService from '@/api/userservice.js'
const columns = [
  {
    title: i18n.global.t('user.name'),
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: i18n.global.t('user.displayName'),
    key: 'displayName',
    dataIndex: 'displayName',
  },

  {
    title: i18n.global.t('user.mail'),
    key: 'mail',
    dataIndex: 'mail',
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
const editUser = ref({})

const pageSize = ref(20)
const current = ref(1)
const total = ref(100)
const searchName = ref(null)

onBeforeMount(() => {
  loadData();

})

function loadData() {
  // userService.getList({ pageSize: pageSize.value, pageIndex: current.value - 1, name: searchName.value }).then(res => {
  //   total.value = res.data.totalElement;
  //   data.value = res.data.dataList;
  // });
}

function onSearch() {
  loadData();
}

function onChangePage(){
  loadData();
}

function showEdit(user) {
  editUser.value = user;
  editShow.value = true;
}

function deleteUser(id){
  userService.delete(id).then(res => {
      location.reload()
    });;
}


</script>


<style></style>