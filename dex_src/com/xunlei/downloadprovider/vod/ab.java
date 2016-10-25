package com.xunlei.downloadprovider.vod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

// compiled from: VodPlayerActivity.java
final class ab implements Runnable {
    final /* synthetic */ File a;
    final /* synthetic */ VodPlayerActivity b;

    ab(VodPlayerActivity vodPlayerActivity, File file) {
        this.b = vodPlayerActivity;
        this.a = file;
    }

    public final void run() {
        File file = new File(this.a, "pluginCdn.apk");
        try {
            if (!file.exists()) {
                InputStream open = this.b.getAssets().open("xldlnapass.jar");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = open.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.close();
                        fileOutputStream.flush();
                        open.close();
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
