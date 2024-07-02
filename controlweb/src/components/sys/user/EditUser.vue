<template>

    <a-modal v-model:open="open" @ok="handleOk"  :ok-text="i18n.global.t('button.edit')" :title="i18n.global.t('button.edit')">
      <a-divider />
      <div style="height:300px;overflow-y:auto">
  
        <a-form  ref="editUserForm"   :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal" style="max-width: 400px">
  
          <a-form-item :label="i18n.global.t('user.displayName')" name="displayName" >
            <a-input v-model:value="editUser.displayName" />
          </a-form-item>

          <a-form-item :label="i18n.global.t('user.mail')" name="mail" >
            <a-input v-model:value="editUser.mail" />
          </a-form-item>

        </a-form>
      </div>
    </a-modal>
  
  
  
   
  </template>
  <script lang="ts"  setup>
  import { ref ,computed ,watch} from 'vue';
  import userService from '@/api/userservice.js' 
  import { message } from 'ant-design-vue';
  import i18n from '@/i18n/index'


 // const props = defineProps(['user']);

 

//   const editUser  = ref({
//     displayName : props.user.displayName,
//     mail: props.user.mail

//   })
  const user = defineModel('user',{default:{}});
  const open = defineModel('open',{default:false});
  const editUserForm = ref();
  const editUser = ref({id:"",displayName:"",mail:""});
  watch(user,(value)=>{
    editUser.value.id = value.id;
    editUser.value.displayName = value.displayName;
    editUser.value.mail = value.mail;
    //console.log(value)
  })

  

  
  const labelCol = { style: { width: '100px' } };
  const wrapperCol = { span: 18 };

  const showModal = () => {
    open.value = true;
  };
  
  const handleOk = (e) => {

    userService.update(editUser.value).then(res=>{

        message.success(i18n.global.t("message.success"),1,()=>{location.reload()})
    })
 
  
  
    //open.value = false;
  };
  
  
  </script>
  