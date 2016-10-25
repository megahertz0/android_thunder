package com.xunlei.downloadprovider.model.protocol.networkcheck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.toolbox.o;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.j.a;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.util.ag;
import com.xunlei.downloadprovider.util.ag.b;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.h;
import com.xunlei.downloadprovider.vod.j;

public class IPAddressErrorActivity extends Activity {
    private static final String a;
    private static Context b;
    private static boolean c;
    private static Handler g;
    private static b h;
    private TextView d;
    private TextView e;
    private TextView f;

    static {
        a = IPAddressErrorActivity.class.getSimpleName();
        b = null;
        c = false;
        g = new c();
        h = new d();
    }

    public static void a(Context context) {
        b = context;
        com.xunlei.xllib.a.b.i(context);
        Request oVar = new o("http://m.sjzhushou.com/android/ithunder_available.dat", null, new e(), new f());
        oVar.setShouldCache(false);
        a.b().a(oVar);
    }

    public static void b(Context context) {
        b = context;
        com.xunlei.xllib.a.b.i(context);
        Request oVar = new o("http://m.sjzhushou.com/android/ithunder_available.dat", null, new g(), new h());
        oVar.setShouldCache(false);
        a.b().a(oVar);
    }

    public static void a() {
        ag.a(h);
    }

    public static void b() {
        if (b != null) {
            b.startActivity(new Intent(b, IPAddressErrorActivity.class));
        }
    }

    public static boolean c() {
        return c;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130969026);
        this.f = (TextView) findViewById(2131757138);
        this.d = (TextView) findViewById(2131757139);
        this.e = (TextView) findViewById(2131757141);
        this.d.setText(new StringBuilder("Your IP address:").append(com.xunlei.xllib.a.b.i(this)).toString());
        this.e.setText(new StringBuilder("\u4f60\u7684IP\u5730\u5740\u662f\uff1a").append(com.xunlei.xllib.a.b.i(this)).toString());
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (com.xunlei.downloadprovider.a.b.u() <= 480) {
            LayoutParams layoutParams = (LayoutParams) this.f.getLayoutParams();
            layoutParams.topMargin = 5;
            this.f.setLayoutParams(layoutParams);
        }
    }

    public void onBackPressed() {
        BrothersApplication.a().d();
        h a = h.a();
        if (VodUtil.a) {
            new j(a).start();
        }
        DownloadService.b();
        System.exit(0);
    }
}
