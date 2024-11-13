import { get, post, httpAction } from '@/api/request'

const versionService = { 

  getDetailPage(params){
      return post("/version/getDetailPage",params);
  },

  create(params){
      return post("/version/create",params);
  },
  update(params){
    return post("/version/update",params);
  },
  getVersionStatusList(){
    return get("/version/getStatusList")
  },
  createVersionStatus(params) {
    return post("/version/createStatus",params);
  },
  updateVersionStatus(params) {
    return post("/version/updateStatus",params);
  }




}


export default versionService;