package com.sisi.control.context;

public class ContextHolder {
    // 创建一个线程变量，类型为BaseContext
    private static final ThreadLocal<ControlContext> contextHolder = new ThreadLocal<>();

    public ContextHolder() {

    }

    // 设置变量
    public static void setContext(ControlContext baseContext) { contextHolder.set(baseContext); }

    public static ControlContext getContext() {
        ControlContext obj = contextHolder.get();
        if (obj == null) {
            // 如果为空就创建一个新的
            obj = new ControlContext();
            setContext((ControlContext) obj);
        }
        return (ControlContext) obj;
    }

    public static void remove(){
        contextHolder.remove();
    }
}
