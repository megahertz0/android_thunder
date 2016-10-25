package com.xunlei.downloadprovider.cloudlist;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.cloudlist.a.a;

// compiled from: CloudListBTFileActivity.java
final class f implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ CloudListBTFileActivity b;

    f(CloudListBTFileActivity cloudListBTFileActivity, a aVar) {
        this.b = cloudListBTFileActivity;
        this.a = aVar;
    }

    public final void onClick(View view) {
        CloudListBTFileActivity.c(this.b, this.a);
        if (CloudListBTFileActivity.k(this.b) != null) {
            CloudListBTFileActivity.k(this.b).dismiss();
        }
    }
}
