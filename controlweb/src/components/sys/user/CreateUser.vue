<template>

    <a-button @click="showModal" type="primary" size="large" >  {{ $t('button.createUser')  }} </a-button>



    <a-modal v-model:open="open" @ok="handleOk"  :ok-text="i18n.global.t('button.create')" :title="i18n.global.t('button.createUser')">
      <a-divider />
      <div style="height:300px;overflow-y:auto">
  
        <a-form  ref="createUserForm" :rules="createRules" :model="user"  :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal" style="max-width: 400px">
  
          <a-form-item :label="i18n.global.t('user.name')" name="name" >
            <a-input v-model:value="user.name" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('user.password')" name="password" >
            <a-input v-model:value="user.password" />
          </a-form-item>

        </a-form>
      </div>
    </a-modal>
  
  
  
   
  </template>
  <script lang="ts"  setup>
  import { ref  } from 'vue';
  import type { Rule } from 'ant-design-vue/es/form';
  import userService from '@/api/userservice.js' 
  import { message } from 'ant-design-vue';
  import i18n from '@/i18n/index'
  const createUserForm = ref();
  const createRules: Record<string, Rule[]> = {
    name: [{ required: true, message: '', trigger: 'change' }],
    password: [{ required: true, message: '', trigger: 'change' }],
   
  };
  
  const labelCol = { style: { width: '100px' } };
  const wrapperCol = { span: 18 };
  const user = ref({
     name : '',
     password : '',

  })
  
  
  
  //
  const open = ref<boolean>(false);
  
  const showModal = () => {
    open.value = true;
  };
  
  const handleOk = (e) => {
    // console.log(e);
   // message.success({ content: h( "a", { style: "" ,href:"http://www.baidu.com",target:"blank"}, "Success")});
   createUserForm.value
      .validate().then(()=>{
          userService.register({'name':user.value.name,'password':user.value.password}).then(res=>{
                message.success(i18n.global.t('message.createSuccess'),1,()=>{ location.reload() });
                open.value = false;
            })
      })
    //   .then(() => {
  
    //     let taskparam = {
    //       title: task.value.title,
    //       assignee: task.value.assignee,
    //       priority: task.value.priority,
    //       tags: task.value.tags,
    //       description: task.value.description,
    //       duedate: task.value.duedate?.format(dateFormat) ?? null,
    //       projectId: task.value.projectId,
    //     }
    //   //  console.log(taskparam);
    //     taskService.create(taskparam).then(res=>{
    //         message.success({ content: h( "a", { target:"blank" ,href:"http://www.baidu.com"},   i18n.global.t("task.success")  )});
    //     })
       
  
        
    //   }).catch((e) => {
    //     console.log(e)
    //   })
  
    //  console.log(task.value.duedate.format(dateFormat))
  
  
    //open.value = false;
  };
  
  
  </script>
  