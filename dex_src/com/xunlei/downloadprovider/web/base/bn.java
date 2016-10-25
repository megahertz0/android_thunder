package com.xunlei.downloadprovider.web.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: UploadVideoTestDialog.java
final class bn implements OnClickListener {
    final /* synthetic */ Context a;

    bn(Context context) {
        this.a = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        bl.a("enter", LoginHelper.a().j);
        dialogInterface.dismiss();
        XLToast.b(this.a, XLToastType.XLTOAST_TYPE_SUC, "\u62a5\u540d\u6210\u529f");
    }
}
