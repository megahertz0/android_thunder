package com.xunlei.downloadlib;

import android.content.Context;
import com.xunlei.downloadlib.XLUtil.NetWorkCarrier;

private class XLDownloadManager$NetworkChangeHandlerThread implements Runnable {
    private boolean m_allow_execution;
    private Context m_context;
    private XLLoader m_loader;
    final /* synthetic */ XLDownloadManager this$0;

    public XLDownloadManager$NetworkChangeHandlerThread(XLDownloadManager xLDownloadManager, Context context, XLLoader xLLoader, boolean z) {
        this.this$0 = xLDownloadManager;
        this.m_context = null;
        this.m_loader = null;
        this.m_allow_execution = true;
        this.m_context = context;
        this.m_loader = xLLoader;
        this.m_allow_execution = z;
    }

    public void run() {
        if (this.m_allow_execution) {
            int networkType = XLUtil.getNetworkType(this.m_context);
            XLLog.d("XLDownloadManager", new StringBuilder("NetworkChangeHandlerThread nettype=").append(networkType).toString());
            this.this$0.notifyNetWorkType(networkType, this.m_loader);
            String bssid = XLUtil.getBSSID(this.m_context);
            XLLog.d("XLDownloadManager", new StringBuilder("NetworkChangeHandlerThread bssid=").append(bssid).toString());
            this.this$0.notifyWifiBSSID(bssid, this.m_loader);
            NetWorkCarrier netWorkCarrier = XLUtil.getNetWorkCarrier(this.m_context);
            XLLog.d("XLDownloadManager", new StringBuilder("NetworkChangeHandlerThread NetWorkCarrier=").append(netWorkCarrier).toString());
            this.this$0.notifyNetWorkCarrier(netWorkCarrier.ordinal());
        }
    }
}
