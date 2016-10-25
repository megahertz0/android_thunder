package com.baidu.mobads.h;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.mobads.h.g.a;

class i extends Handler {
    final /* synthetic */ g a;

    i(g gVar, Looper looper) {
        this.a = gVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        e eVar = (e) message.getData().getParcelable("APK_INFO");
        if ("OK".equals(message.getData().getString("CODE"))) {
            b bVar = new b(eVar.e(), this.a.k, eVar);
            try {
                if (this.a.f == g.e) {
                    bVar.a();
                    bVar.a(this.a.e());
                    if (g.b != null) {
                        g.b.a = eVar.b();
                    }
                } else {
                    this.a.a(bVar);
                    bVar.a(this.a.e());
                    this.a.a(true);
                }
                bVar.delete();
                return;
            } catch (a e) {
                String toString = new StringBuilder("download apk file failed: ").append(e.toString()).toString();
                this.a.a(false);
                this.a.l.e("XAdApkLoader", toString);
                bVar.delete();
            }
        }
        this.a.l.e("XAdApkLoader", new StringBuilder("mOnApkDownloadCompleted: download failed, code: ").append(r1).toString());
        this.a.a(false);
    }
}
