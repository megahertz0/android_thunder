package com.xunlei.downloadprovider.cloudlist;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.R;

// compiled from: CloudListBTFileActivity.java
final class b implements OnClickListener {
    final /* synthetic */ CloudListBTFileActivity a;

    b(CloudListBTFileActivity cloudListBTFileActivity) {
        this.a = cloudListBTFileActivity;
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_left:
                this.a.finish();
            default:
                break;
        }
    }
}
