package com.xunlei.downloadprovider.vod;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.commonview.dialog.q;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.vod.TouchListenerProxy.TouchOperateType;
import com.xunlei.downloadprovider.vod.VodCenterProgressView.CenterProgressType;
import com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow;
import com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.VideoSize;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public final class VodPlayerView extends RelativeLayout implements OnClickListener, com.xunlei.downloadprovider.vod.TouchListenerProxy.a {
    private static final int AUDIO_PROGRESS_BAR_MAX = 100;
    private static final float AUDIO_PROGRESS_STEP = 6.6666665f;
    private static final int CENTER_PROGRESS_HIDE_DELAY = 1500;
    private static final int CONTROL_BAR_HIDE_DELAY = 5000;
    private static final int LOCK_BAR_HIDE_DELAY = 3000;
    public static final int STATE_BUFFERING = 2;
    public static final int STATE_ERROR = 4;
    public static final int STATE_IDLE = -1;
    public static final int STATE_OPENING = 0;
    public static final int STATE_PAUSE = 1;
    public static final int STATE_PLAYING = 3;
    private static final int SYSTEM_BRIGHTNESS_MAX = 255;
    public static final String TAG;
    private Button mAccelBtn;
    private ImageButton mBackBtn;
    private View mBottomBarLyt;
    private VodCenterProgressView mCenterIconPrbView;
    private View mCenterPlayPauseBtn;
    private VodCenterProgressWithTextView mCenterTextPrbView;
    private ImageButton mDLNABtn;
    private ImageView mDLNADotIv;
    private View mDLNALyt;
    private TextView mDurationTv;
    private a mEventListener;
    private View mFirstUseTipIv;
    private Runnable mHideCenterProgressRunnable;
    private Runnable mHideControlBarRunnable;
    private Runnable mHideLockBarRunnable;
    private VodNotifyLoadingCircle mLoadingView;
    private ImageButton mLockBtn;
    private View mMenuIv;
    private VodPlayerMenuPopupWindow mMenuPopWindow;
    private ImageButton mPlayPauseBtn;
    private int mPlayerSate;
    private View mPlayingErroLyt;
    private TextView mPlayingErroMsgTv;
    private TextView mPlayingErroRetryTv;
    private e mPopupSeekTimeWindow;
    private ProgressBar mPowerPbr;
    private TextView mProcessTv;
    private SeekBar mProressSkb;
    private View mRootView;
    private ImageView mShareIv;
    private int mShowDownSpeedTextId;
    private boolean mShowSpeed;
    private String mSpeed;
    private TextView mStatusTv;
    private LinearLayout mSurfaceParent;
    private SurfaceView mSurfaceView;
    private TextView mSystemTimeTv;
    private TextView mTitleTv;
    private View mTopBarLyt;
    private TouchListenerProxy mTouchListenerProxy;
    private d mUIParams;
    private c mUiChangeListener;
    private TextView mUrlTv;
    private boolean mViewLocked;
    private q mXLOneButtonDialog;
    private XLAlarmDialog mXLTwoButtonDialog;

    public static interface a {
        void onAccelBtnClicked();

        void onBackBtnClicked();

        void onBrightnessChanged(float f);

        void onDLNAClicked();

        void onDoubleClick();

        void onPlayPauseBtnClicked();

        void onPlayPostionChangeEnd(int i);

        void onPlayPostionChangeStart();

        void onPlayPostionChanged(boolean z, int i);

        void onRetryBtnClicked();

        void onShareBtnClicked();

        void onVolumnChanged(float f);
    }

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[VideoSize.values().length];
            try {
                a[VideoSize.SIZE_100.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[VideoSize.SIZE_75.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[VideoSize.SIZE_50.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static interface b {
        void a(String str);
    }

    public static interface c {
        void a();

        void a(SurfaceView surfaceView);

        void a(boolean z);
    }

    public static class d {
        float a;
        float b;
        float c;
        public int d;
        public int e;
        public int f;
        public int g;
        public float h;
        public float i;
        public int j;
        public CharSequence k;
        public CharSequence l;
        public int m;
        public int n;

        public d() {
            this.a = 0.0f;
            this.b = 0.0f;
            this.c = 0.0f;
            this.d = 0;
            this.e = 0;
        }
    }

    static {
        TAG = VodPlayerView.class.getSimpleName();
    }

    public final void setShowSpeed(boolean z) {
        this.mShowSpeed = z;
    }

    public VodPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPopupSeekTimeWindow = null;
        this.mBottomBarLyt = null;
        this.mTopBarLyt = null;
        this.mTitleTv = null;
        this.mUrlTv = null;
        this.mSystemTimeTv = null;
        this.mProcessTv = null;
        this.mDurationTv = null;
        this.mProressSkb = null;
        this.mPlayPauseBtn = null;
        this.mBackBtn = null;
        this.mLockBtn = null;
        this.mDLNABtn = null;
        this.mDLNALyt = null;
        this.mDLNADotIv = null;
        this.mPowerPbr = null;
        this.mRootView = null;
        this.mCenterIconPrbView = null;
        this.mCenterTextPrbView = null;
        this.mLoadingView = null;
        this.mSurfaceParent = null;
        this.mSurfaceView = null;
        this.mXLTwoButtonDialog = null;
        this.mXLOneButtonDialog = null;
        this.mMenuIv = null;
        this.mTouchListenerProxy = null;
        this.mPlayerSate = -1;
        this.mUIParams = new d();
        this.mViewLocked = false;
        this.mShowSpeed = false;
        this.mHideControlBarRunnable = new az(this);
        this.mHideCenterProgressRunnable = new ba(this);
        this.mHideLockBarRunnable = new bb(this);
        this.mSpeed = null;
        this.mShowDownSpeedTextId = 0;
    }

    public VodPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPopupSeekTimeWindow = null;
        this.mBottomBarLyt = null;
        this.mTopBarLyt = null;
        this.mTitleTv = null;
        this.mUrlTv = null;
        this.mSystemTimeTv = null;
        this.mProcessTv = null;
        this.mDurationTv = null;
        this.mProressSkb = null;
        this.mPlayPauseBtn = null;
        this.mBackBtn = null;
        this.mLockBtn = null;
        this.mDLNABtn = null;
        this.mDLNALyt = null;
        this.mDLNADotIv = null;
        this.mPowerPbr = null;
        this.mRootView = null;
        this.mCenterIconPrbView = null;
        this.mCenterTextPrbView = null;
        this.mLoadingView = null;
        this.mSurfaceParent = null;
        this.mSurfaceView = null;
        this.mXLTwoButtonDialog = null;
        this.mXLOneButtonDialog = null;
        this.mMenuIv = null;
        this.mTouchListenerProxy = null;
        this.mPlayerSate = -1;
        this.mUIParams = new d();
        this.mViewLocked = false;
        this.mShowSpeed = false;
        this.mHideControlBarRunnable = new az(this);
        this.mHideCenterProgressRunnable = new ba(this);
        this.mHideLockBarRunnable = new bb(this);
        this.mSpeed = null;
        this.mShowDownSpeedTextId = 0;
    }

    public VodPlayerView(Context context) {
        super(context);
        this.mPopupSeekTimeWindow = null;
        this.mBottomBarLyt = null;
        this.mTopBarLyt = null;
        this.mTitleTv = null;
        this.mUrlTv = null;
        this.mSystemTimeTv = null;
        this.mProcessTv = null;
        this.mDurationTv = null;
        this.mProressSkb = null;
        this.mPlayPauseBtn = null;
        this.mBackBtn = null;
        this.mLockBtn = null;
        this.mDLNABtn = null;
        this.mDLNALyt = null;
        this.mDLNADotIv = null;
        this.mPowerPbr = null;
        this.mRootView = null;
        this.mCenterIconPrbView = null;
        this.mCenterTextPrbView = null;
        this.mLoadingView = null;
        this.mSurfaceParent = null;
        this.mSurfaceView = null;
        this.mXLTwoButtonDialog = null;
        this.mXLOneButtonDialog = null;
        this.mMenuIv = null;
        this.mTouchListenerProxy = null;
        this.mPlayerSate = -1;
        this.mUIParams = new d();
        this.mViewLocked = false;
        this.mShowSpeed = false;
        this.mHideControlBarRunnable = new az(this);
        this.mHideCenterProgressRunnable = new ba(this);
        this.mHideLockBarRunnable = new bb(this);
        this.mSpeed = null;
        this.mShowDownSpeedTextId = 0;
    }

    protected final void onFinishInflate() {
        super.onFinishInflate();
        this.mRootView = findViewById(2131757162);
        this.mTopBarLyt = findViewById(2131757165);
        this.mTopBarLyt.setVisibility(STATE_ERROR);
        this.mBackBtn = (ImageButton) findViewById(2131755212);
        this.mTitleTv = (TextView) this.mTopBarLyt.findViewById(R.id.tv_title);
        this.mUrlTv = (TextView) this.mTopBarLyt.findViewById(2131757208);
        this.mShareIv = (ImageView) findViewById(2131755907);
        this.mDLNALyt = this.mTopBarLyt.findViewById(2131757205);
        this.mDLNABtn = (ImageButton) this.mDLNALyt.findViewById(2131757206);
        this.mDLNADotIv = (ImageView) this.mDLNALyt.findViewById(2131757207);
        this.mMenuIv = this.mTopBarLyt.findViewById(2131755282);
        this.mPowerPbr = (ProgressBar) findViewById(2131757203);
        this.mSystemTimeTv = (TextView) this.mTopBarLyt.findViewById(2131757204);
        this.mSystemTimeTv.setText(getTimeString());
        this.mBottomBarLyt = findViewById(2131757166);
        this.mBottomBarLyt.setVisibility(STATE_ERROR);
        this.mPlayPauseBtn = (ImageButton) this.mBottomBarLyt.findViewById(2131757173);
        this.mProcessTv = (TextView) this.mBottomBarLyt.findViewById(2131757157);
        this.mDurationTv = (TextView) this.mBottomBarLyt.findViewById(2131755494);
        this.mProressSkb = (SeekBar) this.mBottomBarLyt.findViewById(2131756519);
        this.mStatusTv = (TextView) this.mBottomBarLyt.findViewById(2131757175);
        this.mAccelBtn = (Button) this.mBottomBarLyt.findViewById(2131757176);
        this.mLockBtn = (ImageButton) findViewById(2131757167);
        this.mLockBtn.setVisibility(XZBDevice.Wait);
        this.mCenterPlayPauseBtn = findViewById(2131757168);
        this.mCenterPlayPauseBtn.setVisibility(XZBDevice.Wait);
        this.mCenterIconPrbView = (VodCenterProgressView) findViewById(2131757170);
        this.mCenterIconPrbView.setVisibility(XZBDevice.Wait);
        this.mCenterTextPrbView = (VodCenterProgressWithTextView) findViewById(2131757171);
        this.mCenterTextPrbView.setVisibility(XZBDevice.Wait);
        this.mLoadingView = (VodNotifyLoadingCircle) findViewById(2131757169);
        this.mLoadingView.setVisibility(XZBDevice.Wait);
        this.mPlayingErroLyt = findViewById(2131757164);
        this.mPlayingErroLyt.setVisibility(XZBDevice.Wait);
        this.mPlayingErroMsgTv = (TextView) this.mPlayingErroLyt.findViewById(2131757202);
        this.mPlayingErroRetryTv = (TextView) this.mPlayingErroLyt.findViewById(2131756715);
        this.mFirstUseTipIv = findViewById(2131757172);
        this.mFirstUseTipIv.setVisibility(XZBDevice.Wait);
        this.mMenuPopWindow = new VodPlayerMenuPopupWindow(getContext());
        this.mSurfaceParent = (LinearLayout) findViewById(2131757163);
        this.mSurfaceParent.setVisibility(STATE_OPENING);
        addSufaceView();
        setSurfaceViewScale(VideoSize.SIZE_100);
        initListener();
    }

    private void addSufaceView() {
        this.mSurfaceView = new SurfaceView(getContext());
        this.mSurfaceView.setLayoutParams(new LayoutParams(-1, -1, 17.0f));
        this.mSurfaceParent.removeAllViews();
        this.mSurfaceParent.addView(this.mSurfaceView);
        if (this.mUiChangeListener != null) {
            this.mUiChangeListener.a(this.mSurfaceView);
        }
    }

    private void initListener() {
        this.mLockBtn.setOnClickListener(this);
        this.mBackBtn.setOnClickListener(this);
        this.mFirstUseTipIv.setOnClickListener(this);
        this.mPlayPauseBtn.setOnClickListener(this);
        this.mDLNABtn.setOnClickListener(this);
        this.mMenuIv.setOnClickListener(this);
        this.mShareIv.setOnClickListener(this);
        this.mAccelBtn.setOnClickListener(this);
        this.mCenterPlayPauseBtn.setOnClickListener(this);
        this.mPlayingErroRetryTv.setOnClickListener(this);
        this.mTouchListenerProxy = new TouchListenerProxy();
        this.mTouchListenerProxy.b = this;
        this.mRootView.setOnTouchListener(this.mTouchListenerProxy);
        setPositionListener();
        initMenuListener();
        this.mBottomBarLyt.setOnTouchListener(new aq(this));
        this.mTopBarLyt.setOnTouchListener(new av(this));
    }

    private void setPositionListener() {
        this.mProressSkb.setOnSeekBarChangeListener(new aw(this));
        this.mProressSkb.setProgress(STATE_OPENING);
    }

    private String getTimeString() {
        return DateFormat.format("kk:mm", System.currentTimeMillis()).toString();
    }

    private void setScreenLock(boolean z) {
        if (z) {
            this.mViewLocked = true;
            this.mLockBtn.setImageResource(2130839685);
            return;
        }
        this.mViewLocked = false;
        this.mLockBtn.setImageResource(2130839686);
    }

    private void switchScreenLock() {
        if (this.mViewLocked) {
            setScreenLock(false);
            showControlBar();
            XLToast.b(getContext(), XLToastType.XLTOAST_TYPE_NORMAL, getResources().getString(2131233171), STATE_PAUSE);
            return;
        }
        hideControlBar(true);
        setScreenLock(true);
        XLToast.b(getContext(), XLToastType.XLTOAST_TYPE_NORMAL, getResources().getString(2131233169), STATE_PAUSE);
    }

    public final SurfaceView getSufaceView() {
        if (this.mSurfaceView == null) {
            addSufaceView();
        }
        return this.mSurfaceView;
    }

    public final void showError(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("msg should not be empty");
        }
        this.mPlayingErroMsgTv.setText(str);
        if (z) {
            this.mPlayingErroRetryTv.setVisibility(STATE_OPENING);
        } else {
            this.mPlayingErroRetryTv.setVisibility(XZBDevice.Wait);
        }
        this.mPlayingErroLyt.setVisibility(STATE_OPENING);
        setPlayerSate(STATE_ERROR);
    }

    private void hideError() {
        this.mPlayingErroLyt.setVisibility(XZBDevice.Wait);
    }

    private void showCircleLoading(CharSequence charSequence) {
        if (!this.mLoadingView.b()) {
            if (this.mSpeed == null && this.mShowDownSpeedTextId == 0) {
                this.mLoadingView.setLoadingText(charSequence);
            }
            this.mLoadingView.a();
            setMainPlayVis(false);
        }
    }

    @SuppressLint({"DefaultLocale"})
    private void updateCircleLoadingPercent(String str, int i) {
        if (this.mLoadingView.b()) {
            if (i < 0) {
                i = 0;
            } else if (i > 100) {
                i = 100;
            }
            if (this.mShowDownSpeedTextId != 0) {
                str = String.format(getContext().getString(this.mShowDownSpeedTextId), new Object[]{this.mSpeed});
            }
            this.mLoadingView.setLoadingText(str);
            if (i < 100 && !this.mLoadingView.b()) {
                this.mLoadingView.setLoadingText(getContext().getString(2131233149));
                this.mLoadingView.a();
            }
        }
    }

    private void hideCircleLoading() {
        if (this.mLoadingView != null) {
            VodNotifyLoadingCircle vodNotifyLoadingCircle = this.mLoadingView;
            if (vodNotifyLoadingCircle.getVisibility() == 0) {
                vodNotifyLoadingCircle.setVisibility(XZBDevice.Wait);
                vodNotifyLoadingCircle.startAnimation(AnimationUtils.loadAnimation(vodNotifyLoadingCircle.getContext(), 2131034234));
                vodNotifyLoadingCircle.b.clearAnimation();
            }
        }
    }

    private void showCenterProgressView(CenterProgressType centerProgressType, int i, int i2) {
        if (this.mCenterIconPrbView.getVisibility() != 0) {
            this.mCenterIconPrbView.a(centerProgressType, i, i2);
            this.mCenterIconPrbView.setVisibility(STATE_OPENING);
            this.mCenterIconPrbView.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034235));
        }
    }

    private void showCenterProgressWithTextView(int i, int i2) {
        if (this.mCenterTextPrbView.getVisibility() != 0) {
            String formatTime = formatTime(i);
            String formatTime2 = formatTime(i2);
            formatTime2 = String.format(getContext().getString(2131233150), new Object[]{formatTime2});
            VodCenterProgressWithTextView vodCenterProgressWithTextView = this.mCenterTextPrbView;
            vodCenterProgressWithTextView.b = i;
            vodCenterProgressWithTextView.a.setMax(i2);
            vodCenterProgressWithTextView.a.setProgress(i);
            this.mCenterTextPrbView.setText(formatTime);
            this.mCenterTextPrbView.setSuffixText(formatTime2);
            this.mCenterTextPrbView.setVisibility(STATE_OPENING);
            this.mCenterTextPrbView.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034235));
        }
    }

    private void hideCenterProgressView() {
        if (this.mCenterIconPrbView.getVisibility() == 0) {
            this.mCenterIconPrbView.setVisibility(XZBDevice.Wait);
            this.mCenterIconPrbView.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034234));
        }
        if (this.mCenterTextPrbView.getVisibility() == 0) {
            this.mCenterTextPrbView.setVisibility(XZBDevice.Wait);
            this.mCenterTextPrbView.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034234));
        }
    }

    private boolean isCenterProgressViewShown() {
        if (this.mCenterIconPrbView == null || this.mCenterIconPrbView.getVisibility() != 0) {
            return this.mCenterTextPrbView != null && this.mCenterTextPrbView.getVisibility() == 0;
        } else {
            return true;
        }
    }

    private void setMainPlayVis(boolean z) {
        this.mCenterPlayPauseBtn.setVisibility(z ? STATE_OPENING : XZBDevice.Wait);
    }

    public final void setSmallPlayBtnIcon(boolean z) {
        int i;
        if (z) {
            i = 2130839694;
        } else {
            i = 2130839690;
        }
        this.mPlayPauseBtn.setImageResource(i);
    }

    public final void setPower(int i) {
        if (this.mPowerPbr != null) {
            this.mPowerPbr.setProgress(i);
        }
    }

    public final boolean isNetworkDialogShowing() {
        return this.mXLTwoButtonDialog != null && this.mXLTwoButtonDialog.isShowing();
    }

    public final void showWifiNotifyDialog(String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, OnCancelListener onCancelListener) {
        if (this.mXLTwoButtonDialog != null && this.mXLTwoButtonDialog.isShowing()) {
            this.mXLTwoButtonDialog.dismiss();
        }
        this.mXLTwoButtonDialog = new XLAlarmDialog(getContext());
        this.mXLTwoButtonDialog.setCancelable(false);
        this.mXLTwoButtonDialog.setCanceledOnTouchOutside(false);
        this.mXLTwoButtonDialog.setContent(str);
        this.mXLTwoButtonDialog.setCancelButtonText(str2);
        this.mXLTwoButtonDialog.setOnClickCancelButtonListener(onClickListener);
        this.mXLTwoButtonDialog.setConfirmButtonText(str3);
        this.mXLTwoButtonDialog.setOnClickConfirmButtonListener(onClickListener2);
        if (onCancelListener != null) {
            this.mXLTwoButtonDialog.setOnCancelListener(onCancelListener);
            this.mXLTwoButtonDialog.setCancelable(true);
        }
        this.mXLTwoButtonDialog.setOnShowListener(new ax(this));
        if (!this.mXLTwoButtonDialog.isShowing()) {
            this.mXLTwoButtonDialog.show();
        }
    }

    public final void dimissWifiNotifyDialog() {
        if (this.mXLTwoButtonDialog != null) {
            this.mXLTwoButtonDialog.dismiss();
            this.mXLTwoButtonDialog = null;
        }
    }

    public final void showOneButtonDialog(String str, String str2, DialogInterface.OnClickListener onClickListener, OnCancelListener onCancelListener) {
        if (this.mXLOneButtonDialog == null) {
            this.mXLOneButtonDialog = new q(getContext());
        }
        this.mXLOneButtonDialog.setCancelable(false);
        this.mXLOneButtonDialog.setCanceledOnTouchOutside(false);
        this.mXLOneButtonDialog.a(str);
        this.mXLOneButtonDialog.d(str2);
        this.mXLOneButtonDialog.b(onClickListener);
        if (onCancelListener != null) {
            this.mXLOneButtonDialog.setOnCancelListener(onCancelListener);
            this.mXLOneButtonDialog.setCancelable(true);
        }
        this.mXLOneButtonDialog.setOnShowListener(new ay(this));
        if (!this.mXLOneButtonDialog.isShowing()) {
            this.mXLOneButtonDialog.show();
        }
    }

    public final void setMenuVolProgress(int i, int i2) {
        if (this.mMenuPopWindow.isShowing()) {
            this.mMenuPopWindow.a(i, i2);
        }
    }

    public final void updateCenterProgressView(int i) {
        if (this.mCenterIconPrbView.getVisibility() == 0) {
            this.mCenterIconPrbView.setProgress(i);
        }
    }

    public final void showAndAutoHideCenterProgressView(CenterProgressType centerProgressType, int i, int i2) {
        if (!(this.mCenterIconPrbView.getVisibility() == 0 && this.mCenterIconPrbView.getType() == centerProgressType)) {
            this.mCenterIconPrbView.a(centerProgressType, i, i2);
            this.mCenterIconPrbView.setVisibility(STATE_OPENING);
            this.mCenterIconPrbView.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034235));
        }
        this.mCenterIconPrbView.setProgress(i);
        removeCallbacks(this.mHideCenterProgressRunnable);
        postDelayed(this.mHideCenterProgressRunnable, 1500);
    }

    public final void updateCenterProgressWithText(int i, int i2) {
        if (this.mCenterTextPrbView.getVisibility() == 0) {
            String formatTime = formatTime(i);
            String formatTime2 = formatTime(i2);
            formatTime2 = String.format(getContext().getString(2131233150), new Object[]{formatTime2});
            this.mCenterTextPrbView.setText(formatTime);
            this.mCenterTextPrbView.setSuffixText(formatTime2);
            this.mCenterTextPrbView.setProgress(i);
        }
    }

    public final void onClick(View view) {
        int id = view.getId();
        if (id == 2131757167) {
            autoHideControlBar(false);
            switchScreenLock();
            autoHideControlBar(true);
        } else if (id == 2131755212) {
            if (this.mEventListener != null) {
                this.mEventListener.onBackBtnClicked();
            }
        } else if (id == 2131757172) {
            showFirstUseTipLay(false);
        } else if (id == 2131756715) {
            doRetry();
        } else if (id == 2131757176) {
            hideControlBar(false);
            if (this.mEventListener != null) {
                this.mEventListener.onAccelBtnClicked();
            }
        }
        if (!this.mViewLocked && this.mPlayerSate != 0 && this.mPlayerSate != 4) {
            switch (id) {
                case 2131755282:
                    hideControlBar(false);
                    showMenu((int) this.mUIParams.h, AUDIO_PROGRESS_BAR_MAX, (int) this.mUIParams.i, SYSTEM_BRIGHTNESS_MAX);
                case 2131755907:
                    hideControlBar(false);
                    if (this.mEventListener != null) {
                        this.mEventListener.onShareBtnClicked();
                    }
                case 2131757168:
                case 2131757173:
                    autoHideControlBar(true);
                    if (this.mEventListener != null) {
                        this.mEventListener.onPlayPauseBtnClicked();
                    }
                case 2131757206:
                    hideControlBar(false);
                    if (this.mEventListener != null) {
                        this.mEventListener.onDLNAClicked();
                    }
                default:
                    break;
            }
        }
    }

    private void doRetry() {
        hideError();
        if (this.mEventListener != null) {
            this.mEventListener.onRetryBtnClicked();
        }
    }

    public final boolean isLocked() {
        return this.mViewLocked;
    }

    public final void setUIParams(d dVar) {
        this.mUIParams = dVar;
        applyUIParams();
    }

    public final d getUIParams() {
        if (this.mUIParams == null) {
            this.mUIParams = new d();
        }
        return this.mUIParams;
    }

    public final void showShareBtn(boolean z) {
        this.mShareIv.setVisibility(z ? STATE_OPENING : XZBDevice.Wait);
    }

    public final void showAccelBtn(boolean z) {
        int i;
        int i2 = XZBDevice.Wait;
        Button button = this.mAccelBtn;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        button.setVisibility(i);
        TextView textView = this.mStatusTv;
        if (!z) {
            i2 = 0;
        }
        textView.setVisibility(i2);
    }

    public final boolean isAccelBtnVisible() {
        return this.mAccelBtn.getVisibility() == 0;
    }

    public final void initTitle(ap apVar, b bVar) {
        if (apVar == null || apVar.a() == null || TextUtils.isEmpty(apVar.a().a)) {
            this.mTitleTv.setVisibility(STATE_ERROR);
            this.mUrlTv.setVisibility(STATE_ERROR);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (VodUtil.a(apVar.b) && apVar.a().a.contains("/")) {
            int lastIndexOf = apVar.a().a.lastIndexOf("/") + 1;
            if (lastIndexOf >= 0 && lastIndexOf <= apVar.a().a.length()) {
                apVar.a().a = apVar.a().a.substring(lastIndexOf);
            }
        }
        if (apVar.a().a.contains(".")) {
            stringBuilder.append(apVar.a().a.substring(STATE_OPENING, apVar.a().a.lastIndexOf(".")));
        } else {
            stringBuilder.append(apVar.a().a);
        }
        this.mTitleTv.setText(stringBuilder.toString());
        if (bVar != null) {
            bVar.a(stringBuilder.toString());
        }
        this.mTitleTv.setVisibility(STATE_OPENING);
        if (shouldShowUrl(apVar.c)) {
            this.mUrlTv.setText(apVar.c);
            this.mUrlTv.setVisibility(STATE_OPENING);
            return;
        }
        this.mUrlTv.setVisibility(XZBDevice.Wait);
    }

    private boolean shouldShowUrl(String str) {
        return (str == null || str.equals(com.umeng.a.d) || !str.trim().toLowerCase().startsWith("http://")) ? false : true;
    }

    private boolean isControlBarVisible() {
        return this.mBottomBarLyt.getVisibility() == 0;
    }

    private void showControlBar() {
        int i = STATE_OPENING;
        autoHideControlBar(false);
        autoHideLockBar(false);
        if (this.mLockBtn.getVisibility() != 0) {
            this.mLockBtn.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034228));
            this.mLockBtn.setVisibility(STATE_OPENING);
        }
        if (this.mViewLocked) {
            XLToast.b(getContext(), XLToastType.XLTOAST_TYPE_NORMAL, getResources().getString(2131233172), STATE_PAUSE);
            return;
        }
        if (this.mBottomBarLyt.getVisibility() != 0) {
            this.mTopBarLyt.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034232));
            this.mBottomBarLyt.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034226));
            this.mBottomBarLyt.setVisibility(STATE_OPENING);
            this.mTopBarLyt.setVisibility(STATE_OPENING);
            if (this.mUiChangeListener != null) {
                this.mUiChangeListener.a(true);
            }
        }
        View view = this.mCenterPlayPauseBtn;
        if (this.mPlayerSate != 1) {
            i = XZBDevice.Wait;
        }
        view.setVisibility(i);
    }

    private void hideControlBar(boolean z) {
        autoHideControlBar(false);
        autoHideLockBar(false);
        if (this.mLockBtn.getVisibility() == 0) {
            if (z) {
                postDelayed(this.mHideLockBarRunnable, TabsImpl.SYNC_TIME_OUT);
            } else {
                this.mHideLockBarRunnable.run();
            }
        }
        if (this.mBottomBarLyt.getVisibility() == 0) {
            this.mBottomBarLyt.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034227));
            this.mTopBarLyt.startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034233));
            this.mBottomBarLyt.setVisibility(XZBDevice.Wait);
            this.mTopBarLyt.setVisibility(XZBDevice.Wait);
            if (this.mUiChangeListener != null) {
                this.mUiChangeListener.a(false);
            }
        }
        this.mCenterPlayPauseBtn.setVisibility(XZBDevice.Wait);
    }

    private void switchControlBarVisibleAutoHide() {
        if (isControlBarVisible()) {
            hideControlBar(false);
            return;
        }
        showControlBar();
        autoHideControlBar(true);
    }

    public final void autoHideControlBar(boolean z) {
        if (z) {
            removeCallbacks(this.mHideControlBarRunnable);
            postDelayed(this.mHideControlBarRunnable, 5000);
            return;
        }
        removeCallbacks(this.mHideControlBarRunnable);
    }

    private void autoHideLockBar(boolean z) {
        if (z) {
            removeCallbacks(this.mHideLockBarRunnable);
            postDelayed(this.mHideLockBarRunnable, TabsImpl.SYNC_TIME_OUT);
            return;
        }
        removeCallbacks(this.mHideLockBarRunnable);
    }

    private void setScreenSize(int i, int i2) {
        if (this.mSurfaceView != null && i > 0 && i2 > 0) {
            LayoutParams layoutParams = (LayoutParams) this.mSurfaceView.getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i2;
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
            layoutParams.gravity = 17;
            this.mSurfaceParent.getLayoutParams().height = i2;
            this.mSurfaceParent.requestLayout();
        }
    }

    private void applyUIParams() {
        CharSequence formatTime = formatTime(this.mUIParams.g);
        CharSequence formatTime2 = formatTime(this.mUIParams.f);
        this.mProcessTv.setText(formatTime);
        this.mDurationTv.setText(formatTime2);
        this.mProressSkb.setMax(this.mUIParams.f);
        this.mProressSkb.setEnabled(true);
        this.mProressSkb.setProgress(this.mUIParams.g);
        setSurfaceViewScale(VideoSize.SIZE_100);
    }

    public final void updatePlayPosition(int i, int i2, int i3) {
        if (i != this.mUIParams.f) {
            this.mUIParams.f = i;
            this.mProressSkb.setMax(i);
        }
        if (i2 >= 0) {
            this.mUIParams.g = i2;
            this.mProcessTv.setText(formatTime(i2));
            this.mProressSkb.setProgress(i2);
        }
        if (i3 >= 0) {
            this.mUIParams.j = i3;
            this.mProressSkb.setSecondaryProgress(i3);
        }
        this.mSystemTimeTv.setText(getTimeString());
    }

    public final void updateDisplayText(CharSequence charSequence, CharSequence charSequence2, int i) {
        this.mUIParams.k = charSequence;
        this.mLoadingView.setLoadingText(charSequence);
        this.mUIParams.l = charSequence2;
        this.mStatusTv.setText(charSequence2);
        if (i != 0) {
            Drawable drawable = getContext().getResources().getDrawable(i);
            int a = g.a(getContext(), 16.0f);
            drawable.setBounds(STATE_OPENING, STATE_OPENING, a, a);
            this.mStatusTv.setCompoundDrawables(drawable, null, null, null);
            return;
        }
        this.mStatusTv.setCompoundDrawables(null, null, null, null);
    }

    public final void updateDownloadSpeed(int i, int i2) {
        this.mUIParams.m = i;
        this.mUIParams.n = i2;
    }

    private void updateVideoSeekBarThumb(SeekBar seekBar, int i) {
        int i2 = STATE_OPENING;
        if (this.mPopupSeekTimeWindow == null) {
            this.mPopupSeekTimeWindow = new e(getContext());
        }
        if (seekBar != null) {
            int measuredWidth;
            e eVar = this.mPopupSeekTimeWindow;
            if (eVar.a != null) {
                measuredWidth = eVar.a.getMeasuredWidth();
            } else {
                measuredWidth = 0;
            }
            int width = (int) ((((double) (measuredWidth / 2)) - (((double) seekBar.getWidth()) * (((double) seekBar.getProgress()) / ((double) seekBar.getMax())))) + ((double) seekBar.getThumbOffset()));
            eVar = this.mPopupSeekTimeWindow;
            if (eVar.a != null) {
                i2 = eVar.a.getMeasuredHeight();
            }
            i2 += seekBar.getHeight();
            CharSequence formatTime = formatTime(i);
            eVar = this.mPopupSeekTimeWindow;
            if (eVar.b != null) {
                eVar.b.setText(formatTime);
            }
            if (!this.mPopupSeekTimeWindow.isShowing()) {
                this.mPopupSeekTimeWindow.showAsDropDown(seekBar, -width, -i2);
            }
            int i3 = -i2;
            this.mPopupSeekTimeWindow.update(seekBar, -width, i3, STATE_IDLE, -1);
            this.mProcessTv.setText(formatTime);
        }
    }

    private void dimissVideoSeekBarThumb() {
        if (this.mPopupSeekTimeWindow != null && this.mPopupSeekTimeWindow.isShowing()) {
            this.mPopupSeekTimeWindow.dismiss();
        }
    }

    private String formatTimeElap(int i, int i2) {
        Context context = getContext();
        if (i <= 0) {
            return context.getString(2131233158);
        }
        String string;
        String formatTime = formatTime(i);
        CharSequence charSequence = null;
        if (i2 > 0) {
            charSequence = formatTime(i2);
        }
        if (TextUtils.isEmpty(string)) {
            string = context.getString(2131233156);
        }
        return String.format(context.getString(2131233157), new Object[]{formatTime, string});
    }

    public final String formatTime(int i) {
        Context context = getContext();
        if (i < 0) {
            return context.getString(2131233156);
        }
        int i2 = i / 1000;
        int i3 = i2 / 3600;
        int i4 = (i2 / 60) % 60;
        i2 %= 60;
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        if (i3 < 10) {
            stringBuilder.append(STATE_OPENING);
        }
        if (i4 < 10) {
            stringBuilder2.append(STATE_OPENING);
        }
        if (i2 < 10) {
            stringBuilder3.append(STATE_OPENING);
        }
        stringBuilder.append(i3);
        stringBuilder2.append(i4);
        stringBuilder3.append(i2);
        return String.format(context.getString(2131233155), new Object[]{stringBuilder, stringBuilder2.toString(), stringBuilder3.toString()});
    }

    public final void changeDLNAState(boolean z) {
        if (z) {
            this.mDLNADotIv.setVisibility(STATE_OPENING);
        } else {
            this.mDLNADotIv.setVisibility(XZBDevice.Wait);
        }
    }

    private void showMenu(int i, int i2, int i3, int i4) {
        this.mMenuPopWindow.a(i, i2);
        VodPlayerMenuPopupWindow vodPlayerMenuPopupWindow = this.mMenuPopWindow;
        vodPlayerMenuPopupWindow.e.setMax(i4);
        vodPlayerMenuPopupWindow.e.setProgress(i3);
        this.mMenuPopWindow.showAtLocation(this, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, STATE_OPENING, STATE_OPENING);
    }

    private void initMenuListener() {
        OnSeekBarChangeListener bcVar = new bc(this);
        OnSeekBarChangeListener arVar = new ar(this);
        com.xunlei.downloadprovider.vod.ui.VodPlayerMenuPopupWindow.a asVar = new as(this);
        OnKeyListener atVar = new at(this);
        VodPlayerMenuPopupWindow vodPlayerMenuPopupWindow = this.mMenuPopWindow;
        vodPlayerMenuPopupWindow.f = asVar;
        vodPlayerMenuPopupWindow.c.setOnCheckedChangeListener(new com.xunlei.downloadprovider.vod.ui.b(vodPlayerMenuPopupWindow));
        vodPlayerMenuPopupWindow.d.setOnSeekBarChangeListener(bcVar);
        vodPlayerMenuPopupWindow.e.setOnSeekBarChangeListener(arVar);
        vodPlayerMenuPopupWindow.b.setOnKeyListener(atVar);
        this.mMenuPopWindow.setOnDismissListener(new au(this));
    }

    private void changeVideoScreenSize(VideoSize videoSize) {
        autoHideControlBar(false);
        setSurfaceViewScale(videoSize);
        autoHideControlBar(true);
        StatReporter.reportVodSizeChange(videoSize);
    }

    private void setSurfaceViewScale(VideoSize videoSize) {
        int[] v = com.xunlei.downloadprovider.a.b.v();
        int i = v[0];
        int i2 = v[1];
        if (getResources().getConfiguration().orientation == 1) {
            i2 = v[0];
            i = v[1];
        }
        if (videoSize != VideoSize.SIZE_FULL) {
            int[] scaleSize = scaleSize(i, i2);
            i = scaleSize[0];
            i2 = scaleSize[1];
            switch (AnonymousClass_1.a[videoSize.ordinal()]) {
                case STATE_BUFFERING:
                    i = (int) (((double) i) * VideoSize.SIZE_75.getValue());
                    i2 = (int) (((double) i2) * VideoSize.SIZE_75.getValue());
                    break;
                case STATE_PLAYING:
                    i = (int) (((double) i) * VideoSize.SIZE_50.getValue());
                    i2 = (int) (((double) i2) * VideoSize.SIZE_50.getValue());
                    break;
            }
        }
        if (i > 0 && i2 > 0) {
            setScreenSize(i, i2);
        }
    }

    private int[] scaleSize(int i, int i2) {
        int[] iArr = new int[2];
        if (this.mUIParams.d > 0 && this.mUIParams.e > 0) {
            if (this.mUIParams.d * i2 > this.mUIParams.e * i) {
                i2 = (this.mUIParams.e * i) / this.mUIParams.d;
            } else if (this.mUIParams.d * i2 < this.mUIParams.e * i) {
                i = (this.mUIParams.d * i2) / this.mUIParams.e;
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }

    public final void showFirstUseTipLay(boolean z) {
        this.mFirstUseTipIv.setVisibility(z ? STATE_OPENING : XZBDevice.Wait);
        if (this.mUiChangeListener != null) {
            this.mUiChangeListener.a();
        }
    }

    public final void setOnUiChangeListener(c cVar) {
        this.mUiChangeListener = cVar;
    }

    public final void setOnEventListener(a aVar) {
        this.mEventListener = aVar;
    }

    public final void setPlayerSate(int i) {
        if (this.mPlayerSate != i) {
            this.mPlayerSate = i;
            if (i == 2) {
                showLoadingView();
            } else {
                hideCircleLoading();
            }
            this.mProressSkb.setEnabled(true);
            this.mPlayPauseBtn.setEnabled(true);
            switch (i) {
                case STATE_OPENING:
                    hideError();
                    setMainPlayVis(false);
                    setSmallPlayBtnIcon(true);
                    enableClick(false);
                    showLoadingView();
                case STATE_PAUSE:
                    enableClick(true);
                    hideError();
                    showControlBar();
                    autoHideControlBar(true);
                    setMainPlayVis(true);
                    setSmallPlayBtnIcon(true);
                case STATE_PLAYING:
                    enableClick(true);
                    hideError();
                    setMainPlayVis(false);
                    setSmallPlayBtnIcon(false);
                case STATE_ERROR:
                    enableClick(false);
                    setMainPlayVis(false);
                    setSmallPlayBtnIcon(true);
                default:
                    break;
            }
        }
    }

    private void enableClick(boolean z) {
        this.mProressSkb.setEnabled(z);
        this.mPlayPauseBtn.setEnabled(z);
        this.mPlayPauseBtn.setEnabled(z);
        this.mDLNABtn.setEnabled(z);
        this.mMenuIv.setEnabled(z);
        this.mShareIv.setEnabled(z);
        this.mCenterPlayPauseBtn.setEnabled(z);
    }

    private void showLoadingView() {
        if (!TextUtils.isEmpty(this.mUIParams.k)) {
            showCircleLoading(this.mUIParams.k);
        }
    }

    public final void onTouchSingleTap() {
        switchControlBarVisibleAutoHide();
    }

    public final void onTouchDoubleTap() {
        if (this.mViewLocked) {
            switchControlBarVisibleAutoHide();
        } else if (this.mPlayerSate != 4 && this.mPlayerSate != 0 && this.mEventListener != null) {
            this.mEventListener.onDoubleClick();
        }
    }

    public final void onTouchMoveStart(TouchOperateType touchOperateType) {
        if (this.mViewLocked) {
            switchControlBarVisibleAutoHide();
        } else if (this.mPlayerSate != 4 && this.mPlayerSate != 0) {
            hideControlBar(false);
            int u = com.xunlei.downloadprovider.a.b.u();
            int t = com.xunlei.downloadprovider.a.b.t();
            if (touchOperateType == TouchOperateType.touch_moveHorizontal) {
                this.mUIParams.a = (((float) this.mUIParams.f) * 0.2f) / ((float) t);
                showCenterProgressWithTextView(this.mUIParams.g, this.mUIParams.f);
                if (this.mEventListener != null) {
                    this.mEventListener.onPlayPostionChangeStart();
                }
            } else if (touchOperateType == TouchOperateType.touch_moveVerticalLeft) {
                if (this.mUIParams.b == 0.0f) {
                    this.mUIParams.b = 255.0f / (((float) u) * 1.0f);
                }
                showCenterProgressView(CenterProgressType.CenterProgress_Brightness, (int) this.mUIParams.i, SYSTEM_BRIGHTNESS_MAX);
            } else if (touchOperateType == TouchOperateType.touch_moveVerticalRight) {
                if (this.mUIParams.c == 0.0f) {
                    this.mUIParams.c = 100.0f / (((float) u) * 1.0f);
                }
                showCenterProgressView(CenterProgressType.CenterProgress_Volume, (int) this.mUIParams.h, AUDIO_PROGRESS_BAR_MAX);
            }
        }
    }

    public final void onTouchMoveUp(TouchOperateType touchOperateType) {
        if (!this.mViewLocked && this.mPlayerSate != 4 && this.mPlayerSate != 0 && isCenterProgressViewShown()) {
            if (touchOperateType == TouchOperateType.touch_moveHorizontal) {
                if (!(this.mUIParams.g == -1 || this.mUIParams.f == -1 || this.mEventListener == null)) {
                    this.mEventListener.onPlayPostionChangeEnd(this.mUIParams.g);
                }
            } else if (touchOperateType != TouchOperateType.touch_moveVerticalLeft) {
                TouchOperateType touchOperateType2 = TouchOperateType.touch_moveVerticalRight;
            }
            hideCenterProgressView();
        }
    }

    public final void onTouchMoveHorizontal(float f, float f2, float f3, float f4) {
        if (!this.mViewLocked && this.mPlayerSate != 4 && this.mPlayerSate != 0 && this.mUIParams.g != -1 && this.mUIParams.f != -1) {
            float f5 = (f - f3) * this.mUIParams.a;
            d dVar = this.mUIParams;
            dVar.g = (int) (f5 + ((float) dVar.g));
            if (this.mUIParams.g < 0) {
                this.mUIParams.g = 0;
            }
            if (this.mUIParams.g > this.mUIParams.f) {
                this.mUIParams.g = this.mUIParams.f;
            }
            updateCenterProgressWithText(this.mUIParams.g, this.mUIParams.f);
            if (this.mEventListener != null) {
                this.mEventListener.onPlayPostionChanged(true, this.mUIParams.g);
            }
        }
    }

    public final void onTouchMoveVerticalLeft(float f, float f2, float f3, float f4) {
        if (!this.mViewLocked && this.mPlayerSate != 4 && this.mPlayerSate != 0) {
            float f5 = (f4 - f2) * this.mUIParams.b;
            d dVar = this.mUIParams;
            dVar.i = f5 + dVar.i;
            updateCenterProgressView((int) this.mUIParams.i);
            if (this.mEventListener != null) {
                this.mEventListener.onBrightnessChanged(this.mUIParams.i);
            }
        }
    }

    public final void onTouchMoveVerticalRight(float f, float f2, float f3, float f4) {
        if (!this.mViewLocked && this.mPlayerSate != 4 && this.mPlayerSate != 0) {
            float f5 = (f4 - f2) * this.mUIParams.c;
            d dVar = this.mUIParams;
            dVar.h = f5 + dVar.h;
            updateCenterProgressView((int) this.mUIParams.h);
            if (this.mEventListener != null) {
                this.mEventListener.onVolumnChanged(this.mUIParams.h);
            }
        }
    }
}
