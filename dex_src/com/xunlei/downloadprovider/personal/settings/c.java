package com.xunlei.downloadprovider.personal.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.tdlive.R;

// compiled from: AboutBoxActivity.java
final class c implements OnClickListener {
    final /* synthetic */ AboutBoxActivity a;

    c(AboutBoxActivity aboutBoxActivity) {
        this.a = aboutBoxActivity;
    }

    public final void onClick(View view) {
        XLAlarmDialog xLAlarmDialog = new XLAlarmDialog(this.a);
        xLAlarmDialog.setContent(this.a.getString(2131232549));
        xLAlarmDialog.setCancelButtonText(this.a.getString(R.string.cancel));
        xLAlarmDialog.setConfirmButtonText(this.a.getString(2131232473));
        xLAlarmDialog.setOnClickCancelButtonListener(new d(this));
        xLAlarmDialog.setOnClickConfirmButtonListener(new e(this));
        xLAlarmDialog.setCanceledOnTouchOutside(true);
        xLAlarmDialog.show();
    }
}
