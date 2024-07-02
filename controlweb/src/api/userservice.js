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
    }
}

export default userService