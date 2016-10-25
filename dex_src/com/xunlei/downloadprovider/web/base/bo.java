package com.xunlei.downloadprovider.web.base;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: UploadVideoTestDialog.java
final class bo implements OnClickListener {
    bo() {
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        bl.a("cancel", LoginHelper.a().j);
        dialogInterface.dismiss();
    }
}
