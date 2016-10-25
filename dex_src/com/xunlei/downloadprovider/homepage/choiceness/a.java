package com.xunlei.downloadprovider.homepage.choiceness;

import android.widget.ImageView;
import com.bumptech.glide.e;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nostra13.universalimageloader.core.b.b;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.tdlive.R;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// compiled from: ChoicenessUtils.java
public final class a {
    private static c a;

    public static String a(int i) {
        if (i < 10000) {
            return String.valueOf(i);
        }
        String format = new DecimalFormat("#.0").format((double) (((float) i) / 10000.0f));
        if (format.charAt(format.length() - 1) == '0') {
            format = format.substring(0, format.length() - 2);
        }
        return format + "\u4e07";
    }

    public static void a(String str, ImageView imageView) {
        int i;
        if (a == null) {
            a = a();
        }
        c cVar = a;
        int id = imageView.getId();
        if (id <= 0) {
            i = 2131755129;
        } else {
            i = id;
        }
        Object tag = imageView.getTag(i);
        if (tag == null || !(tag instanceof String) || !((String) tag).equals(str)) {
            d.a().a(imageView);
            d.a().a(str, imageView, cVar, new c(imageView, i));
        }
    }

    public static void a(String str, ImageView imageView, int i, int i2) {
        a(str, str, imageView, i, i2);
    }

    public static void a(String str, String str2, ImageView imageView, int i, int i2) {
        if (a == null) {
            a = a();
        }
        c cVar = a;
        int id = imageView.getId();
        if (id <= 0) {
            id = R.id.icon;
        }
        Object tag = imageView.getTag(id);
        if (tag == null || !(tag instanceof String) || !((String) tag).equals(str)) {
            d.a().a(imageView);
            new StringBuilder("displayImage url=").append(str2).append(",imageWidth=").append(i).append(",imageHeight=").append(i2);
            d.a().a(str2, imageView, cVar, new d(imageView, id, str, i, i2, str2));
        }
    }

    public static c a() {
        com.nostra13.universalimageloader.core.c.a aVar = new com.nostra13.universalimageloader.core.c.a();
        aVar.a = 2130837804;
        aVar.b = 2130837804;
        aVar.c = 2130837804;
        aVar.m = true;
        aVar.h = true;
        aVar.a();
        aVar.q = new b();
        return aVar.b();
    }

    public static void b(String str, ImageView imageView) {
        Object tag = imageView.getTag(R.id.icon);
        if (tag == null || !(tag instanceof String) || !((String) tag).equals(str)) {
            ((com.bumptech.glide.b) e.b(BrothersApplication.a()).a(String.class).a(str)).b().c().a(DiskCacheStrategy.SOURCE).d().a().a(new e(imageView)).a(imageView);
        }
    }

    public static String b(int i) {
        SimpleDateFormat simpleDateFormat;
        long j = (long) (i * 1000);
        if (j >= 3600000) {
            simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        } else {
            simpleDateFormat = new SimpleDateFormat("mm:ss");
        }
        return simpleDateFormat.format(new Date(j));
    }
}
