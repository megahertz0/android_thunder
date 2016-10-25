package com.baidu.mobads;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import com.baidu.mobads.ao.a;

class c implements a {
    final /* synthetic */ AdView a;

    c(AdView adView) {
        this.a = adView;
    }

    public void a(int i) {
        this.a.c.a(i);
    }

    public void a(boolean z) {
        this.a.c.a(z);
    }

    public void a(int i, int i2) {
        this.a.a();
    }

    @SuppressLint({"MissingSuperCall"})
    public void a() {
        this.a.c.k();
    }

    public void b() {
        this.a.a();
        this.a.c.j();
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return this.a.c.a(i, keyEvent);
    }
}
