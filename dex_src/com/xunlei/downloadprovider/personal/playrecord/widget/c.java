package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: PlayRecordListWidget.java
final class c implements OnClickListener {
    final /* synthetic */ CommixturePlayRecord a;
    final /* synthetic */ PlayRecordListWidget b;

    c(PlayRecordListWidget playRecordListWidget, CommixturePlayRecord commixturePlayRecord) {
        this.b = playRecordListWidget;
        this.a = commixturePlayRecord;
    }

    public final void onClick(View view) {
        PlayRecordListWidget.d(this.b, this.a);
        switch (this.a.b()) {
            case SimpleLog.LOG_LEVEL_TRACE:
                StatReporter.reportVodListVodDownload();
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                StatReporter.reportOnlineListVodDownload();
                break;
        }
        if (PlayRecordListWidget.y(this.b) != null) {
            PlayRecordListWidget.y(this.b).dismiss();
        }
    }
}
