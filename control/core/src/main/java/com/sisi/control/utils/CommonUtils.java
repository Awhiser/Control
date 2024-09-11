package com.sisi.control.utils;

import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;

import java.util.Date;

public class CommonUtils {

    public static String idGenerate() {

        return DateUtils.parseIdDateStr(new Date()) + NanoIdUtils.randomNanoId();
    }
}
