package com.bumptech.glide.load.b;

import android.text.TextUtils;
import java.net.URL;

// compiled from: GlideUrl.java
public class e {
    public final URL a;
    public final f b;
    public final String c;
    public String d;
    public URL e;

    public e(URL url) {
        this(url, f.a);
    }

    public e(String str) {
        this(str, f.a);
    }

    private e(URL url, f fVar) {
        if (url == null) {
            throw new IllegalArgumentException("URL must not be null!");
        } else if (fVar == null) {
            throw new IllegalArgumentException("Headers must not be null");
        } else {
            this.a = url;
            this.c = null;
            this.b = fVar;
        }
    }

    private e(String str, f fVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(new StringBuilder("String url must not be empty or null: ").append(str).toString());
        } else if (fVar == null) {
            throw new IllegalArgumentException("Headers must not be null");
        } else {
            this.c = str;
            this.a = null;
            this.b = fVar;
        }
    }

    public final String a() {
        return this.c != null ? this.c : this.a.toString();
    }

    public String toString() {
        return a() + '\n' + this.b.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return a().equals(eVar.a()) && this.b.equals(eVar.b);
    }

    public int hashCode() {
        return (a().hashCode() * 31) + this.b.hashCode();
    }
}
