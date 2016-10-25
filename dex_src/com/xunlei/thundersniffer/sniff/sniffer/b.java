package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.thundersniffer.sniff.sniffer.ResLinkParser.FoundCallback;

final class b implements FoundCallback {
    final /* synthetic */ aa a;

    b(aa aaVar) {
        this.a = aaVar;
    }

    public final void onFound(String str, int i, String str2) {
        aa aaVar = this.a;
        z zVar = new z(str);
        if (!str.startsWith("thunder://") || !TextUtils.isEmpty(com.xunlei.c.b.i(str))) {
            zVar.e = str2;
            aaVar.a(zVar);
        }
    }
}
