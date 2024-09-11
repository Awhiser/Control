package com.sisi.control.utils;

import com.sisi.control.utils.log.LogHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static SimpleDateFormat commonDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat idDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String parseCommonDateStr(Date date) {
        try {
            return commonDateFormat.format(date);
        }catch (Exception e){
            LogHelper.logError("parseCommonDateStr Error",e);
            return null;
        }
    }

    public static String parseIdDateStr(Date date) {
        try {
            return idDateFormat.format(date);
        }catch (Exception e){
            LogHelper.logError("parseCommonDateStr Error",e);
            return null;
        }
    }

}
