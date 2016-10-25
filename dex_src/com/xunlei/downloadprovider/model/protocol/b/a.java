package com.xunlei.downloadprovider.model.protocol.b;

import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import org.json.JSONException;

// compiled from: DownloadJsonData.java
public class a {
    public static long a;
    private static final String b;
    private static j c;
    private static b d;

    // compiled from: DownloadJsonData.java
    static class a implements Runnable {
        private Handler a;
        private String b;
        private long c;
        private Object d;
        private int e;

        public a(Handler handler, String str, long j, int i, Object obj) {
            this.a = handler;
            this.b = str;
            this.c = j;
            this.e = i;
            this.d = obj;
        }

        public final void run() {
            try {
                if (f.b(this.b, String.valueOf(this.c))) {
                    c = f.a(this.b, String.valueOf(this.c));
                    Message obtainMessage = this.a.obtainMessage();
                    if (c != null) {
                        b;
                        c.a = this.b;
                        c.c = this.c;
                        c.b = this.b;
                        c.d = this.c;
                        obtainMessage.obj = c;
                        obtainMessage.what = 1000;
                        this.a.sendMessage(obtainMessage);
                        return;
                    }
                    b;
                    d.a(this.a, this.b, this.c, this.e, this.d);
                    return;
                }
                b;
                d.a(this.a, this.b, this.c, this.e, this.d);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    // compiled from: DownloadJsonData.java
    private static interface b {
        void a(Handler handler, String str, long j, int i, Object obj);
    }

    static {
        b = a.class.getSimpleName();
        c = null;
        d = new b();
        a = 0;
    }

    public static void a(Handler handler, String str, long j, int i, Object obj) {
        a = System.currentTimeMillis();
        new StringBuilder("start download JSONData --> ").append(a);
        new Thread(new a(handler, str, j, i, obj)).start();
    }
}
