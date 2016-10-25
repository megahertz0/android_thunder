package com.xiaomi.mipush.sdk;

import android.content.pm.PackageItemInfo;

public class MiPushClient$a extends Exception {
    private PackageItemInfo a;

    public MiPushClient$a(String str, PackageItemInfo packageItemInfo) {
        super(str);
        this.a = packageItemInfo;
    }
}
