package com.baidu.mobads.openad.c;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.baidu.mobads.command.a;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import java.util.List;

class e implements IOAdEventListener {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void run(IOAdEvent iOAdEvent) {
        try {
            m.a().f().d("OAdDownloadManager", "\u7f51\u7edc\u72b6\u6001\u5df2\u7ecf\u6539\u53d8");
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.a.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                m.a().f().d("OAdDownloadManager", "\u6ca1\u6709\u53ef\u7528\u7f51\u7edc");
                return;
            }
            String typeName = activeNetworkInfo.getTypeName();
            int type = activeNetworkInfo.getType();
            m.a().f().d("OAdDownloadManager", new StringBuilder("\u5f53\u524d\u7f51\u7edc\u540d\u79f0\uff1a").append(typeName).append("; \u7f51\u7edc\u7c7b\u578b\uff1a").append(type).toString());
            List<IOAdDownloader> allAdsApkDownloaderes = this.a.getAllAdsApkDownloaderes();
            if (allAdsApkDownloaderes != null) {
                for (IOAdDownloader iOAdDownloader : allAdsApkDownloaderes) {
                    if (type == 1) {
                        if (iOAdDownloader.getState() == DownloadStatus.ERROR || iOAdDownloader.getState() == DownloadStatus.PAUSED) {
                            try {
                                iOAdDownloader.resume();
                            } catch (Throwable e) {
                                m.a().f().d("OAdDownloadManager", e);
                            }
                        }
                    } else if (type == 0) {
                        m.a().f().d("OAdDownloadManager", "mobile net work");
                        b a = b.a(iOAdDownloader.getPackageName());
                        if (a != null) {
                            a a2 = a.a();
                            if (a2 == null) {
                                continue;
                            } else if (!a2.r) {
                                try {
                                    iOAdDownloader.pause();
                                } catch (Throwable e2) {
                                    m.a().f().d("OAdDownloadManager", e2);
                                }
                            } else if (iOAdDownloader.getState() == DownloadStatus.ERROR || iOAdDownloader.getState() == DownloadStatus.PAUSED) {
                                try {
                                    iOAdDownloader.resume();
                                } catch (Throwable e22) {
                                    m.a().f().d("OAdDownloadManager", e22);
                                }
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        } catch (Throwable e222) {
            m.a().f().d("OAdDownloadManager", e222);
            com.baidu.mobads.c.a.a().a(new StringBuilder("create apk downloader failed: ").append(e222.toString()).toString());
        }
    }
}
