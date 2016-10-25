package com.sina.weibo.sdk.utils;

import com.umeng.socialize.common.SocializeConstants;

public class LogUtil {
    public static boolean sIsLogEnable;

    static {
        sIsLogEnable = false;
    }

    public static void enableLog() {
        sIsLogEnable = true;
    }

    public static void disableLog() {
        sIsLogEnable = false;
    }

    public static void d(String str, String str2) {
        if (sIsLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(stackTraceElement.getFileName())).append(SocializeConstants.OP_OPEN_PAREN).append(stackTraceElement.getLineNumber()).append(") ").append(stackTraceElement.getMethodName()).toString())).append(": ").append(str2);
        }
    }

    public static void i(String str, String str2) {
        if (sIsLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(stackTraceElement.getFileName())).append(SocializeConstants.OP_OPEN_PAREN).append(stackTraceElement.getLineNumber()).append(") ").append(stackTraceElement.getMethodName()).toString())).append(": ").append(str2);
        }
    }

    public static void e(String str, String str2) {
        if (sIsLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(stackTraceElement.getFileName())).append(SocializeConstants.OP_OPEN_PAREN).append(stackTraceElement.getLineNumber()).append(") ").append(stackTraceElement.getMethodName()).toString())).append(": ").append(str2);
        }
    }

    public static void w(String str, String str2) {
        if (sIsLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(stackTraceElement.getFileName())).append(SocializeConstants.OP_OPEN_PAREN).append(stackTraceElement.getLineNumber()).append(") ").append(stackTraceElement.getMethodName()).toString())).append(": ").append(str2);
        }
    }

    public static void v(String str, String str2) {
        if (sIsLogEnable) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(stackTraceElement.getFileName())).append(SocializeConstants.OP_OPEN_PAREN).append(stackTraceElement.getLineNumber()).append(") ").append(stackTraceElement.getMethodName()).toString())).append(": ").append(str2);
        }
    }

    public static String getStackTraceMsg() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        return new StringBuilder(String.valueOf(stackTraceElement.getFileName())).append(SocializeConstants.OP_OPEN_PAREN).append(stackTraceElement.getLineNumber()).append(") ").append(stackTraceElement.getMethodName()).toString();
    }
}
