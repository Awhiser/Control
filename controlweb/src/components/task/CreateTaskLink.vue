<template>
    <a-modal v-model:open="open" @ok="handleOk" :ok-text="$t('button.create')" :title="$t('button.create')">
        <a-divider />

        <a-form ref="createTaskForm" :rules="taskCreateRules" :model="taskLink" :label-col="labelCol"
            :wrapper-col="wrapperCol" layout="horizontal" style="max-width: 800px">

            <a-form-item :label="$t('taskLink.outTask')" name="outTaskId">
                <TaskSelect v-model:task-id="taskLink.outTaskId" :project-id="projectId"></TaskSelect>
            </a-form-item>

            <a-form-item :label="$t('taskLink.link')" name="linkId">
                <a-select v-model:value="taskLink.linkId">
                    <a-select-option v-for="item in taskLinkTypeList" :value="item.id">{{ item.name }}</a-select-option>
                </a-select>
            </a-form-item>

            <a-form-item :label="$t('taskLink.inTask')" name="inTaskId">
                <TaskSelect v-model:task-id="taskLink.inTaskId" :project-id="projectId"></TaskSelect>
            </a-form-item>
        </a-form>

    </a-modal>
</template>

<script lang="ts"  setup>
import { ref, watch } from 'vue';
import type { Rule } from 'ant-design-vue/es/form';
import { message } from 'ant-design-vue';
import i18n from '@/i18n/index';
import taskLinkTypeService from '@/api/tasklinktypeservice';
import taskLinkService from '@/api/tasklinkservice';
import TaskSelect from './TaskSelect.vue'


const open = defineModel("open", { default: false })
const projectId = defineModel("projectId")
const taskId = defineModel("taskId")
const emit = defineEmits(['updated'])

const createTaskForm = ref();
const taskCreateRules: Record<string, Rule[]> = {
    outTaskId: [{ required: true, message: '', trigger: 'change' }],
    linkId: [{ required: true, message: '', trigger: 'change' }],
    inTaskId: [{ required: true, message: '', trigger: 'change' }]
};

const taskLink = ref({
    outTaskId: "",
    linkId: "",
    inTaskId: ""
})
const labelCol = { style: { width: '100px' } };
const wrapperCol = { span: 18 };



const taskLinkTypeList = ref([]);

watch(open, (value, oldvalue) => {
    if (value == false) {
        return
    }
   
    taskLink.value.outTaskId = taskId.value;
    loadTaskLinkType();
})

// watch(taskId, (value, oldvalue) => {
//     console.log(value)
// })


function loadTaskLinkType() {
    taskLinkTypeService.getAll().then(res => {
        taskLinkTypeList.value = res.data;
    })
}


const handleOk = (e) => {
    createTaskForm.value
        .validate()
        .then(() => {

            taskLinkService.create(taskLink.value).then(res => {
                message.success({ content: i18n.global.t("task.success"), duration: 1 })
                //触发更新事件
                emit('updated');
                open.value = false;
            })

        }).catch((e) => {})
};



</script>
    