package com.xunlei.downloadprovider.homepage.relax;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.nostra13.universalimageloader.core.d.c;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.protocol.b.d;
import uk.co.senab.photoview.PhotoView;

public class RelaxPicBrowseActivity extends Activity {
    private static d i;
    private final int a;
    private final int b;
    private final int c;
    private RelativeLayout d;
    private View e;
    private PhotoView f;
    private WebView g;
    private View h;
    private a j;
    private b k;
    private OnClickListener l;
    private c m;
    private com.xunlei.downloadprovider.homepage.relax.c.a.a n;
    private WebViewClient o;

    public RelaxPicBrowseActivity() {
        this.a = 1000;
        this.b = 10001;
        this.c = 10002;
        this.j = new f(this);
        this.k = new b(this.j);
        this.l = new g(this);
        this.m = new h(this);
        this.n = new i(this);
        this.o = new j(this);
    }

    public static void a(Context context, d dVar) {
        i = dVar;
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setClass(BrothersApplication.a(), RelaxPicBrowseActivity.class);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        overridePendingTransition(17432576, 0);
        setContentView(2130968930);
        this.d = (RelativeLayout) findViewById(2131756822);
        this.e = findViewById(2131756823);
        this.f = (PhotoView) findViewById(2131756824);
        this.h = findViewById(2131756826);
        this.g = (WebView) findViewById(2131756825);
        this.g.setWebViewClient(this.o);
        this.g.setBackgroundColor(0);
        this.g.setFocusable(false);
        this.e.setVisibility(0);
        this.h.setVisibility(0);
        this.h.setOnClickListener(this.l);
        if (i.a() == 1) {
            com.xunlei.downloadprovider.homepage.relax.c.a.a().a(i.e, this.n);
            return;
        }
        this.f.setOnViewTapListener(new k(this));
        com.nostra13.universalimageloader.core.d.a().a(i.e, this.f, this.m);
    }

    public void finish() {
        super.finish();
        this.d.removeView(this.g);
        this.g.removeAllViews();
        this.g.destroy();
        overridePendingTransition(17432577, 0);
    }
}
