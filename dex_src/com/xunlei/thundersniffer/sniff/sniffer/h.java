package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.android.volley.r.b;
import com.xunlei.xiazaibao.BuildConfig;

final class h implements b<String> {
    final /* synthetic */ SnifferSvrCheckWordOperation a;

    h(SnifferSvrCheckWordOperation snifferSvrCheckWordOperation) {
        this.a = snifferSvrCheckWordOperation;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        SnifferSvrCheckWordOperation.a(this.a, false);
        if (!TextUtils.isEmpty(str)) {
            if (str.contains("\n")) {
                obj = str.replace("\n", BuildConfig.VERSION_NAME);
            }
            if (Boolean.valueOf(obj.trim()).booleanValue()) {
                SnifferSvrCheckWordOperation.a(this.a, true);
            }
        }
        this.a.finish();
    }
}
