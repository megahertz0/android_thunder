package com.xunlei.downloadprovider.loading;

import android.graphics.Bitmap;
import android.text.TextUtils;

// compiled from: LoadingData.java
public final class h {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public boolean m;
    public Bitmap n;

    public final String toString() {
        return new StringBuilder("LoadingData [haveImg=").append(this.a).append(", id=").append(this.b).append(", imgUrl=").append(this.c).append(", downloadUrl=").append(this.d).append(", startDate=").append(this.e).append(", endDate=").append(this.f).append(", skipButtonText=").append(this.g).append(", skip=").append(this.h).append(", skipTitle=").append(this.i).append(", skipUrl=").append(this.j).append(", disPlayTimes=").append(this.k).append(", duration=").append(this.l).append(", available=").append(this.m).append(", bitmap=").append(this.n).append("]").toString();
    }

    public final void a() {
        try {
            Object obj = this.e;
            Object obj2 = this.f;
            if (!TextUtils.isEmpty(obj) && !TextUtils.isEmpty(obj2)) {
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                boolean z = currentTimeMillis >= Long.parseLong(obj) && currentTimeMillis <= Long.parseLong(obj2);
                this.m = z;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
