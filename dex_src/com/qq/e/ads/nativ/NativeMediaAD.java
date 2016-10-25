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
import com.qq.e.comm.pi.NVADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class NativeMediaAD {
    private volatile boolean a;
    private volatile boolean b;
    private List<Integer> c;
    private volatile boolean d;
    private NVADI e;
    private NativeMediaADListener f;
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
                        NativeMediaAD.this.e = this.a.getNativeVideoADDelegate(AnonymousClass_1.this.a, AnonymousClass_1.this, AnonymousClass_1.this.c, new ADListenerAdapter((byte) 0));
                        NativeMediaAD.this = true;
                        if (NativeMediaAD.this.g != null) {
                            NativeMediaAD.this.setBrowserType(NativeMediaAD.this.g);
                        }
                        if (NativeMediaAD.this.h != null) {
                            NativeMediaAD.this.setDownAPPConfirmPolicy(NativeMediaAD.this.h);
                        }
                        for (Integer num : NativeMediaAD.this.c) {
                            NativeMediaAD.this.loadAD(num.intValue());
                        }
                    }
                    NativeMediaAD.this = true;
                } catch (Throwable th) {
                    GDTLogger.e("Exception while init NativeMediaAD Core", th);
                    NativeMediaAD.this = true;
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
                    GDTLogger.e("Exception while init NativeMediaAD plugin", th);
                }
            }
            GDTLogger.e("Fail to init ADManager");
        }
    }

    class ADListenerAdapter implements ADListener {
        private ADListenerAdapter() {
        }

        public void onADEvent(ADEvent aDEvent) {
            if (NativeMediaAD.this.f == null) {
                GDTLogger.i("No DevADListener Binded");
                return;
            }
            switch (aDEvent.getType()) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        NativeMediaAD.this.f.onNoAD(((Integer) aDEvent.getParas()[0]).intValue());
                    } else {
                        GDTLogger.e(new StringBuilder("AdEvent.Paras error for NativeMediaAD(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof List)) {
                        NativeMediaAD.this.f.onADLoaded((List) aDEvent.getParas()[0]);
                    } else {
                        GDTLogger.e(new StringBuilder("ADEvent.Paras error for NativeMediaAD(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeMediaADData)) {
                        NativeMediaAD.this.f.onADStatusChanged((NativeMediaADData) aDEvent.getParas()[0]);
                    } else {
                        GDTLogger.e(new StringBuilder("ADEvent.Paras error for NativeMediaAD(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    if (aDEvent.getParas().length == 2 && (aDEvent.getParas()[0] instanceof NativeADDataRef) && (aDEvent.getParas()[1] instanceof Integer)) {
                        NativeMediaAD.this.f.onADError((NativeMediaADData) aDEvent.getParas()[0], ((Integer) aDEvent.getParas()[1]).intValue());
                    } else {
                        GDTLogger.e(new StringBuilder("ADEvent.Paras error for NativeMediaAD(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeMediaADData)) {
                        NativeMediaAD.this.f.onADVideoLoaded((NativeMediaADData) aDEvent.getParas()[0]);
                    } else {
                        GDTLogger.e(new StringBuilder("ADEvent.Paras error for NativeMediaAD(").append(aDEvent).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                    }
                default:
                    break;
            }
        }
    }

    public static interface NativeMediaADListener {
        void onADError(NativeMediaADData nativeMediaADData, int i);

        void onADLoaded(List<NativeMediaADData> list);

        void onADStatusChanged(NativeMediaADData nativeMediaADData);

        void onADVideoLoaded(NativeMediaADData nativeMediaADData);

        void onNoAD(int i);
    }

    public NativeMediaAD(Context context, String str, String str2, NativeMediaADListener nativeMediaADListener) {
        this.c = new ArrayList();
        this.d = false;
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || context == null) {
            GDTLogger.e(String.format("NativeMediaAD Contructor paras error, appid=%s, posId=%s, context=%s", new Object[]{str, str2, context}));
            return;
        }
        this.a = true;
        if (a.a(context)) {
            this.b = true;
            this.f = nativeMediaADListener;
            GDTADManager.INIT_EXECUTOR.execute(new AnonymousClass_1(context, str, str2));
            return;
        }
        GDTLogger.e("Required Activity/Service/Permission Not Declared in AndroidManifest.xml");
    }

    public void loadAD(int i) {
        if (!this.a || !this.b) {
            GDTLogger.e("AD init Paras OR Context error, details in logs produced while init NativeMediaAD");
        } else if (!this.d) {
            this.c.add(Integer.valueOf(i));
        } else if (this.e != null) {
            this.e.loadAd(i);
        } else {
            GDTLogger.e("NativeMediaAD Init error, See More Logs");
        }
    }

    public void setBrowserType(BrowserType browserType) {
        this.g = browserType;
        if (this.e != null && browserType != null) {
            this.e.setBrowserType(browserType.value());
        }
    }

    public void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.h = downAPPConfirmPolicy;
        if (this.e != null && downAPPConfirmPolicy != null) {
            this.e.setDownAPPConfirmPolicy(downAPPConfirmPolicy);
        }
    }
}
