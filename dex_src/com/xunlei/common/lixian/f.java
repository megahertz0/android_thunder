package com.xunlei.common.lixian;

import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

final class f extends Handler {
    private FileOutputStream a;
    private File b;

    public f() {
        this.a = null;
        this.b = null;
        a();
    }

    private boolean a() {
        if (e.a()) {
            File b = e.b();
            if (!b.exists()) {
                b.mkdirs();
            }
            this.b = new File(b.getAbsolutePath() + "/log.txt");
            if (!this.b.exists()) {
                try {
                    this.b.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    this.b = null;
                    return false;
                }
            }
            return true;
        }
        this.b = null;
        return false;
    }

    public final void handleMessage(Message message) {
        if (this.b != null) {
            try {
                if (this.a == null) {
                    this.a = new FileOutputStream(this.b, true);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                if (!a()) {
                }
            }
            if (this.a != null) {
                String str = ((String) message.obj) + "\n\n";
                if (str != null) {
                    byte[] bytes = str.getBytes();
                    try {
                        this.a.write(bytes, 0, bytes.length);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        this.a = null;
                    }
                }
            }
        }
    }
}
