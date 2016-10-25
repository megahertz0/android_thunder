package com.xunlei.downloadprovider.model.protocol.b;

import android.content.Context;
import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// compiled from: SDCardManager.java
public final class k {
    static Context a;

    public k(Context context) {
        a = context;
    }

    public static String a(String str) throws IOException {
        if (!a()) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                fileInputStream.close();
                return new String(toByteArray);
            }
        }
    }

    public static boolean a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
