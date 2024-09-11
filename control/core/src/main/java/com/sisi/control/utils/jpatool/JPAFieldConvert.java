package com.sisi.control.utils.jpatool;

import com.sisi.control.utils.log.LogHelper;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JPAFieldConvert {

    private static Map<Class, String> CLASS_LAMDBA_CACHE = new ConcurrentHashMap<>();

    public static <T> String convertToFieldName(IJPAFieldGetter<T> fn) {
        String fieldName = CLASS_LAMDBA_CACHE.getOrDefault(fn.getClass(),null);
        // 先检查缓存中是否已存在
        if(fieldName == null) {
            try {
                // 提取SerializedLambda并缓存
                Method method = fn.getClass().getDeclaredMethod("writeReplace");
                method.setAccessible(Boolean.TRUE);
                var lambda = (SerializedLambda) method.invoke(fn);
                // 获取方法名
                String methodName = lambda.getImplMethodName();
                String prefix = null;
                if(methodName.startsWith("get")){
                    prefix = "get";
                }
                else if(methodName.startsWith("is")){
                    prefix = "is";
                }
                if(prefix == null){
                    LogHelper.logInfo("无效的getter方法: "+ methodName);
                }

                fieldName = methodName.replace(prefix, "");
                fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
                // 截取get/is之后的字符串并转换首字母为小写
                CLASS_LAMDBA_CACHE.putIfAbsent(fn.getClass(), fieldName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fieldName;

    }


}
