package com.xunlei.tdlive.play.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.xunlei.tdlive.util.XLog;

// compiled from: BaseActivityPresenter.java
public class a {
    private final String a;
    private Activity b;

    public a(Activity activity) {
        this.a = a.class.getSimpleName();
        this.b = activity;
        if (this.b == null) {
            throw new IllegalArgumentException("activity cannot be null!");
        }
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

    public void e() {
    }

    protected Intent f() {
        return this.b.getIntent();
    }

    public void g() {
        this.b.finish();
    }

    public void h() {
    }
}
