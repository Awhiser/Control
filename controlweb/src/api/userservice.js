import { get, post, httpAction } from '@/api/request'



const userService = {
     login(params) {
        return post("/user/login",params);

    },

    register(params) {
        return post("/user/register",params);

    }
}

export default userService