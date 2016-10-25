package com.inmobi.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.InMobiInterstitial.InterstitialAdListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.android.spdy.SpdyAgent;

public final class InMobiInterstitial {
    private static final String TAG;
    private a mClientCallbackHandler;
    private Context mContext;
    private Map<String, String> mExtras;
    private final a mInterstitialAdListener;
    private o mInterstitialAdUnit;
    private boolean mIsHardwareAccelerationDisabled;
    private boolean mIsInitialized;
    private String mKeywords;
    private InterstitialAdListener mListener;
    private long mPlacementId;

    public static interface InterstitialAdListener {
        void onAdDismissed(InMobiInterstitial inMobiInterstitial);

        void onAdDisplayed(InMobiInterstitial inMobiInterstitial);

        void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial);

        void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onUserLeftApplication(InMobiInterstitial inMobiInterstitial);
    }

    private static final class a extends Handler {
        private WeakReference<InterstitialAdListener> a;
        private WeakReference<InMobiInterstitial> b;
        private boolean c;

        public a(InMobiInterstitial inMobiInterstitial, InterstitialAdListener interstitialAdListener) {
            super(Looper.getMainLooper());
            this.c = false;
            this.b = new WeakReference(inMobiInterstitial);
            this.a = new WeakReference(interstitialAdListener);
        }

        public final void a() {
            this.c = false;
        }

        public final void handleMessage(Message message) {
            Map map = null;
            InMobiInterstitial inMobiInterstitial = (InMobiInterstitial) this.b.get();
            InterstitialAdListener interstitialAdListener = (InterstitialAdListener) this.a.get();
            if (inMobiInterstitial != null && interstitialAdListener != null) {
                switch (message.what) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if (!this.c) {
                            this.c = true;
                            interstitialAdListener.onAdLoadSucceeded(inMobiInterstitial);
                        }
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        interstitialAdListener.onAdLoadFailed(inMobiInterstitial, (InMobiAdRequestStatus) message.obj);
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        interstitialAdListener.onAdDisplayed(inMobiInterstitial);
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        interstitialAdListener.onAdDismissed(inMobiInterstitial);
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        if (message.obj != null) {
                            map = (Map) message.obj;
                        }
                        interstitialAdListener.onAdInteraction(inMobiInterstitial, map);
                    case R.styleable.Toolbar_contentInsetEnd:
                        interstitialAdListener.onUserLeftApplication(inMobiInterstitial);
                    case R.styleable.Toolbar_contentInsetLeft:
                        if (message.obj != null) {
                            map = (Map) message.obj;
                        }
                        interstitialAdListener.onAdRewardActionCompleted(inMobiInterstitial, map);
                    default:
                        Logger.a(InternalLogLevel.INTERNAL, TAG, "Unhandled ad lifecycle event! Ignoring ...");
                }
            }
        }
    }

    static {
        TAG = InMobiInterstitial.class.getSimpleName();
    }

    public InMobiInterstitial(Context context, long j, InterstitialAdListener interstitialAdListener) {
        this.mIsInitialized = false;
        this.mInterstitialAdListener = new a() {
            public void a() {
                InMobiInterstitial.this.mClientCallbackHandler.sendEmptyMessage(1);
            }

            public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                obtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.mClientCallbackHandler.sendMessage(obtain);
            }

            public void b() {
                InMobiInterstitial.this.mClientCallbackHandler.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_FAILED);
            }

            public void c() {
                i.a().b(InMobiInterstitial.this.getPlacementObj());
                InMobiInterstitial.this.mClientCallbackHandler.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_ALL);
            }

            public void a(Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 5;
                obtain.obj = map;
                InMobiInterstitial.this.mClientCallbackHandler.sendMessage(obtain);
            }

            public void d() {
                InMobiInterstitial.this.mClientCallbackHandler.sendEmptyMessage(R.styleable.Toolbar_contentInsetEnd);
            }

            public void b(Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 7;
                obtain.obj = map;
                InMobiInterstitial.this.mClientCallbackHandler.sendMessage(obtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before trying to create an ad.");
        } else if (interstitialAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "The Ad unit cannot be created as no event listener was supplied. Please attach a listener to proceed");
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Unable to create ad unit with NULL context object.");
        } else {
            this.mIsInitialized = true;
            this.mContext = context;
            this.mPlacementId = j;
            this.mListener = interstitialAdListener;
            this.mClientCallbackHandler = new a(this, this.mListener);
        }
    }

    public final void setKeywords(String str) {
        if (this.mIsInitialized) {
            this.mKeywords = str;
        }
    }

    private ak getPlacementObj() {
        ak akVar = new ak(this.mPlacementId, i.a(this.mExtras));
        akVar.a(this.mKeywords);
        akVar.a(this.mExtras);
        return akVar;
    }

    public final void load() {
        if (this.mIsInitialized) {
            AdUnit a = i.a().a(getPlacementObj());
            this.mClientCallbackHandler.a();
            if (a != null) {
                this.mInterstitialAdUnit = (o) a;
                this.mInterstitialAdUnit.a(this.mInterstitialAdListener);
                setupAdUnit();
                if (this.mInterstitialAdUnit.g() == AdState.STATE_READY) {
                    this.mInterstitialAdListener.a();
                    return;
                }
                return;
            }
            this.mInterstitialAdUnit = new o(this.mContext, this.mPlacementId, this.mInterstitialAdListener);
            setupAdUnit();
            this.mInterstitialAdUnit.o();
        }
    }

    private void setupAdUnit() {
        this.mInterstitialAdUnit.a(this.mExtras);
        this.mInterstitialAdUnit.a(this.mKeywords);
        if (this.mIsHardwareAccelerationDisabled) {
            this.mInterstitialAdUnit.A();
        }
    }

    public final void show() {
        if (this.mIsInitialized && this.mInterstitialAdUnit != null) {
            this.mInterstitialAdUnit.x();
        }
    }

    public final void show(int i, int i2) {
        if (this.mIsInitialized && this.mInterstitialAdUnit != null) {
            this.mInterstitialAdUnit.a(i, i2);
        }
    }

    public final boolean isReady() {
        return (!this.mIsInitialized || this.mInterstitialAdUnit == null) ? false : this.mInterstitialAdUnit.z();
    }

    public final void setExtras(Map<String, String> map) {
        if (this.mIsInitialized) {
            this.mExtras = map;
        }
    }

    public final void disableHardwareAcceleration() {
        if (this.mIsInitialized) {
            this.mIsHardwareAccelerationDisabled = true;
        }
    }
}
