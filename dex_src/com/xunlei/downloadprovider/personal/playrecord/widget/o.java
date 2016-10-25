package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord;

// compiled from: PlayRecordListWidget.java
final class o implements OnLongClickListener {
    final /* synthetic */ CommixturePlayRecord a;
    final /* synthetic */ b b;

    o(b bVar, CommixturePlayRecord commixturePlayRecord) {
        this.b = bVar;
        this.a = commixturePlayRecord;
    }

    public final boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.cloud_list_item_layout:
                if (!this.b.a.e) {
                    PlayRecordListWidget.c(this.b.a, this.a);
                }
                break;
        }
        return true;
    }
}
