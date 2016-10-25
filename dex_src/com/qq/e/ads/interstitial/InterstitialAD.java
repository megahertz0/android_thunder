package com.qq.e.ads.interstitial;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.IADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

@SuppressLint({"ViewConstructor"})
public class InterstitialAD {
    private IADI a;
    private InterstitialADListener b;
    private boolean c;
    private boolean d;
    private boolean e;
    private volatile int f;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ Activity a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;

        class AnonymousClass_1 implements Runnable {
            private /* synthetic */ POFactory a;

            AnonymousClass_1(POFactory pOFactory) {
                this.a = pOFactory;
            }

            public void run() {
                try {
                    if (this.a != null) {
                        InterstitialAD.this.a = this.a.getIADView(AnonymousClass_1.this.a, AnonymousClass_1.this, AnonymousClass_1.this.c);
                        InterstitialAD.this.a.setAdListener(new ADListenerAdapter((byte) 0));
                        InterstitialAD.this.e = true;
                        while (InterstitialAD.c(InterstitialAD.this) > 0) {
                            InterstitialAD.this.loadAD();
                        }
                    }
                    InterstitialAD.this.e = true;
                } catch (Throwable th) {
                    GDTLogger.e("Exception while init IAD Core", th);
                    InterstitialAD.this.e = true;
                }
            }
        }

        AnonymousClass_1(Activity activity, String str, String str2) {
            this.a = activity;
            this.b = str;
            this.c = str2;
        }

        public void run() {
            if (GDTADManager.getInstance().initWith(this.a, this.b)) {
                try {
                    new Handler(Looper.getMainLooper()).post(new AnonymousClass_1(GDTADManager.getInstance().getPM().getPOFactory()));
                    return;
                } catch (Throwable th) {
                    GDTLogger.e("Exception while init IAD plugin", th);
                }
            }
            GDTLogger.e("Fail to init ADManager");
        }
    }

    class ADListenerAdapter implements ADListener {
        private ADListenerAdapter() {
        }

        public void onADEvent(ADEvent aDEvent) {
            if (InterstitialAD.this.b == null) {
                GDTLogger.i("No DevADListener Binded");
                return;
            }
            switch (aDEvent.getType()) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        InterstitialAD.this.b.onNoAD(((Integer) aDEvent.getParas()[0]).intValue());
                    } else {
                        GDTLogger.e(new StringBuilder("AdEvent.Paras error for Banner(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    InterstitialAD.this.b.onADReceive();
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    InterstitialAD.this.b.onADExposure();
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    InterstitialAD.this.b.onADOpened();
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    InterstitialAD.this.b.onADClicked();
                case R.styleable.Toolbar_contentInsetEnd:
                    InterstitialAD.this.b.onADLeftApplication();
                case R.styleable.Toolbar_contentInsetLeft:
                    InterstitialAD.this.b.onADClosed();
                default:
                    break;
            }
        }
    }

    public InterstitialAD(Activity activity, String str, String str2) {
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = 0;
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || activity == null) {
            GDTLogger.e(String.format("Intersitial Contructor paras error,appid=%s,posId=%s,context=%s", new Object[]{str, str2, activity}));
            return;
        }
        this.c = true;
        if (a.a((Context) activity)) {
            this.d = true;
            GDTADManager.INIT_EXECUTOR.execute(new AnonymousClass_1(activity, str, str2));
            return;
        }
        GDTLogger.e("Required Activity/Service/Permission Not Declared in AndroidManifest.xml");
    }

    static /* synthetic */ int c(InterstitialAD interstitialAD) {
        int i = interstitialAD.f;
        interstitialAD.f = i - 1;
        return i;
    }

    public void closePopupWindow() {
        if (this.a != null) {
            this.a.closePopupWindow();
        }
    }

    public void destory() {
        if (this.a != null) {
            this.a.destory();
        }
    }

    public void loadAD() {
        if (!this.c || !this.d) {
            GDTLogger.e("InterstitialAD init Paras OR Context error,See More logs while new InterstitialAD");
        } else if (!this.e) {
            this.f++;
        } else if (this.a != null) {
            this.a.loadAd();
        } else {
            GDTLogger.e("InterstitialAD Init error,See More Logs");
        }
    }

    public void setADListener(InterstitialADListener interstitialADListener) {
        this.b = interstitialADListener;
    }

    public synchronized void show() {
        if (this.a != null) {
            this.a.show();
        }
    }

    public synchronized void show(Activity activity) {
        if (this.a != null) {
            this.a.show(activity);
        }
    }

    public synchronized void showAsPopupWindow() {
        if (this.a != null) {
            this.a.showAsPopupWindown();
        }
    }

    public synchronized void showAsPopupWindow(Activity activity) {
        if (this.a != null) {
            this.a.showAsPopupWindown(activity);
        }
    }
}
