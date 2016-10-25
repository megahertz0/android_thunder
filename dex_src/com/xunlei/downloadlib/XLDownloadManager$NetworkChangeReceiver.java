package com.xunlei.downloadlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

private class XLDownloadManager$NetworkChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "TAG_DownloadReceiver";
    final /* synthetic */ XLDownloadManager this$0;

    public XLDownloadManager$NetworkChangeReceiver(XLDownloadManager xLDownloadManager) {
        this.this$0 = xLDownloadManager;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            new Thread(new XLDownloadManager$NetworkChangeHandlerThread(this.this$0, context, XLDownloadManager.access$000(this.this$0), XLDownloadManager.access$100())).start();
        }
    }
}
