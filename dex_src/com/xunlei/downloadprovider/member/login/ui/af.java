package com.xunlei.downloadprovider.member.login.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import org.android.agoo.message.MessageService;

// compiled from: XLTwoButtonDialogActivity.java
final class af implements OnClickListener {
    final /* synthetic */ XLTwoButtonDialogActivity a;

    af(XLTwoButtonDialogActivity xLTwoButtonDialogActivity) {
        this.a = xLTwoButtonDialogActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        LoginHelper.a().a(true);
        this.a.finish();
        XLTwoButtonDialogActivity.a(MessageService.MSG_DB_NOTIFY_CLICK);
    }
}
