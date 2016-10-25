package com.umeng.socialize.editorpage;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: ShareActivity.java
class d implements OnClickListener {
    final /* synthetic */ ShareActivity a;

    d(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
