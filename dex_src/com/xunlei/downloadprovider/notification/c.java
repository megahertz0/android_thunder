package com.xunlei.downloadprovider.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.d;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadNotification.java
final class c extends BroadcastReceiver {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("accelerate_this_time")) {
            new StringBuilder("DownloadNotification---onClickReceiver---").append(Thread.currentThread().getId());
            if (intent.hasExtra("bar_or_button")) {
                StatReporter.reportNotiAccelerateButton("ThisTimeAccelerate");
                a.a("download_in", "in_vip_speedup");
            }
            new RemoteViews(a.i(this.a).getPackageName(), 2130968887).setViewVisibility(R.id.noti_accelerate_now_vip, SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.a.j = true;
            try {
                d.a();
                d.h();
                if (a.c() && a.i(this.a) != null) {
                    a.i(this.a).startActivity(a.b(a.i(this.a)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (intent.getAction().equals("bxbb_click")) {
            if (intent.hasExtra("bxbb_task")) {
                a.a("download_in_bxbb", "in_bxbb");
            }
            try {
                a.a(this.a, (TaskInfo) intent.getSerializableExtra("bxbb_task"), "from_bxbb_noti");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (intent.getAction().equals("bxbb_bar_click")) {
            try {
                a.a(this.a, (TaskInfo) intent.getSerializableExtra("bxbb_task_bar"), "from_bxbb_noti");
            } catch (Exception e22) {
                e22.printStackTrace();
            }
        }
    }
}
