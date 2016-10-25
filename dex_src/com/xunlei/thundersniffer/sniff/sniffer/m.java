package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;

final class m implements a {
    final /* synthetic */ SniffingDetailPageTask a;

    m(SniffingDetailPageTask sniffingDetailPageTask) {
        this.a = sniffingDetailPageTask;
    }

    public final void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            this.a.m.b = str;
        }
        if (TextUtils.isEmpty(this.a.m.b)) {
            this.a.m.b = this.a.m.a;
        }
        if (z) {
            new StringBuilder("Timeout url = ").append(this.a.m.b);
            this.a.a.a(this.a.m.a, BuildConfig.VERSION_NAME, false);
            return;
        }
        SniffingDetailPageTask.a(this.a, this.a.m.b, this.a.a);
    }
}
