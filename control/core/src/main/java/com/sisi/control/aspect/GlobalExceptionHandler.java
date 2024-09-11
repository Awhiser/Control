package com.sisi.control.aspect;

import com.sisi.control.model.controlenum.MessageEnum;
import com.sisi.control.model.response.Response;
import com.sisi.control.utils.log.LogHelper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e){

        LogHelper.logError("GlobalException: "+e.getMessage());
        return Response.fail(MessageEnum.ServerError);
    }
}
