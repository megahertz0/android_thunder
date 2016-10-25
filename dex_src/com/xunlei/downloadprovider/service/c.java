package com.xunlei.downloadprovider.service;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.IHost;

// compiled from: DownloadEngine.java
final class c implements a {
    final /* synthetic */ DownloadEngine a;

    c(DownloadEngine downloadEngine) {
        this.a = downloadEngine;
    }

    public final void a(Message message) {
        new StringBuilder("start :").append(message.toString());
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                DownloadEngine.a(this.a, message);
                break;
            case IHost.HOST_NOFITY_REFRESH_LIST:
                if (DownloadEngine.a(this.a)) {
                    DownloadEngine.b(this.a);
                }
                break;
            case IHost.HOST_NOFITY_PAGE_DESELECTED:
                DownloadEngine.c(this.a);
                break;
        }
        new StringBuilder("end :").append(message.toString());
    }
}
