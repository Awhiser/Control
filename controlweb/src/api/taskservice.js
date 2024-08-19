import { get, post, httpAction } from '@/api/request'



const taskService = {
     create(params) {
        return post("/task/create",params);

    },

    getTaskList(params){
        return post("/task/getList",params);
    },
    
    getTask(id){
        return get("/task/get?id="+id);
    },
    getCreateParams() {
        return get("/task/getCreateParam");
    }

 
}

export default taskService