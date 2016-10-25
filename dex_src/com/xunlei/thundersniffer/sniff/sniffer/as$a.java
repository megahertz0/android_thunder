package com.xunlei.thundersniffer.sniff.sniffer;

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class as$a {
    FileOutputStream a;
    File b;

    public as$a() {
        this.a = null;
        this.b = null;
        a();
    }

    final boolean a() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/xunlei/log");
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            this.b = new File(file.getAbsolutePath() + "/SniffingLog.SDK" + ".log");
            if (!this.b.exists()) {
                try {
                    this.b.createNewFile();
                } catch (IOException e) {
                    this.b = null;
                    return false;
                }
            }
            return true;
        }
        this.b = null;
        return false;
    }
}
