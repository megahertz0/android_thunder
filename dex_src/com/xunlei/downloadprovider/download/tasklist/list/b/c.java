package com.xunlei.downloadprovider.download.tasklist.list.b;

import android.view.View;
import android.view.ViewStub;
import android.view.ViewStub.OnInflateListener;

// compiled from: BasicCardViewStubBanner.java
public class c implements OnInflateListener {
    protected final ViewStub a;

    public c(ViewStub viewStub) {
        this.a = viewStub;
        this.a.setOnInflateListener(this);
    }

    public final int a() {
        return this.a.getVisibility();
    }

    public final void a(int i) {
        this.a.setVisibility(i);
    }

    public void onInflate(ViewStub viewStub, View view) {
    }
}
