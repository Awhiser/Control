<template>
    <a-modal v-model:open="open" @ok="handleOk" :ok-text="i18n.global.t('button.edit')"
        :title="i18n.global.t('button.edit')">
        <a-divider />
        <div style="height:400px;overflow-y:auto">

            <a-form ref="projectForm" :rules="formRules" :model="project" :label-col="labelCol" :wrapper-col="wrapperCol"
                layout="horizontal" style="max-width: 800px">

                <a-form-item :label="i18n.global.t('project.name')" name="name">
                    <a-input v-model:value="project.name" />
                </a-form-item>

                <a-form-item :label="i18n.global.t('project.leader')" name="leaderId">
                    <UserSelect v-model:userId="project.leaderId" v-model:userOpt="userOpt"></UserSelect>
                </a-form-item>


            </a-form>
        </div>
    </a-modal>
</template>
<script lang="ts"  setup>
import { ref, watch } from 'vue';
import type { Rule } from 'ant-design-vue/es/form';
import { message } from 'ant-design-vue';
import i18n from '@/i18n/index'
import UserSelect from '../../UserSelect.vue';
import projectService from '@/api/projectservice'
const projectForm = ref();
const formRules: Record<string, Rule[]> = {
    name: [{ required: true, message: '', trigger: 'change' }],
    leaderId: [{ required: true, message: '', trigger: 'change' }]
};

const labelCol = { style: { width: '100px' } };
const wrapperCol = { span: 18 };
const project = ref({
    id: '',
    name: '',
    leaderId: '',
})

const projectModel = defineModel('projectModel', { default: {} });
const open = defineModel('open', { default: false });

let userOpt = ref([]);

watch(projectModel, (value) => {
    project.value.id = value.id;
    project.value.name = value.name;
    project.value.leaderId = value.leader.id;
    userOpt.value = [{
        label: `${value.leader.displayName} (${value.leader.name})`,
        value: value.leader.id,
    }]
})


const handleOk = (e) => {

    projectForm.value
        .validate().then(() => {
            projectService.update(project.value).then(res => {
                   message.success(i18n.global.t('message.success'),1,()=>{location.reload()})
                 
            })

        })
 }




</script>
  