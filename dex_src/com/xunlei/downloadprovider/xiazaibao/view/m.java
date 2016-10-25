package com.xunlei.downloadprovider.xiazaibao.view;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.List;

// compiled from: XZBTaskMoreOperationDialog.java
final class m implements OnClickListener {
    final /* synthetic */ j a;

    m(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        this.a.dismiss();
        if (this.a.a != null) {
            this.a.a.a();
        }
        List arrayList = new ArrayList();
        arrayList.add(this.a.g);
        this.a.h.a(arrayList, "delete_from_bottom", new n(this));
    }
}
