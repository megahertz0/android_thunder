package com.xunlei.downloadprovider.player.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.b;
import com.xunlei.downloadprovider.player.q;

public class MediaTestDetailActivity extends Activity implements b {
    private int a;
    private ViewGroup b;
    private String c;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = new FrameLayout(this);
        setContentView(this.b);
        this.a = getIntent().getIntExtra("PlayerId", -1);
        this.c = getIntent().getStringExtra("VideoPath");
        q.a().a(this, this, this.a);
    }

    public final void a(ab abVar) {
        this.b.addView(abVar.f, new LayoutParams(-1, 600));
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    public void onBackPressed() {
        super.onBackPressed();
        q a = q.a();
        for (int i = 0; i < a.a.size(); i++) {
            ab abVar = (ab) a.a.valueAt(i);
            if (abVar != null) {
                abVar.b();
            }
        }
        a.a.clear();
        a.b.clear();
    }

    public final void b(ab abVar) {
        abVar.e.a();
        this.b.removeView(abVar.f);
    }
}
