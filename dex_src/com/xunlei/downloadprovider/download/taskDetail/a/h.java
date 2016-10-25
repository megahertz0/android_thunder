package com.xunlei.downloadprovider.download.taskDetail.a;

import android.app.Activity;
import android.view.View;
import com.xunlei.downloadprovider.download.tasklist.list.e.b.a;
import com.xunlei.downloadprovider.download.tasklist.list.e.b.c;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: TaskDetailImageAdInfo.java
public abstract class h<T> implements a, c {
    protected T a;
    protected String b;

    public abstract String a();

    public abstract void a(View view);

    public abstract String h();

    public abstract void onClick(Activity activity, View view);

    public h(T t) {
        this.a = null;
        this.b = BuildConfig.VERSION_NAME;
        this.a = t;
    }

    public final String j() {
        return this.b;
    }

    public final void a(String str) {
        this.b = str;
    }

    public String toString() {
        return new StringBuilder("title: ").append(c()).append(" isApp: ").append(b()).toString();
    }
}
