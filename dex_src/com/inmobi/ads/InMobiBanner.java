package com.inmobi.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;
import com.inmobi.ads.AdUnit.AdState;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.InMobiBanner.BannerAdListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.taobao.accs.common.Constants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyAgent;

public final class InMobiBanner extends RelativeLayout {
    private static final String TAG;
    private long mAdLoadCalledTimestamp;
    private AnimationType mAnimationType;
    private k mBackgroundBannerAdUnit;
    private final a mBannerAdListener;
    private k mBannerAdUnit1;
    private k mBannerAdUnit2;
    private int mBannerHeightInDp;
    private int mBannerWidthInDp;
    private b mClientCallbackHandler;
    private BannerAdListener mClientListener;
    private k mForegroundBannerAdUnit;
    private boolean mIsAutoRefreshEnabled;
    private boolean mIsInitialized;
    private l mRefreshHandler;
    private int mRefreshInterval;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ boolean a;

        AnonymousClass_1(boolean z) {
            this.a = z;
        }

        public void run() {
            if (InMobiBanner.this.hasValidSize()) {
                InMobiBanner.this.cancelScheduledRefresh();
                if (InMobiBanner.this.checkForRefreshRate()) {
                    InMobiBanner.this.mBackgroundBannerAdUnit.b(this.a);
                    return;
                }
                return;
            }
            Logger.a(InternalLogLevel.ERROR, TAG, "The height or width of the banner can not be determined");
            InMobiBanner.this.mBannerAdListener.a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
        }
    }

    class AnonymousClass_3 implements AnimationListener {
        final /* synthetic */ a a;

        AnonymousClass_3(a aVar) {
            this.a = aVar;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            InMobiBanner.this.displayAd();
            this.a.a();
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    private static interface a {
        void a();
    }

    public enum AnimationType {
        ANIMATION_OFF,
        ROTATE_HORIZONTAL_AXIS,
        ANIMATION_ALPHA,
        ROTATE_VERTICAL_AXIS
    }

    public static interface BannerAdListener {
        void onAdDismissed(InMobiBanner inMobiBanner);

        void onAdDisplayed(InMobiBanner inMobiBanner);

        void onAdInteraction(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiBanner inMobiBanner);

        void onAdRewardActionCompleted(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onUserLeftApplication(InMobiBanner inMobiBanner);
    }

    private static final class b extends Handler {
        private WeakReference<BannerAdListener> a;
        private WeakReference<InMobiBanner> b;

        public b(InMobiBanner inMobiBanner, BannerAdListener bannerAdListener) {
            super(Looper.getMainLooper());
            this.b = new WeakReference(inMobiBanner);
            this.a = new WeakReference(bannerAdListener);
        }

        public final void a(BannerAdListener bannerAdListener) {
            this.a = new WeakReference(bannerAdListener);
        }

        public final void handleMessage(Message message) {
            Map map = null;
            InMobiBanner inMobiBanner = (InMobiBanner) this.b.get();
            BannerAdListener bannerAdListener = (BannerAdListener) this.a.get();
            if (inMobiBanner != null && bannerAdListener != null) {
                switch (message.what) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        bannerAdListener.onAdLoadSucceeded(inMobiBanner);
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        bannerAdListener.onAdLoadFailed(inMobiBanner, (InMobiAdRequestStatus) message.obj);
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        bannerAdListener.onAdDisplayed(inMobiBanner);
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        bannerAdListener.onAdDismissed(inMobiBanner);
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        if (message.obj != null) {
                            map = (Map) message.obj;
                        }
                        bannerAdListener.onAdInteraction(inMobiBanner, map);
                    case R.styleable.Toolbar_contentInsetEnd:
                        bannerAdListener.onUserLeftApplication(inMobiBanner);
                    case R.styleable.Toolbar_contentInsetLeft:
                        if (message.obj != null) {
                            map = (Map) message.obj;
                        }
                        bannerAdListener.onAdRewardActionCompleted(inMobiBanner, map);
                    default:
                        Logger.a(InternalLogLevel.INTERNAL, TAG, "Unhandled ad lifecycle event! Ignoring ...");
                }
            }
        }
    }

    static {
        TAG = InMobiBanner.class.getSimpleName();
    }

    public InMobiBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsInitialized = false;
        this.mIsAutoRefreshEnabled = true;
        this.mBannerWidthInDp = 0;
        this.mBannerHeightInDp = 0;
        this.mAnimationType = AnimationType.ROTATE_HORIZONTAL_AXIS;
        this.mAdLoadCalledTimestamp = 0;
        this.mBannerAdListener = new a() {
            public void a() {
                if (InMobiBanner.this.mForegroundBannerAdUnit == null || !InMobiBanner.this.mForegroundBannerAdUnit.z()) {
                    InMobiBanner.this.swapAdUnitsAndDisplayAd(new a() {
                        public void a() {
                            AnonymousClass_4.this.mClientCallbackHandler.sendEmptyMessage(1);
                            AnonymousClass_4.this.scheduleRefresh();
                        }
                    });
                }
            }

            public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                obtain.obj = inMobiAdRequestStatus;
                InMobiBanner.this.mClientCallbackHandler.sendMessage(obtain);
                InMobiBanner.this.scheduleRefresh();
            }

            public void b() {
                InMobiBanner.this.mClientCallbackHandler.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_FAILED);
            }

            public void c() {
                InMobiBanner.this.scheduleRefresh();
                InMobiBanner.this.mClientCallbackHandler.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_ALL);
            }

            public void a(Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 5;
                obtain.obj = map;
                InMobiBanner.this.mClientCallbackHandler.sendMessage(obtain);
            }

            public void d() {
                InMobiBanner.this.mClientCallbackHandler.sendEmptyMessage(R.styleable.Toolbar_contentInsetEnd);
            }

            public void b(Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 7;
                obtain.obj = map;
                InMobiBanner.this.mClientCallbackHandler.sendMessage(obtain);
            }
        };
        if (com.inmobi.commons.a.a.a()) {
            this.mClientCallbackHandler = new b(this, this.mClientListener);
            String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "placementId");
            String attributeValue2 = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "refreshInterval");
            if (attributeValue != null) {
                try {
                    initializeAdUnit(context, Long.parseLong(attributeValue.trim()));
                } catch (Throwable e) {
                    Map hashMap = new HashMap();
                    hashMap.put(Constants.KEY_ERROR_CODE, "InvalidPlacement");
                    hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "banner");
                    com.inmobi.commons.core.c.a.a().a("ads", "AdLoadFailed", hashMap);
                    Logger.a(InternalLogLevel.ERROR, TAG, "Placement id value supplied in XML layout is not valid. Banner creation failed.", e);
                }
            } else {
                Logger.a(InternalLogLevel.ERROR, TAG, "Placement id value is not supplied in XML layout. Banner creation failed.");
            }
            if (attributeValue2 != null) {
                try {
                    setRefreshInterval(Integer.parseInt(attributeValue2.trim()));
                    return;
                } catch (Throwable e2) {
                    Logger.a(InternalLogLevel.ERROR, TAG, "Refresh interval value supplied in XML layout is not valid. Falling back to default value.", e2);
                }
            }
            return;
        }
        Logger.a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before trying to create an ad.");
    }

    public InMobiBanner(Context context, long j) {
        super(context);
        this.mIsInitialized = false;
        this.mIsAutoRefreshEnabled = true;
        this.mBannerWidthInDp = 0;
        this.mBannerHeightInDp = 0;
        this.mAnimationType = AnimationType.ROTATE_HORIZONTAL_AXIS;
        this.mAdLoadCalledTimestamp = 0;
        this.mBannerAdListener = new a() {
            public void a() {
                if (InMobiBanner.this.mForegroundBannerAdUnit == null || !InMobiBanner.this.mForegroundBannerAdUnit.z()) {
                    InMobiBanner.this.swapAdUnitsAndDisplayAd(new a() {
                        public void a() {
                            AnonymousClass_4.this.mClientCallbackHandler.sendEmptyMessage(1);
                            AnonymousClass_4.this.scheduleRefresh();
                        }
                    });
                }
            }

            public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                obtain.obj = inMobiAdRequestStatus;
                InMobiBanner.this.mClientCallbackHandler.sendMessage(obtain);
                InMobiBanner.this.scheduleRefresh();
            }

            public void b() {
                InMobiBanner.this.mClientCallbackHandler.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_FAILED);
            }

            public void c() {
                InMobiBanner.this.scheduleRefresh();
                InMobiBanner.this.mClientCallbackHandler.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_ALL);
            }

            public void a(Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 5;
                obtain.obj = map;
                InMobiBanner.this.mClientCallbackHandler.sendMessage(obtain);
            }

            public void d() {
                InMobiBanner.this.mClientCallbackHandler.sendEmptyMessage(R.styleable.Toolbar_contentInsetEnd);
            }

            public void b(Map<Object, Object> map) {
                Message obtain = Message.obtain();
                obtain.what = 7;
                obtain.obj = map;
                InMobiBanner.this.mClientCallbackHandler.sendMessage(obtain);
            }
        };
        if (context == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Context supplied as null, the ad unit can't be created.");
        } else if (com.inmobi.commons.a.a.a()) {
            this.mClientCallbackHandler = new b(this, this.mClientListener);
            initializeAdUnit(context, j);
        } else {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please initialize the SDK before trying to create an ad.");
        }
    }

    public final void load() {
        load(false);
    }

    final void load(boolean z) {
        if (!this.mIsInitialized) {
            return;
        }
        if (getLayoutParams() == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "The layout params of the banner must be set before calling load");
            this.mBannerAdListener.a(new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
        } else if (getLayoutParams().width == -2 || getLayoutParams().height == -2) {
            Logger.a(InternalLogLevel.ERROR, TAG, "The height or width of a Banner ad can't be WRAP_CONTENT");
            this.mBannerAdListener.a(new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID));
        } else if (this.mForegroundBannerAdUnit == null || !this.mForegroundBannerAdUnit.z()) {
            if (!hasValidSize()) {
                setSizeFromLayoutParams();
            }
            if (hasValidSize()) {
                cancelScheduledRefresh();
                if (checkForRefreshRate()) {
                    this.mBackgroundBannerAdUnit.b(z);
                    return;
                }
                return;
            }
            new Handler().postDelayed(new AnonymousClass_1(z), Constants.ST_UPLOAD_MAX_COUNT);
        } else {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = new InMobiAdRequestStatus(StatusCode.AD_ACTIVE);
            this.mClientCallbackHandler.sendMessage(obtain);
            this.mForegroundBannerAdUnit.c("AdActive");
            Logger.a(InternalLogLevel.ERROR, TAG, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad.");
        }
    }

    private final boolean checkForRefreshRate() {
        if (this.mAdLoadCalledTimestamp != 0) {
            int f = this.mBackgroundBannerAdUnit.l().f();
            if (SystemClock.elapsedRealtime() - this.mAdLoadCalledTimestamp < ((long) (f * 1000))) {
                this.mBackgroundBannerAdUnit.a(new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST).setCustomMessage(new StringBuilder("Ad cannot be refreshed before ").append(f).append(" seconds").toString()), false);
                Logger.a(InternalLogLevel.ERROR, TAG, new StringBuilder("Ad cannot be refreshed before ").append(f).append(" seconds").toString());
                return false;
            }
        }
        this.mAdLoadCalledTimestamp = SystemClock.elapsedRealtime();
        return true;
    }

    public final void setExtras(Map<String, String> map) {
        if (this.mIsInitialized) {
            this.mBannerAdUnit1.a(map);
            this.mBannerAdUnit2.a(map);
        }
    }

    public final void setKeywords(String str) {
        if (this.mIsInitialized) {
            this.mBannerAdUnit1.a(str);
            this.mBannerAdUnit2.a(str);
        }
    }

    public final void setListener(BannerAdListener bannerAdListener) {
        if (bannerAdListener == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Please pass a non-null listener to the banner.");
            return;
        }
        this.mClientListener = bannerAdListener;
        if (this.mClientCallbackHandler != null) {
            this.mClientCallbackHandler.a(bannerAdListener);
        }
    }

    public final void setEnableAutoRefresh(boolean z) {
        if (this.mIsInitialized && this.mIsAutoRefreshEnabled != z) {
            this.mIsAutoRefreshEnabled = z;
            if (this.mIsAutoRefreshEnabled) {
                scheduleRefresh();
            } else {
                cancelScheduledRefresh();
            }
        }
    }

    public final void setRefreshInterval(int i) {
        if (this.mIsInitialized) {
            if (i < this.mBackgroundBannerAdUnit.l().f()) {
                i = this.mBackgroundBannerAdUnit.l().f();
            }
            this.mRefreshInterval = i;
        }
    }

    public final void setAnimationType(AnimationType animationType) {
        if (this.mIsInitialized) {
            this.mAnimationType = animationType;
        }
    }

    public final void disableHardwareAcceleration() {
        if (this.mIsInitialized) {
            this.mBannerAdUnit1.x();
            this.mBannerAdUnit2.x();
        }
    }

    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsInitialized) {
            setSizeFromLayoutParams();
            if (!hasValidSize()) {
                setupBannerSizeObserver();
            }
            scheduleRefresh();
        }
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsInitialized) {
            cancelScheduledRefresh();
        }
    }

    private void setSizeFromLayoutParams() {
        if (getLayoutParams() != null) {
            this.mBannerWidthInDp = DisplayInfo.b(getLayoutParams().width);
            this.mBannerHeightInDp = DisplayInfo.b(getLayoutParams().height);
        }
    }

    @TargetApi(16)
    final void setupBannerSizeObserver() {
        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                InMobiBanner.this.mBannerWidthInDp = DisplayInfo.b(InMobiBanner.this.getMeasuredWidth());
                InMobiBanner.this.mBannerHeightInDp = DisplayInfo.b(InMobiBanner.this.getMeasuredHeight());
                if (!InMobiBanner.this.hasValidSize()) {
                    return;
                }
                if (VERSION.SDK_INT >= 16) {
                    InMobiBanner.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    InMobiBanner.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    final boolean hasValidSize() {
        return this.mBannerWidthInDp > 0 && this.mBannerHeightInDp > 0;
    }

    final String getFrameSizeString() {
        return this.mBannerWidthInDp + "x" + this.mBannerHeightInDp;
    }

    protected final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (!this.mIsInitialized) {
            return;
        }
        if (i == 0) {
            scheduleRefresh();
        } else {
            cancelScheduledRefresh();
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!this.mIsInitialized) {
            return;
        }
        if (z) {
            scheduleRefresh();
        } else {
            cancelScheduledRefresh();
        }
    }

    private void initializeAdUnit(Context context, long j) {
        this.mBannerAdUnit1 = new k(this, context, j, this.mBannerAdListener);
        this.mBannerAdUnit2 = new k(this, context, j, this.mBannerAdListener);
        this.mBackgroundBannerAdUnit = this.mBannerAdUnit1;
        this.mRefreshInterval = this.mBackgroundBannerAdUnit.l().g();
        this.mRefreshHandler = new l(this);
        this.mIsInitialized = true;
    }

    private void scheduleRefresh() {
        if (isShown() && hasWindowFocus()) {
            this.mRefreshHandler.removeMessages(1);
            if (this.mBackgroundBannerAdUnit.g() == AdState.STATE_LOADING || this.mBackgroundBannerAdUnit.g() == AdState.STATE_AVAILABLE || (this.mForegroundBannerAdUnit != null && this.mForegroundBannerAdUnit.g() == AdState.STATE_ACTIVE)) {
                Logger.a(InternalLogLevel.INTERNAL, TAG, "Ignoring an attempt to schedule refresh when an ad is already loading or active.");
            } else if (this.mIsAutoRefreshEnabled) {
                this.mRefreshHandler.sendEmptyMessageDelayed(1, (long) (this.mRefreshInterval * 1000));
            }
        }
    }

    private void cancelScheduledRefresh() {
        this.mRefreshHandler.removeMessages(1);
    }

    private void displayAd() {
        if (this.mForegroundBannerAdUnit.y()) {
            this.mForegroundBannerAdUnit.n().n();
        }
        ViewGroup viewGroup = (ViewGroup) this.mForegroundBannerAdUnit.n().getParent();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (viewGroup == null) {
            addView(this.mForegroundBannerAdUnit.n(), layoutParams);
        } else {
            viewGroup.removeAllViews();
            viewGroup.addView(this.mForegroundBannerAdUnit.n(), layoutParams);
        }
        this.mBackgroundBannerAdUnit.t();
    }

    private void swapAdUnitsAndDisplayAd(a aVar) {
        if (this.mForegroundBannerAdUnit == null) {
            this.mForegroundBannerAdUnit = this.mBannerAdUnit1;
            this.mBackgroundBannerAdUnit = this.mBannerAdUnit2;
        } else if (this.mForegroundBannerAdUnit.equals(this.mBannerAdUnit1)) {
            this.mForegroundBannerAdUnit = this.mBannerAdUnit2;
            this.mBackgroundBannerAdUnit = this.mBannerAdUnit1;
        } else if (this.mForegroundBannerAdUnit.equals(this.mBannerAdUnit2)) {
            this.mForegroundBannerAdUnit = this.mBannerAdUnit1;
            this.mBackgroundBannerAdUnit = this.mBannerAdUnit2;
        }
        Animation a = j.a(this.mAnimationType, (float) getWidth(), (float) getHeight());
        if (a == null) {
            displayAd();
            aVar.a();
            return;
        }
        a.setAnimationListener(new AnonymousClass_3(aVar));
        startAnimation(a);
    }
}
