package com.android.volley.toolbox;

import android.graphics.Bitmap;
import com.android.volley.r.b;

// compiled from: ImageLoader.java
final class j implements b<Bitmap> {
    final /* synthetic */ String a;
    final /* synthetic */ i b;

    j(i iVar, String str) {
        this.b = iVar;
        this.a = str;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        i iVar = this.b;
        String str = this.a;
        iVar.b.a(str, bitmap);
        a aVar = (a) iVar.c.remove(str);
        if (aVar != null) {
            aVar.a = bitmap;
            iVar.a(str, aVar);
        }
    }
}
