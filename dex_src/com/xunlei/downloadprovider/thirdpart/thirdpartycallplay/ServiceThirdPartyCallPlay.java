package com.xunlei.downloadprovider.thirdpart.thirdpartycallplay;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.vod.a.c;
import com.xunlei.downloadprovider.web.w;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.security.Key;
import javax.crypto.Cipher;

public class ServiceThirdPartyCallPlay extends Service {
    String a;

    public ServiceThirdPartyCallPlay() {
        this.a = a.d;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String str = null;
        int i3 = 0;
        if (intent == null) {
            return super.onStartCommand(intent, i, i2);
        }
        byte[] byteArrayExtra;
        int i4;
        String str2;
        String str3 = a.d;
        String str4 = a.d;
        String str5 = a.d;
        if (intent.hasExtra(SocialConstants.PARAM_URL) && intent.getStringExtra(SocialConstants.PARAM_URL) != null) {
            str3 = intent.getStringExtra(SocialConstants.PARAM_URL).toString();
        }
        if (intent.hasExtra("url_decode") && intent.getStringExtra("url_decode") != null) {
            intent.getStringExtra("url_decode").toString();
        }
        if (intent.hasExtra(WebBrowserActivity.EXTRA_TITLE) && intent.getStringExtra(WebBrowserActivity.EXTRA_TITLE) != null) {
            str4 = intent.getStringExtra(WebBrowserActivity.EXTRA_TITLE).toString();
        }
        if (intent.hasExtra(LogBuilder.KEY_CHANNEL)) {
            try {
                byteArrayExtra = intent.getByteArrayExtra(LogBuilder.KEY_CHANNEL);
            } catch (Exception e) {
            }
            this.a = a(byteArrayExtra);
            if ((this.a).equals("uc")) {
                i4 = 0;
            } else {
                i4 = 1;
            }
            if (intent.hasExtra("past_play_time") || intent.getStringExtra("past_play_time") == null) {
                str2 = str5;
            } else {
                str2 = intent.getStringExtra("past_play_time").toString();
            }
            i3 = Integer.parseInt(str2);
            if (intent.hasExtra("package_name") && intent.getStringExtra("package_name") != null) {
                str = intent.getStringExtra("package_name").toString();
            }
            w.a();
            w.a();
            w.b(w.a("UCCall", "play", str4, str3));
            StatReporter.reportOverallPlay("uc_play", "tcloud");
            c.a(new com.xunlei.downloadprovider.vod.a.a(str3, str4, i4, i3, str));
            StatReporter.reportThirdPartyCallPlay(this.a);
            return super.onStartCommand(intent, i, i2);
        }
        byteArrayExtra = null;
        this.a = a(byteArrayExtra);
        if ((this.a).equals("uc")) {
            i4 = 0;
        } else {
            i4 = 1;
        }
        if (intent.hasExtra("past_play_time")) {
        }
        str2 = str5;
        try {
            i3 = Integer.parseInt(str2);
        } catch (Exception e2) {
        }
        str = intent.getStringExtra("package_name").toString();
        w.a();
        w.a();
        w.b(w.a("UCCall", "play", str4, str3));
        StatReporter.reportOverallPlay("uc_play", "tcloud");
        c.a(new com.xunlei.downloadprovider.vod.a.a(str3, str4, i4, i3, str));
        StatReporter.reportThirdPartyCallPlay(this.a);
        return super.onStartCommand(intent, i, i2);
    }

    private static String a(byte[] bArr) {
        try {
            com.xunlei.downloadprovider.f.a aVar = new com.xunlei.downloadprovider.f.a();
            Key a = com.xunlei.downloadprovider.f.a.a(com.xunlei.downloadprovider.f.a.a, com.xunlei.downloadprovider.f.a.c);
            Cipher instance = Cipher.getInstance("RSA");
            instance.init(XZBDevice.DOWNLOAD_LIST_RECYCLE, a);
            return new String(instance.doFinal(bArr));
        } catch (Exception e) {
            return null;
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
