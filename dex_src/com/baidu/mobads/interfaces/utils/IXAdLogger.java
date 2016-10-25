package com.baidu.mobads.interfaces.utils;

public interface IXAdLogger {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final String TAG = "BaiduXAdSDK";
    public static final int UNLOGGABLE = -1;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    int d(String str);

    int d(String str, String str2);

    int d(String str, Throwable th);

    int d(Throwable th);

    int d(Object... objArr);

    int e(String str);

    int e(String str, Throwable th);

    int e(Throwable th);

    int e(Object... objArr);

    int i(String str);

    int i(String str, String str2);

    int i(String str, Throwable th);

    int i(Object... objArr);

    boolean isLoggable(int i);

    boolean isLoggable(String str, int i);

    int w(String str);

    int w(String str, Throwable th);

    int w(Throwable th);

    int w(Object... objArr);
}
