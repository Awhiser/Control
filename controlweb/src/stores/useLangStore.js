import { defineStore } from 'pinia'


export const useLangStore = defineStore('lang', {
  state: ()=> {
    return {
      language: sessionStorage.getItem('localeLang') || 'zhCn',
    }
  },
  actions: {
    changeLang(data) {
      this.language = data
    },
  },
  persist: true,
})