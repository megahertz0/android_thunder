package com.tencent.stat.common;

import java.io.File;

class o {
    private static int a;

    static {
        a = -1;
    }

    public static boolean a() {
        if (a == 1) {
            return true;
        }
        if (a == 0) {
            return false;
        }
        String[] strArr = new String[]{"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 6) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    a = 1;
                    return true;
                }
                i++;
            } catch (Exception e) {
            }
        }
        a = 0;
        return false;
    }
}
