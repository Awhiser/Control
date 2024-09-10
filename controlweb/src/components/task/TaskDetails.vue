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
                <a-col :span="12"> <a-button type="primary" size="large" @click="openEditForm()"> Edit </a-button> <a-button
                        type="primary" size="large"> More </a-button> </a-col>

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
                <a-col :span="18"> <span> {{ taskView.createTime ? dayjs(taskView.createTime).format(dateFormat) : ""
                }}</span> </a-col>
            </a-row>

            <a-row>
                <a-col :span="6"> <span>更新时间:</span> </a-col>
                <a-col :span="18"> <span> {{ taskView.updateTime ? dayjs(taskView.updateTime).format(dateFormat) : ""
                }}</span> </a-col>
            </a-row>

            <a-row>
                <a-divider orientation="left">描述</a-divider>
                <div style="word-break: break-all; width: 90%;">
                    <span> {{ taskView.description }}</span>
                </div>
            </a-row>




        </a-flex>
        <a-divider orientation="left">附件</a-divider>
        <a-upload v-model:file-list="attachmentList" action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
            list-type="picture-card" @preview="handlePreview">
            <div v-if="attachmentList.length < 8">
                <plus-outlined />
                <div style="margin-top: 8px">Upload</div>
            </div>
        </a-upload>
        <a-modal :open="previewVisible" :title="previewTitle" :footer="null" @cancel="handleCancel">
            <img alt="example" style="width: 100%" :src="previewImage" />
        </a-modal>


        <a-divider orientation="left">链接 <a @click="openCreateTaskLink()">+</a></a-divider>

        <a-collapse :activeKey="activetaskLinkKey"  ghost>
            <a-collapse-panel :show-arrow="false"  v-for="link in taskLinkList" :header="link.name" :key="link.id" >
               <a-list size="small" bordered >
                    <template v-for="item in link.outTask">
                    <a-list-item>{{ item.link.outName }}  <a>{{ item.inTask.title }}</a></a-list-item>
                    </template>
                    <template v-for="item in link.inTask">
                    <a-list-item>{{ item.link.inName }}  <a>{{ item.outTask.title }}</a></a-list-item>
                    </template>
                </a-list> 
              
            </a-collapse-panel>
          
        </a-collapse>

       
        <a-tabs @tabClick="changeTab" v-model:activeKey="tabKey">
            <a-tab-pane key="Comment" tab="Comment">

                <a-list item-layout="horizontal" :data-source="commentList">
                    <template #renderItem="{ item }">
                        <a-list-item>
                            <a-list-item-meta>
                                <template #title>
                                    <div style="word-break: break-all; width: 90%;">
                                        {{ item.content }}
                                    </div>
                                  
                                </template>
                                <template #description>
                                  {{ item.user.displayName }}  {{ DateUtils.format(item.createTime) }}
                                </template>
                            </a-list-item-meta>
                            <!-- {{ item.content }}1111 -->
                            <!-- <a-comment :author="item.user.displayName">

                                <template #content>
                                    <div style="word-break: break-all; width: 90%;">
                                        {{ item.content }}
                                    </div>
                                </template>
                                <template #datetime> {{ DateUtils.format(item.createTime) }}

                                </template>
                            </a-comment> -->

                        </a-list-item>
                    </template>
                </a-list>


                <a-comment>
                    <template #content>
                        <a-form-item>
                            <a-textarea v-model:value="inputComment" :rows="4" />
                        </a-form-item>
                        <a-form-item>
                            <a-button html-type="submit" :loading="isSubmitComment" type="primary" @click="submitComment">
                                {{ i18n.global.t('button.comment') }}
                            </a-button>
                        </a-form-item>
                    </template>
                </a-comment>


            </a-tab-pane>
            <a-tab-pane key="TaskChangeLog" tab="TaskChange">

                <a-timeline>
                    <a-timeline-item v-for="log in changeLogs">


                        <a-list-item>
                            <a-list-item-meta>
                                <template #title>
                                    <a-row> <a-col :span="2">{{ log.operator }} </a-col> <a-col> {{
                                        dayjs(log.operateTime).format(dateFormat) }} </a-col> </a-row>
                                </template>

                                <template #description>
                                    <p v-for="item in log.data">
                                        {{ (i18n.global.t(item.name)) }} ： {{ item.fromValue }} ---> {{ item.toValue }}</p>
                                </template>

                            </a-list-item-meta>
                        </a-list-item>
                    </a-timeline-item>
                </a-timeline>


            </a-tab-pane>
        </a-tabs>



    </a-drawer>

    <edit-task v-model:open="openEdit" :editTask="taskView" @updated="() => { loadData() }"></edit-task>
   <create-task-link :taskId="taskView.id" :projectId="taskView.projectId"  v-model:open="openTaskLink" >  </create-task-link>
</template>



<script setup lang="ts">


import { PlusOutlined } from '@ant-design/icons-vue';
import { ref, watch } from 'vue'
import dayjs from 'dayjs';
import taskService from '@/api/taskservice';
import taskChangeLogService from '@/api/taskchangelogservice';
import commentService from '@/api/commentservice';
import { message, type UploadProps } from 'ant-design-vue';
import EditTask from './EditTask.vue';
import i18n from '@/i18n';
import ARow from 'ant-design-vue/es/grid/Row';
import DateUtils from '@/api/dateutils';
import CreateTaskLink from './CreateTaskLink.vue';
import taskLinkService from '@/api/tasklinkservice';


const tabKey = ref("Comment");


function getBase64(file: File) {

    const reader = new FileReader();
    reader.readAsDataURL(file);
    return reader.result;


}
const previewVisible = ref(false);
const previewImage = ref('');
const previewTitle = ref('');
const openTaskLink = ref(false);
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

const changeLogs = ref([]);

const inputComment = ref('');
const isSubmitComment = ref<boolean>(false);
const commentList = ref([]);


const taskLinkList = ref([]);
const activetaskLinkKey = ref([]);
watch(show, (value, oldvalue) => {
    if (value == false) {
        return
    }

    loadData();
    loadTaskLink();
    tabKey.value = 'Comment';
    loadComment();
  

})

function closeDraw() {
    taskView.value = {
        assignee: "",
        createTime: "",
        description: "",
        duedate: "",
        id: "",
        priority: "",
        status: "",
        projectId: "",
        tags: [],
        title: "",
        type: "",
        updateTime: ""
    }

    tabKey.value = "Comment";

    showDetails.value = false;
}

function openEditForm() {
    openEdit.value = true;
}

function changeTab(key) {
    if (key == "TaskChangeLog") {
        taskChangeLogService.getByTaskId(taskView.value.id).then(res => {
            convertChangeLog(res.data)

        });
    }

    if (key == "Comment") {
        loadComment();
    }

}

function loadData() {
    taskService.getTask(taskId.value).then(res => {
        taskView.value = res.data;
        showDetails.value = true;
    })

}

function convertChangeLog(logs) {

    if (logs.length == 0) {
        return;
    }

    let group = {
        data: [],
        operator: logs[0].operator.displayName,
        operateTime: logs[0].operateTime
    }
    changeLogs.value = [];
    group.data.push(logs[0]);
    changeLogs.value.push(group);
    for (let i = 1; i < logs.length; i++) {
        if (logs[i].operateTime == logs[i - 1].operateTime && logs[i].operator.id == logs[i - 1].operator.id) {
            group.data.push(logs[i]);
        } else {
            group = {
                data: [],
                operator: logs[i].operator.displayName,
                operateTime: logs[i].operateTime
            };
            group.data.push(logs[i]);
            changeLogs.value.push(group);
        }
    }
}

function submitComment() {


    isSubmitComment.value = true;
    commentService.create({
        taskId: taskView.value.id,
        content: inputComment.value
    }).then(res => {
        inputComment.value = '';
        loadComment();
        message.info(i18n.global.t('message.success'))
        isSubmitComment.value = false;

    })

}

function loadComment() {
    commentService.getByTaskId(taskId.value).then(res => {
        commentList.value = res.data;
    })
}

function loadTaskLink() {

    

    let group = [];
    taskLinkList.value = [];
    activetaskLinkKey.value = [];
    taskLinkService.getByTaskId(taskId.value).then(res=>{

        for(let i of res.data) {
         
            if( !group[i.link.id]) {
                group[i.link.id] = {
                            id:i.link.id,
                            name:i.link.name,
                            outTask: [],
                            inTask:[],
                         }

            }

            if(i.inTask.id === taskId.value) {
                group[i.link.id].inTask.push(i)
            }

            if(i.outTask.id === taskId.value) {
                group[i.link.id].outTask.push(i)
            }

        }

        for(var key in group){
            taskLinkList.value.push(group[key]);
            activetaskLinkKey.value.push(key)
        }



   
    //  console.log(group);
        
    })
}


function openCreateTaskLink() {
    openTaskLink.value = true;
}


</script>


<style scoped >
span {
    font-size: 18px;
}
</style>
