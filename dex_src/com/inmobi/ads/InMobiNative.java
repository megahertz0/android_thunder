package com.inmobi.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import com.inmobi.ads.InMobiNative.NativeAdListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import org.android.spdy.SpdyAgent;

public class InMobiNative {
    private static final String TAG;
    protected static WeakHashMap<View, p> sMappedAdUnits;
    private a mClientCallbackHandler;
    private final a mListener;
    private NativeAdListener mNativeAdListener;
    protected p mNativeAdUnit;

    public static interface NativeAdListener {
        void onAdDismissed(InMobiNative inMobiNative);

        void onAdDisplayed(InMobiNative inMobiNative);

        void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiNative inMobiNative);

        void onUserLeftApplication(InMobiNative inMobiNative);
    }

    private static final class a extends Handler {
        private WeakReference<NativeAdListener> a;
        private WeakReference<InMobiNative> b;

        public a(InMobiNative inMobiNative, NativeAdListener nativeAdListener) {
            super(Looper.getMainLooper());
            this.b = new WeakReference(inMobiNative);
            this.a = new WeakReference(nativeAdListener);
        }

        public final void handleMessage(Message message) {
            InMobiNative inMobiNative = (InMobiNative) this.b.get();
            NativeAdListener nativeAdListener = (NativeAdListener) this.a.get();
            if (inMobiNative != null && nativeAdListener != null) {
                switch (message.what) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        nativeAdListener.onAdLoadSucceeded(inMobiNative);
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        nativeAdListener.onAdLoadFailed(inMobiNative, (InMobiAdRequestStatus) message.obj);
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        nativeAdListener.onAdDisplayed(inMobiNative);
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        nativeAdListener.onAdDismissed(inMobiNative);
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        nativeAdListener.onUserLeftApplication(inMobiNative);
                    default:
                        Logger.a(InternalLogLevel.INTERNAL, TAG, "Unhandled ad lifecycle event! Ignoring ...");
                }
            }
        }
    }

    static {
        TAG = InMobiNative.class.getSimpleName();
        sMappedAdUnits = new WeakHashMap();
    }

    public InMobiNative(long j, NativeAdListener nativeAdListener) {
        this.mListener = new a() {
            public void a() {
                InMobiNative.this.mClientCallbackHandler.sendEmptyMessage(1);
            }

            public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                obtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.mClientCallbackHandler.sendMessage(obtain);
            }

            public void b() {
                InMobiNative.this.mClientCallbackHandler.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_FAILED);
            }

            public void c() {
                InMobiNative.this.mClientCallbackHandler.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_ALL);
            }

            public void a(Map<Object, Object> map) {
            }

            public void d() {
                InMobiNative.this.mClientCallbackHandler.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            }

            public void b(Map<Object, Object> map) {
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before trying to create an ad.");
        } else if (nativeAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "The Ad unit cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else {
            this.mNativeAdListener = nativeAdListener;
            this.mNativeAdUnit = new p(j, this.mListener);
            this.mClientCallbackHandler = new a(this, nativeAdListener);
        }
    }

    public final void load() {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.o();
        }
    }

    public final void resume() {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.x();
        }
    }

    public final void pause() {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.y();
        }
    }

    public final Object getAdContent() {
        return this.mNativeAdUnit == null ? null : this.mNativeAdUnit.z();
    }

    public static void bind(View view, InMobiNative inMobiNative) {
        if (view == null || inMobiNative == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please pass non-null instances of a view and InMobiNative to bind.");
            return;
        }
        if (((p) sMappedAdUnits.get(view)) != null) {
            unbind(view);
        }
        p pVar = inMobiNative.mNativeAdUnit;
        if (pVar != null) {
            sMappedAdUnits.remove(view);
            sMappedAdUnits.put(view, pVar);
            pVar.a(view, null, null);
        }
    }

    public static void unbind(View view) {
        if (view == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please pass a non-null view object to bind.");
            return;
        }
        p pVar = (p) sMappedAdUnits.remove(view);
        if (pVar != null) {
            pVar.a(view);
        }
    }

    public final void reportAdClickAndOpenLandingPage(Map<String, String> map) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.a((Map) map, null, null);
            this.mNativeAdUnit.B();
        }
    }

    public final void reportAdClick(Map<String, String> map) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.a((Map) map, null, null);
        }
    }

    public final void setExtras(Map<String, String> map) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.a(map);
        }
    }

    public final void setKeywords(String str) {
        if (this.mNativeAdUnit != null) {
            this.mNativeAdUnit.a(str);
        }
    }
}
