<template>
    <a-modal v-model:open="open" :footer="null" width="1100px" :title="i18n.global.t('button.member')">
      
        <a-divider />

 
  
    <a-flex  justify="space-between">
    
        <a-input-search  style="width: 200px;" :placeholder="i18n.global.t('input.searchName')" enter-button />
    
        <a-button type="primary"  @click="showAddModal"  > {{$t('button.add')}} </a-button>
    
    </a-flex >
  
      
        <a-divider />
        <div style="height:800px; overflow-y:auto">
            <a-table :columns="columns" :data-source="data" :pagination="false">
                <template #bodyCell="{ column, record }">
                    <template v-if="column.key === 'name'">
                        <a>
                            {{ record.user.displayName }} ({{ record.user.name }})
                        </a>
                    </template>
                    <template v-else-if="column.key === 'projectRole'">
                        <span>
                            <a-tag v-for="tag in record.projectRole" :key="tag">
                                <span style="font-size: 16px;"> {{ tag }} </span>
                            </a-tag>
                        </span>
                    </template>
                    <template v-else-if="column.key === 'action'">
                        <span>
                            <a>{{ $t('button.edit') }}</a>
                            <a-divider type="vertical" />
                            <a  @click="deleteMember(record.id)"  >{{ $t('button.delete') }}</a>

                        </span>
                    </template>
                </template>
            </a-table>

        </div>







    </a-modal>



    <a-modal :open="openAdd" :closable="false" :title="i18n.global.t('button.add')" :ok-text="i18n.global.t('button.add')" @cancel="closeAdd" @ok="addMember">
        <a-divider />
        
        用户：    <UserSelect v-model:userId="addUserId" ></UserSelect>
</a-modal>
  

</template>
<script lang="ts"  setup>

import { ref, watch } from 'vue';
import { message } from 'ant-design-vue';
import i18n from '@/i18n/index'
import UserSelect from '../../UserSelect.vue';
import projectService from '@/api/projectservice'


const projectId = defineModel('projectId', { default: {} });
const open = defineModel('open', { default: false });
const openAdd = ref(false);

const addUserId = ref();

const columns = [
    {
        title: i18n.global.t('projectMember.name'),
        name: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: i18n.global.t('projectMember.role'),
        key: 'projectRole',
        dataIndex: 'projectRole',
        width: 600
    },
    {
        title:  i18n.global.t('table.operation'),
        key: 'action',
    },
];

let data = ref([
    // {
    //     key: '1',
    //     name: 'John Brown',
    //     age: 32,
    //     address: 'New York No. 1 Lake Park',
    //     projectRole: ['开发', 'developer'],
    // },
    // {
    //     key: '2',
    //     name: 'Jim Green',
    //     age: 42,
    //     address: 'London No. 1 Lake Park',
    //     projectRole: ['loser'],
    // },
    // {
    //     key: '3',
    //     name: 'Joe Black',
    //     age: 32,
    //     address: 'Sidney No. 1 Lake Park',
    //     projectRole: ['cool', 'teacher', 'cool', 'teacher', 'cool', 'teacher', 'cool', 'teacher', 'cool', 'teacher', 'cool', 'teacher', 'cool', 'teacher', 'cool', 'teacher'],
    // },
]);


watch(open,(value) => {
    if(value == true){
        loadData()
    }



})

function loadData(){
    projectService.getProjectMemberList({projectId:projectId.value}).then(res=>{
        data.value = res.data.dataList;
    })
}



function showAddModal(){
    openAdd.value = true;
}

function addMember(){
    projectService.saveProjectMember({projectId:projectId.value,userId:addUserId.value}).then(res=>{
        console.log(res)
        openAdd.value = false;
        loadData();
    })
}

function deleteMember(id){
    projectService.deleteProjectMember(id).then(res=>{
      data.value =  data.value.filter(i=>i.id != id);
    })
}

function closeAdd(){
    console.log(addUserId.value)
    openAdd.value = false;
}

</script>
  