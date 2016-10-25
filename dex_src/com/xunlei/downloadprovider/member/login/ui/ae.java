package com.xunlei.downloadprovider.member.login.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import org.android.agoo.message.MessageService;

// compiled from: XLTwoButtonDialogActivity.java
final class ae implements OnClickListener {
    final /* synthetic */ XLTwoButtonDialogActivity a;

    ae(XLTwoButtonDialogActivity xLTwoButtonDialogActivity) {
        this.a = xLTwoButtonDialogActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        LoginHelper.a().a(this.a, null, 1);
        this.a.finish();
        XLTwoButtonDialogActivity.a(MessageService.MSG_DB_NOTIFY_REACHED);
    }
}
