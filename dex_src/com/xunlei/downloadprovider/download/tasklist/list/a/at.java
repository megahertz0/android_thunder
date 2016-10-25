package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.download.tasklist.list.b.f;

// compiled from: TaskExpandHolder.java
public final class at extends f {
    public int a;
    private TextView b;

    public static at a(Context context, ViewGroup viewGroup) {
        return new at(LayoutInflater.from(context).inflate(R.layout.layout_download_center_expand, viewGroup, false));
    }

    private at(View view) {
        super(view);
        this.b = (TextView) view.findViewById(R.id.expand);
    }

    public final void a(e eVar) {
        super.a(eVar);
        new StringBuilder("expandHeight: ").append(this.a);
        if (this.a < 0) {
            this.a = g.a(BrothersApplication.a(), 7.0f);
        } else {
            this.a += 42;
        }
        this.b.setPadding(0, this.a, 0, 0);
    }
}
