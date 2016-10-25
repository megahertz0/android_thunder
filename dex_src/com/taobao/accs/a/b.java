package com.taobao.accs.a;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.a.a.a;
import com.taobao.accs.utl.ALog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

// compiled from: Taobao
final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    b(Context context, a aVar) {
        this.a = context;
        this.b = aVar;
    }

    public final void run() {
        Throwable th;
        FileOutputStream fileOutputStream = null;
        try {
            if (a.d() == null) {
                a.a();
            }
            if (a.d() != null) {
                a.d().mkdirs();
            }
            ALog.i(a.TAG, "saveElectionResult electionFile", "path", a.e().getPath(), com.taobao.accs.internal.b.ELECTION_KEY_HOST, this.b.a, "retry", Integer.valueOf(this.b.b));
            FileOutputStream fileOutputStream2 = new FileOutputStream(a.e());
            try {
                Map hashMap = new HashMap();
                hashMap.put("package", TextUtils.isEmpty(this.b.a) ? com.umeng.a.d : this.b.a);
                hashMap.put("retry", this.b.b);
                if (a.f() <= 0 || a.f() >= System.currentTimeMillis()) {
                    hashMap.put("lastFlushTime", System.currentTimeMillis());
                } else {
                    hashMap.put("lastFlushTime", a.f());
                }
                fileOutputStream2.write(new JSONObject(hashMap).toString().getBytes(GameManager.DEFAULT_CHARSET));
                try {
                    fileOutputStream2.close();
                } catch (Throwable th2) {
                }
            } catch (IOException e) {
                fileOutputStream = fileOutputStream2;
                try {
                    a.a(new File(this.a.getFilesDir().getPath() + a.c()));
                    ALog.i(a.TAG, new StringBuilder("path invailable, new path=").append(a.d()).toString(), new Object[0]);
                    a.b(new File(a.d(), com.taobao.accs.internal.b.ELECTION_SERVICE_ID));
                    a.a = a.e().getPath();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th3) {
                }
            } catch (Throwable th4) {
                th = th4;
                try {
                    ALog.e(a.TAG, new StringBuilder("saveElectionResult is error,e=").append(th.toString()).toString(), new Object[0]);
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable th5) {
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable th7) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e2) {
            a.a(new File(this.a.getFilesDir().getPath() + a.c()));
            ALog.i(a.TAG, new StringBuilder("path invailable, new path=").append(a.d()).toString(), new Object[0]);
            a.b(new File(a.d(), com.taobao.accs.internal.b.ELECTION_SERVICE_ID));
            a.a = a.e().getPath();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th8) {
            r8 = th8;
            fileOutputStream2 = null;
            th = r8;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }
}
