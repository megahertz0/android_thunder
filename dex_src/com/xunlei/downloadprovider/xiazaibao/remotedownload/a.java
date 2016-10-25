package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: EditExpandViewHolder.java
public final class a extends g {
    int a;
    private TextView f;

    public static a a(Context context, ViewGroup viewGroup) {
        return new a(LayoutInflater.from(context).inflate(R.layout.layout_download_center_expand, viewGroup, false));
    }

    private a(View view) {
        super(view);
        this.f = (TextView) view.findViewById(R.id.expand);
    }

    public final void a(al alVar) {
        if (this.a < 0) {
            this.a = g.a(BrothersApplication.a(), 7.0f);
        } else {
            this.a += 42;
        }
        new StringBuilder("padding:  ").append(this.a);
        this.f.setPadding(0, this.a, 0, 0);
    }
}
