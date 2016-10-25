package com.a.a.c;

import android.text.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.NameValuePair;

// compiled from: URIBuilder.java
public final class a {
    public String a;
    public List<NameValuePair> b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;

    public a() {
        this.h = -1;
    }

    public a(String str) {
        try {
            List list;
            URI uri = new URI(str);
            this.c = uri.getScheme();
            this.d = uri.getRawSchemeSpecificPart();
            this.e = uri.getRawAuthority();
            this.a = uri.getHost();
            this.h = uri.getPort();
            this.g = uri.getRawUserInfo();
            this.f = uri.getUserInfo();
            this.j = uri.getRawPath();
            this.i = uri.getPath();
            this.k = uri.getRawQuery();
            Object rawQuery = uri.getRawQuery();
            if (TextUtils.isEmpty(rawQuery)) {
                list = null;
            } else {
                list = b.a(rawQuery);
            }
            this.b = list;
            this.m = uri.getRawFragment();
            this.l = uri.getFragment();
        } catch (URISyntaxException e) {
        }
    }
}
