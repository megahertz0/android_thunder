package com.xunlei.downloadprovidercommon.b.b;

import android.net.Uri;
import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// compiled from: SignatureUrlBuilder.java
public final class c {
    protected String a;
    protected String b;
    protected String c;
    private final b d;
    private final String e;

    public c(String str, String str2, String str3) {
        this.d = new b("3c661997dc341cbafa661e7b7dca3d90");
        this.b = null;
        this.c = BuildConfig.VERSION_NAME;
        this.c = str.toUpperCase();
        this.e = str2;
        this.a = this.e;
        if (this.e.contains("?")) {
            this.a = this.e.substring(0, this.e.indexOf("?"));
        }
        this.b = str3;
    }

    public final String a() {
        return (String) a(this.c, this.b).get(0);
    }

    public final String b() {
        return (String) a(this.c, this.b).get(1);
    }

    public final String c() {
        return !TextUtils.isEmpty(this.b) ? this.b : BuildConfig.VERSION_NAME;
    }

    private ArrayList<a> d() {
        ArrayList<a> arrayList = new ArrayList();
        Uri parse = Uri.parse(this.e);
        Set<String> queryParameterNames = parse.getQueryParameterNames();
        if (!(queryParameterNames == null || queryParameterNames.isEmpty())) {
            for (String str : queryParameterNames) {
                String queryParameter = parse.getQueryParameter(str);
                if (queryParameter != null) {
                    arrayList.add(new a(str, queryParameter));
                }
            }
        }
        return arrayList;
    }

    private ArrayList<String> a(String str, String str2) {
        String str3 = this.a;
        ArrayList<String> arrayList = new ArrayList();
        long a = b.a();
        String b = b.b();
        List d = d();
        d.add(new a("timestamp", String.valueOf(a)));
        d.add(new a("nonce", b));
        d.add(new a("accesskey", "android.m.xunlei"));
        String a2 = b.a(str, str3, d, str2 == null ? BuildConfig.VERSION_NAME : str2);
        b bVar = this.d;
        if (str2 == null) {
            str2 = BuildConfig.VERSION_NAME;
        }
        arrayList.add(this.e + (this.e.contains("?") ? "&" : "?") + new StringBuilder("timestamp=").append(String.valueOf(a)).append("&nonce=").append(b).append("&accesskey=android.m.xunlei&sig=").append(bVar.b(str, str3, d, str2)).toString());
        arrayList.add(a2);
        return arrayList;
    }
}
