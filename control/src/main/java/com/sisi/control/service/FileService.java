package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.utils.DateUtils;
import com.sisi.control.utils.log.LogHelper;
import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class FileService {

    private String rootPath = "controlFile/";



    public File getFile(String path) {
        //todo CustomerFileSore getFile

        File file = new File(rootPath+path);
        if(!file.exists()){
            return null;
        }
       return file;
    }

    // url
    public String saveFile(MultipartFile file) {
        String fileSavePath = ContextHolder.getContext().getTenantId()+"/"+ DateUtils.parseCommonDateStr(new Date());
        File folder = new File(rootPath+fileSavePath);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 对上传的文件重命名，避免文件重名
        String oldName = file.getOriginalFilename();
        String newName = NanoIdUtils.randomNanoId()+ oldName.substring(oldName.lastIndexOf("."));

        //todo CustomerFileSore post传输文件流 给外部自定义接口

        try {
            // 文件保存
            file.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
            String filePath =  fileSavePath+ "/" +newName;
            return filePath;
        } catch (IOException e) {
            LogHelper.logError("FileService SaveFile Error " ,e);
        }
        return null;
    }

    public void deleteFile(String path) {
        File file = new File(path);
        if(!file.exists()){
            return;
        }
        file.delete();
    }

}
