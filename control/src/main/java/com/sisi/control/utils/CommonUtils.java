package com.sisi.control.utils;

import com.soundicly.jnanoidenhanced.jnanoid.NanoIdUtils;

import java.util.Date;

public class CommonUtils {

    public static String idGenerate() {

        return DateUtils.parseCommonDateStr(new Date()).trim() + NanoIdUtils.randomNanoId();
    }
}
