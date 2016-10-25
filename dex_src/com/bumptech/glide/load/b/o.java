package com.bumptech.glide.load.b;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.load.a.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ResourceLoader.java
public class o<T> implements m<Integer, T> {
    private final m<Uri, T> a;
    private final Resources b;

    public o(Context context, m<Uri, T> mVar) {
        this(context.getResources(), (m) mVar);
    }

    private o(Resources resources, m<Uri, T> mVar) {
        this.b = resources;
        this.a = mVar;
    }

    private c<T> a(Integer num, int i, int i2) {
        Object parse;
        try {
            parse = Uri.parse(new StringBuilder("android.resource://").append(this.b.getResourcePackageName(num.intValue())).append('/').append(this.b.getResourceTypeName(num.intValue())).append('/').append(this.b.getResourceEntryName(num.intValue())).toString());
        } catch (NotFoundException e) {
            if (Log.isLoggable("ResourceLoader", XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)) {
                new StringBuilder("Received invalid resource id: ").append(num);
            }
            parse = null;
        }
        return parse != null ? this.a.a(parse, i, i2) : null;
    }
}
