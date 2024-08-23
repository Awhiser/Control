<template>
    <a-modal v-model:open="open" @ok="handleOk"  :ok-text="$t('button.edit')" :title="$t('button.edit')">
      <a-divider />
      <div style="height:700px;overflow-y:auto">
        <a-form  ref="createTaskForm" :rules="taskCreateRules" :model="task"  :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal" style="max-width: 800px">
  
            <div v-if="loading" >

          <a-form-item :label="$t('project.name')" name="projectId" >
            <a-select  v-model:value ="task.projectId"  >
              <a-select-option v-for="item in createParam?.projectList" :value="item.id">{{item.name}}</a-select-option>
            </a-select>
          </a-form-item>
  
         
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
            <UserSelect v-model:userId="task.assignee" :user-opt="userOpt" :onlyProjectUser="true" :projectId="task.projectId" ></UserSelect>
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
  
   
  </template>
  <script lang="ts"  setup>
  import { ref, h ,watch } from 'vue';
  import type { Rule } from 'ant-design-vue/es/form';
  import taskService from '@/api/taskservice';
  import dayjs, { Dayjs } from 'dayjs';
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
  
  const userOpt = ref([]);
  
  const createParam = ref(null);
  
  
  const open = defineModel("open",{default:false})
  const edittask = defineModel("editTask",{default:null})
  
  //

  const loading = ref(false);

  watch(open, (value, oldvalue) => {
    if (value == false) {
        return
    }
   
    loadCreateParams();
   
})

  

  
  const handleOk = (e) => {
    console.log("111")
    createTaskForm.value
      .validate()
      .then(() => {
  
        let taskparam = {
            id : task.value.id,
          title: task.value.title,
          assignee: task.value.assignee,//id
          priority: task.value.priority,
          tags: task.value.tags,
          description: task.value.description,
          duedate: task.value.duedate?.format(dateFormat) ?? null,
          projectId: task.value.projectId,
          type:task.value.type
        }
        taskService.update(taskparam).then(res=>{
            message.success( { content: i18n.global.t("task.success") , duration:1 })
        })
        
      }).catch((e) => {
        
      })
  };
  
  
  
  function loadCreateParams(){
    console.log(edittask.value)
    loading.value = false;
    taskService.getCreateParams().then(res=>{
      createParam.value = res.data;
      loading.value = true;
    })

     task.value = {
        id: edittask.value.id,
        title : edittask.value.title,
        assignee : edittask.value.assignee.id,
        priority:edittask.value.priority,
        tags : edittask.value.tags,
        description : edittask.value.description,
        duedate :  dayjs(edittask.value.duedate)  ,
        projectId : edittask.value.projectId,
        type:edittask.value.type
   }
   userOpt.value = [{ label: edittask.value.assignee.displayName, value: edittask.value.assignee.id}]
 

}

  
  
  
  </script>
  