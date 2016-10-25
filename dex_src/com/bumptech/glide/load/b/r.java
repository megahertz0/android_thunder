package com.bumptech.glide.load.b;

import android.content.Context;
import android.net.Uri;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.b;
import com.bumptech.glide.load.a.c;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;

// compiled from: UriLoader.java
public abstract class r<T> implements m<Uri, T> {
    private final Context a;
    private final m<e, T> b;

    public abstract c<T> a(Context context, Uri uri);

    public abstract c<T> a(Context context, String str);

    public final /* synthetic */ c a(Object obj, int i, int i2) {
        Object obj2;
        Uri uri = (Uri) obj;
        String scheme = uri.getScheme();
        if ("file".equals(scheme) || ParamKey.CONTENT.equals(scheme) || "android.resource".equals(scheme)) {
            obj2 = 1;
        } else {
            obj2 = null;
        }
        if (obj2 != null) {
            if (!a.a(uri)) {
                return a(this.a, uri);
            }
            return a(this.a, a.b(uri));
        } else if (this.b != null) {
            return (HttpConstant.HTTP.equals(scheme) || b.a.equals(scheme)) ? this.b.a(new e(uri.toString()), i, i2) : null;
        } else {
            return null;
        }
    }

    public r(Context context, m<e, T> mVar) {
        this.a = context;
        this.b = mVar;
    }
}
