<template>

    <a-button @click="showModal" type="primary" size="large" >  {{ $t('button.create')  }} </a-button>



    <a-modal v-model:open="open" @ok="handleOk"  :ok-text="i18n.global.t('button.create')" :title="i18n.global.t('button.create')">
      <a-divider />
      <div style="height:700px;overflow-y:auto">

     
        <a-alert message="Request Resut : [{idField:' ', nameField:' ', displayNameField:' ', mailField:' ', passwordField:' '}]" type="success" />

        <a-divider />
        <a-form  ref="createForm" :rules="createRules" :model="userConnector"  :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal" style="max-width: 400px">
  
            <a-form-item :label="i18n.global.t('userConnector.name')" name="name" >
            <a-input v-model:value="userConnector.name" />
          </a-form-item>
          <a-form-item :label="i18n.global.t('userConnector.url')" name="url" >
            <a-input v-model:value="userConnector.url" />
          </a-form-item>
          <a-form-item :label="i18n.global.t('userConnector.idField')" name="idField" >
            <a-input v-model:value="userConnector.idField" />
          </a-form-item>
          <a-form-item :label="i18n.global.t('userConnector.nameField')" name="nameField" >
            <a-input v-model:value="userConnector.nameField" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('userConnector.displayNameField')" name="displayNameField" >
            <a-input v-model:value="userConnector.displayNameField" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('userConnector.passwordField')" name="passwordField" >
            <a-input v-model:value="userConnector.passwordField" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('userConnector.mailField')" name="mailField" >
            <a-input v-model:value="userConnector.mailField" />
          </a-form-item>

    

        </a-form>
      </div>
    </a-modal>
  
  
  
   
  </template>
  <script lang="ts"  setup>
  import { ref } from 'vue';
  import type { Rule } from 'ant-design-vue/es/form';
  import userService from '@/api/userservice.js' 
  import { message } from 'ant-design-vue';
  import i18n from '@/i18n/index'

  const createForm = ref();
  const createRules: Record<string, Rule[]> = {
    name: [{ required: true, message: '', trigger: 'change' }],
    url: [{ required: true, message: '', trigger: 'change' }],
    idField: [{ required: true, message: '', trigger: 'change' }],
    nameField: [{ required: true, message: '', trigger: 'change' }],
   
    passwordField: [{ required: true, message: '', trigger: 'change' }]
  };

  
  const labelCol = { style: { width: '100px' } };
  const wrapperCol = { span: 18 };
  const userConnector = ref({
     name : '',
     url : '',
     idField : '',
     nameField : '',
     displayNameField : '',
     mailField : '',
     passwordField : '',
  })
  
  
  
  //
  const open = ref<boolean>(false);
  
  const showModal = () => {
    open.value = true;
  };
  
  const handleOk = (e) => {
    createForm.value
      .validate().then(()=>{
          userService.createUserConnector(userConnector.value).then(res=>{
                message.success(i18n.global.t('message.createSuccess'),1,()=>{ location.reload() });
                open.value = false;
            })
      })
  };
  
  
  </script>
  