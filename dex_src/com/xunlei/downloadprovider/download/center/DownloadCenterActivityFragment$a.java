package com.xunlei.downloadprovider.download.center;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

private class DownloadCenterActivityFragment$a extends BroadcastReceiver {
    final /* synthetic */ DownloadCenterActivityFragment a;

    private DownloadCenterActivityFragment$a(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            DownloadCenterActivityFragment.a(this.a, context);
        }
    }
}
