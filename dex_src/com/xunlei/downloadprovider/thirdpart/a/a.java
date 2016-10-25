package com.xunlei.downloadprovider.thirdpart.a;

import android.content.Context;
import android.content.Intent;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.launch.LaunchActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadService;

// compiled from: ThirdPartUtil.java
public final class a {
    public static void a(Context context, Intent intent) {
        if (context != null && intent != null) {
            if (DownloadService.a() == null) {
                Intent intent2 = new Intent();
                intent2.setClass(context, LaunchActivity.class);
                intent2.addFlags(268435456);
                intent2.addFlags(67108864);
                if (intent != null) {
                    intent2.putExtra("business_intent", intent);
                }
                context.startActivity(intent2);
                return;
            }
            String stringExtra = intent.getStringExtra(a);
            com.xunlei.downloadprovider.thirdpart.a aVar = null;
            if ("shortcut_download".equals(stringExtra)) {
                BrothersApplication.a().a("launch_from_shortcut_download");
                aVar = new com.xunlei.downloadprovider.download.e.a(context, intent);
            }
            if (aVar != null) {
                aVar.a();
                StatReporter.reportOutsideCall(stringExtra);
            }
        }
    }
}
