package com.xunlei.downloadprovider.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import java.io.File;

public class ApkReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getBooleanExtra("isDoneListNoti", false)) {
            a.a(context);
            a.a();
            return;
        }
        a.a(context);
        if (a.b() == 1) {
            a.a(context);
            a.a();
        }
        String stringExtra = intent.getStringExtra("filePath");
        if (stringExtra != null) {
            try {
                Uri fromFile = Uri.fromFile(new File(stringExtra));
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setDataAndType(fromFile, "application/vnd.android.package-archive");
                intent2.setFlags(268435456);
                context.startActivity(intent2);
            } catch (Exception e) {
                new StringBuilder("open fail ").append(e.getMessage());
            }
        }
        StatReporter.reportNotiClick("oneFinish");
    }
}
