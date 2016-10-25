package com.xunlei.downloadprovider.search.ui.search;

import android.text.TextUtils;
import com.xunlei.downloadprovider.search.b.b;
import com.xunlei.downloadprovider.search.bean.a;
import java.util.List;

// compiled from: SearchAssociativeFragment.java
final class j implements b<a> {
    final /* synthetic */ SearchAssociativeFragment a;

    j(SearchAssociativeFragment searchAssociativeFragment) {
        this.a = searchAssociativeFragment;
    }

    public final /* synthetic */ void a(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            return;
        }
        if (TextUtils.isEmpty(this.a.c) || this.a.c.equals(aVar.a)) {
            List list = aVar.b;
            SearchAssociativeFragment.a(this.a.c, list);
            this.a.a.a(this.a.c, list);
        }
    }
}
