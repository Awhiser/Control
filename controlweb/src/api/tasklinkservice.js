import { get, post, httpAction } from '@/api/request'



const taskLinkService = {
    create(params) {
        return post("/taskLink/create",params);
    },
    getByTaskId(taskId){
        return get("/taskLink/getByTaskId?taskId="+taskId);
    },

    delete(id){
        return get("/taskLink/delete?id="+id);
    }

}

export default taskLinkService