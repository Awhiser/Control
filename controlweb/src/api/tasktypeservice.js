import { get, post, httpAction } from '@/api/request'



const taskTypeService = {
     create(params) {
        return post("/taskType/create",params);
    },

    getList(){
        return get("/taskType/getList");
    },
    
    get(id){
        return get("/taskType/get?id="+id);
    },

    delete(id){
        return get("/taskType/delete?id="+id);
    }
 

 
}

export default taskTypeService