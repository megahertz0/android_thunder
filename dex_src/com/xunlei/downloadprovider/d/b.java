package com.xunlei.downloadprovider.d;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: DateUtil.java
public final class b {
    public static boolean a(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j2);
        return instance.get(1) == instance2.get(1) && instance.get(SimpleLog.LOG_LEVEL_DEBUG) == instance2.get(SimpleLog.LOG_LEVEL_DEBUG) && instance.get(SimpleLog.LOG_LEVEL_ERROR) == instance2.get(SimpleLog.LOG_LEVEL_ERROR);
    }

    public static long a(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date != null ? date.getTime() : 0;
    }
}
