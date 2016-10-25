package com.qq.e.ads.banner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.qq.e.ads.cfg.BannerRollAnimation;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.BVI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

@SuppressLint({"ViewConstructor"})
public class BannerView extends FrameLayout {
    private BVI a;
    private BannerADListener b;
    private boolean c;
    private boolean d;
    private boolean e;
    private Integer f;
    private BannerRollAnimation g;
    private DownAPPConfirmPolicy h;
    private Boolean i;
    private volatile int j;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ Activity a;
        final /* synthetic */ String b;
        final /* synthetic */ ADSize c;
        final /* synthetic */ String d;

        class AnonymousClass_1 implements Runnable {
            private /* synthetic */ POFactory a;

            AnonymousClass_1(POFactory pOFactory) {
                this.a = pOFactory;
            }

            public void run() {
                try {
                    if (this.a != null) {
                        BannerView.this.a = this.a.getBannerView(AnonymousClass_1.this.a, AnonymousClass_1.this.c, AnonymousClass_1.this, AnonymousClass_1.this.d);
                        BannerView.this.a.setAdListener(new ADListenerAdapter((byte) 0));
                        BannerView.this.addView(BannerView.this.a.getView());
                        BannerView.this = true;
                        if (BannerView.this.h != null) {
                            BannerView.this.setDownConfirmPilicy(BannerView.this.h);
                        }
                        if (BannerView.this.f != null) {
                            BannerView.this.setRefresh(BannerView.this.f.intValue());
                        }
                        if (BannerView.this.g != null) {
                            BannerView.this.setRollAnimation(BannerView.this.g);
                        }
                        if (BannerView.this.i != null) {
                            BannerView.this.setShowClose(BannerView.this.i.booleanValue());
                        }
                        while (BannerView.g(BannerView.this) > 0) {
                            BannerView.this.loadAD();
                        }
                    }
                    BannerView.this = true;
                } catch (Throwable th) {
                    GDTLogger.e("Exception while init Banner Core", th);
                    BannerView.this = true;
                }
            }
        }

        AnonymousClass_1(Activity activity, String str, ADSize aDSize, String str2) {
            this.a = activity;
            this.b = str;
            this.c = aDSize;
            this.d = str2;
        }

        public void run() {
            if (GDTADManager.getInstance().initWith(this.a, this.b)) {
                try {
                    new Handler(Looper.getMainLooper()).post(new AnonymousClass_1(GDTADManager.getInstance().getPM().getPOFactory()));
                    return;
                } catch (Throwable th) {
                    GDTLogger.e("Exception while init Banner plugin", th);
                }
            }
            GDTLogger.e("Fail to init ADManager");
        }
    }

    class ADListenerAdapter implements ADListener {
        private ADListenerAdapter() {
        }

        public void onADEvent(ADEvent aDEvent) {
            if (BannerView.this.b == null) {
                GDTLogger.i("No DevADListener Binded");
                return;
            }
            switch (aDEvent.getType()) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        BannerView.this.b.onNoAD(((Integer) aDEvent.getParas()[0]).intValue());
                    } else {
                        GDTLogger.e(new StringBuilder("AdEvent.Paras error for Banner(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    BannerView.this.b.onADReceiv();
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    BannerView.this.b.onADExposure();
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    BannerView.this.b.onADClosed();
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    BannerView.this.b.onADClicked();
                case R.styleable.Toolbar_contentInsetEnd:
                    BannerView.this.b.onADLeftApplication();
                case R.styleable.Toolbar_contentInsetLeft:
                    BannerView.this.b.onADOpenOverlay();
                case XZBDevice.Wait:
                    BannerView.this.b.onADCloseOverlay();
                default:
                    break;
            }
        }
    }

    public BannerView(Activity activity, ADSize aDSize, String str, String str2) {
        super(activity);
        this.c = false;
        this.d = false;
        this.e = false;
        this.j = 0;
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || activity == null) {
            GDTLogger.e(String.format("Banner ADView Contructor paras error,appid=%s,posId=%s,context=%s", new Object[]{str, str2, activity}));
            return;
        }
        this.c = true;
        if (a.a((Context) activity)) {
            this.d = true;
            setLayoutParams(new LayoutParams(-1, -2));
            GDTADManager.INIT_EXECUTOR.execute(new AnonymousClass_1(activity, str, aDSize, str2));
            return;
        }
        GDTLogger.e("Required Activity/Service/Permission Not Declared in AndroidManifest.xml");
    }

    static /* synthetic */ int g(BannerView bannerView) {
        int i = bannerView.j;
        bannerView.j = i - 1;
        return i;
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public void loadAD() {
        if (!this.c || !this.d) {
            GDTLogger.e("Banner init Paras OR Context error,See More logs while new BannerView");
        } else if (!this.e) {
            this.j++;
        } else if (this.a != null) {
            this.a.fetchAd();
        } else {
            GDTLogger.e("Banner Init error,See More Logs");
        }
    }

    public void setADListener(BannerADListener bannerADListener) {
        this.b = bannerADListener;
    }

    public void setDownConfirmPilicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.h = downAPPConfirmPolicy;
        if (downAPPConfirmPolicy != null && this.a != null) {
            this.a.setDownAPPConfirmPolicy(downAPPConfirmPolicy.value());
        }
    }

    public void setRefresh(int i) {
        this.f = Integer.valueOf(i);
        if (i < 30 && i != 0) {
            i = 30;
        } else if (i > 120) {
            i = 120;
        }
        if (this.a != null) {
            this.a.setRefresh(i);
        }
    }

    public void setRollAnimation(BannerRollAnimation bannerRollAnimation) {
        this.g = bannerRollAnimation;
        if (bannerRollAnimation != null && this.a != null) {
            this.a.setRollAnimation(bannerRollAnimation.value());
        }
    }

    public void setShowClose(boolean z) {
        this.i = Boolean.valueOf(z);
        if (this.a != null) {
            this.a.setShowCloseButton(z);
        }
    }
}
