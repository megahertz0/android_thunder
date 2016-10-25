package com.xunlei.downloadprovider.web.record;

import android.widget.Toast;
import com.xunlei.downloadprovider.web.record.aa.b;
import java.util.ArrayList;

// compiled from: FavorAndHistroyActivity.java
final class l implements b {
    final /* synthetic */ FavorAndHistroyActivity a;

    l(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void a(ArrayList<t> arrayList, int i, long j, boolean z) {
        this.a.a(j);
        this.a.w.clearAnimation();
        this.a.z.setText(new StringBuilder("\u4e0a\u6b21\u540c\u6b65:").append(FavorAndHistroyActivity.c(System.currentTimeMillis())).toString());
        this.a.a(System.currentTimeMillis(), null);
        Toast.makeText(this.a, "\u540c\u6b65\u6210\u529f", 0).show();
        this.a.a();
    }

    public final void a(int i) {
        this.a.w.clearAnimation();
        Toast.makeText(this.a, "\u540c\u6b65\u5931\u8d25", 0).show();
    }
}
