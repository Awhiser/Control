import { get, post, httpAction } from '@/api/request'



const taskService = {
     create(params) {
        return post("/task/create",params);

    },

    getTaskList(params){
        return post("/task/getList",params);
    }

 
}

export default taskService