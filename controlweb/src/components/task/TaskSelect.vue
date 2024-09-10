<template>
    <a-select
     v-model:value="taskId" show-search 
    :placeholder="i18n.global.t('input.searchName')"
    :options="selectOpt"
    :filter-option="false"
      :not-found-content="null"  @search="search"> </a-select>
  </template>
  <script lang="ts"  setup>
  
  import taskService from '@/api/taskservice';
  import i18n from '@/i18n';
  import { ref, watch ,onMounted} from 'vue'
  const taskId = defineModel("taskId")
  const projectId =  defineModel("projectId", { default: null })
  const selectOpt = defineModel("selectOpt", { default: [] })

  
  function search(name) {
    if (name == "") {
      return;
    }
    taskId.value = null
  
    taskService.getTaskList({ title: name,projectId: projectId.value  }).then(res => {
      selectOpt.value = res.data.dataList.map(item => ({
        label: item.title,
        value: item.id,
      }))
      console.log(selectOpt.value)
    })
  
  }



  onMounted(()=>{

    if(!taskId.value) {
      return;
    }
    taskService.getTask(taskId.value).then(res => {
        selectOpt.value = [{
        label: res.data.title,
        value: res.data.id
      }]
  }) 
})
  
  
  </script>
    