package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.InMobiNativeStrand.NativeStrandAdListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyAgent;

public final class InMobiNativeStrand {
    private static final String TAG;
    private a mClientCallbackHandler;
    private Context mContext;
    private a mInteractionCallback;
    private q mNativeStrandAd;
    private NativeStrandAdListener mNativeStrandAdListener;
    private s mNativeStrandAdUnit;

    public static interface NativeStrandAdListener {
        void onAdClicked(InMobiNativeStrand inMobiNativeStrand);

        void onAdImpressed(InMobiNativeStrand inMobiNativeStrand);

        void onAdLoadFailed(InMobiNativeStrand inMobiNativeStrand, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiNativeStrand inMobiNativeStrand);
    }

    private static final class a extends Handler {
        private WeakReference<InMobiNativeStrand> a;

        public a(InMobiNativeStrand inMobiNativeStrand) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(inMobiNativeStrand);
        }

        public final void handleMessage(Message message) {
            InMobiNativeStrand inMobiNativeStrand = (InMobiNativeStrand) this.a.get();
            if (inMobiNativeStrand == null) {
                Logger.a(InternalLogLevel.ERROR, TAG, "Lost reference to InMobiNativeStrand! callback cannot be given");
                return;
            }
            NativeStrandAdListener access$500 = inMobiNativeStrand.mNativeStrandAdListener;
            if (access$500 == null) {
                Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNative Strand is already destroyed ! callback cannot be given");
                return;
            }
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    inMobiNativeStrand.mNativeStrandAdListener.onAdLoadSucceeded(inMobiNativeStrand);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    inMobiNativeStrand.mNativeStrandAdListener.onAdLoadFailed(inMobiNativeStrand, (InMobiAdRequestStatus) message.obj);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                case XZBDevice.DOWNLOAD_LIST_ALL:
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                case R.styleable.Toolbar_contentInsetEnd:
                    break;
                case R.styleable.Toolbar_contentInsetLeft:
                    access$500.onAdImpressed(inMobiNativeStrand);
                case XZBDevice.Wait:
                    access$500.onAdClicked(inMobiNativeStrand);
                default:
                    Logger.a(InternalLogLevel.INTERNAL, TAG, "Unhandled ad lifecycle event! Ignoring ...");
            }
        }
    }

    static {
        TAG = InMobiNativeStrand.class.getSimpleName();
    }

    public InMobiNativeStrand(Context context, long j, NativeStrandAdListener nativeStrandAdListener) {
        this.mInteractionCallback = new a() {
            public void a() {
                Message obtain = Message.obtain();
                obtain.what = 7;
                InMobiNativeStrand.this.mClientCallbackHandler.sendMessage(obtain);
            }

            public void b() {
                Message obtain = Message.obtain();
                obtain.what = 8;
                InMobiNativeStrand.this.mClientCallbackHandler.sendMessage(obtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please initialize the InMobiSdk before trying to instantiate InMobiNativeStrand.");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Context is null, Native Strand cannot be created.");
        } else if (!(context instanceof Activity)) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Context is not Activity, Native Strand cannot be created.");
        } else if (nativeStrandAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Listener supplied is null, the Native Strand cannot be created.");
        } else {
            a anonymousClass_2 = new a() {
                public void a() {
                    if (InMobiNativeStrand.this.mNativeStrandAdUnit == null) {
                        Logger.a(InternalLogLevel.INTERNAL, TAG, "NativeStrandUnit already destroyed, ignoring ad load success");
                        return;
                    }
                    InMobiNativeStrand.this.mNativeStrandAd = InMobiNativeStrand.this.mNativeStrandAdUnit.x();
                    if (InMobiNativeStrand.this.mNativeStrandAd != null) {
                        InMobiNativeStrand.this.mNativeStrandAd.a(InMobiNativeStrand.this.mInteractionCallback);
                        InMobiNativeStrand.this.mClientCallbackHandler.sendEmptyMessage(1);
                        Logger.a(InternalLogLevel.INTERNAL, TAG, "Ad load successful notifying app");
                    }
                }

                public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = inMobiAdRequestStatus;
                    InMobiNativeStrand.this.mClientCallbackHandler.sendMessage(obtain);
                }

                public void b() {
                }

                public void c() {
                }

                public void a(Map<Object, Object> map) {
                }

                public void d() {
                }

                public void b(Map<Object, Object> map) {
                }
            };
            this.mContext = context;
            this.mNativeStrandAdUnit = new s(context, j, new Integer[0], anonymousClass_2);
            this.mNativeStrandAdListener = nativeStrandAdListener;
            this.mClientCallbackHandler = new a(this);
        }
    }

    public final void load() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized. Ignoring InMobiNativeStrand.load()");
        } else if (this.mNativeStrandAdUnit == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized, ignoring InMobiNativeStrand.load()");
        } else {
            deInit();
            Logger.a(InternalLogLevel.INTERNAL, TAG, "Ad not loaded already requesting it now");
            this.mNativeStrandAdUnit.o();
        }
    }

    public final View getStrandView(View view, ViewGroup viewGroup) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized.Ignoring InMobiNativeStrand.getStrandView()");
            return null;
        } else if (this.mNativeStrandAdUnit == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized.Ignoring InMobiNativeStrand.getStrandView()");
            return null;
        } else if (this.mNativeStrandAd == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "No ad is available. Call load to request for ad or wait for wait for callback if ad load is already requested");
            return null;
        } else {
            View a = this.mNativeStrandAd.a(view, viewGroup, this.mNativeStrandAdUnit.l().l());
            if (a != null) {
                return a;
            }
            com.inmobi.commons.core.c.a.a().a("ads", "StrandInflationFailed", new HashMap());
            Logger.a(InternalLogLevel.INTERNAL, TAG, "Error inflating view even with a valid data model!");
            return null;
        }
    }

    public final void setExtras(Map<String, String> map) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized.Ignoring InMobiNativeStrand.setExtras()");
        } else if (this.mNativeStrandAdUnit == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized, ignoring setExtras.");
        } else {
            this.mNativeStrandAdUnit.a(map);
        }
    }

    public final void setKeywords(String str) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized.Ignoring InMobiNativeStrand.setKeywords()");
        } else if (this.mNativeStrandAdUnit == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized, ignoring setKeywords().");
        } else {
            this.mNativeStrandAdUnit.a(str);
        }
    }

    public final void destroy() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "InMobiNativeStrand is not initialized. Ignoring InMobiNativeStrand.destroy()");
        }
        if (this.mClientCallbackHandler != null) {
            this.mClientCallbackHandler.removeMessages(0);
        }
        this.mNativeStrandAdListener = null;
        if (this.mNativeStrandAdUnit != null) {
            this.mNativeStrandAdUnit.y();
            this.mNativeStrandAdUnit = null;
        }
        deInit();
    }

    private void deInit() {
        if (this.mNativeStrandAd != null) {
            this.mNativeStrandAd.c();
            this.mNativeStrandAd = null;
        }
    }
}
