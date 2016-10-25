package com.xunlei.tdlive.withdraw;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.i;

// compiled from: BasePage.java
public class a extends i {
    a k;
    boolean l;

    // compiled from: BasePage.java
    public static interface a {
        Object a(String str);

        void a();

        void a(Class<?> cls);

        void a(Class<? extends a> cls, boolean z);

        void a(Class<? extends a> cls, boolean z, Bundle bundle);

        void a(String str, Object obj);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.k = (a) activity;
        this.l = true;
    }

    public void onDetach() {
        super.onDetach();
        this.l = false;
    }

    public void onResume() {
        super.onResume();
        this.k.a(getClass());
    }

    public Toast c(int i) {
        return super.a(i, 0, R.layout.xllive_toast_uiserinfo_edit, R.id.text, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
    }

    public Toast a_(String str) {
        return super.a(str, 0, R.layout.xllive_toast_uiserinfo_edit, R.id.text, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
    }

    public boolean c() {
        return this.l;
    }
}
