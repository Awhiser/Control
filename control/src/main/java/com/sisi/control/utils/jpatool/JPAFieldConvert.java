package com.sisi.control.utils.jpatool;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JPAFieldConvert {

    private static Map<Class, SerializedLambda> CLASS_LAMDBA_CACHE = new ConcurrentHashMap<>();

    public static <T> String convertToFieldName(IJPAFieldGetter<T> fn) {
        SerializedLambda lambda = getSerializedLambda(fn);
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
            System.out.println("无效的getter方法: "+ methodName);
        }

        String field = methodName.replace(prefix, "");
        // 截取get/is之后的字符串并转换首字母为小写
        return Character.toLowerCase(field.charAt(0)) + field.substring(1);
    }

    private static SerializedLambda getSerializedLambda(Serializable fn){
        SerializedLambda lambda = CLASS_LAMDBA_CACHE.get(fn.getClass());
        // 先检查缓存中是否已存在
        if(lambda == null) {
            try {
                // 提取SerializedLambda并缓存
                Method method = fn.getClass().getDeclaredMethod("writeReplace");
                method.setAccessible(Boolean.TRUE);
                lambda = (SerializedLambda) method.invoke(fn);
                CLASS_LAMDBA_CACHE.put(fn.getClass(), lambda);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lambda;

    }

}
