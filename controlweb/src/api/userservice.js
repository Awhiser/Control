import { get, post, httpAction } from '@/api/request'



const userService = {
     login(params) {
        return post("/user/login",params);
    },

    register(params) {
        return post("/user/register",params);

    },
    getList(params){
        return post("/user/getList",params);
    },
    update(params){
        return post("/user/update",params);
    },
    delete(id){
        return get("/user/delete?id="+id);
    },

    createUserConnector(params){
        return post("/user/saveUserConnector",params);
    },

    getUserConnectors(){
        return get("/user/getUserConnectors");
    },

    updateUserConnector(params){
        return post("/user/updateUserConnector",params);
    },

    deleteUserConnector(id){
        return get("/user/deleteUserConnector?id="+id);
    },

    syncUserConnector(id){
        return get("/user/syncUserConnector?id="+id);
    },

  

}

export default userService