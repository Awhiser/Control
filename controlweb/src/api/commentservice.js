import { get, post, httpAction } from '@/api/request'



const commentService = {

    create(params) {
        return post("/comment/create", params);
    },

    getByTaskId(taskId) {
        return get("/comment/getByTaskId?taskId=" + taskId);
    },

    delete(id) {
        return get("/comment/delete?id=" + id);
    },
   


}

export default commentService