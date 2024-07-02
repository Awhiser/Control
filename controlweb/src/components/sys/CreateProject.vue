<template>

    <a-button @click="showModal" type="primary" size="large" >  {{ $t('button.createProject')  }} </a-button>



    <a-modal v-model:open="open" @ok="handleOk"  ok-text="创建" title="创建任务 : Project-X">
      <a-divider />
      <div style="height:700px;overflow-y:auto">
  
        <a-form  ref="createProjectForm" :rules="taskCreateRules" :model="project"  :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal" style="max-width: 800px">
  
          <a-form-item label="项目名" name="name" >
            <a-input v-model:value="project.title" />
          </a-form-item>
        
          <a-form-item label="负责人" name="leader" >
            <a-select v-model:value="project.leader">  </a-select>
          </a-form-item>
          
  
        </a-form>
      </div>
    </a-modal>
  
  
  
   
  </template>
  <script lang="ts"  setup>
  import { ref, h  } from 'vue';
  import type { Rule } from 'ant-design-vue/es/form';

  import { message } from 'ant-design-vue';
  import i18n from '@/i18n/index'
  const createTaskForm = ref();
  const taskCreateRules: Record<string, Rule[]> = {
    projectId: [{ required: true, message: '', trigger: 'change' }],
    type: [{ required: true, message: '', trigger: 'change' }],
    title: [{ required: true, message: '', trigger: 'blur' }],
  };
  
  const labelCol = { style: { width: '100px' } };
  const wrapperCol = { span: 18 };
  const dateFormat = 'YYYY-MM-DD';
  const project = ref({
     name : '',
     leader : '',

  })
  
  
  
  //
  const open = ref<boolean>(true);
  
  const showModal = () => {
    open.value = true;
  };
  
  const handleOk = (e) => {
    // console.log(e);
   // message.success({ content: h( "a", { style: "" ,href:"http://www.baidu.com",target:"blank"}, "Success")});
    // createTaskForm.value
    //   .validate()
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
  