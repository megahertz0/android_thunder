package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: PlayRecordListWidget.java
final class n implements OnClickListener {
    final /* synthetic */ CommixturePlayRecord a;
    final /* synthetic */ int b;
    final /* synthetic */ b c;

    n(b bVar, CommixturePlayRecord commixturePlayRecord, int i) {
        this.c = bVar;
        this.a = commixturePlayRecord;
        this.b = i;
    }

    public final void onClick(View view) {
        String c;
        switch (view.getId()) {
            case R.id.cloud_list_item_layout:
                if (this.c.a.e) {
                    switch (AnonymousClass_1.a[this.a.c.ordinal()]) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (PlayRecordListWidget.a(this.c.a, this.a.a.src_url, this.a.a.url_hash)) {
                                PlayRecordListWidget.b(this.c.a, this.a.a.src_url, this.a.a.url_hash);
                            } else {
                                PlayRecordListWidget.c(this.c.a, this.a.a.src_url, this.a.a.url_hash);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (PlayRecordListWidget.t(this.c.a).contains(String.valueOf(this.a.b.a))) {
                                PlayRecordListWidget.t(this.c.a).remove(String.valueOf(this.a.b.a));
                            } else {
                                PlayRecordListWidget.t(this.c.a).add(String.valueOf(this.a.b.a));
                            }
                            break;
                    }
                    if (PlayRecordListWidget.p(this.c.a) != null) {
                        PlayRecordListWidget.p(this.c.a).b();
                    }
                } else {
                    switch (AnonymousClass_1.a[this.a.c.ordinal()]) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (PlayRecordListWidget.a(this.a.a.src_url)) {
                                PlayRecordListWidget.a(this.c.a, this.a.a);
                            } else {
                                PlayRecordListWidget.b(this.c.a, this.a.a);
                                StatReporter.reportOverallPlay("cloud_play_record", "tcloud");
                                StatReporter.reportVodListVodPlay();
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            c = this.a.c();
                            if (c == null || !c.equals("shortVideo")) {
                                PlayRecordListWidget.b(this.c.a, this.a);
                            } else {
                                ShortMovieDetailActivity.a(this.c.a.getContext(), From.PLAY_LIST, this.a.b.p, this.a.b.i, this.a.b.b, this.a.b.k, this.a.b.l);
                            }
                            break;
                        default:
                            break;
                    }
                }
                this.c.notifyDataSetChanged();
            case R.id.cloud_list_item_btn_right:
                switch (AnonymousClass_1.a[this.a.c.ordinal()]) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (PlayRecordListWidget.a(this.a.a.src_url)) {
                            PlayRecordListWidget.a(this.c.a, this.a.a);
                            return;
                        }
                        StatReporter.reportVodListVodPlay();
                        PlayRecordListWidget.b(this.c.a, this.a.a);
                        StatReporter.reportOverallPlay("cloud_play_record", "tcloud");
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        c = this.a.c();
                        if (c == null || !c.equals("shortVideo")) {
                            PlayRecordListWidget.b(this.c.a, this.a);
                        } else {
                            ShortMovieDetailActivity.a(this.c.a.getContext(), From.PLAY_LIST, this.a.b.p, this.a.b.i, this.a.b.b, this.a.b.k, this.a.b.l);
                        }
                    default:
                        break;
                }
            default:
                break;
        }
    }
}
