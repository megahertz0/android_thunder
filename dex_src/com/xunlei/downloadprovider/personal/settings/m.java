package com.xunlei.downloadprovider.personal.settings;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import org.android.agoo.message.MessageService;

// compiled from: GeneralSettingActivity.java
final class m implements OnClickListener {
    final /* synthetic */ GeneralSettingActivity a;

    m(GeneralSettingActivity generalSettingActivity) {
        this.a = generalSettingActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        StatReporter.reportClick(5004, "clearOpenwith", MessageService.MSG_DB_READY_REPORT);
    }
}
