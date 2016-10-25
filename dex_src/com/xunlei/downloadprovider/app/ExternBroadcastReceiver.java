package com.xunlei.downloadprovider.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.tdlive.R;

public class ExternBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.xunlei.downloadprovider.app.ExternBroadcast")) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.size() != 0) {
                String string = extras.getString("fileName");
                String string2 = extras.getString("fileUrl");
                extras.getString("mainApkId");
                extras.getString("subApkId");
                if (extras.getBoolean("showTaskList")) {
                    Intent intent2 = new Intent();
                    intent2.setClassName("com.xunlei.downloadprovider", "com.xunlei.downloadprovider.app.MainActivity");
                    intent2.addFlags(268435456);
                    Bundle bundle = new Bundle();
                    bundle.putString("name_goto_page", "com.xunlei.downloadprovider.app.HomePageActivity");
                    intent2.putExtras(bundle);
                    BrothersApplication.a.startActivity(intent2);
                }
                new StringBuilder("fileName = ").append(string).append(" fileUrl = ").append(string2);
                if (DownloadService.a() == null) {
                    DownloadService.a(new j(this, string2, string));
                } else {
                    DownloadService.a().a(string2, string, (int) R.styleable.Toolbar_titleTextColor, "BHO/BHO", BrothersApplication.a().e);
                }
            }
        }
    }
}
