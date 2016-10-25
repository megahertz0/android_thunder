package com.taobao.accs.a;

import android.content.Context;
import com.taobao.accs.utl.ALog;
import java.io.File;

// compiled from: Taobao
final class c implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ byte[] b;
    final /* synthetic */ String c;

    c(Context context, byte[] bArr, String str) {
        this.a = context;
        this.b = bArr;
        this.c = str;
    }

    public final void run() {
        try {
            if (a.d() == null) {
                a.a();
            }
            a.d().mkdirs();
            com.taobao.accs.utl.c.a(this.b, new File(this.c));
        } catch (Throwable th) {
            ALog.e(a.TAG, "saveBlackList", th, new Object[0]);
        }
    }
}
