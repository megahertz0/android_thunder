package com.xunlei.downloadprovider.filemanager.ui;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.filemanager.model.i;
import java.util.List;

// compiled from: FileDetailsWindow.java
final class c implements a {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void a(Message message) {
        long j = 0;
        if (message.what == com.xunlei.downloadprovider.filemanager.a.a.a) {
            for (i iVar : (List) message.obj) {
                j = iVar.i + j;
            }
            a.b(this.a).setText(com.xunlei.downloadprovider.d.a.b(j));
        } else if (message.what == a.a()) {
            for (i iVar2 : (List) message.obj) {
                j += iVar2.i;
            }
            a.b(this.a).setText(com.xunlei.downloadprovider.d.a.b(j));
        }
    }
}
