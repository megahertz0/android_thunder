package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.xunlei.downloadprovider.util.b.a;
import com.xunlei.downloadprovider.util.b.c;

// compiled from: PlayRecordListWidget.java
final class e implements OnCancelListener {
    final /* synthetic */ PlayRecordListWidget a;

    e(PlayRecordListWidget playRecordListWidget) {
        this.a = playRecordListWidget;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        c a = c.a();
        Object C = PlayRecordListWidget.C(this.a);
        if (C != null && a.a.containsKey(C)) {
            a aVar = (a) a.a.remove(C);
            if (aVar != null) {
                aVar.mCancel = true;
            }
        }
    }
}
