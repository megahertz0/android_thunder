package com.xunlei.downloadprovider.web.core;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: BottomTipController.java
public final class a implements OnClickListener {
    public boolean a;
    public View b;
    public TextView c;
    public com.xunlei.downloadprovider.a.h.a d;
    private Activity e;
    private View f;
    private long g;
    private int h;
    private Animation i;

    public a(Activity activity) {
        this.f = null;
        this.d = new b(this);
        this.e = activity;
        this.g = 0;
        this.h = 0;
        this.a = false;
        if (this.f != null) {
            this.b = this.f.findViewById(2131755633);
            this.c = (TextView) this.f.findViewById(2131755635);
            this.f.findViewById(2131755634).setOnClickListener(this);
        } else {
            this.b = this.e.findViewById(2131755633);
            this.c = (TextView) this.e.findViewById(2131755635);
            this.e.findViewById(2131755634).setOnClickListener(this);
        }
        this.b.setOnClickListener(this);
        a();
        this.i = AnimationUtils.loadAnimation(this.e.getApplicationContext(), 2131034123);
    }

    public final void a(String str, long j) {
        if (!this.a) {
            this.g = j;
            this.b.setVisibility(0);
            if (TextUtils.isEmpty(this.c.getText().toString())) {
                CharSequence charSequence;
                this.h = 1;
                if (TextUtils.isEmpty(str)) {
                    charSequence = "\u521b\u5efa\u6210\u529f\uff0c\u70b9\u51fb\u67e5\u770b";
                } else {
                    charSequence = str + "\u521b\u5efa\u6210\u529f\uff0c\u70b9\u51fb\u67e5\u770b";
                }
                this.c.setText(charSequence);
                this.c.startAnimation(this.i);
                return;
            }
            this.h++;
            this.c.setText(this.h + "\u4e2a\u4efb\u52a1\u521b\u5efa\u6210\u529f\uff0c\u70b9\u51fb\u67e5\u770b");
        }
    }

    public final void a() {
        if (!this.a) {
            this.b.setVisibility(XZBDevice.Wait);
        }
    }

    public final void b() {
        this.b.setVisibility(XZBDevice.Wait);
        this.c.setText(com.umeng.a.d);
        this.h = 0;
        this.g = 0;
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case 2131755633:
                b();
                DownloadCenterActivity.a(this.e, this.g, com.umeng.a.d);
            case 2131755634:
                b();
            default:
                break;
        }
    }
}
