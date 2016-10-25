package com.nostra13.universalimageloader.b;

import android.content.Context;
import android.os.Environment;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import java.io.File;
import java.io.IOException;

// compiled from: StorageUtils.java
public final class f {
    public static File a(Context context, boolean z) {
        File file = null;
        try {
            Object externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = a.d;
        } catch (IncompatibleClassChangeError e2) {
            externalStorageState = a.d;
        }
        if (z && "mounted".equals(r1)) {
            int i;
            if (context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) == 0) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                file = a(context);
            }
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        c.c("Can't define system cache directory! '%s' will be used.", new StringBuilder("/data/data/").append(context.getPackageName()).append("/cache/").toString());
        return new File(new StringBuilder("/data/data/").append(context.getPackageName()).append("/cache/").toString());
    }

    private static File a(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), SocializeConstants.OS), SocializeConstants.JSON_DATA), context.getPackageName()), "cache");
        if (file.exists()) {
            return file;
        }
        if (file.mkdirs()) {
            try {
                new File(file, ".nomedia").createNewFile();
                return file;
            } catch (IOException e) {
                c.b("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
                return file;
            }
        }
        c.c("Unable to create external cache directory", new Object[0]);
        return null;
    }
}
