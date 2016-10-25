package com.xiaomi.channel.commonutils.file;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import java.io.File;

public class c {
    public static boolean a() {
        try {
            return Environment.getExternalStorageState().equals("removed");
        } catch (Throwable e) {
            b.a(e);
            return true;
        }
    }

    public static boolean b() {
        try {
            return !Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable e) {
            b.a(e);
            return true;
        }
    }

    public static boolean c() {
        return e() <= 102400;
    }

    public static boolean d() {
        return (b() || c() || a()) ? false : true;
    }

    public static long e() {
        long j = 0;
        if (b()) {
            return 0;
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null || TextUtils.isEmpty(externalStorageDirectory.getPath())) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(externalStorageDirectory.getPath());
            j = (((long) statFs.getAvailableBlocks()) - 4) * ((long) statFs.getBlockSize());
            return j;
        } catch (Throwable th) {
            return j;
        }
    }
}
