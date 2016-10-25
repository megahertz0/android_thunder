package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovidershare.R;

// compiled from: LoadingMoreViewHolder.java
public final class f extends g {
    private f(View view) {
        super(view);
    }

    public static f a(Context context, ViewGroup viewGroup) {
        return new f(LayoutInflater.from(context).inflate(R.layout.common_pull_up_refresh_item, viewGroup, false));
    }

    public final void a(al alVar) {
    }

    public final void a(boolean z) {
        super.a(z);
    }
}
