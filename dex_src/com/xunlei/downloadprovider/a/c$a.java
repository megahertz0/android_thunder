package com.xunlei.downloadprovider.a;

import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;

// compiled from: ApkHelper.java
public final class c$a {
    PackageInfo a;
    private Resources b;

    public c$a(PackageInfo packageInfo, Resources resources) {
        this.a = packageInfo;
        this.b = resources;
    }

    public final CharSequence a() {
        if (this.a == null || this.b == null) {
            return null;
        }
        CharSequence charSequence = this.a.applicationInfo.nonLocalizedLabel;
        if (charSequence != null) {
            return charSequence;
        }
        int i = this.a.applicationInfo.labelRes;
        if (i == 0) {
            return this.a.applicationInfo.packageName;
        }
        try {
            return this.b.getText(i);
        } catch (NotFoundException e) {
            return charSequence;
        }
    }

    public final Drawable b() {
        if (this.a == null || this.b == null) {
            return null;
        }
        int i = this.a.applicationInfo.icon;
        if (i == 0) {
            return null;
        }
        try {
            return this.b.getDrawable(i);
        } catch (NotFoundException e) {
            return null;
        }
    }

    public final String c() {
        return this.a != null ? this.a.packageName : null;
    }
}
