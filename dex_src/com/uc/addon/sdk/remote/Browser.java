package com.uc.addon.sdk.remote;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v4.media.TransportMediator;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.uc.addon.sdk.remote.protocol.IApp;
import com.uc.addon.sdk.remote.protocol.IValueCallback;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.R;
import java.util.List;

public class Browser {
    public static final String RUN_POLICY_NO_NEED_BACK = "UCM_NO_NEED_BACK";
    public static final String RUN_POLICY_ONE_WINDOW = "UCM_ONE_WINDOW";
    public static final int STATE_BACKGROUND = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_FOREGROUND = 1;
    public static final int STATE_NORUNNING = 0;
    public static final int STATE_PERCEPTIBLE = 4;
    public static final int STATE_SERVICE = 5;
    public static final int STATE_UNKNOW = -1;
    public static final int STATE_VISIBLE = 6;
    private Context a;
    private String b;
    private String c;
    private BrowserImpl d;
    public Download download;
    public FileManager fileManager;
    public History history;
    public Handler mHandler;
    public Navigation navigation;
    public Stat stat;
    public Tabs tabs;
    public Util util;

    class AnonymousClass_1 implements Runnable {
        private /* synthetic */ String a;
        private /* synthetic */ Bundle b;
        private /* synthetic */ IValueCallback c;

        AnonymousClass_1(String str, Bundle bundle, IValueCallback iValueCallback) {
            this.a = str;
            this.b = bundle;
            this.c = iValueCallback;
        }

        public void run() {
            Browser.this.request(this.a, this.b, this.c);
        }
    }

    final void a(IApp iApp, Context context, Handler handler) {
        this.a = context;
        this.mHandler = handler;
        try {
            String[] applicationInfo = iApp.getApplicationInfo();
            if (applicationInfo != null) {
                this.b = applicationInfo[0];
                this.c = applicationInfo[1];
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.d = new BrowserImpl(iApp);
        this.d.init(context, this);
        this.tabs = new TabsImpl(iApp, this.d);
        this.util = new UtilImpl(iApp, this.mHandler);
        this.download = new DownloadImpl(iApp);
        this.stat = new StatImpl(iApp);
        this.navigation = new NavigationImpl(iApp);
        this.fileManager = new FileManagerImpl(iApp);
        this.history = new HistoryImpl(iApp);
    }

    final void a(String str, Bundle bundle, IValueCallback iValueCallback) {
        this.mHandler.post(new AnonymousClass_1(str, bundle, iValueCallback));
    }

    public void disConnect() {
        if (this.d != null) {
            this.d.b();
        }
    }

    public String getBrowserPackageName() {
        return this.b;
    }

    public int getBrowserState() {
        String str = this.b;
        if (str != null) {
            ActivityManager activityManager = (ActivityManager) this.a.getSystemService("activity");
            if (activityManager != null) {
                List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (str.equals(runningAppProcessInfo.processName)) {
                            switch (runningAppProcessInfo.importance) {
                                case R.styleable.AppCompatTheme_buttonStyle:
                                    return STATE_FOREGROUND;
                                case TransportMediator.KEYCODE_MEDIA_RECORD:
                                    return STATE_PERCEPTIBLE;
                                case Impl.STATUS_SUCCESS:
                                    return STATE_VISIBLE;
                                case XLErrorCode.OAUTH_PARAM_ERROR:
                                    return STATE_SERVICE;
                                case Impl.STATUS_BAD_REQUEST:
                                    return STATE_BACKGROUND;
                                case Impl.STATUS_PEER_NOT_FOUND_ERROR:
                                    return STATE_EMPTY;
                                default:
                                    return -1;
                            }
                        }
                    }
                    return STATE_NORUNNING;
                }
            }
        }
        return -1;
    }

    public void runBrowser(String str, String str2) {
        Intent intent = new Intent("com.UCMobile.intent.action.INVOKE");
        if (!(this.b == null || this.c == null)) {
            intent.setClassName(this.b, this.c);
        }
        if (str != null) {
            intent.putExtra(IXAdRequestInfo.PHONE_TYPE, "UCM_OPENURL");
            intent.putExtra("openurl", str);
        }
        if (str2 != null) {
            intent.putExtra("policy", str2);
        }
        intent.putExtra("pd", new StringBuilder("addon:").append(this.a.getPackageName()).toString());
        intent.addFlags(268435456);
        this.a.startActivity(intent);
    }
}
