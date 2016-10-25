package com.xunlei.downloadprovider.member.payment.ui.a;

import android.view.View;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.member.payment.external.c;

// compiled from: PayProblemAdapter.java
final class e extends c {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public final void onClick(View view) {
        c cVar = this.a;
        XLAlarmDialog xLAlarmDialog = new XLAlarmDialog(cVar.a);
        xLAlarmDialog.setContent(cVar.a.getString(R.string.sett_sure_call_xl));
        xLAlarmDialog.setCancelButtonText(cVar.a.getString(com.xunlei.downloadprovidershare.R.string.cancel));
        xLAlarmDialog.setConfirmButtonText(cVar.a.getString(R.string.sett_dial));
        xLAlarmDialog.setOnClickCancelButtonListener(new h(cVar));
        xLAlarmDialog.setOnClickConfirmButtonListener(new i(cVar));
        xLAlarmDialog.setCanceledOnTouchOutside(true);
        xLAlarmDialog.show();
    }
}
