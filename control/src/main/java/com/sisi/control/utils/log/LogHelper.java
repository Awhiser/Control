package com.sisi.control.utils.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {
    /**
     * 打印普通日志
     */
    public static void logInfo(String msg){
        logger.log(Level.INFO,msg);
    }

    /**
     * 打印错误日志
     */
    public static void logError(String msg){
        logger.log(Level.ERROR,msg);
    }

    /**
     * 打印错误日志
     */
    public static void logError(String msg, Exception e){

//        StringBuffer buffer = new StringBuffer();
//        for (var i = 0 ; i <e.getStackTrace().length; i++ ){
//            buffer.append(e.getStackTrace()[i].toString() + "\n");
//        }
        logger.log(Level.ERROR,msg +":\n"+e.getMessage());
    }

    /**
     * 日志句柄
     */
    private static final Logger logger = LogManager.getLogger();
}
