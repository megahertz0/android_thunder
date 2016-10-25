package com.qq.e.ads.nativ;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.qq.e.ads.cfg.BrowserType;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.NADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class NativeAD {
    private NADI a;
    private NativeAdListener b;
    private boolean c;
    private boolean d;
    private List<Integer> e;
    private boolean f;
    private BrowserType g;
    private DownAPPConfirmPolicy h;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ Context a;
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
                        NativeAD.this.a = this.a.getNativeADDelegate(AnonymousClass_1.this.a, AnonymousClass_1.this, AnonymousClass_1.this.c, new ADListenerAdapter((byte) 0));
                        NativeAD.this.f = true;
                        if (NativeAD.this.g != null) {
                            NativeAD.this.setBrowserType(NativeAD.this.g);
                        }
                        if (NativeAD.this.h != null) {
                            NativeAD.this.setDownAPPConfirmPolicy(NativeAD.this.h);
                        }
                        for (Integer num : NativeAD.this.e) {
                            NativeAD.this.loadAD(num.intValue());
                        }
                    }
                    NativeAD.this.f = true;
                } catch (Throwable th) {
                    GDTLogger.e("Exception while init Native Core", th);
                    NativeAD.this.f = true;
                }
            }
        }

        AnonymousClass_1(Context context, String str, String str2) {
            this.a = context;
            this.b = str;
            this.c = str2;
        }

        public void run() {
            if (GDTADManager.getInstance().initWith(this.a, this.b)) {
                try {
                    new Handler(Looper.getMainLooper()).post(new AnonymousClass_1(GDTADManager.getInstance().getPM().getPOFactory()));
                    return;
                } catch (Throwable th) {
                    GDTLogger.e("Exception while init Native plugin", th);
                }
            }
            GDTLogger.e("Fail to init ADManager");
        }
    }

    class ADListenerAdapter implements ADListener {
        private ADListenerAdapter() {
        }

        public void onADEvent(ADEvent aDEvent) {
            if (NativeAD.this.b == null) {
                GDTLogger.i("No DevADListener Binded");
                return;
            }
            switch (aDEvent.getType()) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        NativeAD.this.b.onNoAD(((Integer) aDEvent.getParas()[0]).intValue());
                    } else {
                        GDTLogger.e(new StringBuilder("AdEvent.Paras error for NativeAD(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof List)) {
                        NativeAD.this.b.onADLoaded((List) aDEvent.getParas()[0]);
                    } else {
                        GDTLogger.e(new StringBuilder("ADEvent.Paras error for NativeAD(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeADDataRef)) {
                        NativeAD.this.b.onADStatusChanged((NativeADDataRef) aDEvent.getParas()[0]);
                    } else {
                        GDTLogger.e(new StringBuilder("ADEvent.Paras error for NativeAD(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    if (aDEvent.getParas().length == 2 && (aDEvent.getParas()[0] instanceof NativeADDataRef) && (aDEvent.getParas()[1] instanceof Integer)) {
                        NativeAD.this.b.onADError((NativeADDataRef) aDEvent.getParas()[0], ((Integer) aDEvent.getParas()[1]).intValue());
                    } else {
                        GDTLogger.e(new StringBuilder("ADEvent.Paras error for NativeAD(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                default:
                    break;
            }
        }
    }

    public static interface NativeAdListener {
        void onADError(NativeADDataRef nativeADDataRef, int i);

        void onADLoaded(List<NativeADDataRef> list);

        void onADStatusChanged(NativeADDataRef nativeADDataRef);

        void onNoAD(int i);
    }

    public NativeAD(Context context, String str, String str2, NativeAdListener nativeAdListener) {
        this.e = new ArrayList();
        this.f = false;
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || context == null) {
            GDTLogger.e(String.format("GDTNativeAd Contructor paras error,appid=%s,posId=%s,context=%s", new Object[]{str, str2, context}));
            return;
        }
        this.c = true;
        if (a.a(context)) {
            this.d = true;
            this.b = nativeAdListener;
            GDTADManager.INIT_EXECUTOR.execute(new AnonymousClass_1(context, str, str2));
            return;
        }
        GDTLogger.e("Required Activity/Service/Permission Not Declared in AndroidManifest.xml");
    }

    public void loadAD(int i) {
        if (!this.c || !this.d) {
            GDTLogger.e("AD init Paras OR Context error,details in logs produced while init NativeAD");
        } else if (!this.f) {
            this.e.add(Integer.valueOf(i));
        } else if (this.a != null) {
            this.a.loadAd(i);
        } else {
            GDTLogger.e("NativeAD Init error,See More Logs");
        }
    }

    public void setBrowserType(BrowserType browserType) {
        this.g = browserType;
        if (this.a != null && browserType != null) {
            this.a.setBrowserType(browserType.value());
        }
    }

    public void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.h = downAPPConfirmPolicy;
        if (this.a != null && downAPPConfirmPolicy != null) {
            this.a.setDownAPPConfirmPolicy(downAPPConfirmPolicy);
        }
    }
}
