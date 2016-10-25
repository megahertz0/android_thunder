package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord;
import com.xunlei.downloadprovider.vod.b.b;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: PlayRecordListWidget.java
final class d implements OnClickListener {
    final /* synthetic */ CommixturePlayRecord a;
    final /* synthetic */ PlayRecordListWidget b;

    d(PlayRecordListWidget playRecordListWidget, CommixturePlayRecord commixturePlayRecord) {
        this.b = playRecordListWidget;
        this.a = commixturePlayRecord;
    }

    public final void onClick(View view) {
        PlayRecordListWidget.z(this.b);
        switch (AnonymousClass_1.a[this.a.c.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                PlayRecordListWidget.t(this.b).clear();
                PlayRecordListWidget.A(this.b).clear();
                PlayRecordListWidget.B(this.b).clear();
                PlayRecordListWidget.c(this.b, this.a.a.src_url, this.a.a.url_hash);
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                PlayRecordListWidget.t(this.b).clear();
                PlayRecordListWidget.A(this.b).clear();
                PlayRecordListWidget.B(this.b).clear();
                PlayRecordListWidget.t(this.b).add(String.valueOf(this.a.b.a));
                String c = this.a.c();
                if (c != null && c.equals("shortVideo")) {
                    b.a().c(this.a.b.p);
                }
                break;
        }
        if (PlayRecordListWidget.p(this.b) != null) {
            PlayRecordListWidget.p(this.b).a();
        }
        if (PlayRecordListWidget.y(this.b) != null) {
            PlayRecordListWidget.y(this.b).dismiss();
        }
    }
}
