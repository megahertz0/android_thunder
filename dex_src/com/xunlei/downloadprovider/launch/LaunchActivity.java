package com.xunlei.downloadprovider.launch;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.android.volley.Request;
import com.android.volley.f;
import com.android.volley.toolbox.o;
import com.xunlei.common.accelerator.XLAccelUtil;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.discovery.kuainiao.a.b;
import com.xunlei.downloadprovider.download.c.c;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.frame.MainTabSpec.Tab;
import com.xunlei.downloadprovider.loading.LoadingActivity;
import com.xunlei.downloadprovider.model.protocol.d.a;
import com.xunlei.downloadprovider.model.protocol.d.e;
import com.xunlei.downloadprovider.util.v;
import com.xunlei.downloadprovider.util.w;
import com.xunlei.downloadprovider.util.x;
import com.xunlei.downloadprovider.util.y;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xllib.a.d;

public class LaunchActivity extends BaseActivity {
    private boolean a;
    private long b;
    private boolean c;

    public LaunchActivity() {
        this.a = false;
        this.b = 0;
        this.c = false;
    }

    protected void onCreate(Bundle bundle) {
        a();
        super.onCreate(bundle);
        this.a = true;
        this.b = SystemClock.elapsedRealtime();
        if (requestRequiredPermissionsForLaunch() == 0) {
            b();
            return;
        }
        setContentView(2130968610);
        View findViewById = findViewById(2131756294);
        int d = d.d(this);
        if (d > 0) {
            LayoutParams layoutParams = (LayoutParams) findViewById.getLayoutParams();
            layoutParams.bottomMargin -= d;
            findViewById.setLayoutParams(layoutParams);
        }
        if (VERSION.SDK_INT < 16) {
            a();
        } else {
            getWindow().getDecorView().setSystemUiVisibility(1284);
        }
    }

    private void a() {
        getWindow().setFlags(JsInterface.MSG_JS_COLLECT_WEBSITE, JsInterface.MSG_JS_COLLECT_WEBSITE);
    }

    protected void onResume() {
        super.onResume();
        if (requestRequiredPermissionsForLaunch() == 0) {
            b();
        }
    }

    private void b() {
        if (this.a) {
            this.a = false;
            if (!this.c) {
                this.c = true;
                new StringBuilder("doInitWhenLaunchActivityCreate: cost = ").append(SystemClock.elapsedRealtime() - this.b);
                if (!BrothersApplication.a().c) {
                    BrothersApplication.i().a();
                    b i = BrothersApplication.i();
                    if (i.b != null) {
                        i.a();
                        XLAccelUtil.getAccelerator().queryStatus();
                    }
                    a a = a.a();
                    Request dVar = new com.xunlei.downloadprovider.model.d(a.b(), new com.xunlei.downloadprovider.model.protocol.d.d(a), new e(a));
                    dVar.setRetryPolicy(new f(5000, 1, 1.0f));
                    com.xunlei.downloadprovider.j.a.a().d().a(dVar);
                    v a2 = v.a();
                    String toString = new StringBuilder("http://static.m.sjzhushou.com/red_point_cfg.json?versionCode=").append(com.xunlei.downloadprovider.a.b.x()).toString();
                    if (v.c()) {
                        new w(a2).start();
                    } else {
                        Request oVar = new o(0, toString, null, new x(a2), new y(a2, toString));
                        oVar.setRetryPolicy(new f(10000, 1, 1.0f));
                        oVar.setShouldCache(false);
                        com.xunlei.downloadprovider.j.a.b().a(oVar);
                    }
                    c.a().b();
                    com.xunlei.downloadprovider.util.a.a.a();
                    com.xunlei.downloadprovider.util.a.a.c();
                }
                if (BrothersApplication.a().c) {
                    MainTabActivity.a(this, Tab.THUNDER.getTag(), null, true);
                } else {
                    Intent intent = new Intent(this, LoadingActivity.class);
                    Intent intent2 = (Intent) getIntent().getParcelableExtra("business_intent");
                    if (intent2 != null) {
                        intent.putExtra("business_intent", intent2);
                    }
                    startActivity(intent);
                }
                BrothersApplication.a().c = true;
                finish();
            }
        }
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void onRequiredPermissionsForLaunchReady() {
        b();
    }
}
