package com.xunlei.downloadprovider.frame;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.umeng.a;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.core.JsInterface;
import org.android.agoo.message.MessageService;

// compiled from: MainTabActivity.java
final class n implements OnClickListener {
    final /* synthetic */ XLAlarmDialog a;
    final /* synthetic */ MainTabActivity b;

    n(MainTabActivity mainTabActivity, XLAlarmDialog xLAlarmDialog) {
        this.b = mainTabActivity;
        this.a = xLAlarmDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        StatReporter.reportClick(JsInterface.MSG_JS_SET_WEBSITE_STATE, "dialog_quit", a.d);
        this.a.dismiss();
        StatReporter.reportAppExitClick("cancel", MainTabActivity.g(this.b), MainTabActivity.h(this.b), MainTabActivity.i(this.b), MessageService.MSG_DB_READY_REPORT);
    }
}
