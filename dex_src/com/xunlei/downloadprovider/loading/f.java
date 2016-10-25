package com.xunlei.downloadprovider.loading;

import android.os.Message;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.a.h.a;
import java.io.File;

// compiled from: LoadingActivity.java
final class f implements a {
    final /* synthetic */ LoadingActivity a;

    f(LoadingActivity loadingActivity) {
        this.a = loadingActivity;
    }

    public final void a(Message message) {
        if (message != null) {
            String str = com.umeng.a.d;
            switch (message.what) {
                case 5000:
                    String str2 = LoadingActivity.a;
                    String m = LoadingActivity.m(this.a);
                    if (message.arg1 == 0 && (message.obj instanceof h)) {
                        h hVar = (h) message.obj;
                        String str3 = LoadingActivity.a;
                        new StringBuilder("\u8282\u5047\u65e5 tempData: ").append(hVar == null ? "null" : hVar.toString());
                        String str4 = hVar.c;
                        File a = d.a().c().a(str4);
                        if (a != null) {
                            str3 = a.getAbsolutePath();
                        } else {
                            str3 = str;
                        }
                        k.a(hVar, str3, m);
                        d.a().a(str4, this.a.options, null);
                        return;
                    }
                    k.a(LoadingActivity.m(this.a));
                default:
                    break;
            }
        }
    }
}
