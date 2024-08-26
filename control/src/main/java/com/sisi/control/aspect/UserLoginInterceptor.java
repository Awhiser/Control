package com.sisi.control.aspect;

import com.google.gson.Gson;
import com.sisi.control.context.ContextHolder;
import com.sisi.control.context.ControlContext;
import com.sisi.control.model.controlenum.MessageEnum;
import com.sisi.control.model.response.Response;
import com.sisi.control.utils.token.TokenUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UserLoginInterceptor implements HandlerInterceptor {

    TokenUtil tokenUtil;

    public UserLoginInterceptor(TokenUtil tokenUtil){
        this.tokenUtil = tokenUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取token

        String tenantId = request.getHeader("tenantid");

        var context = new ControlContext(tenantId);
        ContextHolder.setContext(context);
        var uri = request.getRequestURI();
        if(uri.endsWith("/api/user/login") || uri.endsWith("/api/user/register") ){
            return true;
        }
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token) || (context.token = tokenUtil.containsToken(token) ) ==null  ){
            returnNoLogin(response);
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextHolder.remove();
    }

    /**
     * 返回未登录的错误信息
     * @param response ServletResponse
     */
    private void returnNoLogin(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        // 设置返回401 和响应编码
        response.setStatus(200);
        response.setContentType("Application/json;charset=utf-8");
        // 构造返回响应体
        String resultString =   new Gson().toJson(Response.fail(405,MessageEnum.ReLogin));
        outputStream.write(resultString.getBytes(StandardCharsets.UTF_8));
    }

}
