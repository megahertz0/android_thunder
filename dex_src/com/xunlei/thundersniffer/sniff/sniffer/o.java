package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.android.volley.r.b;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class o implements b<String> {
    final /* synthetic */ String a;
    final /* synthetic */ b b;
    final /* synthetic */ long c;
    final /* synthetic */ SniffingDetailPageTask d;

    o(SniffingDetailPageTask sniffingDetailPageTask, String str, b bVar, long j) {
        this.d = sniffingDetailPageTask;
        this.a = str;
        this.b = bVar;
        this.c = j;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = null;
        String str2 = (String) obj;
        new StringBuilder("onResponse url = ").append(this.a);
        SniffingDetailPageTask.b(this.d);
        if (!this.d.x) {
            this.d.w = true;
            if (this.b != null) {
                if (!(str2 == null || str2.length() > 10240 || TextUtils.isEmpty(str2))) {
                    Matcher matcher = Pattern.compile("<meta\\shttp-equiv=\"refresh\"\\scontent=\"[^\"]+;\\surl=(http://[^\"\\s]+)\"").matcher(str2);
                    if (matcher.find()) {
                        MatchResult toMatchResult = matcher.toMatchResult();
                        str = str2.substring(toMatchResult.start(1), toMatchResult.end(1));
                        if (!TextUtils.isEmpty(str)) {
                            str = str.replace("&amp;", "&");
                        }
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    new StringBuilder("spend_time_1 --> ").append(System.currentTimeMillis() - this.c);
                    this.b.a(this.a, str2, true);
                    return;
                }
                new StringBuilder("JumpPage: ").append(str).append(" from: ").append(this.a);
                SniffingDetailPageTask.b(this.d, str, this.b);
            }
        }
    }
}
