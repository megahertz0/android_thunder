package com.xunlei.tdlive.usercenter;

import org.apache.commons.logging.impl.SimpleLog;

// compiled from: Constants.java
public class a {
    public static String a(int i) {
        switch (Math.abs(i)) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return "\u7537";
            case SimpleLog.LOG_LEVEL_DEBUG:
                return "\u5973";
            default:
                return "\u672a\u8bbe\u7f6e";
        }
    }
}
