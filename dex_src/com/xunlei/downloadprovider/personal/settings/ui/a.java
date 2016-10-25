package com.xunlei.downloadprovider.personal.settings.ui;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.filemanager.PathChooserActivity;

// compiled from: SDCardViewHolder.java
final class a implements OnClickListener {
    final /* synthetic */ SDCardViewHolder a;

    a(SDCardViewHolder sDCardViewHolder) {
        this.a = sDCardViewHolder;
    }

    public final void onClick(View view) {
        if (!SDCardViewHolder.a(this.a)) {
            this.a.setSelected(true);
            if (SDCardViewHolder.d(this.a) != null) {
                com.xunlei.downloadprovider.personal.settings.ui.SDCardViewHolder.a d = SDCardViewHolder.d(this.a);
                int i = this.a.i;
                d.a(SDCardViewHolder.a(this.a));
            }
        } else if (k.a(this.a.j) == 0) {
            XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_ALARM, "SD\u5361\u5df2\u62d4\u51fa");
        } else if (SDCardViewHolder.b(this.a)) {
            PathChooserActivity.a((Activity) this.a.getContext(), SDCardViewHolder.c(this.a));
        }
    }
}
