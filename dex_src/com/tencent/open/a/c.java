package com.tencent.open.a;

import com.umeng.message.MsgConstant;
import com.umeng.socialize.PlatformConfig.TencentWeibo;
import java.io.File;

// compiled from: ProGuard
public class c {
    public static int a;
    public static int b;
    public static String c;
    public static String d;
    public static String e;
    public static long f;
    public static int g;
    public static int h;
    public static int i;
    public static String j;
    public static String k;
    public static String l;
    public static int m;
    public static long n;

    static {
        a = 62;
        b = 60;
        c = "OpenSDK.Client.File.Tracer";
        d = new StringBuilder("Tencent").append(File.separator).append("msflogs").append(File.separator).append("com").append(File.separator).append(TencentWeibo.Name).append(File.separator).append("mobileqq").append(File.separator).toString();
        e = MsgConstant.CACHE_LOG_FILE_EXT;
        f = 8388608;
        g = 262144;
        h = 1024;
        i = 10000;
        j = "debug.file.blockcount";
        k = "debug.file.keepperiod";
        l = "debug.file.tracelevel";
        m = 24;
        n = 604800000;
    }
}
