package com.xunlei.downloadprovider.player.test;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MediaTestItemView.java
final class a implements OnClickListener {
    final /* synthetic */ MediaTestItemView a;

    a(MediaTestItemView mediaTestItemView) {
        this.a = mediaTestItemView;
    }

    public final void onClick(View view) {
        Intent intent = new Intent(this.a.getContext(), MediaTestDetailActivity.class);
        if (this.a.a != null) {
            intent.putExtra("PlayerId", this.a.a.a);
        }
        intent.putExtra("VideoPath", this.a.b);
        this.a.getContext().startActivity(intent);
    }
}
