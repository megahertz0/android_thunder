package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import com.android.volley.p;
import java.io.File;

// compiled from: Volley.java
public final class u {
    public static p a(Context context, g gVar) {
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = packageName + "/" + context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (NameNotFoundException e) {
        }
        if (gVar == null) {
            if (VERSION.SDK_INT >= 9) {
                gVar = new h();
            } else {
                gVar = new e(AndroidHttpClient.newInstance(str));
            }
        }
        p pVar = new p(new d(file), new a(gVar), (byte) 0);
        pVar.a();
        return pVar;
    }
}
