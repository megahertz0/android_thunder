package com.xunlei.downloadprovider.download.tasklist.list.f;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.b;
import com.xunlei.downloadprovider.download.tasklist.list.b.c;

// compiled from: TaskDownloadRedEnvelopeView.java
public final class e extends c {
    TextView b;
    private b c;
    private Context d;
    private TextView e;
    private ImageView f;
    private a g;
    private com.xunlei.downloadprovider.download.tasklist.list.f.a.a h;
    private a i;

    public e(Context context, ViewStub viewStub) {
        super(viewStub);
        this.d = context;
    }

    public final void onInflate(ViewStub viewStub, View view) {
        super.onInflate(viewStub, view);
        this.c = new b(view);
        this.b = this.c.e;
        this.e = this.c.c;
        this.f = this.c.f;
        if (this.h != null) {
            this.e.setText(this.h.f);
            d.a().a(this.h.e, this.f);
        }
        this.c.a.setOnClickListener(new f(this));
        this.c.b.setOnClickListener(new g(this));
    }
}
