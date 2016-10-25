package com.xunlei.downloadprovider.personal.settings;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import org.android.agoo.message.MessageService;

// compiled from: GeneralSettingActivity.java
final class n implements OnClickListener {
    final /* synthetic */ GeneralSettingActivity a;

    n(GeneralSettingActivity generalSettingActivity) {
        this.a = generalSettingActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = 0;
        dialogInterface.dismiss();
        Editor edit = BrothersApplication.a().getApplicationContext().getSharedPreferences("default_app_setting", 0).edit();
        edit.clear();
        if (!edit.commit()) {
            i2 = -1;
        }
        if (i2 == 0) {
            XLToast.a(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_NORMAL, this.a.getString(2131232472));
        }
        StatReporter.reportClick(5004, "clearOpenwith", MessageService.MSG_DB_NOTIFY_REACHED);
    }
}
