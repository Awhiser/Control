import { get, post, httpAction } from '@/api/request'



const taskChangeLogService = {

    getByTaskId(id){
        return get("/taskChangeLog/getByTaskId?taskId="+id);
    }

 
}

export default taskChangeLogService