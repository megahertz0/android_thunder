package com.xunlei.tdlive.play.a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import com.xunlei.tdlive.base.h;
import com.xunlei.tdlive.util.XLog;

// compiled from: BaseDialogPresenter.java
public class b {
    private final String a;
    private h b;

    public b(h hVar) {
        this.a = b.class.getSimpleName();
        this.b = hVar;
    }

    public void a(Bundle bundle) {
        XLog.d(this.a, "onCreate");
    }

    public void a() {
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }

    public Context e() {
        return this.b.getContext();
    }

    public Activity f() {
        return this.b.getOwnerActivity();
    }

    public Window g() {
        return this.b.getWindow();
    }

    public boolean a(KeyEvent keyEvent) {
        return false;
    }
}
