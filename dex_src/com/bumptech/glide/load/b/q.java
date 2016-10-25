package com.bumptech.glide.load.b;

import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.load.a.c;
import java.io.File;

// compiled from: StringLoader.java
public class q<T> implements m<String, T> {
    private final m<Uri, T> a;

    public final /* synthetic */ c a(Object obj, int i, int i2) {
        String str = (String) obj;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Object a;
        if (str.startsWith("/")) {
            a = a(str);
        } else {
            a = Uri.parse(str);
            if (a.getScheme() == null) {
                a = a(str);
            }
        }
        return this.a.a(a, i, i2);
    }

    public q(m<Uri, T> mVar) {
        this.a = mVar;
    }

    private static Uri a(String str) {
        return Uri.fromFile(new File(str));
    }
}
