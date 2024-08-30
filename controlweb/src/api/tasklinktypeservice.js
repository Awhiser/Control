import { get, post, httpAction } from '@/api/request'



const taskLinkTypeService = {
     create(params) {
        return post("/taskLinkType/create",params);
    },
    update(params) {
        return post("/taskLinkType/update",params);
    },

    getAll(){
        return get("/taskLinkType/getAll");
    },
    
    get(id){
        return get("/taskLinkType/get?id="+id);
    },

    delete(id){
        return get("/taskLinkType/delete?id="+id);
    }

}

export default taskLinkTypeService