package com.xunlei.downloadprovider.discovery.kuainiao.a;

import android.app.Activity;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import java.lang.ref.WeakReference;

// compiled from: KuaiNiaoAccelerator.java
final class d implements Runnable {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void run() {
        b bVar = this.a.a;
        String str = this.a.a.a;
        Activity activity = (Activity) ((WeakReference) bVar.c.peek()).get();
        if (activity != null) {
            XLAlarmDialog xLAlarmDialog = new XLAlarmDialog(activity);
            xLAlarmDialog.setTitle(bVar.b.getResources().getString(R.string.kuainiao_dialog_title));
            xLAlarmDialog.setContent(bVar.b.getResources().getString(R.string.kuainiao_dialog_content, new Object[]{str}));
            xLAlarmDialog.setCancelButtonText(bVar.b.getResources().getString(com.xunlei.downloadprovidershare.R.string.cancel));
            xLAlarmDialog.setOnClickCancelButtonListener(new e(bVar));
            xLAlarmDialog.setConfirmButtonText(bVar.b.getResources().getString(R.string.kuainiao_open_member));
            xLAlarmDialog.setOnClickConfirmButtonListener(new f(bVar, activity));
            xLAlarmDialog.show();
        }
    }
}
