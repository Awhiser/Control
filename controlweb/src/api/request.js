import axios from 'axios'
import { message } from 'ant-design-vue';
// import signMd5Utils from '@/utils/encryption/signMd5Utils'
import router from "@/router/index.js";
const config = {
  tenantId: '1',
  token: '1',
  serviceApi: '/api', //http://localhost:8088
  permission: '/mock/api/permission',
  permissionNoPager: '/mock/api/permission/no-pager'
}



export default config

export function post(url,parameter) {
    return httpAction(url,parameter,'post');
}

export function get(url,parameter) {
    return httpAction(url,parameter,'get');
}

export function httpAction(url, parameter, method) {
    let user  = localStorage.getItem('user');
    let token = localStorage.getItem('token')

    let header = {
        "tenantId": config.tenantId,
        "token": token,
        "Access-Control-Allow-Origin":'*'
    }

    return axios({
        url: config.serviceApi + url,
        method: method,
        data: parameter,
        headers: header
    }).then(res =>  {
       // console.log(res)
        if(res.data.code != 200 )
        {
            message.error(res.data.message,1);
            if(res.data.code == 405) {
                router.push({ path: "/" })
            }
            return new Promise(() => {});
        }
        return res.data;
    })
}


//get



// export function getUserList(parameter) {
//   return axios({
//     url: api.user,
//     method: 'get',
//     params: parameter
//   })
// }

// export function getRoleList(parameter) {
//   return axios({
//     url: api.role,
//     method: 'get',
//     params: parameter
//   })
// }

// export function getServiceList(parameter) {
//   return axios({
//     url: api.service,
//     method: 'get',
//     params: parameter
//   })
// }

// export function getPermissions(parameter) {
//   return axios({
//     url: api.permissionNoPager,
//     method: 'get',
//     params: parameter
//   })
// }

// // id == 0 add     post
// // id != 0 update  put
// export function saveService(parameter) {
//   return axios({
//     url: api.service,
//     method: parameter.id == 0 ? 'post' : 'put',
//     data: parameter
//   })
// }

// /**
//  * 下载文件 用于excel导出
//  * @param url
//  * @param parameter
//  * @returns {*}
//  */
// export function downFile(url,parameter, method='get'){
//   if(method=='get'){
//     return axios({
//       url: url,
//       params: parameter,
//       method: method ,
//       responseType: 'blob'
//     })
//   }else{
//     return axios({
//       url: url,
//       method: method,
//       data: parameter,
//       responseType: 'blob'
//     })
//   }

// }

// /**
//  * 下载文件
//  * @param url 文件路径
//  * @param fileName 文件名
//  * @param parameter
//  * @returns {*}
//  */
// export function downloadFile(url, fileName, parameter) {
//   return downFile(url, parameter).then((data) => {
//     if (!data || data.size === 0) {
//       Vue.prototype['$message'].warning('文件下载失败')
//       return
//     }
//     if (typeof window.navigator.msSaveBlob !== 'undefined') {
//       window.navigator.msSaveBlob(new Blob([data]), fileName)
//     } else {
//       let url = window.URL.createObjectURL(new Blob([data]))
//       let link = document.createElement('a')
//       link.style.display = 'none'
//       link.href = url
//       link.setAttribute('download', fileName)
//       document.body.appendChild(link)
//       link.click()
//       document.body.removeChild(link) //下载完成移除元素
//       window.URL.revokeObjectURL(url) //释放掉blob对象
//     }
//   })
// }

// /**
//  * 文件上传 用于富文本上传图片
//  * @param url
//  * @param parameter
//  * @returns {*}
//  */
// export function uploadAction(url,parameter){
//   return axios({
//     url: url,
//     data: parameter,
//     method:'post' ,
//     headers: {
//       'Content-Type': 'multipart/form-data',  // 文件上传
//     },
//   })
// }

// /**
//  * 获取文件服务访问路径
//  * @param avatar
//  * @param subStr
//  * @returns {*}
//  */
// export function getFileAccessHttpUrl(avatar,subStr) {
//   if(!subStr) subStr = 'http'
//   try {
//     if(avatar && avatar.startsWith(subStr)){
//       return avatar;
//     }else{
//       if(avatar &&　avatar.length>0 && avatar.indexOf('[')==-1){
//         return window._CONFIG['staticDomainURL'] + "/" + avatar;
//       }
//     }
//   }catch(err){
//    return;
//   }
// }