package com.xiaomi.channel.commonutils.android;

import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class e {
    private static Boolean a;

    public static synchronized boolean a() {
        boolean z = false;
        synchronized (e.class) {
            if (a == null) {
                try {
                    Properties properties = new Properties();
                    properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
                    if (!(properties.getProperty("ro.miui.ui.version.code", null) == null && properties.getProperty("ro.miui.ui.version.name", null) == null)) {
                        z = true;
                    }
                    a = Boolean.valueOf(z);
                } catch (Throwable th) {
                    a = Boolean.valueOf(false);
                }
            }
            z = a.booleanValue();
        }
        return z;
    }
}
