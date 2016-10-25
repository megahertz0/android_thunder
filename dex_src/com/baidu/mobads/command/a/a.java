package com.baidu.mobads.command.a;

import android.content.Context;
import android.widget.Toast;
import com.baidu.mobads.AdSettings;
import com.baidu.mobads.command.b;
import com.baidu.mobads.interfaces.IXAdContainerContext;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdResource;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.interfaces.utils.IXAdIOUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.j.d;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.net.URL;

public class a extends b {
    public a(IXNonLinearAdSlot iXNonLinearAdSlot, IXAdInstanceInfo iXAdInstanceInfo, IXAdResource iXAdResource) {
        super(iXNonLinearAdSlot, iXAdInstanceInfo, iXAdResource);
    }

    public void a() {
        boolean z = false;
        d m = m.a().m();
        IXAdIOUtils k = m.a().k();
        IXAdURIUitls i = m.a().i();
        IXAdSystemUtils n = m.a().n();
        IXAdContainerContext adContainerContext = this.b.getCurrentXAdContainer().getAdContainerContext();
        try {
            String md5;
            com.baidu.mobads.command.a aVar;
            com.baidu.mobads.command.a a;
            com.baidu.mobads.command.a aVar2;
            String appPackageName = this.c.getAppPackageName();
            this.e.i("XAdDownloadAPKCommand", new StringBuilder("download pkg = ").append(appPackageName).toString());
            if ((appPackageName == null || appPackageName.equals(com.umeng.a.d)) && !AdSettings.getSupportHttps().equals(AdSettings.b.c.a())) {
                this.e.i("XAdDownloadAPKCommand", "start to download but package is empty");
                md5 = m.getMD5(this.c.getOriginClickUrl());
            } else {
                md5 = appPackageName;
            }
            IOAdDownloader adsApkDownloader = com.baidu.mobads.openad.c.d.a(this.a).getAdsApkDownloader(md5);
            com.baidu.mobads.openad.c.b a2 = com.baidu.mobads.openad.c.b.a(md5);
            if (a2 == null || adsApkDownloader == null) {
                if (adsApkDownloader != null) {
                    adsApkDownloader.cancel();
                    adsApkDownloader.removeObservers();
                }
                com.baidu.mobads.openad.c.b.b(md5);
                com.baidu.mobads.openad.c.d.a(this.a).removeAdsApkDownloader(md5);
                aVar = null;
            } else {
                a = a2.a();
                DownloadStatus state = adsApkDownloader.getState();
                this.e.d("XAdDownloadAPKCommand", new StringBuilder("startDownload>> downloader exist: state=").append(state).toString());
                if (state == DownloadStatus.CANCELLED || state == DownloadStatus.ERROR || state == DownloadStatus.PAUSED) {
                    adsApkDownloader.resume();
                    i.pintHttpInNewThread(this.c.getClickThroughUrl());
                    return;
                } else if (state == DownloadStatus.COMPLETED) {
                    if (a(this.a, a)) {
                        i.pintHttpInNewThread(this.c.getClickThroughUrl());
                        b(a);
                        return;
                    }
                    adsApkDownloader.cancel();
                    adsApkDownloader.removeObservers();
                    com.baidu.mobads.openad.c.b.b(md5);
                    com.baidu.mobads.openad.c.d.a(this.a).removeAdsApkDownloader(md5);
                    aVar = a;
                } else if (state == DownloadStatus.DOWNLOADING || state == DownloadStatus.INITING) {
                    Toast.makeText(this.a, adsApkDownloader.getTitle() + adsApkDownloader.getState().getMessage(), 0).show();
                    return;
                } else {
                    aVar = a;
                }
            }
            a = com.baidu.mobads.command.a.a(this.a, md5);
            if (a != null) {
                if (a.g == DownloadStatus.COMPLETED && a(this.a, a)) {
                    b(a);
                    return;
                } else {
                    i.pintHttpInNewThread(this.c.getClickThroughUrl());
                    aVar2 = a;
                }
            } else if (b()) {
                m.a().l().openApp(this.a, this.c.getAppPackageName());
                i.pintHttpInNewThread(this.c.getClickThroughUrl());
                b(aVar);
                return;
            } else {
                String appName = this.c.getAppName();
                if (appName == null || appName.equals(com.umeng.a.d)) {
                    appName = this.c.getTitle();
                    if (appName == null || appName.equals(com.umeng.a.d)) {
                        appPackageName = "\u60a8\u70b9\u51fb\u7684\u5e94\u7528";
                        a = new com.baidu.mobads.command.a(md5, appPackageName);
                        a.a(this.c.getQueryKey(), this.c.getAdId(), this.c.getClickThroughUrl(), this.c.isAutoOpen());
                        a.a(m.getMD5(a.j) + ".apk", k.getStoreagePath(this.a));
                        a.b(this.b.getAdRequestInfo().getApid(), this.b.getProdInfo().getProdType());
                        a.f = com.baidu.mobads.openad.c.b.c(md5);
                        if (!this.c.isActionOnlyWifi()) {
                            z = true;
                        }
                        a.r = z;
                        a.a(System.currentTimeMillis());
                        a.b(this.c.getAppSize());
                        a.a(this.c.isTooLarge());
                        aVar2 = a;
                    }
                }
                appPackageName = appName;
                a = new com.baidu.mobads.command.a(md5, appPackageName);
                a.a(this.c.getQueryKey(), this.c.getAdId(), this.c.getClickThroughUrl(), this.c.isAutoOpen());
                a.a(m.getMD5(a.j) + ".apk", k.getStoreagePath(this.a));
                a.b(this.b.getAdRequestInfo().getApid(), this.b.getProdInfo().getProdType());
                a.f = com.baidu.mobads.openad.c.b.c(md5);
                if (this.c.isActionOnlyWifi()) {
                    z = true;
                }
                a.r = z;
                a.a(System.currentTimeMillis());
                a.b(this.c.getAppSize());
                a.a(this.c.isTooLarge());
                aVar2 = a;
            }
            aVar2.s = System.currentTimeMillis();
            IOAdDownloader createAdsApkDownloader = adContainerContext.getDownloaderManager(this.a).createAdsApkDownloader(new URL(aVar2.j), aVar2.c, aVar2.b, XZBDevice.DOWNLOAD_LIST_FAILED, aVar2.a, aVar2.i);
            if (!(!this.c.getAPOOpen() || this.c.getPage() == null || this.c.getPage().equals(com.umeng.a.d))) {
                aVar2.v = true;
                aVar2.w = this.c.getPage();
            }
            createAdsApkDownloader.addObserver(new com.baidu.mobads.openad.c.b(this.a, aVar2));
            if (aVar2.r || !n.is3GConnected(this.a).booleanValue()) {
                createAdsApkDownloader.start();
                return;
            }
            createAdsApkDownloader.pause();
            Toast.makeText(this.a, createAdsApkDownloader.getTitle() + " \u5c06\u5728\u8fde\u5165Wifi\u540e\u5f00\u59cb\u4e0b\u8f7d", 0).show();
        } catch (Throwable e) {
            this.e.e("XAdDownloadAPKCommand", e);
            com.baidu.mobads.c.a.a().a(new StringBuilder("ad app download failed: ").append(e.toString()).toString());
        }
    }

    private boolean b() {
        return m.a().l().isInstalled(this.a, this.c.getAppPackageName());
    }

    protected boolean a(Context context, com.baidu.mobads.command.a aVar) {
        if (m.a().l().isInstalled(context, aVar.i)) {
            m.a().l().openApp(context, aVar.i);
            return true;
        }
        String str = aVar.c + aVar.b;
        File file = new File(str);
        if (!file.exists() || file.length() <= 0) {
            return false;
        }
        context.startActivity(m.a().l().getInstallIntent(str));
        return true;
    }

    private void b(com.baidu.mobads.command.a aVar) {
        if (com.baidu.mobads.production.a.b() != null) {
            IXAppInfo a = a(aVar);
            if (a != null) {
                com.baidu.mobads.production.a.b().getXMonitorActivation(this.a, this.e).addAppInfoForMonitor(a);
            } else {
                this.e.e("addAppInfoForMonitor error, appInfo is null");
            }
        }
    }

    public static IXAppInfo a(com.baidu.mobads.command.a aVar) {
        if (aVar == null) {
            return null;
        }
        IXAdContainerFactory b = com.baidu.mobads.production.a.b();
        if (b == null) {
            return null;
        }
        IXAppInfo createAppInfo = b.createAppInfo();
        createAppInfo.setAdId(aVar.g());
        createAppInfo.setAppSize(aVar.e());
        createAppInfo.setClickTime(aVar.c());
        createAppInfo.setPackageName(aVar.d());
        createAppInfo.setQk(aVar.h());
        createAppInfo.setProd(aVar.i());
        createAppInfo.setTooLarge(aVar.f());
        return createAppInfo;
    }
}
