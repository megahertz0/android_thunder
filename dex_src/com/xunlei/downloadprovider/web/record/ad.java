package com.xunlei.downloadprovider.web.record;

import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.t;
import com.sina.weibo.sdk.component.GameManager;
import java.io.UnsupportedEncodingException;

// compiled from: RecordServerUtils.java
final class ad extends t {
    final /* synthetic */ String a;

    ad(String str, b bVar, a aVar, String str2) {
        this.a = str2;
        super(1, str, bVar, aVar);
    }

    public final byte[] getBody() throws com.android.volley.a {
        try {
            return this.a == null ? null : this.a.getBytes(GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
