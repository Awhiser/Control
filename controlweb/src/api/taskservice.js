import { get, post, httpAction } from '@/api/request'



const taskService = {
     create(params) {
        return post("/task/create",params);
    },
    update(params){
        return post("/task/update",params);
    },

    getTaskList(params){
        return post("/task/getList",params);
    },
    
    getTask(id){
        return get("/task/get?id="+id);
    },
    getCreateParams() {
        return get("/task/getCreateParam");
    },
    updateStatus(taskId, status) {
        return get(`/task/updateStatus?taskId=${taskId}&status=${status}`)
    },
    getFilterParam(projectId) {
        return get(`/task/getFilterParam?projectId=${projectId}`)
    }

 
}

export default taskService