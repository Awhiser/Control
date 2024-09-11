<template  >

<a-layout>
      <HeaderLayOut v-model:projectId="route.query.projectId" > </HeaderLayOut>
      <a-layout-content :style="{padding:'15px'}" ><TaskDetails :taskId="route.query.taskId" > </TaskDetails></a-layout-content>
</a-layout>


   
    
</template>
<script lang="ts" setup>
import { ref } from 'vue'

import { onMounted, watch } from 'vue'
import TaskDetails from '@/components/task/TaskDetails.vue'

import HeaderLayOut from '@/components/HeaderLayOut.vue';
import { useRoute } from 'vue-router';

const route = useRoute();


const props = defineProps({
    taskId: String
})

const selectedProject = ref(null);

onMounted(() => {
  //  console.log(route.params.taskId)
})

watch(() => route.params.projectId, (newId, oldId) => {

})




</script>