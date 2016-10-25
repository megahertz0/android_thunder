package com.xunlei.downloadprovider.frame;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.umeng.a;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.core.JsInterface;
import org.android.agoo.message.MessageService;

// compiled from: MainTabActivity.java
final class t implements OnClickListener {
    final /* synthetic */ XLAlarmDialog a;
    final /* synthetic */ MainTabActivity b;

    t(MainTabActivity mainTabActivity, XLAlarmDialog xLAlarmDialog) {
        this.b = mainTabActivity;
        this.a = xLAlarmDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        StatReporter.reportClick(JsInterface.MSG_JS_SET_WEBSITE_STATE, "dialog_hide", a.d);
        this.a.dismiss();
        StatReporter.reportAppExitClick("confirm", MainTabActivity.g(this.b), MessageService.MSG_DB_READY_REPORT, MainTabActivity.i(this.b), this.a.mContentCheckbox.isChecked() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT);
        if (this.a.mContentCheckbox.isChecked()) {
            this.b.moveTaskToBack(true);
        } else {
            this.b.exit();
        }
    }
}
