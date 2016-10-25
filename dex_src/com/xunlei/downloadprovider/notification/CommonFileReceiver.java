package com.xunlei.downloadprovider.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.message.proguard.k;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;

public class CommonFileReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("com.xunlei.action.COMMON_FILE_CLICK")) {
            Intent intent2 = new Intent();
            intent2.putExtra(k.l, intent.getLongExtra(k.l, 0));
            intent2.putExtra("from", "from_done_noti");
            intent2.setClass(context, DownloadCenterActivity.class);
            intent2.setFlags(268435456);
            context.startActivity(intent2);
            a.a(context);
            a.a();
        }
        if (action.equals("com.xunlei.action.COMMON_MERGE_FILES_CLICK")) {
            intent2 = new Intent();
            intent2.putExtra(k.l, 8000);
            intent2.putExtra("from", "from_done_noti");
            intent2.setClass(context, DownloadCenterActivity.class);
            intent2.setFlags(268435456);
            context.startActivity(intent2);
            a.a(context);
            a.a();
        }
        if (action.equals("com.xunlei.action.COMMON_DELETE_NOTI_CLICK")) {
            a.a(context);
            a.a();
        }
    }
}
