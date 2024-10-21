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
}


}


export default versionService;