package com.xunlei.downloadprovider.member.login.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import org.android.agoo.message.MessageService;

// compiled from: XLTwoButtonDialogActivity.java
final class ag implements OnClickListener {
    final /* synthetic */ XLTwoButtonDialogActivity a;

    ag(XLTwoButtonDialogActivity xLTwoButtonDialogActivity) {
        this.a = xLTwoButtonDialogActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        LoginHelper.a();
        LoginHelper.r();
        this.a.finish();
        XLTwoButtonDialogActivity.a(MessageService.MSG_DB_READY_REPORT);
    }
}
