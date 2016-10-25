package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import com.xunlei.tdlive.base.h;
import com.xunlei.tdlive.play.a.b;

// compiled from: BaseMvpDialog.java
public abstract class a extends h {
    protected abstract b a();

    protected abstract void a(Bundle bundle);

    public a(Context context, int i) {
        super(context, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(bundle);
        a().a(bundle);
    }

    public void j() {
        super.j();
        a().a();
    }

    public void i() {
        super.i();
        a().b();
    }

    protected void onStart() {
        super.onStart();
        a().d();
    }

    protected void onStop() {
        super.onStop();
        a().c();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return a().a(keyEvent);
    }
}
