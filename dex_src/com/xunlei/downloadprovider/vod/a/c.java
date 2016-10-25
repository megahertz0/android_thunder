package com.xunlei.downloadprovider.vod.a;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.loading.LoadingActivity;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.vod.VodPlayerForBtActivity;
import com.xunlei.downloadprovider.vod.VodUtil;

// compiled from: CooperationUtil.java
public class c {
    private static final String a;

    static {
        a = c.class.getSimpleName();
    }

    public static void a(a aVar) {
        if (DownloadService.a() == null) {
            Bundle bundle;
            Intent intent = new Intent();
            intent.setClass(BrothersApplication.a(), LoadingActivity.class);
            intent.addFlags(268435456);
            intent.addFlags(67108864);
            if (aVar == null) {
                bundle = null;
            } else {
                bundle = new Bundle();
                bundle.putString("cooperation_url", aVar.a);
                bundle.putString("cooperation_decode_url", aVar.b);
                bundle.putString("cooperation_title", aVar.c);
                bundle.putInt("cooperation_type", aVar.d);
                bundle.putInt("cooperation_play_time", aVar.e);
            }
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            BrothersApplication.a().startActivity(intent);
            return;
        }
        Object obj;
        String str = aVar.a;
        if (str.isEmpty() || !str.endsWith(".torrent")) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            aVar.b = aVar.a;
            VodUtil.a();
            VodUtil.a(aVar);
        } else if (TextUtils.isEmpty(aVar.b)) {
            Intent intent2 = new Intent(BrothersApplication.a(), VodPlayerForBtActivity.class);
            intent2.putExtra("cooperation_url", aVar.a);
            intent2.putExtra("cooperation_caller_packagename", aVar.g);
            intent2.setFlags(268435456);
            intent2.addFlags(67108864);
            BrothersApplication.a().startActivity(intent2);
        } else {
            VodUtil.a();
            VodUtil.a(aVar);
        }
    }

    public static a a(Intent intent) {
        if (intent == null) {
            return null;
        }
        a aVar;
        int intExtra = intent.getIntExtra("cooperation_type", 0);
        if (b.a(intExtra)) {
            String stringExtra = intent.getStringExtra("cooperation_url");
            intent.getStringExtra("cooperation_decode_url");
            aVar = new a(stringExtra, intent.getStringExtra("cooperation_title"), intExtra, intent.getIntExtra("cooperation_play_time", 0), null);
        } else {
            aVar = null;
        }
        return aVar;
    }

    public static String a(int i) {
        return i == 1 ? "uc" : a.d;
    }

    public static int a(String str) {
        return "uc".equals(str) ? 1 : 0;
    }
}
