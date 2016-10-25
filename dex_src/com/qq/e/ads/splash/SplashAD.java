package com.qq.e.ads.splash;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.constants.ErrorCode.OtherError;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.NSPVI;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.common.Constants;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public final class SplashAD {
    private NSPVI a;
    private SplashADListener b;

    class ADListenerAdapter implements ADListener {
        private ADListenerAdapter() {
        }

        public void onADEvent(ADEvent aDEvent) {
            if (SplashAD.this.b == null) {
                GDTLogger.e("SplashADListener == null");
                return;
            }
            switch (aDEvent.getType()) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    SplashAD.this.b.onADDismissed();
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        SplashAD.this.b.onNoAD(((Integer) aDEvent.getParas()[0]).intValue());
                    } else {
                        GDTLogger.e("Splash ADEvent error,");
                    }
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    SplashAD.this.b.onADPresent();
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    SplashAD.this.b.onADClicked();
                default:
                    break;
            }
        }
    }

    public SplashAD(Activity activity, ViewGroup viewGroup, String str, String str2, SplashADListener splashADListener) {
        this(activity, viewGroup, str, str2, splashADListener, 0);
    }

    public SplashAD(Activity activity, ViewGroup viewGroup, String str, String str2, SplashADListener splashADListener, int i) {
        this.b = splashADListener;
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || viewGroup == null || activity == null) {
            GDTLogger.e(String.format("SplashAd Constructor paras error,appid=%s,posId=%s,context=%s", new Object[]{str, str2, activity}));
            a(splashADListener, XLErrorCode.OAUTH_PARAM_ERROR);
        } else if (a.a((Context) activity)) {
            try {
                if (GDTADManager.getInstance().initWith(activity, str)) {
                    this.a = GDTADManager.getInstance().getPM().getPOFactory().getNativeSplashAdView(activity, str, str2);
                    if (this.a != null) {
                        this.a.setFetchDelay(i);
                        this.a.setAdListener(new ADListenerAdapter());
                        this.a.fetchAndShowIn(viewGroup);
                        return;
                    }
                    GDTLogger.e("SplashAdView created by factory return null");
                    a(splashADListener, ErrorCode.DM_APPKEY_INVALID);
                    return;
                }
                GDTLogger.e("Fail to Init GDT AD SDK,report logcat info filter by gdt_ad_mob");
                a(splashADListener, Constants.COMMAND_STOP_FOR_ELECTION);
            } catch (Throwable e) {
                GDTLogger.e("Fail to init splash plugin", e);
                a(splashADListener, ErrorCode.DM_DEVICEID_INVALID);
            } catch (Throwable e2) {
                GDTLogger.e("Unknown Exception", e2);
                a(splashADListener, OtherError.UNKNOWN_ERROR);
            }
        } else {
            GDTLogger.e("Required Activity/Service/Permission Not Declared in AndroidManifest.xml");
            a(splashADListener, XLErrorCode.OAUTH_PARAM_ERROR);
        }
    }

    private static void a(SplashADListener splashADListener, int i) {
        if (splashADListener != null) {
            splashADListener.onNoAD(i);
        }
    }
}
