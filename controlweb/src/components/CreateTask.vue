<template>
  <a-modal v-model:open="open" @ok="handleOk"  ok-text="创建" title="创建任务 : Project-X">
    <a-divider />
    <div style="height:700px;overflow-y:auto">

      <a-form  ref="createTaskForm" :rules="taskCreateRules" :model="task"  :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal" style="max-width: 800px">

        <a-form-item label="项目" name="projectId" >
          <a-select  v-model:value ="task.projectId"  >
            <a-select-option value="X">X</a-select-option>
            <a-select-option value="S">S</a-select-option>
            <a-select-option value="A">A</a-select-option>
          </a-select>
        </a-form-item>

        <a-form-item label="问题类型" name="type" >
          <a-select  v-model:value ="task.type"  >
            <a-select-option value="Task">Task</a-select-option>
            <a-select-option value="Bug">Bug</a-select-option>
            <a-select-option value="Story">Story</a-select-option>
          </a-select>
        </a-form-item>
        <a-divider />

        <a-form-item label="标题" name="title" >
          <a-input v-model:value="task.title" />
        </a-form-item>
      
        <a-form-item label="经办人">
          <a-select v-model:value="task.assignee" placeholder="Input Search">
          </a-select>
        </a-form-item>

        <a-form-item label="优先级">
          <a-select v-model:value="task.priority" >
            <a-select-option v-for="num in 10" :value="'p' + num">P{{ num }}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="标签">
          <!-- <a-select mode="multiple" v-model:value="task.tags" placeholder="Input Search">
          </a-select> -->

          <a-select
      v-model:value="task.tags"
      mode="tags"
      placeholder="Please select"
     
    ></a-select>

        </a-form-item>

        <a-form-item label="描述">
          <a-textarea v-model:value="task.description" :rows="6" />
        </a-form-item>

        <a-form-item label="到期日">
          <a-date-picker  v-model:value="task.duedate" />
        </a-form-item>

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
     <span style="font-size: 15px;"> 新增 </span>  
    </template>

  </a-float-button>
</template>
<script lang="ts"  setup>
import { ref } from 'vue';
import {PlusOutlined} from '@ant-design/icons-vue';
import type { Rule } from 'ant-design-vue/es/form';
import taskService from '@/api/taskservice';
import dayjs, { Dayjs } from 'dayjs';
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
})



//
const open = ref<boolean>(false);

const showModal = () => {
  open.value = true;
};

const handleOk = (e) => {
  // console.log(e);
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
      }
    //  console.log(taskparam);
     taskService.create(taskparam);
    }).catch((e) => {
      console.log(e)
    })

  //  console.log(task.value.duedate.format(dateFormat))


  //open.value = false;
};


</script>
