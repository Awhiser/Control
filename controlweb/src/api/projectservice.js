import { get, post, httpAction } from '@/api/request'



const projectService = {

    save(params) {
        return post("/project/save", params);
    },
    getProjectPage(params) {
        return post("/project/getProjectPage", params);
    },
    delete(id) {
        return get("/project/delete?id=" + id);
    },
    update(params) {
        return post("/project/update", params);
    },
    getProjectByUserId(userId){
        return get("/project/getProjectByUserId?userId=" + userId);
    },

    saveProjectMember(params){
        return post("/project/projectMemmber/save", params);
    },
    getProjectMemberList(params){
        return post("/project/projectMemmber/getList", params);
    },
    deleteProjectMember(id){
        return get("/project/projectMemmber/delete?id=" + id);
    }


}

export default projectService