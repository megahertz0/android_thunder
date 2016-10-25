package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

final class n implements b {
    final /* synthetic */ SniffingDetailPageTask a;

    n(SniffingDetailPageTask sniffingDetailPageTask) {
        this.a = sniffingDetailPageTask;
    }

    public final void a(String str, String str2, boolean z) {
        this.a.r.b(SniffingDetailPageTask.p, "GetPage End");
        if (z) {
            this.a.m.f = 2;
            SniffingPageInfo sniffingPageInfo = this.a.m;
            aj ajVar = this.a.b;
            String str3 = BuildConfig.VERSION_NAME;
            if (!TextUtils.isEmpty(str2)) {
                if (ajVar.a == null) {
                    ajVar.a = Pattern.compile("<title>([^<>]+)</title>");
                }
                Matcher matcher = ajVar.a.matcher(str2);
                if (matcher.find()) {
                    MatchResult toMatchResult = matcher.toMatchResult();
                    int start = toMatchResult.start(1);
                    int end = toMatchResult.end(1);
                    if (end >= start && start >= 0) {
                        str3 = str2.substring(start, end).replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace('\n', ' ').replace('\r', ' ').trim();
                    }
                }
            }
            sniffingPageInfo.d = str3;
        }
        this.a.m.c = str2;
        if (!this.a.m.a.equals(str)) {
            this.a.m.b = str;
        }
        this.a.B = MqttConnectOptions.MQTT_VERSION_3_1;
        this.a.a(SniffingTask.h);
    }
}
