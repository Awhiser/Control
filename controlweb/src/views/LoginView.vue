<template >
<div id="building">

    <div class="wrapper">


        <h1>Login</h1>
        <div class="input-box">
            <input type="text" v-model="username" placeholder="username" required>
        </div>
        <div class="input-box">
            <input type="password" v-model="password" placeholder="password" required>
        </div>

        <button type="submit" class="btn" @click="handleAction()">{{actionName}}</button>

        <div style="float: right; margin-top: 10px;">
           <div @click="changeBtnName()"  >{{actionName == 'Login' ? 'Register' : 'Login'}} </div>
         
        
     
        </div>

   </div>
 


</div>

</template>

<script setup>
import { ref } from 'vue'
import userService from '@/api/userservice.js' 
import { message } from 'ant-design-vue';
import { useRouter  } from 'vue-router'
const username = ref('')
const password = ref('')
let actionName = ref("Login") ;

const router = useRouter();


function handleAction() {
   // console.log(username)
   // console.log(password)
   if(actionName.value == 'Login') {
    userService.login({'name':username.value,'password':password.value}).then(res=>{
        localStorage.setItem('user',res.data.userInfo)
        localStorage.setItem('token',res.data.token)
        router.push({ path: "/project/X" })
    })

   }

   if(actionName.value == 'Register'){
    // message.success( {content: () => '注册成功,请登录', duration: 1,  style: {
    //   marginTop: '20vh',
    // } } );
    userService.register({'name':username.value,'password':password.value}).then(res=>{
        // console.log("Register! ")
        message.success("注册成功,请登录");
        this.actionName.value = "Login"
    })

   
   }

}


function changeBtnName(){
   
    actionName.value = actionName.value == 'Login' ? 'Register' : 'Login' //: ref('Login')
  
}


</script>

<style scoped>
#building {
    /* margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Poppins", sans-serif; */
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: seagreen;
    /* background: url('@/assets/login.jpeg') no-repeat;  */
width:100%;

min-height: 100vh;
background-size:100% 100%;
}

body {
   
    min-height: 100vh;
    
  
    background-color: seagreen;
    background: url('@/assets/login.jpeg') no-repeat; 
    background-size: cover;
    background-position: center;
   
}

.wrapper {
    width: 420px;
    margin-left: 0%;
    background-color: transparent;
    border: 2px solid rgba(255, 255, 255, .2);
    backdrop-filter: blur(20px);
    color: #fff;
    border-radius: 10px;
    padding: 30px;
}

.wrapper h1 {
    font-size: 36px;
    text-align: center;
    color: white;
}

.wrapper .input-box {
    position: relative;
    width: 100%;
    height: 50px;
    margin: 30px 0;
}

.input-box input {
    width: 100%;
    height: 100%;
    background-color: transparent;
    border: none;
    outline: none;
    border: 2px solid rgba(255, 255, 255, .2);
    border-radius: 40px;
    font-size: 16px;
    color: #fff;
    padding: 20px 45px 20px 20px;
}

.input-box i {
    position: absolute;
    right: 20px;
    top: 50%;
    transform: translateY(-50%);
    font-size: 20px;

}

.input-box input::placeholder {
    color: #fff;
}

.wrapper .btn {
    width: 100%;
    height: 45px;
    background-color: #fff;
    border: none;
    outline: none;
    border-radius: 40px;
    box-shadow: 0 0 10px rgba(0, 0, 0, .1);
    cursor: pointer;
    font-size: 16px;
    color: #333;
    font-weight: 600;
}

.input-box {
    position: relative;
    width: 100%;
    height: 50px;

    transform: translateY(-50%);
    font-size: 20px;
}
</style>
