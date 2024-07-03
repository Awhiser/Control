import { get, post, httpAction } from '@/api/request'



const projectService = {

    save(params) {
        return post("/project/save", params);
    },
    getList(params) {
        return post("/project/getList", params);
    },
    delete(id) {
        return get("/project/delete?id=" + id);
    },
    update(params) {
        return post("/project/update", params);
    },

}

export default projectService