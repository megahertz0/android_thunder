package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.download.tasklist.list.b.b;
import com.xunlei.downloadprovider.download.tasklist.list.b.c;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: DownloadExceptionBanner.java
public final class a extends c {
    b b;
    com.xunlei.downloadprovider.download.tasklist.a.a c;
    String d;
    boolean e;
    boolean f;
    boolean g;
    private j h;
    private Context i;

    public a(Context context, ViewStub viewStub) {
        super(viewStub);
        this.d = BuildConfig.VERSION_NAME;
        this.e = false;
        this.f = false;
        this.g = false;
        this.i = context;
    }

    public final void onInflate(ViewStub viewStub, View view) {
        super.onInflate(viewStub, view);
        this.b = new b(view);
        this.b.a.setOnClickListener(new b(this));
        this.b.b.setOnClickListener(new c(this));
    }

    static /* synthetic */ void a(a aVar, com.xunlei.downloadprovider.download.tasklist.a.a aVar2) {
        if (aVar.h == null) {
            aVar.h = new j(aVar.i, "sp_exception_close");
        }
        if (aVar2 != null) {
            aVar.h.a(new StringBuilder("taskId").append(aVar2.getTaskId()).toString(), aVar2.getTaskId());
        }
    }
}
