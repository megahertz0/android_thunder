package com.xunlei.downloadprovider.homepage.relax;

import android.os.Message;
import com.tencent.tauth.Tencent;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: RelaxPicBrowseActivity.java
final class f implements a {
    final /* synthetic */ RelaxPicBrowseActivity a;

    f(RelaxPicBrowseActivity relaxPicBrowseActivity) {
        this.a = relaxPicBrowseActivity;
    }

    public final void a(Message message) {
        switch (message.what) {
            case IHost.HOST_NOFITY_REFRESH_LIST:
                this.a.e.setVisibility(XZBDevice.Wait);
                this.a.h.setVisibility(XZBDevice.Wait);
                this.a.f.setVisibility(0);
            case Tencent.REQUEST_LOGIN:
                this.a.e.setVisibility(XZBDevice.Wait);
                this.a.g.loadDataWithBaseURL("file:///", new StringBuilder("<html><center><img width='100%' border='0' src=\"").append((String) message.obj).append("\"/></center></html>").toString(), "text/html", "utf-8", com.umeng.a.d);
            case 10002:
                this.a.e.setVisibility(XZBDevice.Wait);
                this.a.g.loadDataWithBaseURL("file:///", "<html><center><h3>\u56fe\u7247\u52a0\u8f7d\u5931\u8d25</h3></center></html>", "text/html", "utf-8", com.umeng.a.d);
            default:
                break;
        }
    }
}
