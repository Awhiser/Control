<template>
    <a-drawer v-model:open="show" :width="'50%'" placement="right" @close="closeDraw">
        <a-flex gap="middle" vertical>
            <!-- <a-row>
                <a-col :span="24">
                    <span> {{ taskView.projectId }}</span>
                </a-col>
            </a-row> -->

            <a-row>
                <a-col :span="24">
                    <span style="font-size: 28px;"> {{ taskView.title }}</span>
                </a-col>
            </a-row>

            <a-row>
                <a-col :span="12"> <a-button type="primary" size="large" @click="openEditForm()" > Edit </a-button> <a-button type="primary" size="large"> More </a-button> </a-col>
           
                <a-col :span="12"> <a-button type="primary" size="large"> Transition </a-button> </a-col>
            </a-row>
            
            <template>
                <div>
                    
                </div>
            </template>

            <a-row>
                <a-col :span="6"> <span>经办人:</span> </a-col>
                <a-col :span="6"> <span> {{ taskView.assignee.displayName }}</span> </a-col>

                <a-col :span="6"> <span>状态:</span> </a-col>
                <a-col :span="6"> <span> {{ taskView.status }}</span> </a-col>
            </a-row>

            <a-row>
                <a-col :span="6"> <span>类型:</span> </a-col>
                <a-col :span="6"> <span> {{ taskView.type }}</span> </a-col>

                <a-col :span="6"> <span>优先级:</span> </a-col>
                <a-col :span="6"> <span> {{ taskView.priority }}</span> </a-col>
            </a-row>


            <a-row>
                <a-col :span="6"> <span>标签:</span> </a-col>
                <a-col :span="18">
                    <a-tag v-for="tag in taskView.tags" :key="tag">
                        {{ tag }}
                    </a-tag>
                </a-col>
            </a-row>


            <a-row>
                <a-col :span="6"> <span>到期日:</span> </a-col>
                <a-col :span="18"> <span> {{ dayjs(taskView.duedate).format('YYYY-MM-DD') }}</span> </a-col>
            </a-row>

            <a-row>
                <a-col :span="6"> <span>创建时间:</span> </a-col>
                <a-col :span="18"> <span> {{ taskView.createTime? dayjs(taskView.createTime).format(dateFormat) :"" }}</span> </a-col>
            </a-row>

            <a-row>
                <a-col :span="6"> <span>更新时间:</span> </a-col>
                <a-col :span="18"> <span> {{ taskView.updateTime ? dayjs(taskView.updateTime).format(dateFormat) : ""  }}</span> </a-col>
            </a-row>

            <a-row>
                <a-divider orientation="left">描述</a-divider>
                <a-col :span="24"> <span> {{taskView.description}} </span> </a-col>

            </a-row>

            <a-row>
                <a-divider orientation="left">附件</a-divider>
                <a-col :span="24"> <a-upload v-model:file-list="attachmentList"
                        action="https://www.mocky.io/v2/5cc8019d300000980a055e76" list-type="picture-card"
                        @preview="handlePreview">
                        <div v-if="attachmentList.length < 8">
                            <plus-outlined />
                            <div style="margin-top: 8px">Upload</div>
                        </div>
                    </a-upload>
                    <a-modal :open="previewVisible" :title="previewTitle" :footer="null" @cancel="handleCancel">
                        <img alt="example" style="width: 100%" :src="previewImage" />
                    </a-modal> </a-col>
            </a-row>

            <a-row>
                <a-divider orientation="left">链接</a-divider>
                <a-col :span="24"> 
                    <a-collapse v-model:activeKey="activeKey" ghost>
                        <a-collapse-panel key="1" header="CloneA">
                            <a-list size="small" bordered :data-source="changeLogs">
                                <template #renderItem="{ item }">
                                <a-list-item>{{ item }}</a-list-item>
                                </template>
                                <template #header>
                                <div>Header</div>
                                </template>
                                <template #footer>
                                <div>Footer</div>
                                </template>
                        </a-list>
                        </a-collapse-panel>
                        <a-collapse-panel key="2" header="ClonB">
                        <p>Link1</p>
                        </a-collapse-panel>
 
                   </a-collapse>
                
                
                </a-col>
            </a-row>



            <a-row>
                <a-col :span="24">
                    <a-tabs @tabClick="changeTab"  >
                        <a-tab-pane key="Comment" tab="Comment">
                           
                            <a-list item-layout="horizontal" :data-source="changeLogs">
                                <template #renderItem="{ item }">
                                    <a-list-item>
                                        <a-list-item-meta description="Task Change Log">
                                            <template #title>
                                                <a> {{ item.title }}</a>
                                            </template>
                                        </a-list-item-meta>
                                    </a-list-item>
                                </template>
                            </a-list>


                        </a-tab-pane>
                        <a-tab-pane key="TaskChangeLog" tab="TaskChange" >
                        
                            <a-timeline>
                                <a-timeline-item> <a-list item-layout="horizontal" :data-source="changeLogs">
                                        <template #renderItem="{ item }">
                                            <a-list-item>
                                                <a-list-item-meta>
                                                    <template #title>
                                                        <a> {{ item.title }} </a> 2015-09-01
                                                    </template>

                                                    <template #description>
                                                        {{ item.title }}XXX
                                                    </template>

                                                </a-list-item-meta>
                                            </a-list-item>
                                        </template>
                                    </a-list></a-timeline-item>
                                <a-timeline-item>Solve initial network problems 2015-09-01</a-timeline-item>
                                <a-timeline-item>Technical testing 2015-09-01</a-timeline-item>
                                <a-timeline-item>Network problems being solved 2015-09-01</a-timeline-item>
                            </a-timeline>


                        </a-tab-pane>
                    </a-tabs>

                </a-col>
            </a-row>
        </a-flex>

    </a-drawer>

    <edit-task v-model:open="openEdit" :editTask="taskView" @updated="()=>{loadData()}" ></edit-task>

</template>



<script setup lang="ts">

 
import { PlusOutlined } from '@ant-design/icons-vue';
import { ref, watch } from 'vue'
import dayjs from 'dayjs';
import taskService from '@/api/taskservice';
import taskChangeLogService from '@/api/taskchangelogservice';
import type { UploadProps } from 'ant-design-vue';
import EditTask from './EditTask.vue';

const changeLogs = [
    {
        title: 'Azea ',
    },
    {
        title: 'Ant Design Title 2',
    },
    {
        title: 'Ant Design Title 3',
    },
    {
        title: 'Ant Design Title 4',
    },
];
const activeKey = ref(['1','2']);
//attachment data

function getBase64(file: File) {

    const reader = new FileReader();
    reader.readAsDataURL(file);
    return reader.result;


}
const previewVisible = ref(false);
const previewImage = ref('');
const previewTitle = ref('');
const attachmentList = ref<UploadProps['fileList']>([
    {
        uid: '-1',
        name: 'image.png',
        status: 'done',
        url: 'https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BB1p1XFm.img?w=768&h=1123&m=6&x=162&y=193&s=164&d=164',
    },
    {
        uid: '-2',
        name: 'image.png',
        status: 'done',
        url: 'https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BB1p01pN.img?w=768&h=820&m=6&x=381&y=257&s=174&d=174',
    },
    {
        uid: '-xxx',
        percent: 50,
        name: 'image.png',
        status: 'uploading',
        url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
    },
    {
        uid: '-5',
        name: 'image.png',
        status: 'error',
    },
]);
const handleCancel = () => {
    previewVisible.value = false;
    previewTitle.value = '';
};
const handlePreview = (file: UploadProps['fileList'][number]) => {
    if (!file.url && !file.preview) {
        file.preview = (getBase64(file.originFileObj)) as string;
    }
    previewImage.value = file.url || file.preview;
    previewVisible.value = true;
    previewTitle.value = file.name || file.url.substring(file.url.lastIndexOf('/') + 1);
};

//attachment data

const dateFormat = 'YYYY-MM-DD HH:mm:ss';


const show = defineModel('show')
const taskId = defineModel("taskId")
const showDetails = ref(false)

const openEdit = ref(false)
let taskView = ref({
    assignee: "YFM",
    createTime: "2024-06-27T06:40:35.543+00:00",
    description: "",
    duedate: "2024-06-12T00:00:00.000+00:00",
    id: "1Sc907586e-e853-4a76-b3db-2e37fe366828",
    priority: "P1",
    status: "Todo",
    projectId: "S",
    tags: ['111', '222'],
    title: "xxxxxxx笑笑笑SSSXXXX",
    type: "Task",
    updateTime: "2024-06-27T06:40:35.543+00:00"
})

let changLogs = [];

watch(show, (value, oldvalue) => {
    if (value == false) {
        return
    }
   
    loadData();

})

function closeDraw() {
    showDetails.value = false;
}

function openEditForm() {
    openEdit.value = true;
}

function changeTab(key){
    if(key=="TaskChangeLog"){
        taskChangeLogService.getByTaskId(taskView.value.id).then(res=>{
           changLogs = res.data;
           console.log(changLogs)
        });
    }
}

function loadData(){
    taskService.getTask(taskId.value).then(res=>{
        taskView.value = res.data;
        showDetails.value = true;
    })

}


</script>


<style scoped >
span {
    font-size: 18px;
}
</style>
