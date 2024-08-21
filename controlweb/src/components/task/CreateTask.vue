<template>
  <a-modal v-model:open="open" @ok="handleOk"  :ok-text="$t('button.create')" :title="$t('button.create')">
    <a-divider />
    <div style="height:700px;overflow-y:auto">

  
         
      
      <a-form  ref="createTaskForm" :rules="taskCreateRules" :model="task"  :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal" style="max-width: 800px">


  

        <a-form-item :label="$t('project.name')" name="projectId" >
          <a-select  v-model:value ="task.projectId"  >
            <a-select-option v-for="item in createParam?.projectList" :value="item.id">{{item.name}}</a-select-option>
          </a-select>
        </a-form-item>

        <div v-if="task.projectId" >
        <a-form-item :label="$t('task.type')"  name="type" >
          <a-select  v-model:value ="task.type"  >
            <a-select-option v-for="item in createParam?.taskTypeList" :value="item.name">{{item.name}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-divider />

        <a-form-item :label="$t('task.title')"  name="title" >
          <a-input v-model:value="task.title" />
        </a-form-item>
      
        <a-form-item :label="$t('task.assignee')"  >
          <!-- <a-select v-model:value="task.assignee">
          </a-select> -->
          <UserSelect v-model:userId="task.assignee" :onlyProjectUser="true" :projectId="task.projectId" ></UserSelect>
        </a-form-item>

        <a-form-item  :label="$t('task.priority')"  >
          <a-select v-model:value="task.priority" >
            <a-select-option v-for="num in 10" :value="'p' + num">P{{ num }}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item  :label="$t('task.tags')"  >
          <!-- <a-select mode="multiple" v-model:value="task.tags" placeholder="Input Search">
          </a-select> -->

          <a-select  v-model:value="task.tags" mode="tags"></a-select>

        </a-form-item>

        <a-form-item :label="$t('task.desc')"  >
          <a-textarea v-model:value="task.description" :rows="6" />
        </a-form-item>

        <a-form-item :label="$t('task.dueDate')" >
          <a-date-picker  v-model:value="task.duedate" />
        </a-form-item>
      </div>
      </a-form>
    </div>
  </a-modal>



  <a-float-button 
    type="primary" 
    shape = "square"
    :style="{
    right: '20px',
    width: '2.3%',
    height: '4%',

  }" @click="showModal">
    <template #icon>
      <PlusOutlined />
    </template>
    <template #description>
     <span style="font-size: 15px;"> {{ $t('task.create') }} </span>  
    </template>

  </a-float-button>
</template>
<script lang="ts"  setup>
import { ref, h  } from 'vue';
import {PlusOutlined} from '@ant-design/icons-vue';
import type { Rule } from 'ant-design-vue/es/form';
import taskService from '@/api/taskservice';
import { Dayjs } from 'dayjs';
import { message } from 'ant-design-vue';
import i18n from '@/i18n/index';
import UserSelect from '../UserSelect.vue';

const createTaskForm = ref();
const taskCreateRules: Record<string, Rule[]> = {
  projectId: [{ required: true, message: '', trigger: 'change' }],
  type: [{ required: true, message: '', trigger: 'change' }],
  title: [{ required: true, message: '', trigger: 'blur' }],
};

const labelCol = { style: { width: '100px' } };
const wrapperCol = { span: 18 };
const dateFormat = 'YYYY-MM-DD';
const task = ref({
   title : '',
   assignee : '',
   priority:'',
   tags : [],
   description : '',
   duedate :  ref<Dayjs>(),
   projectId : '',
   type:''
})


const createParam = ref(null);




//
const open = ref<boolean>(false);

const showModal = () => {
  //projectService.getList();
  //加载资源
  taskService.getCreateParams().then(res=>{
    console.log(res.data)
    createParam.value = res.data;
  })
  open.value = true;
};

const handleOk = (e) => {
  // console.log(e);
 // message.success({ content: h( "a", { style: "" ,href:"http://www.baidu.com",target:"blank"}, "Success")});
  createTaskForm.value
    .validate()
    .then(() => {

      let taskparam = {
        title: task.value.title,
        assignee: task.value.assignee,
        priority: task.value.priority,
        tags: task.value.tags,
        description: task.value.description,
        duedate: task.value.duedate?.format(dateFormat) ?? null,
        projectId: task.value.projectId,
        type:task.value.type
      }
    //  console.log(taskparam);
      taskService.create(taskparam).then(res=>{
          message.success(
            { 
              content: h( "a", { target:"blank" ,href:"http://www.baidu.com"},   i18n.global.t("task.success") ),
              duration:1,
              onClose:()=>{ open.value = false; location.reload(); }
            }
          )
      })
     

      
    }).catch((e) => {
      
    })

  


  //open.value = false;
};



function loadCreateParams(){
  return taskService.getCreateParams();

}



</script>
