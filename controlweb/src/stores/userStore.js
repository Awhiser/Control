import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const userStore = defineStore('user',  () => {
  const token = ref(null)
  const user = ref(null)
//   const doubleCount = computed(() => count.value * 2)

  function setToken(value){
    token.value = value;
  }

  function setUser(value){
    user.value = value;
  }

  function getToken() {
    return token.value;
  }

  function getUser(){
    return user.value;
  }

  return { getToken , setToken, getUser, setUser }
},{persist: true})
