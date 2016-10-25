package com.xunlei.downloadprovider.filemanager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import com.xunlei.downloadprovider.filemanager.ui.h;

// compiled from: PathChooserActivity.java
final class n implements OnDismissListener {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        String str = ((h) dialogInterface).a;
        if (str != null) {
            PathChooserActivity.e(this.a.a).b();
            PathChooserActivity.e(this.a.a).a();
            PathChooserActivity.e(this.a.a).setSelection(str);
            PathChooserActivity.a(this.a.a, null);
        }
    }
}
