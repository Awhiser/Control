<template>

    <a-button @click="showModal" type="primary" size="large" >  {{ $t('button.createProject')  }} </a-button>
 


    <a-modal v-model:open="open" @ok="handleOk"  :ok-text="i18n.global.t('button.create')" :title="i18n.global.t('button.create')" >
      <a-divider />
      <div style="height:400px;overflow-y:auto">
  
        <a-form  ref="projectForm" :rules="formRules" :model="project"  :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal" style="max-width: 800px">
  
          <a-form-item :label="i18n.global.t('project.name')" name="name" >
            <a-input v-model:value="project.name" />
          </a-form-item>
        
          <a-form-item :label="i18n.global.t('project.leader')" name="leaderId" >
            <UserSelect v-model:userId="project.leaderId" ></UserSelect>

            
          </a-form-item>
          
  
        </a-form>
      </div>
    </a-modal>
  
  
  
   
  </template>
  <script lang="ts"  setup>
  import { ref  } from 'vue';
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
     name : '',
     leaderId : '',
  })
  
  const open = ref<boolean>(false);
  
  const showModal = () => {
    open.value = true;
  };
  
  const handleOk = (e) => {
   projectForm.value
      .validate().then(()=>{
        projectService.save(project.value).then(res=>{
          message.success(i18n.global.t('message.success'),1,()=>{location.reload()})
        })
      })
  };


  
  
  </script>
  