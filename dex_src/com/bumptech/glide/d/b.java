package com.bumptech.glide.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.List;

// compiled from: ManifestParser.java
public final class b {
    private final Context a;

    public b(Context context) {
        this.a = context;
    }

    public final List<a> a() {
        List<a> arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = this.a.getPackageManager().getApplicationInfo(this.a.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo.metaData != null) {
                for (String str : applicationInfo.metaData.keySet()) {
                    if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                        arrayList.add(a(str));
                    }
                }
            }
            return arrayList;
        } catch (Throwable e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
    }

    private static a a(String str) {
        try {
            Class forName = Class.forName(str);
            try {
                Object newInstance = forName.newInstance();
                if (newInstance instanceof a) {
                    return (a) newInstance;
                }
                throw new RuntimeException(new StringBuilder("Expected instanceof GlideModule, but found: ").append(newInstance).toString());
            } catch (Throwable e) {
                throw new RuntimeException(new StringBuilder("Unable to instantiate GlideModule implementation for ").append(forName).toString(), e);
            } catch (Throwable e2) {
                throw new RuntimeException(new StringBuilder("Unable to instantiate GlideModule implementation for ").append(forName).toString(), e2);
            }
        } catch (Throwable e3) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e3);
        }
    }
}
