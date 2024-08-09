package com.sisi.control.service;

import com.sisi.control.context.ContextHolder;
import com.sisi.control.model.controlfile.ControlFile;
import com.sisi.control.repository.impl.ControlFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Service
public class ControlFileService {

    private ControlFileDao controlFileDao;
    private FileService fileService;

    @Autowired
    public ControlFileService(ControlFileDao controlFileDao) {
        this.controlFileDao = controlFileDao;
    }

    private ControlFile create(MultipartFile file, String taskId) {
        ControlFile controlFile = new ControlFile();
        controlFile.setCreateTime(new Date());
        controlFile.setName(file.getOriginalFilename());
        controlFile.setTaskId(taskId);
        controlFile.setTenantId(ContextHolder.getContext().getTenantId());
        controlFile.setIsDelete(false);
        var filePath = fileService.saveFile(file);
        if(StringUtils.hasText(filePath)) {
            controlFile.setUrl(filePath);
            return controlFileDao.save(controlFile);
        }
        return null;
    }


    public File getFile(String url){
       return fileService.getFile(url);
    }

}
