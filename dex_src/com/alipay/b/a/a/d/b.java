package com.alipay.b.a.a.d;

import android.os.Environment;
import java.io.File;

public final class b {
    public static String a(String str) {
        try {
            if (a()) {
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                if (new File(absolutePath, str).exists()) {
                    return com.alipay.b.a.a.a.b.a(absolutePath, str);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean a() {
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState != null && externalStorageState.length() > 0) {
            if ((externalStorageState.equals("mounted") || externalStorageState.equals("mounted_ro")) && Environment.getExternalStorageDirectory() != null) {
                return true;
            }
        }
        return false;
    }
}
