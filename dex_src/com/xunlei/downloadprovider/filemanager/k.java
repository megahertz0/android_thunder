package com.xunlei.downloadprovider.filemanager;

import android.text.TextUtils;
import com.xunlei.downloadprovider.filemanager.model.i;
import com.xunlei.downloadprovider.filemanager.ui.FileManagerDirView.c;
import java.util.List;

// compiled from: PathChooserActivity.java
final class k implements c {
    final /* synthetic */ PathChooserActivity a;

    k(PathChooserActivity pathChooserActivity) {
        this.a = pathChooserActivity;
    }

    public final void a(int i, String str, List<i> list) {
        if (TextUtils.isEmpty(str) || !str.startsWith("#*sdcard.choose@!~")) {
            this.a.c.setText(this.a.e.a(str));
            this.a.d.setEnabled(true);
            this.a.b.n.setEnabled(true);
            return;
        }
        this.a.c.setText(2131232401);
        this.a.d.setEnabled(false);
        this.a.b.n.setEnabled(false);
    }
}
