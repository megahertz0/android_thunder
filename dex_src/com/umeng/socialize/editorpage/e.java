package com.umeng.socialize.editorpage;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: ShareActivity.java
class e implements OnClickListener {
    final /* synthetic */ ShareActivity a;

    e(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.C = null;
        this.a.a(false);
        dialogInterface.cancel();
    }
}
