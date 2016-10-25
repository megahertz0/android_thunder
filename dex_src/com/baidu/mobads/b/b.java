package com.baidu.mobads.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.a.d;
import com.umeng.a;
import java.io.File;

public class b extends d {
    protected final IXAdLogger a;
    private a d;
    private String e;
    private File f;
    private Boolean g;

    public b(Context context, String str, File file, boolean z) {
        super(context);
        this.a = m.a().f();
        this.e = str;
        this.f = file;
        this.g = Boolean.valueOf(z);
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        this.d = (a) broadcastReceiver;
    }

    public void a() {
        try {
            if (this.g.booleanValue() && this.e != null) {
                IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
                intentFilter.addDataScheme("package");
                this.b.registerReceiver(this.d, intentFilter);
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(268435456);
            intent.setDataAndType(Uri.fromFile(this.f), "application/vnd.android.package-archive");
            this.b.startActivity(intent);
        } catch (Exception e) {
            this.a.e("XAdInstallController", a.d);
        }
    }
}
