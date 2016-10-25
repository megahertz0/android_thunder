package com.xunlei.downloadprovider.vod;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.aplayer.aplayerandroid.APlayerAndroid;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.dlnaplugin.IOUtil;
import com.xunlei.downloadprovider.dlnaplugin.PluginStatic;
import com.xunlei.downloadprovider.download.taskDetail.au;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.model.protocol.report.ReportContants.Vod.VodReportPlayState;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.a.k$b;
import com.xunlei.downloadprovider.service.downloads.task.f;
import com.xunlei.downloadprovider.util.ag;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.vod.VodCenterProgressView.CenterProgressType;
import com.xunlei.downloadprovider.vod.VodPlayerView.c;
import com.xunlei.downloadprovider.vod.VodPlayerView.d;
import com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.h;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovidershare.ba;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RejectedExecutionException;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public final class VodPlayerActivity extends BaseActivity implements OnDLNADialogListener, com.xunlei.downloadprovider.vod.VodPlayerView.a, com.xunlei.downloadprovidershare.d.a {
    public static final int AUDIO_PROGRESS_BAR_MAX = 100;
    public static final String BUNDEL_KEY_IS_FROM_NOTIFICATION = "bundle_key_is_from_notification";
    public static int EntryFrom = 0;
    private static final int FINISH_DELAY = 1500;
    private static final int HANDLE_MSG_MEDIAPLAYER_PREPARED = 77893;
    private static final int HANDLE_MSG_ON_ERROR = 77894;
    private static final int HANDLE_MSG_ON_SLICED_FAILED = 77896;
    private static final int HANDLE_MSG_ON_SLICED_SUCCESS = 77895;
    private static final int HANDLE_MSG_PLAY_ERROR = 77898;
    private static final int HANDLE_MSG_PLAY_NEXT = 77897;
    private static final int HANDLE_MSG_UPDATE = 77892;
    public static final String INTENT_KEY_DOWNLOAD_VOD_PARAMS = "intent_key_download_vod_params";
    public static final String INTENT_KEY_VOD_PLAYER_PARAMS = "intent_key_vod_player_params";
    private static final long OPEN_TIMEOUT_DELAY = 30000;
    private static final int QUIT_INTERVAL = 2500;
    private static final int REQUEST_CODE_WIFI_SETTING = 1;
    private static final String SHARE_PREFENERCE_KEY_IS_SHOW_DEFAULT_PLAYER_TIPS = "is_show_default_player_tips";
    private static final String SHARE_PREFENERCE_KEY_IS_SHOW_FIRST_DOWNLOADING_PLAY_TIPS = "is_show_first_downloading_play_tips";
    private static final String SHARE_PREFENERCE_NAME = "vod_player";
    private static final int SUSPEND_DRAG = 0;
    private static final int SUSPEND_NO_DRAG = 1;
    public static final int SYSTEM_BRIGHTNESS_MAX = 255;
    private static final String TAG;
    private static final int UPDATE_INTERVAL = 1000;
    private APlayerAndroid aPlayerAndroid;
    private Class<?> cls;
    private boolean handleNewIntent;
    private boolean hasError;
    private boolean isBuffering;
    private boolean isFromNotification;
    private boolean isLogout;
    private boolean isSeeking;
    private boolean isSetNotiSoundOff;
    private String mAccelSpeed;
    private boolean mAccelTipEnable;
    private OnAudioFocusChangeListener mAudioFocusChangedListener;
    private AudioManager mAudioManager;
    private int mBufferCount;
    private ClickableSpan mClickableSpan;
    private boolean mClickableSpanClicked;
    private float mCurAudioProgress;
    private float mCurBrightness;
    private int mCurPlayPos;
    private String mCurPlayingUrl;
    private a mDlnaInfo;
    private a mDownloadVodParams;
    private long mDownloadedTaskId;
    private Runnable mFinishRunnable;
    private String mGCID;
    private boolean mIfVipBxbb;
    private boolean mIsBeforePlaying;
    private boolean mIsOpenningBeforePaused;
    private boolean mIsPlayingBeforePaused;
    private boolean mIsResumed;
    private boolean mIsVisibleToUser;
    private long mLastKeyTime;
    private CharSequence mLoadingTextPrefix;
    private CharSequence mLoadingTextSuffix;
    private long mLoadingTime;
    private g mLogoutObserver;
    private int mMinuUpdate;
    private boolean mMobileNetworkNotify;
    private boolean mNeedStartResume;
    private boolean mNoNetworkNotify;
    private boolean mNotReportSpeedBfTextShow;
    private com.xunlei.downloadprovider.vod.protocol.b.a mObtainDownloadVodInfoListener;
    private APlayerAndroid mOldPlayer;
    private b mOnDefinitionChoiceListener;
    private com.xunlei.downloadprovider.vod.VodPlayerView.b mOnInitiTitleListener;
    private c mOnUiChangeListener;
    private boolean mOpenTimeOut;
    private Runnable mOpenTimoutRunnable;
    private List<Long> mPausedTaskIds;
    private MediaPlayerPlayCMD mPlayCMD;
    private boolean mPlayInfoReady;
    private String mPlaySessionId;
    private SharpnessValue mPreDefinition;
    private Runnable mResumeRunnable;
    private View mShare;
    private Object mShowDLNADialog;
    private int mStatusIconResId;
    private CharSequence mStatusText;
    private long mSuspenDragTime;
    private int mSuspendDragCount;
    private long mSuspendEndTime;
    private int mSuspendNoDragCount;
    private long mSuspendNoDragTime;
    private long mSuspendStartTime;
    private int mSuspendType;
    private TaskInfo mTaskInfo;
    private Callback mTaskOperateMessageListener;
    private Timer mTimer;
    private String mTitleForDlna;
    private String mToPlayUrl;
    private String mTotalSpeed;
    private Handler mUIHandler;
    private b mUpdateTimerTask;
    private int mUseDuration;
    private int mVideoDuration;
    private int mVideoHeight;
    private int mVideoWidth;
    private ap mVodPlayerParams;
    private VodPlayerView mVodPlayerView;
    private Object mVodProtocolObj;
    private com.xunlei.downloadprovider.util.ag.b mXLBroadcastListener;
    private boolean playAnother;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            b = new int[VodSourceType.values().length];
            try {
                b[VodSourceType.normal.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[VodSourceType.download_detail.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[VodSourceType.local_appinner.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[VodSourceType.local_system.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[VodSourceType.lixian.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[VodSourceType.vod_history.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[VodSourceType.sniffing_list.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[VodSourceType.old_detail_other.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[VodSourceType.uc_cloud.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                b[VodSourceType.space_his.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            a = new int[VodVideoFormat.values().length];
            try {
                a[VodVideoFormat.flv.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[VodVideoFormat.mp4.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    public enum MediaPlayerPlayCMD {
        Play_None,
        Play_Prepare,
        Play_Prepare_Start,
        Play_OnResume_Start;

        static {
            Play_None = new com.xunlei.downloadprovider.vod.VodPlayerActivity.MediaPlayerPlayCMD("Play_None", 0);
            Play_Prepare = new com.xunlei.downloadprovider.vod.VodPlayerActivity.MediaPlayerPlayCMD("Play_Prepare", 1);
            Play_Prepare_Start = new com.xunlei.downloadprovider.vod.VodPlayerActivity.MediaPlayerPlayCMD("Play_Prepare_Start", 2);
            Play_OnResume_Start = new com.xunlei.downloadprovider.vod.VodPlayerActivity.MediaPlayerPlayCMD("Play_OnResume_Start", 3);
            a = new com.xunlei.downloadprovider.vod.VodPlayerActivity.MediaPlayerPlayCMD[]{Play_None, Play_Prepare, Play_Prepare_Start, Play_OnResume_Start};
        }
    }

    class a {
        String a;
        boolean b;
        List<com.xunlei.downloadprovider.vod.protocol.h.b> c;

        a() {
        }
    }

    private class b extends TimerTask {
        private b() {
        }

        public final void run() {
            if (VodPlayerActivity.this.aPlayerAndroid != null) {
                VodPlayerActivity.this.mUIHandler.obtainMessage(HANDLE_MSG_UPDATE).sendToTarget();
                VodPlayerActivity.access$7012(VodPlayerActivity.this, SUSPEND_NO_DRAG);
            }
        }
    }

    public VodPlayerActivity() {
        this.mUseDuration = 0;
        this.mPausedTaskIds = null;
        this.mVideoDuration = 0;
        this.mCurPlayPos = 0;
        this.mMinuUpdate = 0;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mCurAudioProgress = 0.0f;
        this.mCurBrightness = 0.0f;
        this.mVodPlayerParams = null;
        this.mDownloadVodParams = null;
        this.mDownloadedTaskId = -1;
        this.mCurPlayingUrl = null;
        this.mVodProtocolObj = null;
        this.isSetNotiSoundOff = false;
        this.isLogout = false;
        this.isSeeking = false;
        this.isBuffering = false;
        this.mPlayCMD = MediaPlayerPlayCMD.Play_Prepare_Start;
        this.mVodPlayerView = null;
        this.mUIHandler = null;
        this.mLoadingTextPrefix = null;
        this.mLoadingTextSuffix = null;
        this.mStatusText = "\u672c\u5730";
        this.mStatusIconResId = 0;
        this.mTotalSpeed = null;
        this.mAccelSpeed = null;
        this.mTimer = null;
        this.mUpdateTimerTask = null;
        this.isFromNotification = false;
        this.mTitleForDlna = null;
        this.cls = null;
        this.mPlaySessionId = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        this.mLoadingTime = 0;
        this.mSuspenDragTime = 0;
        this.mSuspendDragCount = 0;
        this.mSuspendNoDragTime = 0;
        this.mSuspendNoDragCount = 0;
        this.mSuspendStartTime = System.currentTimeMillis();
        this.mSuspendEndTime = this.mSuspendStartTime;
        this.mSuspendType = 1;
        this.mIfVipBxbb = false;
        this.mAccelTipEnable = true;
        this.mIsBeforePlaying = true;
        this.mIsPlayingBeforePaused = false;
        this.mIsOpenningBeforePaused = false;
        this.mPreDefinition = null;
        this.mOpenTimeOut = false;
        this.mPlayInfoReady = false;
        this.mMobileNetworkNotify = true;
        this.mNoNetworkNotify = true;
        this.mIsVisibleToUser = false;
        this.mIsResumed = false;
        this.mNeedStartResume = false;
        this.handleNewIntent = false;
        this.mOldPlayer = null;
        this.mBufferCount = 0;
        this.mNotReportSpeedBfTextShow = true;
        this.mClickableSpanClicked = false;
        this.mLogoutObserver = new z(this);
        this.mFinishRunnable = new ac(this);
        this.mLastKeyTime = 0;
    }

    static /* synthetic */ int access$4908(VodPlayerActivity vodPlayerActivity) {
        int i = vodPlayerActivity.mBufferCount;
        vodPlayerActivity.mBufferCount = i + 1;
        return i;
    }

    static /* synthetic */ long access$5714(VodPlayerActivity vodPlayerActivity, long j) {
        long j2 = vodPlayerActivity.mSuspenDragTime + j;
        vodPlayerActivity.mSuspenDragTime = j2;
        return j2;
    }

    static /* synthetic */ int access$5808(VodPlayerActivity vodPlayerActivity) {
        int i = vodPlayerActivity.mSuspendDragCount;
        vodPlayerActivity.mSuspendDragCount = i + 1;
        return i;
    }

    static /* synthetic */ long access$5914(VodPlayerActivity vodPlayerActivity, long j) {
        long j2 = vodPlayerActivity.mSuspendNoDragTime + j;
        vodPlayerActivity.mSuspendNoDragTime = j2;
        return j2;
    }

    static /* synthetic */ int access$6008(VodPlayerActivity vodPlayerActivity) {
        int i = vodPlayerActivity.mSuspendNoDragCount;
        vodPlayerActivity.mSuspendNoDragCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$7012(VodPlayerActivity vodPlayerActivity, int i) {
        int i2 = vodPlayerActivity.mUseDuration + i;
        vodPlayerActivity.mUseDuration = i2;
        return i2;
    }

    static {
        TAG = VodPlayerActivity.class.getSimpleName();
        EntryFrom = -1;
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new Thread(new k(this)).start();
        StatReporter.reportVodOpen();
        enterfullScreen();
        setContentView(2130969037);
        this.mLoadingTime = System.currentTimeMillis();
        this.mIsBeforePlaying = true;
        initUI();
        initEvent();
        initHardware();
        initData();
        this.mUIHandler = new Handler(this.mTaskOperateMessageListener);
        this.mResumeRunnable = new v(this);
        handleIntent();
    }

    private void loadDLNADex() {
        File file = new File(getDir("pluginApkDex", SUSPEND_DRAG), "pluginCdn.apk");
        try {
            if (!file.exists()) {
                InputStream open = getAssets().open("xldlnapass.jar");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = open.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, SUSPEND_DRAG, read);
                    } else {
                        fileOutputStream.close();
                        fileOutputStream.flush();
                        open.close();
                        return;
                    }
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void initUI() {
        this.mVodPlayerView = (VodPlayerView) findViewById(2131757162);
        this.aPlayerAndroid = new APlayerAndroid();
        this.aPlayerAndroid.SetView(this.mVodPlayerView.getSufaceView());
        this.aPlayerAndroid.SetConfig(209, MessageService.MSG_DB_NOTIFY_REACHED);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREFENERCE_NAME, SUSPEND_DRAG);
        if (sharedPreferences.getBoolean(SHARE_PREFENERCE_KEY_IS_SHOW_DEFAULT_PLAYER_TIPS, false)) {
            tryShowToastForFirstDownloadingPlay();
            return;
        }
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(SHARE_PREFENERCE_KEY_IS_SHOW_DEFAULT_PLAYER_TIPS, true);
        edit.commit();
        this.mVodPlayerView.showFirstUseTipLay(true);
    }

    private void initEvent() {
        this.mXLBroadcastListener = new ad(this);
        this.mAudioFocusChangedListener = new af(this);
        this.mOnUiChangeListener = new ag(this);
        this.mOnInitiTitleListener = new ah(this);
        this.mObtainDownloadVodInfoListener = new ai(this);
        this.mOpenTimoutRunnable = new aj(this);
        initPlayerCallback();
        this.mTaskOperateMessageListener = new ak(this);
    }

    private void initPlayerCallback() {
        this.aPlayerAndroid.setOnOpenCompleteListener(new l(this));
        this.aPlayerAndroid.setOnPlayCompleteListener(new m(this));
        this.aPlayerAndroid.setOnBufferListener(new n(this));
    }

    private void postTimeoutRunnable() {
        this.mOpenTimeOut = false;
        this.mUIHandler.postDelayed(this.mOpenTimoutRunnable, OPEN_TIMEOUT_DELAY);
    }

    private void initHardware() {
        this.mCurBrightness = (float) getScreenBrightness(this);
        this.mAudioManager = (AudioManager) getSystemService("audio");
        setVolumeControlStream(XZBDevice.DOWNLOAD_LIST_FAILED);
        getCurrentVoice();
        createDLNADialog();
        d dVar = new d();
        dVar.i = this.mCurBrightness;
        dVar.h = this.mCurAudioProgress;
        dVar.d = this.mVideoWidth;
        dVar.e = this.mVideoHeight;
        this.mVodPlayerView.setUIParams(dVar);
    }

    private void initData() {
        boolean z = true;
        r.c cVar = r.c().m;
        if (cVar.a == null || !cVar.a.optBoolean("show_vip_speedup_for_player", true)) {
            z = false;
        }
        this.mAccelTipEnable = z;
        new StringBuilder("mAccelTipEnable=>").append(this.mAccelTipEnable);
    }

    private void handleOpenError(int i) {
        handleOpenError(getResources().getString(i));
    }

    private void handleOpenError(String str) {
        if (this.mUIHandler != null) {
            this.hasError = true;
            XLToast.a();
            if (this.mUIHandler != null) {
                this.mUIHandler.removeCallbacksAndMessages(null);
            } else {
                this.mUIHandler = new Handler(this.mTaskOperateMessageListener);
            }
            this.mVodPlayerView.setPlayerSate(XZBDevice.DOWNLOAD_LIST_ALL);
            this.mUIHandler.postDelayed(new o(this, str), 300);
        }
    }

    private void onOpenVideoSuccess() {
        setCurrentPlayUrl(this.mToPlayUrl);
        prepareDlnaInfo();
        this.mVideoDuration = this.aPlayerAndroid.GetDuration();
        this.mVideoWidth = this.aPlayerAndroid.GetVideoWidth();
        this.mVideoHeight = this.aPlayerAndroid.GetVideoHeight();
        VodUtil.a();
        int a = VodUtil.a(this.mVodPlayerParams.a());
        if (a > 0 && a < this.aPlayerAndroid.GetDuration() - 3000) {
            seekToPlayer(a);
            if (a > 3000) {
                XLToast.c(this, XLToastType.XLTOAST_TYPE_NORMAL, getResources().getString(2131233170, new Object[]{this.mVodPlayerView.formatTime(a)}));
            }
        } else if (this.mVodPlayerParams.g > 0) {
            seekToPlayer(this.mVodPlayerParams.g);
        }
        d uIParams = this.mVodPlayerView.getUIParams();
        uIParams.e = this.mVideoHeight;
        uIParams.d = this.mVideoWidth;
        uIParams.f = this.mVideoDuration;
        uIParams.g = this.aPlayerAndroid.GetPosition();
        this.mVodPlayerView.setUIParams(uIParams);
        this.aPlayerAndroid.Play();
        getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        this.mVodPlayerView.setPlayerSate(XZBDevice.DOWNLOAD_LIST_FAILED);
        reportVodPlay(VodReportPlayState.success, "sucess");
        onVideoStartPlay();
        this.mIsBeforePlaying = false;
        this.mIsOpenningBeforePaused = false;
    }

    private void onOpenVideoFailed() {
        handleOpenError(2131233175);
    }

    private void onPlayEnd() {
        this.mVodPlayerParams.a().s = this.mVodPlayerParams.a().r;
        if (this.mVodPlayerParams != null) {
            VodUtil.a();
            VodUtil.a(this.mVodPlayerParams.a(), this.mVodPlayerParams.b);
        }
        back();
    }

    private void prepareDlnaInfo() {
        this.mDlnaInfo = getRealUrlForDLNA();
        List list = null;
        if (this.mDlnaInfo != null) {
            list = this.mDlnaInfo.c;
        }
        if (list != null && !list.isEmpty()) {
            postEpLiForRealUrl(changeArrayDateToJson(list));
        }
    }

    private void handlePlayingError(String str) {
        if (this.mUIHandler != null) {
            XLToast.a();
            if (this.mUIHandler != null) {
                this.mUIHandler.removeCallbacksAndMessages(null);
            } else {
                this.mUIHandler = new Handler(this.mTaskOperateMessageListener);
            }
            if ("0x80000006".contentEquals(str)) {
                this.aPlayerAndroid.SetConfig(209, MessageService.MSG_DB_READY_REPORT);
            }
            this.mVodPlayerView.setPlayerSate(XZBDevice.DOWNLOAD_LIST_ALL);
            this.mUIHandler.postDelayed(new p(this), 300);
        }
    }

    private void checkUrl() {
        if (!checkVideoInfo(this.mVodPlayerParams)) {
            handleOpenError(2131233175);
        }
    }

    private void setPlayerViewWithPlayParam() {
        this.mVodPlayerView.setOnUiChangeListener(this.mOnUiChangeListener);
        this.mVodPlayerView.setOnEventListener(this);
        this.mVodPlayerView.initTitle(this.mVodPlayerParams, this.mOnInitiTitleListener);
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.mIsVisibleToUser = z;
    }

    protected final void onResume() {
        super.onResume();
        if (this.mUIHandler != null) {
            this.mUIHandler.post(this.mResumeRunnable);
        }
    }

    private void onVisible() {
        this.mAudioManager.requestAudioFocus(this.mAudioFocusChangedListener, XZBDevice.DOWNLOAD_LIST_FAILED, SUSPEND_NO_DRAG);
        ag.a(this.mXLBroadcastListener);
        hideNavigation();
        startUpdateTimerTask();
        if (this.mIsOpenningBeforePaused) {
            onOpenVideoSuccess();
            return;
        }
        if (this.mIsPlayingBeforePaused) {
            startPlayer(true);
        } else if (this.mPlayInfoReady && isPaused()) {
            this.mVodPlayerView.setPlayerSate(SUSPEND_NO_DRAG);
            getWindow().clearFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        }
        if (this.mVodPlayerParams != null && !VodUtil.a(this.mVodPlayerParams.b) && !isPaused()) {
            if (this.mPlayInfoReady) {
                checkNetworkEnvironmentPlaying();
            } else if (this.mNeedStartResume) {
                checkNetworkEnvironmentStart();
            }
        }
    }

    private void enterfullScreen() {
        getWindow().getDecorView().setSystemUiVisibility((((getWindow().getDecorView().getSystemUiVisibility() | 512) | 1024) | 4) | 4096);
    }

    private void hideNavigation() {
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | 2);
    }

    private void showNavigation() {
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() & -3);
    }

    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (isPrepared()) {
            handleIntent();
            return;
        }
        this.handleNewIntent = true;
        this.aPlayerAndroid.Close();
    }

    private void handleIntent() {
        if (this.mUIHandler == null) {
            this.mUIHandler = new Handler(this.mTaskOperateMessageListener);
        }
        parseIntentData(getIntent());
        if (this.mVodPlayerParams == null) {
            XLToast.b(this, XLToastType.XLTOAST_TYPE_ALARM, "\u83b7\u53d6\u64ad\u653e\u53c2\u6570\u5931\u8d25");
            finishDelay();
            return;
        }
        checkUrl();
        setPlayerViewWithPlayParam();
        startupThroughSourceType(true);
    }

    private void setDownloadingPlayTask() {
        if (this.mDownloadVodParams != null && this.mDownloadVodParams.b > 0) {
            if (this.mDownloadVodParams.c == -1) {
                com.xunlei.downloadprovider.service.downloads.task.d.a().b(this.mDownloadVodParams.b);
            } else {
                com.xunlei.downloadprovider.service.downloads.task.d a = com.xunlei.downloadprovider.service.downloads.task.d.a();
                long j = this.mDownloadVodParams.b;
                long j2 = this.mDownloadVodParams.c;
                try {
                    a.b.execute(new f(a, new k$b(Long.valueOf(j), Long.valueOf(j2))));
                } catch (RejectedExecutionException e) {
                    com.xunlei.downloadprovider.service.downloads.kernel.c.a().a(j, j2);
                }
            }
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            this.mTaskInfo = com.xunlei.downloadprovider.service.downloads.task.d.d(this.mDownloadVodParams.b);
            updateVipInfo();
            h a2 = this.mVodPlayerParams.a();
            if (a2 != null) {
                this.mGCID = a2.e;
            } else if (this.mTaskInfo != null) {
                this.mGCID = this.mTaskInfo.mGCID;
            }
            if (this.mGCID == null || this.mGCID.trim().isEmpty()) {
                this.mGCID = "no_gcid";
            }
        }
    }

    private void updateVipInfo() {
        if (this.mTaskInfo != null) {
            if (!(this.mTaskInfo.mHasVipChannelSpeedup && this.mTaskInfo.mVipChannelStatus == 8)) {
                if (!(this.mTaskInfo.mHasLixianSpeedup && this.mTaskInfo.mLixianStatus == 8) && this.mTaskInfo.mLixianSpeed <= 0 && this.mTaskInfo.mVipChannelSpeed <= 0) {
                    this.mIfVipBxbb = false;
                    return;
                }
            }
            this.mIfVipBxbb = true;
            return;
        }
        this.mIfVipBxbb = false;
    }

    private void clearDownloadingPlayTask() {
        if (this.mDownloadVodParams != null && this.mDownloadVodParams.b > 0) {
            com.xunlei.downloadprovider.service.downloads.task.d.a().b(-1);
        }
    }

    private void parseIntentData(Intent intent) {
        this.mVodPlayerParams = null;
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra(INTENT_KEY_VOD_PLAYER_PARAMS);
            if (serializableExtra != null) {
                this.mVodPlayerParams = (ap) serializableExtra;
            } else if ("android.intent.action.VIEW".equals(intent.getAction()) && intent.getData() != null) {
                try {
                    this.mVodPlayerParams = ao.a(new URI(intent.getDataString()).getPath(), VodSourceType.local_system);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            if (!(this.mVodPlayerParams == null || this.mVodPlayerParams.a() == null)) {
                StatReporter.reportVodBehavior(this.mVodPlayerParams.b, this.mVodPlayerParams.a().c, this.mVodPlayerParams.a().a, this.mVodPlayerParams.c);
            }
            this.isFromNotification = intent.getBooleanExtra(BUNDEL_KEY_IS_FROM_NOTIFICATION, false);
            this.mDownloadVodParams = (a) getIntent().getSerializableExtra(INTENT_KEY_DOWNLOAD_VOD_PARAMS);
            this.mDownloadedTaskId = getIntent().getLongExtra("downloaded_taskId", -1);
            processShareBtn();
            setDownloadingPlayTask();
        }
    }

    private boolean checkVideoInfo(ap apVar) {
        if (apVar == null || apVar.a() == null || apVar.k.size() <= 0) {
            return false;
        }
        h a = apVar.a();
        if (TextUtils.isEmpty(a.c)) {
            new StringBuilder("ep.mUrl : ").append(a.c);
            return false;
        }
        apVar.a = 0;
        return true;
    }

    private void startupThroughSourceType(boolean z) {
        this.mPlayCMD = MediaPlayerPlayCMD.Play_Prepare_Start;
        this.mVodPlayerView.showAccelBtn(false);
        processVideoStatusText();
        this.mVodPlayerView.setPlayerSate(SUSPEND_DRAG);
        if (VodUtil.a(this.mVodPlayerParams.b)) {
            String string;
            new StringBuilder("local video=>").append(this.mVodPlayerParams.b);
            if (com.xunlei.downloadprovider.businessutil.b.a().h()) {
                com.xunlei.downloadprovider.businessutil.b.a().a(false);
                this.isSetNotiSoundOff = true;
            }
            String str = this.mVodPlayerParams.a().c;
            if (str.startsWith("/external/video")) {
                Cursor query = getContentResolver().query(Uri.parse(new StringBuilder("content://media").append(str).toString()), new String[]{Impl._DATA}, null, null, WebBrowserActivity.EXTRA_TITLE);
                int columnIndex = query.getColumnIndex(Impl._DATA);
                query.moveToFirst();
                string = query.getString(columnIndex);
                query.close();
            } else {
                string = str;
            }
            if (string == null || (string.startsWith("/") && !new File(string).exists())) {
                this.mToPlayUrl = null;
                handleOpenError("\u6587\u4ef6\u4e0d\u5b58\u5728");
                return;
            }
            postTimeoutRunnable();
            this.mToPlayUrl = string;
            this.aPlayerAndroid.Open(string);
            return;
        }
        new StringBuilder("onLine video=>").append(this.mVodPlayerParams.b);
        if (z) {
            checkNetworkEnvironmentStart();
        } else {
            getOnlineVideoUrl();
        }
    }

    private void processVideoStatusText() {
        CharSequence spannableString;
        int i = SUSPEND_DRAG;
        if (VodUtil.b(this.mVodPlayerParams.b)) {
            int i2;
            if (this.mTaskInfo == null) {
                this.mLoadingTextPrefix = "\u4e0b\u8f7d\u51c6\u5907\u4e2d";
                this.mAccelSpeed = com.umeng.a.d;
                this.mTotalSpeed = com.umeng.a.d;
                this.mStatusText = com.umeng.a.d;
                this.mStatusIconResId = 0;
            } else if (this.mTaskInfo.mFileSize <= 0 || this.mTaskInfo.mDownloadedSize < this.mTaskInfo.mFileSize) {
                updateVipInfo();
                this.mAccelSpeed = new StringBuilder("(+").append(formatSpeed(this.mTaskInfo.mVipAcceleratedChannelSpeed)).append(SocializeConstants.OP_CLOSE_PAREN).toString();
                this.mTotalSpeed = formatSpeed(this.mTaskInfo.mDownloadSpeed);
                this.mLoadingTextPrefix = "\u6b63\u5728\u4e0b\u8f7d\u4e2d\uff1a";
                if (this.mAccelTipEnable) {
                    this.mStatusText = "\u52a0\u901f\u4e2d";
                    this.mStatusIconResId = 2130838437;
                } else {
                    this.mStatusText = com.umeng.a.d;
                    this.mStatusIconResId = 0;
                }
            } else {
                this.mLoadingTextPrefix = com.umeng.a.d;
                this.mStatusText = "\u672c\u5730";
                this.mStatusIconResId = 0;
                this.mVodPlayerView.updateDisplayText(this.mLoadingTextPrefix, this.mStatusText, this.mStatusIconResId);
                return;
            }
            if (this.mIfVipBxbb && this.mAccelTipEnable) {
                this.mLoadingTextSuffix = com.umeng.a.d;
                this.mVodPlayerView.showAccelBtn(false);
            } else if (this.mTaskInfo == null || !this.mAccelTipEnable) {
                this.mLoadingTextSuffix = com.umeng.a.d;
                this.mAccelSpeed = com.umeng.a.d;
                this.mVodPlayerView.showAccelBtn(false);
            } else {
                if ((isPrepared() || isOpening()) && System.currentTimeMillis() - this.mLoadingTime > 2800) {
                    this.mLoadingTextSuffix = "\n\u5f00\u542f\u4f1a\u5458\u52a0\u901f\uff0c\u64ad\u653e\u66f4\u987a\u7545";
                    if (this.mNotReportSpeedBfTextShow) {
                        this.mNotReportSpeedBfTextShow = false;
                        String str = com.umeng.a.d;
                        String str2 = com.umeng.a.d;
                        LoginHelper.a();
                        if (LoginHelper.c()) {
                            str = LoginHelper.a().j;
                            str2 = LoginHelper.a().h;
                        }
                        com.xunlei.downloadprovidercommon.a.c a = com.xunlei.downloadprovidercommon.a.a.a("android_player", "bxbb_buffer_qp_show");
                        a.a("product_type", str2);
                        a.a("userid", str);
                        com.xunlei.downloadprovidercommon.a.d.a(a);
                    }
                } else {
                    this.mLoadingTextSuffix = com.umeng.a.d;
                }
                this.mAccelSpeed = com.umeng.a.d;
                this.mVodPlayerView.showAccelBtn(true);
            }
            StringBuilder stringBuilder = new StringBuilder(this.mLoadingTextPrefix);
            stringBuilder.append(this.mTotalSpeed);
            int indexOf;
            if (this.mIfVipBxbb) {
                stringBuilder.append(this.mAccelSpeed);
                i = stringBuilder.indexOf(SocializeConstants.OP_OPEN_PAREN);
                indexOf = stringBuilder.indexOf(SocializeConstants.OP_CLOSE_PAREN) + 1;
                stringBuilder.append(this.mLoadingTextSuffix);
                i2 = i;
                i = indexOf;
            } else if (this.mLoadingTextSuffix == null || this.mLoadingTextSuffix.length() <= 0) {
                i2 = -1;
            } else {
                stringBuilder.append(this.mLoadingTextSuffix);
                i2 = stringBuilder.length() - this.mLoadingTextSuffix.length();
                indexOf = stringBuilder.length();
                if (this.mClickableSpan == null) {
                    this.mClickableSpanClicked = false;
                    this.mClickableSpan = new q(this);
                }
                i = indexOf;
            }
            if (i2 < 0 || i < 0) {
                StringBuilder stringBuilder2 = stringBuilder;
            } else {
                spannableString = new SpannableString(stringBuilder);
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(2131689507)), i2, i, R.styleable.Toolbar_collapseIcon);
                if (!(this.mClickableSpan == null || this.mIfVipBxbb)) {
                    spannableString.setSpan(this.mClickableSpan, i2, i, R.styleable.Toolbar_collapseIcon);
                }
            }
        } else if (VodUtil.a(this.mVodPlayerParams.b)) {
            this.mLoadingTextPrefix = null;
            this.mStatusText = "\u672c\u5730";
            this.mStatusIconResId = 0;
            spannableString = this.mLoadingTextPrefix;
        } else {
            this.mLoadingTextPrefix = "\u6b63\u5728\u7f13\u51b2...";
            this.mStatusText = "\u5728\u7ebf";
            this.mStatusIconResId = 0;
            spannableString = this.mLoadingTextPrefix;
        }
        this.mVodPlayerView.updateDisplayText(spannableString, this.mStatusText, this.mStatusIconResId);
    }

    private void handleSpeedUpEvent(long j, String str) {
        LoginHelper a = LoginHelper.a();
        if (!LoginHelper.d()) {
            com.xunlei.downloadprovider.download.a.a.a(this, PayFrom.DOWNLOAD_TASK_SPEED_UP, str);
            a.a(new r(this, j));
        } else if (a.f() || a.y > 0) {
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            com.xunlei.downloadprovider.service.downloads.task.d.a(j);
        } else {
            com.xunlei.downloadprovider.download.a.a.a(this, PayFrom.DOWNLOAD_TASK_SPEED_UP, str);
        }
    }

    private String formatSpeed(long j) {
        double d = 4096.0d;
        double d2 = 0.0d;
        if (j > 0) {
            d2 = (double) (j / 1024);
        }
        if (d2 <= 4096.0d) {
            d = d2;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        if (d >= 1024.0d) {
            d /= 1024.0d;
            return String.format(getResources().getString(2131233154), new Object[]{decimalFormat.format(d)});
        }
        return String.format(getResources().getString(2131233153), new Object[]{decimalFormat.format(d)});
    }

    private void checkNetworkEnvironmentStart() {
        if (com.xunlei.xllib.a.b.f(getApplicationContext())) {
            this.mVodPlayerView.dimissWifiNotifyDialog();
            this.mMobileNetworkNotify = true;
            this.mMobileNetworkNotify = true;
            getOnlineVideoUrl();
            return;
        }
        String string = getString(2131233182);
        OnClickListener sVar = new s(this);
        OnCancelListener tVar = new t(this);
        if (com.xunlei.xllib.a.b.e(getApplicationContext())) {
            String string2 = getString(2131233186);
            String string3 = getString(2131233179);
            OnClickListener uVar = new u(this);
            this.mNoNetworkNotify = true;
            this.mVodPlayerView.showWifiNotifyDialog(string2, string3, string, uVar, sVar, tVar);
            this.mMobileNetworkNotify = false;
            return;
        }
        uVar = new w(this);
        this.mMobileNetworkNotify = true;
        this.mVodPlayerView.showWifiNotifyDialog("\u5f53\u524d\u65e0\u7f51\u7edc\u8fde\u63a5", "\u505c\u6b62\u64ad\u653e", string, uVar, sVar, tVar);
        this.mNoNetworkNotify = false;
    }

    private void checkNetworkEnvironmentPlaying() {
        if (com.xunlei.xllib.a.b.f(getApplicationContext()) || this.mVodPlayerView.isNetworkDialogShowing()) {
            this.mMobileNetworkNotify = true;
            this.mMobileNetworkNotify = true;
            this.mVodPlayerView.dimissWifiNotifyDialog();
            return;
        }
        new StringBuilder("player state=>").append(this.aPlayerAndroid.GetState());
        if (!this.mIsPlayingBeforePaused) {
            this.mPlayCMD = MediaPlayerPlayCMD.Play_None;
        }
        OnClickListener xVar = new x(this);
        OnClickListener yVar = new y(this);
        String string;
        String string2;
        String string3;
        if (com.xunlei.xllib.a.b.e(getApplicationContext())) {
            string = getString(2131233185);
            string2 = getString(2131233183);
            string3 = getString(2131233184);
            this.mNoNetworkNotify = true;
            if (this.mMobileNetworkNotify) {
                if (isPlaying()) {
                    pausePlayer(false);
                }
                this.mVodPlayerView.showWifiNotifyDialog(string, string2, string3, xVar, yVar, null);
                this.mMobileNetworkNotify = false;
                return;
            }
            return;
        }
        string = getString(2131233187);
        string2 = getString(2131233181);
        string3 = getString(2131233180);
        this.mMobileNetworkNotify = true;
        if (this.mNoNetworkNotify) {
            if (isPlaying()) {
                pausePlayer(false);
            }
            this.mVodPlayerView.showWifiNotifyDialog(string, string2, string3, xVar, yVar, null);
            this.mNoNetworkNotify = false;
        }
    }

    private void setCurrentPlayUrl(String str) {
        if (!TextUtils.isEmpty(this.mCurPlayingUrl)) {
            onVideoEndPlay();
        }
        this.mCurPlayingUrl = str;
        if (str == null) {
            handleOpenError(2131233175);
        }
    }

    private void getOnlineVideoUrl() {
        if (this.mVodPlayerParams != null && this.mVodPlayerParams.a() != null) {
            h a = this.mVodPlayerParams.a();
            if (a.j) {
                VodUtil.a();
                String c = VodUtil.c(this.mVodPlayerParams.a());
                postTimeoutRunnable();
                this.mToPlayUrl = c;
                this.aPlayerAndroid.Open(c);
                return;
            }
            new StringBuilder("vod type =>").append(a.o);
            switch (a.o) {
                case SUSPEND_NO_DRAG:
                    this.mVodPlayerParams.b = VodSourceType.local_appinner;
                    initDownloadVodUtil();
                    requestDownloadVodInfoForEpisode(a);
                default:
                    new StringBuilder("USE SERVER=>").append(a.c);
                    a.p = a.c;
                    checkVodServerInited();
            }
        }
    }

    private void checkVodServerInited() {
        if (VodUtil.a) {
            VodUtil.a();
            String b = VodUtil.b(this.mVodPlayerParams.a());
            if (TextUtils.isEmpty(b)) {
                handleOpenError("\u83b7\u53d6\u64ad\u653e\u5730\u5740\u5931\u8d25");
                return;
            }
            this.mToPlayUrl = b;
            if (isPrepared()) {
                postTimeoutRunnable();
                this.aPlayerAndroid.Open(b);
                return;
            }
            this.playAnother = true;
            this.aPlayerAndroid.Close();
            return;
        }
        h a = h.a();
        Handler handler = this.mUIHandler;
        com.xunlei.downloadprovider.service.g gVar = new com.xunlei.downloadprovider.service.g();
        gVar.a = handler;
        new i(a, gVar).start();
    }

    public static int getScreenBrightness(Context context) {
        try {
            return System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (SettingNotFoundException e) {
            e.getMessage();
            e.printStackTrace();
            return TransportMediator.KEYCODE_MEDIA_RECORD;
        }
    }

    public static void setScreenBrightness(int i, Activity activity) {
        LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.screenBrightness = ((float) i) / 255.0f;
        activity.getWindow().setAttributes(attributes);
    }

    private void startUpdateTimerTask() {
        if (this.mTimer == null) {
            this.mTimer = new Timer();
            this.mUpdateTimerTask = new b();
            this.mMinuUpdate = (int) ((System.currentTimeMillis() / 1000) % 60);
            if (this.mMinuUpdate < 0) {
                this.mMinuUpdate = 0;
            }
            this.mTimer.scheduleAtFixedRate(this.mUpdateTimerTask, 500, 1000);
        }
    }

    private void stopUpdateTimerTask() {
        if (this.mUpdateTimerTask != null) {
            this.mUpdateTimerTask.cancel();
            this.mUpdateTimerTask = null;
        }
        if (this.mTimer != null) {
            this.mTimer.purge();
            this.mTimer.cancel();
            this.mTimer = null;
        }
    }

    public final void onDoubleClick() {
        switchPlayAndPause();
    }

    public final void onBackBtnClicked() {
        back();
    }

    public final void onPlayPauseBtnClicked() {
        switchPlayAndPause();
    }

    public final void onShareBtnClicked() {
        com.xunlei.downloadprovider.download.tasklist.a.a a = com.xunlei.downloadprovider.download.tasklist.a.h.a().a(getDownloadTaskId());
        if (a != null) {
            ShareBean a2 = au.a(a, "local_player");
            a2.e = "local_player";
            com.xunlei.downloadprovidershare.d b = com.xunlei.downloadprovidershare.d.b();
            if (com.xunlei.xllib.a.b.a(this)) {
                com.xunlei.downloadprovidershare.d.a(a2);
                com.xunlei.downloadprovidershare.a.b bVar = new com.xunlei.downloadprovidershare.a.b(this);
                bVar.a = new com.xunlei.downloadprovidershare.ag(b, this, this, a2);
                bVar.show();
            } else {
                XLToast.b(this, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
            }
            com.xunlei.downloadprovidercommon.a.d.a(com.xunlei.downloadprovidercommon.a.a.a("android_player", "player_share_click"));
        }
    }

    public final void onRetryBtnClicked() {
        if (isPrepared() || isOpening()) {
            if (isOpening()) {
                this.mOldPlayer = this.aPlayerAndroid;
                this.aPlayerAndroid = new APlayerAndroid();
                this.aPlayerAndroid.SetView(this.mVodPlayerView.getSufaceView());
                this.aPlayerAndroid.SetConfig(209, MessageService.MSG_DB_NOTIFY_REACHED);
                initPlayerCallback();
            }
            postTimeoutRunnable();
            this.mBufferCount = 0;
            this.mNotReportSpeedBfTextShow = true;
            this.mLoadingTime = System.currentTimeMillis();
            this.mVodPlayerView.setPlayerSate(SUSPEND_DRAG);
            this.aPlayerAndroid.Open(this.mToPlayUrl);
            return;
        }
        this.playAnother = true;
        this.aPlayerAndroid.Close();
    }

    public final void onAccelBtnClicked() {
        String str = com.umeng.a.d;
        String str2 = com.umeng.a.d;
        LoginHelper.a();
        if (LoginHelper.c()) {
            str = LoginHelper.a().j;
            str2 = LoginHelper.a().h;
        }
        com.xunlei.downloadprovidercommon.a.c a = com.xunlei.downloadprovidercommon.a.a.a("android_player", "bxbb_vipspeedup_btn_click");
        a.a("product_type", str2);
        a.a("userid", str);
        com.xunlei.downloadprovidercommon.a.d.a(a);
        handleSpeedUpEvent(this.mTaskInfo.mTaskId, "v_an_shoulei_hytq_bxbb_btnjs");
    }

    public final void onPlayPostionChangeStart() {
        this.isSeeking = true;
        this.mSuspendType = 0;
    }

    public final void onPlayPostionChanged(boolean z, int i) {
        if (z) {
            this.aPlayerAndroid.SetPosition(i);
            if (isPaused()) {
                startPlayer(false);
                return;
            }
            return;
        }
        if (i == this.aPlayerAndroid.GetDuration()) {
            finish();
        }
        this.mSuspendType = 1;
    }

    public final void onPlayPostionChangeEnd(int i) {
        this.isSeeking = false;
        seekToPlayer(i);
    }

    public final void onVolumnChanged(float f) {
        this.mCurAudioProgress = f;
        setVoice();
    }

    public final void onBrightnessChanged(float f) {
        this.mCurBrightness = f;
        setBrightness();
    }

    private void sendTimeEventToThirdPart(ap apVar) {
        h a = apVar.a();
        if (a != null) {
            String str;
            Context a2 = BrothersApplication.a();
            String a3 = com.xunlei.downloadprovider.vod.a.c.a(apVar.l.d);
            String str2 = apVar.l.c;
            String str3 = apVar.l.a;
            String str4 = a.c;
            int i = a.s / 1000;
            int i2 = a.r / 1000;
            int i3 = apVar.l.f;
            Intent intent = new Intent();
            intent.setAction("com.xunlei.ThirdPartyCallPlay");
            intent.putExtra(JsInterface.FUNPLAY_AD_TRPE, "out");
            if (a3.equals("uc")) {
                str = "jk";
            } else {
                str = null;
            }
            intent.putExtra("channel_public", str);
            intent.putExtra(WebBrowserActivity.EXTRA_TITLE, str2);
            intent.putExtra(SocialConstants.PARAM_URL, str3);
            intent.putExtra("url_decode", str4);
            intent.putExtra("past_play_time", String.valueOf(i));
            intent.putExtra("total_time", String.valueOf(i2));
            intent.putExtra("return_code", i3);
            a2.sendBroadcast(intent);
        }
    }

    private boolean seekToPlayer(int i) {
        if (this.aPlayerAndroid == null) {
            return false;
        }
        try {
            int GetDuration = this.aPlayerAndroid.GetDuration();
            if (i <= GetDuration) {
                GetDuration = i;
            }
            if (GetDuration < 0) {
                GetDuration = 0;
            }
            this.aPlayerAndroid.SetPosition(GetDuration);
            this.mVodPlayerParams.a().s = this.aPlayerAndroid.GetPosition();
            return true;
        } catch (Exception e) {
            new StringBuilder("seek position failed:").append(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private String getVodResourceFrom() {
        if (!VodUtil.a(this.mVodPlayerParams.b)) {
            return (this.mVodPlayerParams.a().j || this.mVodPlayerParams.a().o == 2) ? null : "cloud";
        } else {
            return this.mVodPlayerParams.b == VodSourceType.local_system ? "local_system" : "local";
        }
    }

    private boolean isFirstBuffering() {
        return this.aPlayerAndroid == null || this.aPlayerAndroid.GetState() == 1;
    }

    private boolean isPlaying() {
        return this.aPlayerAndroid.GetState() == 5 || this.aPlayerAndroid.GetState() == 4;
    }

    private boolean isPaused() {
        return this.aPlayerAndroid.GetState() == 3 || this.aPlayerAndroid.GetState() == 2;
    }

    private boolean isPrepared() {
        return this.aPlayerAndroid.GetState() == 0;
    }

    private boolean isOpening() {
        return this.aPlayerAndroid.GetState() == 1;
    }

    private boolean startPlayer(boolean z) {
        boolean z2;
        if (this.aPlayerAndroid != null) {
            try {
                this.aPlayerAndroid.Play();
                getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                this.mVodPlayerView.setPlayerSate(this.isBuffering ? XZBDevice.DOWNLOAD_LIST_RECYCLE : XZBDevice.DOWNLOAD_LIST_FAILED);
                z2 = true;
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
            if (z && !VodUtil.a(this.mVodPlayerParams.b)) {
                checkNetworkEnvironmentPlaying();
            }
            return z2;
        }
        z2 = false;
        checkNetworkEnvironmentPlaying();
        return z2;
    }

    private boolean pausePlayer(boolean z) {
        try {
            if (this.aPlayerAndroid != null) {
                this.aPlayerAndroid.Pause();
                getWindow().clearFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                this.mIsPlayingBeforePaused = false;
                this.mVodPlayerView.setPlayerSate(SUSPEND_NO_DRAG);
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return false;
    }

    private int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    private float getScreedensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    private void startDLNA() {
        if (isPlaying()) {
            pausePlayer(true);
            this.mPlayCMD = MediaPlayerPlayCMD.Play_OnResume_Start;
        }
        h a = this.mVodPlayerParams.a();
        int GetPosition = this.aPlayerAndroid.GetPosition();
        this.mCurPlayPos = GetPosition;
        a.s = GetPosition;
        String.valueOf(GetPosition);
        this.mVodPlayerView.changeDLNAState(false);
        createDLNADialog();
        try {
            Method method = this.cls.getMethod("showDialog", new Class[]{MediaPlayerPlayCMD.class, String.class, String.class, Integer.TYPE});
            if (this.mShowDLNADialog != null) {
                method.invoke(this.mShowDLNADialog, new Object[]{this.mPlayCMD, this.mTitleForDlna, this.mDlnaInfo.a, Integer.valueOf(GetPosition)});
                com.xunlei.downloadprovidercommon.a.d.a(com.xunlei.downloadprovidercommon.a.a.a("android_player", "player_dlna_click"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryShowToastForFirstDownloadingPlay() {
        if (this.mDownloadVodParams != null && this.mDownloadVodParams.b > 0) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREFENERCE_NAME, SUSPEND_DRAG);
            if (!sharedPreferences.getBoolean(SHARE_PREFENERCE_KEY_IS_SHOW_FIRST_DOWNLOADING_PLAY_TIPS, false)) {
                XLToast.c(this, XLToastType.XLTOAST_TYPE_NONE, getString(2131231366));
                Editor edit = sharedPreferences.edit();
                edit.putBoolean(SHARE_PREFENERCE_KEY_IS_SHOW_FIRST_DOWNLOADING_PLAY_TIPS, true);
                edit.commit();
            }
        }
    }

    private void switchPlayAndPause() {
        new StringBuilder("is seeking=>").append(this.isSeeking);
        if (!this.isSeeking) {
            new StringBuilder("player is =>").append(this.aPlayerAndroid);
            if (this.aPlayerAndroid == null) {
                return;
            }
            if (isPlaying()) {
                pausePlayer(true);
            } else {
                startPlayer(true);
            }
        }
    }

    private void createDLNADialog() {
        if (this.mShowDLNADialog == null) {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences("plugindlnashare", SUSPEND_DRAG);
                File dir = getDir("pluginApkDex", SUSPEND_DRAG);
                File file = new File(dir, "pluginCdn.apk");
                File dir2 = getDir("pluginCdnodex", SUSPEND_DRAG);
                File file2 = new File(dir2, "pluginCdn.dex");
                String absolutePath = file.getAbsolutePath();
                if (!file.exists()) {
                    return;
                }
                if (IOUtil.md5.compareToIgnoreCase(IOUtil.getFileMd5(absolutePath)) == 0) {
                    DexClassLoader dexClassLoader;
                    String string = sharedPreferences.getString("plugindlnmode", com.umeng.a.d);
                    if (string.length() <= 0 || string.compareToIgnoreCase(IOUtil.getFileMd5(file2.getAbsolutePath())) != 0) {
                        if (file2.exists()) {
                            file2.delete();
                        }
                        dexClassLoader = new DexClassLoader(file.getAbsolutePath(), dir2.getAbsolutePath(), null, getClassLoader());
                        PluginStatic.sClassLoaderMap.put("pluginCdn", dexClassLoader);
                        Editor edit = sharedPreferences.edit();
                        edit.putString("plugindlnmode", IOUtil.getFileMd5(file2.getAbsolutePath()));
                        edit.commit();
                    } else {
                        dexClassLoader = PluginStatic.getClassLoader("pluginCdn");
                        if (dexClassLoader == null) {
                            dexClassLoader = new DexClassLoader(file.getAbsolutePath(), dir2.getAbsolutePath(), null, getClassLoader());
                            PluginStatic.sClassLoaderMap.put("pluginCdn", dexClassLoader);
                        }
                    }
                    if (dexClassLoader != null) {
                        try {
                            Class loadClass = dexClassLoader.loadClass("com.xunlei.downloadprovider.dlna.ShowDLNADialog");
                            this.mShowDLNADialog = loadClass.getConstructor(new Class[]{Context.class, OnDLNADialogListener.class}).newInstance(new Object[]{this, this});
                            this.cls = loadClass;
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
                file.delete();
                file2.delete();
                new Thread(new ab(this, dir)).start();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void releaseDLNA() {
        if (this.mShowDLNADialog != null && this.cls != null) {
            try {
                this.cls.getMethod(com.umeng.a.c, new Class[0]).invoke(this.mShowDLNADialog, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private a getRealUrlForDLNA() {
        a aVar = new a();
        h a = this.mVodPlayerParams.a();
        String str;
        if (this.mVodPlayerParams.c() == 1) {
            String str2 = a.p;
            str = a.c;
            if (str2 != null) {
                aVar.a = str2;
                aVar.b = true;
            } else if (str != null) {
                aVar.a = str;
                aVar.b = true;
            } else {
                aVar.b = false;
            }
            aVar.c = null;
        } else {
            if (this.mVodPlayerParams.c() == 2) {
                List list = a.t;
                if (list != null) {
                    int size = list.size();
                    new StringBuilder().append(TAG).append(" list.size");
                    if (!(list == null || list.isEmpty() || list.size() <= 0)) {
                        if (size == 1) {
                            com.xunlei.downloadprovider.vod.protocol.h.b bVar = (com.xunlei.downloadprovider.vod.protocol.h.b) list.get(SUSPEND_DRAG);
                            if (!(bVar == null || bVar.a == null || bVar.a.equals(com.umeng.a.d))) {
                                aVar.a = bVar.a;
                                aVar.b = true;
                            }
                        } else {
                            aVar.c = list;
                        }
                    }
                } else if (a.c != null) {
                    aVar.a = a.c;
                    aVar.b = true;
                } else {
                    aVar.b = false;
                }
            } else {
                str = com.xunlei.downloadprovider.service.downloads.kernel.c.a().a(a.c);
                if (!TextUtils.isEmpty(str)) {
                    CharSequence i = com.xunlei.xllib.a.b.i(this);
                    if (!TextUtils.isEmpty(i)) {
                        str = str.replace("127.0.0.1", i);
                    }
                }
                if (str != null) {
                    aVar.a = str;
                    aVar.b = true;
                } else {
                    aVar.b = false;
                }
            }
            aVar.c = null;
        }
        return aVar;
    }

    private String changeArrayDateToJson(List<com.xunlei.downloadprovider.vod.protocol.h.b> list) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (i < list.size()) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("duration", ((com.xunlei.downloadprovider.vod.protocol.h.b) list.get(i)).b);
                    jSONObject2.put(SocialConstants.PARAM_URL, ((com.xunlei.downloadprovider.vod.protocol.h.b) list.get(i)).a);
                    jSONArray.put(jSONObject2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        jSONObject.put("dataList", jSONArray);
        jSONObject.put("productId", com.xunlei.downloadprovider.a.b.h());
        jSONObject.put(JsInterface.KEY_APK_VERSION_CODE, com.xunlei.downloadprovider.a.b.x());
        jSONObject.put(Constants.PARAM_PLATFORM, anet.channel.strategy.dispatch.a.ANDROID);
        return jSONObject.toString();
    }

    private void getCurrentVoice() {
        if (this.mAudioManager != null) {
            int streamVolume = this.mAudioManager.getStreamVolume(XZBDevice.DOWNLOAD_LIST_FAILED);
            this.mCurAudioProgress = (float) ((int) ((((float) streamVolume) / ((float) this.mAudioManager.getStreamMaxVolume(XZBDevice.DOWNLOAD_LIST_FAILED))) * 100.0f));
            new StringBuilder("curAudio : ").append(streamVolume).append(" mCurAudioProgress : ").append(this.mCurAudioProgress);
        }
    }

    private void setVoice() {
        if (this.mCurAudioProgress < 0.0f) {
            this.mCurAudioProgress = 0.0f;
        }
        if (this.mCurAudioProgress > 100.0f) {
            this.mCurAudioProgress = 100.0f;
        }
        if (this.mAudioManager != null) {
            this.mAudioManager.setStreamVolume(XZBDevice.DOWNLOAD_LIST_FAILED, (int) (((float) this.mAudioManager.getStreamMaxVolume(XZBDevice.DOWNLOAD_LIST_FAILED)) * (this.mCurAudioProgress / 100.0f)), SUSPEND_DRAG);
        }
        this.mVodPlayerView.updateCenterProgressView((int) this.mCurAudioProgress);
    }

    private void setBrightness() {
        if (this.mCurBrightness < 1.0f) {
            this.mCurBrightness = 1.0f;
        }
        if (this.mCurBrightness > 255.0f) {
            this.mCurBrightness = 255.0f;
        }
        setScreenBrightness((int) this.mCurBrightness, this);
        this.mVodPlayerView.updateCenterProgressView((int) this.mCurBrightness);
    }

    private void finishDelay() {
        if (this.mUIHandler != null) {
            this.mUIHandler.removeCallbacks(this.mFinishRunnable);
            this.mUIHandler.postDelayed(this.mFinishRunnable, 1500);
            return;
        }
        back();
    }

    @SuppressLint({"DefaultLocale"})
    private void reportVodPlay(VodReportPlayState vodReportPlayState, String str) {
        if (this.mVodPlayerParams != null && this.mVodPlayerParams.a() != null) {
            if (VodUtil.a(this.mVodPlayerParams.b)) {
                String str2 = this.mVodPlayerParams.a().c;
                if (!TextUtils.isEmpty(str2)) {
                    String trim = str2.substring(str2.lastIndexOf(".") + 1, str2.length()).toLowerCase().trim();
                    StatReporter.reportVodFormat(trim, vodReportPlayState, getVodResourceFrom(), str, this.mVodPlayerParams.b);
                    reportLocalFilePlay(str2, trim);
                }
            } else if (!this.mVodPlayerParams.a().j && this.mVodPlayerParams.a().o != 2 && this.mVodPlayerParams.a().x != null) {
                switch (AnonymousClass_1.a[this.mVodPlayerParams.a().x.ordinal()]) {
                    case SUSPEND_NO_DRAG:
                        StatReporter.reportVodFormat("flv", vodReportPlayState, "cloud", str, this.mVodPlayerParams.b);
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        StatReporter.reportVodFormat("mp4", vodReportPlayState, "cloud", str, this.mVodPlayerParams.b);
                    default:
                        break;
                }
            }
        }
    }

    private void reportLocalFilePlay(String str, String str2) {
        File file = new File(str);
        if (file.exists()) {
            String str3;
            long length = file.length();
            double d = ((double) length) / 1.048576E9d;
            if (d >= 2.0d) {
                str3 = "[2GB,~)";
            } else if (d >= 1.0d) {
                str3 = "[1GB,2GB)";
            } else {
                double d2 = ((double) length) / 1024000.0d;
                if (d2 >= 500.0d) {
                    str3 = "[500MB,1GB)";
                } else if (d2 >= 100.0d) {
                    str3 = "[100MB,500MB)";
                } else if (d2 >= 50.0d) {
                    str3 = "[50MB,100MB)";
                } else {
                    str3 = "[0,50MB)";
                }
            }
            StatReporter.reportVodPlayLocalFile(str3, this.mVideoWidth + "X" + this.mVideoHeight, str2);
        }
    }

    protected final void onActivityResult(int i, int i2, Intent intent) {
        com.xunlei.downloadprovidershare.d.b().a(i, i2, intent);
        switch (i) {
            case SUSPEND_NO_DRAG:
                checkNetworkEnvironmentStart();
            default:
                super.onActivityResult(i, i2, intent);
        }
    }

    private void showQuiteToast() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastKeyTime > 2500) {
            this.mLastKeyTime = currentTimeMillis;
            XLToast.b(this, XLToastType.XLTOAST_TYPE_NORMAL, getResources().getString(2131233161), QUIT_INTERVAL);
            return;
        }
        XLToast.a();
        back();
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_ALL:
                if (this.mVodPlayerView.isLocked()) {
                    XLToast.c(this, XLToastType.XLTOAST_TYPE_NORMAL, getResources().getString(2131233172));
                    return true;
                } else if (isOpening()) {
                    back();
                    return true;
                } else {
                    showQuiteToast();
                    return true;
                }
            case R.styleable.Toolbar_subtitleTextColor:
                this.mAudioManager.adjustStreamVolume(XZBDevice.DOWNLOAD_LIST_FAILED, SUSPEND_NO_DRAG, SUSPEND_DRAG);
                getCurrentVoice();
                this.mVodPlayerView.showAndAutoHideCenterProgressView(CenterProgressType.CenterProgress_Volume, (int) this.mCurAudioProgress, AUDIO_PROGRESS_BAR_MAX);
                return true;
            case R.styleable.AppCompatTheme_actionMenuTextAppearance:
                this.mAudioManager.adjustStreamVolume(XZBDevice.DOWNLOAD_LIST_FAILED, -1, SUSPEND_DRAG);
                getCurrentVoice();
                this.mVodPlayerView.showAndAutoHideCenterProgressView(CenterProgressType.CenterProgress_Volume, (int) this.mCurAudioProgress, AUDIO_PROGRESS_BAR_MAX);
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    private void back() {
        savePlayInfo();
        if (this.mIsBeforePlaying && !TextUtils.isEmpty(this.mGCID)) {
            this.mLoadingTime = System.currentTimeMillis() - this.mLoadingTime;
            String str = this.mGCID;
            long j = this.mLoadingTime;
            com.xunlei.downloadprovidercommon.a.c a = com.xunlei.downloadprovidercommon.a.a.a("android_play", "play_bxbb_exit");
            a.a(Impl.COLUMN_GCID, str);
            a.a("load_time", String.valueOf(j));
            com.xunlei.downloadprovidercommon.a.d.a(a);
            new StringBuilder("[STAT_EVENT]").append(a);
        }
        backToThirdPartApp();
        finish();
    }

    private void backToThirdPartApp() {
        if (isCooperationPlay() && this.mVodPlayerParams.l.d == 1) {
            Object obj = this.mVodPlayerParams.l.g;
            if (!TextUtils.isEmpty(obj)) {
                com.xunlei.downloadprovider.thirdpart.thirdpartycallplay.a.a(obj);
            }
        }
    }

    private boolean isCooperationPlay() {
        return this.mVodPlayerParams != null && this.mVodPlayerParams.b();
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected final void onStart() {
        super.onStart();
    }

    protected final void onPause() {
        super.onPause();
        if (this.mIsResumed) {
            if (this.mVodPlayerView != null) {
                this.mVodPlayerView.dimissWifiNotifyDialog();
            }
            XLToast.a();
            if (isPaused()) {
                this.mIsPlayingBeforePaused = false;
            } else if (isPlaying()) {
                this.aPlayerAndroid.Pause();
                this.mIsPlayingBeforePaused = true;
            }
            stopUpdateTimerTask();
            getWindow().clearFlags(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            ag.b(this.mXLBroadcastListener);
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusChangedListener);
            this.mIsResumed = false;
        }
    }

    protected final void onRestart() {
        super.onRestart();
    }

    protected final void onStop() {
        super.onStop();
    }

    protected final void onDestroy() {
        reportVodPlayTime(this.mUseDuration);
        onVideoEndPlay();
        if (this.mUIHandler != null) {
            this.mUIHandler.removeCallbacksAndMessages(null);
            this.mUIHandler = null;
        }
        LoginHelper.a().b(this.mLogoutObserver);
        com.xunlei.downloadprovider.vod.protocol.b a = com.xunlei.downloadprovider.vod.protocol.b.a();
        Object obj = this.mVodProtocolObj;
        synchronized (a.c) {
            a.c.remove(obj);
        }
        savePlayInfo();
        clearDownloadingPlayTask();
        if (this.mPausedTaskIds != null) {
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            com.xunlei.downloadprovider.service.downloads.task.d.a(this.mPausedTaskIds);
            this.mPausedTaskIds = null;
        }
        if (com.xunlei.downloadprovider.businessutil.b.a().i()) {
            int k = com.xunlei.downloadprovider.businessutil.b.a().k();
            if (k < 10) {
                k = AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
            }
            com.xunlei.downloadprovider.service.downloads.task.d.a().c((long) k);
        } else {
            com.xunlei.downloadprovider.service.downloads.task.d.a().c(-1);
        }
        if (this.isSetNotiSoundOff) {
            com.xunlei.downloadprovider.businessutil.b.a().a(true);
            this.isSetNotiSoundOff = false;
        }
        this.aPlayerAndroid.Destroy();
        super.onDestroy();
    }

    private void savePlayInfo() {
        if (this.mVodPlayerParams != null && this.mVodPlayerParams.a() != null && this.mCurPlayPos != 0) {
            this.mVodPlayerParams.a().s = this.mCurPlayPos;
            this.mVodPlayerParams.a().r = this.mVideoDuration;
            VodUtil.a();
            VodUtil.a(this.mVodPlayerParams.a(), this.mVodPlayerParams.b);
        }
    }

    private void reportVodPlayTime(int i) {
        String str;
        if (i >= 7200) {
            str = "[2h,~)";
        } else if (i >= 3600) {
            str = "[1h,2h)";
        } else if (i >= 1800) {
            str = "[30min,1h)";
        } else if (i >= 600) {
            str = "[10min,30min)";
        } else if (i >= 300) {
            str = "[5min,10min)";
        } else {
            str = "[0\uff0c5min)";
        }
        StatReporter.reportVodPlayTime(str);
    }

    public final void finish() {
        if (this.isFromNotification) {
            MainTabActivity.a((Context) this, "thunder");
        }
        releaseDLNA();
        super.finish();
    }

    private com.xunlei.downloadprovider.vod.protocol.h.a getEpisodeResource(int i) {
        if (this.mVodPlayerParams == null || this.mVodPlayerParams.a() == null) {
            return null;
        }
        List list = this.mVodPlayerParams.a().w;
        return (list == null || i < 0 || i >= list.size()) ? null : (com.xunlei.downloadprovider.vod.protocol.h.a) list.get(i);
    }

    public final void postEpLiForRealUrl(String str) {
        try {
            Class loadClass = ((DexClassLoader) PluginStatic.sClassLoaderMap.get("pluginCdn")).loadClass("com.xunlei.downloadprovider.dlna.util.DlnaClient");
            Object newInstance = loadClass.getConstructor(new Class[]{Handler.class, Object.class}).newInstance(new Object[]{this.mUIHandler, null});
            loadClass.getMethod("query", new Class[]{String.class}).invoke(newInstance, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void onDialogDismiss(boolean z, MediaPlayerPlayCMD mediaPlayerPlayCMD) {
        if (z) {
            this.mPlayCMD = mediaPlayerPlayCMD;
            if (this.mPlayCMD == MediaPlayerPlayCMD.Play_OnResume_Start) {
                startPlayer(true);
                try {
                    Method method = this.cls.getMethod("hideDialog", new Class[0]);
                    if (this.mShowDLNADialog != null) {
                        method.invoke(this.mShowDLNADialog, new Object[0]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public final void onListChange(boolean z) {
        if (z && this.mShowDLNADialog != null) {
            boolean booleanValue;
            try {
                booleanValue = ((Boolean) this.cls.getMethod("isDialogShowing", new Class[0]).invoke(this.mShowDLNADialog, new Object[0])).booleanValue();
            } catch (Exception e) {
                e.printStackTrace();
                booleanValue = false;
            }
            if (booleanValue) {
                this.mVodPlayerView.changeDLNAState(true);
            }
        }
    }

    private void onVideoStartPlay() {
        this.mLoadingTime = System.currentTimeMillis() - this.mLoadingTime;
        reportVideoStartPlay();
    }

    private void onVideoEndPlay() {
        reportVideoEndPlay();
        this.mLoadingTime = System.currentTimeMillis();
        this.mSuspenDragTime = 0;
        this.mSuspendNoDragTime = 0;
        this.mSuspendDragCount = 0;
        this.mSuspendNoDragCount = 0;
        this.mGCID = null;
    }

    private void reportVideoStartPlay() {
        if (this.mVodPlayerParams != null && this.mVodPlayerParams.a() != null) {
            this.mPlaySessionId = System.currentTimeMillis();
            boolean isXunleiResource = isXunleiResource();
            String str = this.mPlaySessionId;
            String str2 = UtilityImpl.NET_TYPE_UNKNOWN;
            String str3 = UtilityImpl.NET_TYPE_UNKNOWN;
            String str4 = com.umeng.a.d;
            switch (AnonymousClass_1.b[this.mVodPlayerParams.b.ordinal()]) {
                case SUSPEND_NO_DRAG:
                    str2 = "download_cloud";
                    str3 = "online_cloud";
                    str4 = "online_cloud";
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    str2 = "download_detail";
                    str3 = "native";
                    str4 = "native_single";
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    str2 = "old_detail_other";
                    str3 = "native";
                    str4 = "native_single";
                    if (!(this.mVodPlayerParams == null || this.mVodPlayerParams.d == null)) {
                        str2 = this.mVodPlayerParams.d.b;
                        str3 = this.mVodPlayerParams.d.a;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    str2 = "app_other";
                    str3 = "native";
                    str4 = "native_single";
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    str2 = "space_lixian";
                    str3 = "bxbb";
                    str4 = "bxbb";
                    break;
                case R.styleable.Toolbar_contentInsetEnd:
                    str2 = "space_his";
                    str3 = "bxbb";
                    str4 = "bxbb";
                    break;
                case R.styleable.Toolbar_contentInsetLeft:
                    str2 = "sniff_cloud";
                    str3 = "online_cloud";
                    str4 = "online_cloud";
                    break;
                case XZBDevice.Wait:
                    str2 = "old_detail_other";
                    str3 = "online_cloud";
                    str4 = "online_cloud";
                    break;
                case XZBDevice.Pause:
                    str2 = "uc_cloud";
                    str3 = "bxbb";
                    str4 = "bxbb";
                    break;
                case XZBDevice.Stop:
                    str2 = "space_his";
                    str3 = "bxbb";
                    str4 = "bxbb";
                    break;
            }
            if (EntryFrom == 2064) {
                str3 = "online_url";
                str4 = "online_url";
            }
            if (EntryFrom == 21) {
                str2 = "old_detail_push";
                str4 = "online_url";
            }
            String str5 = "other";
            if (this.mVodPlayerParams.a().x != null) {
                switch (AnonymousClass_1.a[this.mVodPlayerParams.a().x.ordinal()]) {
                    case SUSPEND_NO_DRAG:
                        str5 = "flv";
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        str5 = "mp4";
                        break;
                }
            }
            String str6 = "player";
            String str7 = this.mVodPlayerParams.a().a;
            String str8 = UtilityImpl.NET_TYPE_UNKNOWN;
            if (str7 != null && str7.length() > 0) {
                int lastIndexOf = str7.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
                if (lastIndexOf > 0) {
                    str8 = str7.substring(lastIndexOf, str7.length());
                }
            }
            String str9 = this.mVodPlayerParams.a().c;
            long j = this.mVodPlayerParams.a().f;
            String str10 = this.mVideoWidth + "*" + this.mVideoHeight;
            long j2 = (long) this.mVodPlayerParams.a().r;
            com.xunlei.downloadprovider.player.a.b bVar = new com.xunlei.downloadprovider.player.a.b();
            bVar.a = str2;
            bVar.b = str3;
            bVar.c = str5;
            bVar.d = str6;
            bVar.e = str8;
            bVar.f = j;
            bVar.g = str9;
            bVar.h = str7;
            bVar.i = str10;
            bVar.j = j2;
            bVar.k = str;
            bVar.l = str4;
            bVar.t = this.mLoadingTime;
            if (!TextUtils.isEmpty(this.mGCID)) {
                bVar.v = this.mGCID;
                bVar.w = this.mIfVipBxbb ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT;
            }
            bVar.x = isXunleiResource ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT;
            com.xunlei.downloadprovider.player.a.a(bVar);
        }
    }

    private void reportVideoEndPlay() {
        if (this.mVodPlayerParams != null && this.mVodPlayerParams.a() != null && !WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(this.mPlaySessionId)) {
            String str = this.mPlaySessionId;
            long j = this.mVodPlayerParams.a().f;
            long j2 = (long) this.mVodPlayerParams.a().s;
            long j3 = (long) this.mVodPlayerParams.a().r;
            String str2 = MessageService.MSG_DB_NOTIFY_REACHED;
            if (this.mVodPlayerParams.a().s == this.mVodPlayerParams.a().r) {
                str2 = MessageService.MSG_DB_READY_REPORT;
            }
            if (this.hasError) {
                str2 = MessageService.MSG_DB_NOTIFY_CLICK;
            }
            String str3 = UtilityImpl.NET_TYPE_UNKNOWN;
            String str4 = UtilityImpl.NET_TYPE_UNKNOWN;
            switch (AnonymousClass_1.b[this.mVodPlayerParams.b.ordinal()]) {
                case SUSPEND_NO_DRAG:
                    str3 = "online_cloud";
                    str4 = "online_cloud";
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    str3 = "native";
                    str4 = "native_single";
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    str3 = "native";
                    str4 = "native_single";
                    if (!(this.mVodPlayerParams == null || this.mVodPlayerParams.d == null)) {
                        str3 = this.mVodPlayerParams.d.a;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    str3 = "native";
                    str4 = "native_single";
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    str3 = "bxbb";
                    str4 = "bxbb";
                    break;
                case R.styleable.Toolbar_contentInsetEnd:
                    str3 = "bxbb";
                    str4 = "bxbb";
                    break;
                case R.styleable.Toolbar_contentInsetLeft:
                    str3 = "online_cloud";
                    str4 = "online_cloud";
                    break;
                case XZBDevice.Wait:
                    str3 = "online_cloud";
                    str4 = "online_cloud";
                    break;
                case XZBDevice.Pause:
                    str3 = "bxbb";
                    str4 = "bxbb";
                    break;
                case XZBDevice.Stop:
                    str3 = "bxbb";
                    str4 = "bxbb";
                    break;
            }
            if (EntryFrom == 2064) {
                EntryFrom = -1;
                str3 = "online_url";
                str4 = "online_url";
            }
            if (EntryFrom == 21) {
                EntryFrom = -1;
                str4 = "online_url";
            }
            com.xunlei.downloadprovider.player.a.a aVar = new com.xunlei.downloadprovider.player.a.a();
            aVar.a = str3;
            aVar.b = str2;
            aVar.c = j3;
            aVar.d = j2;
            aVar.e = j;
            aVar.f = str;
            aVar.g = str4;
            float f = (float) this.mVodPlayerParams.a().s;
            float f2 = (float) this.mVodPlayerParams.a().r;
            long j4 = 0;
            if (f2 > 0.0f) {
                j4 = (long) (((float) this.mVodPlayerParams.a().f) * (f / f2));
            }
            aVar.j = j4;
            if (!TextUtils.isEmpty(this.mGCID)) {
                aVar.k = this.mGCID;
            }
            aVar.o = this.mSuspenDragTime;
            aVar.p = (long) this.mSuspendDragCount;
            aVar.q = this.mSuspendNoDragTime;
            aVar.r = (long) this.mSuspendNoDragCount;
            com.xunlei.downloadprovider.player.a.a(aVar);
            this.mPlaySessionId = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
    }

    private final void initDownloadVodUtil() {
        com.xunlei.downloadprovider.vod.protocol.b.a().a = BrothersApplication.a().getApplicationContext();
    }

    private void requestDownloadVodInfoForEpisode(h hVar) {
        com.xunlei.downloadprovider.vod.protocol.b a = com.xunlei.downloadprovider.vod.protocol.b.a();
        String str = hVar.c;
        String str2 = hVar.d;
        String str3 = hVar.e;
        long j = hVar.f;
        com.xunlei.downloadprovider.vod.protocol.b.a aVar = this.mObtainDownloadVodInfoListener;
        Object obj = this.mVodProtocolObj;
        com.xunlei.downloadprovider.vod.protocol.b.b bVar = new com.xunlei.downloadprovider.vod.protocol.b.b(str, str2, str3, j, hVar.a);
        bVar.f = obj;
        synchronized (a.c) {
            a.c.put(obj, aVar);
        }
        a.b.execute(new com.xunlei.downloadprovider.vod.protocol.c(a, bVar));
    }

    private boolean isXunleiResource() {
        return (this.mDownloadVodParams != null && this.mDownloadVodParams.b > 0) || this.mDownloadedTaskId > 0;
    }

    private void processShareBtn() {
        if (shouldShowShareBtn()) {
            this.mVodPlayerView.showShareBtn(true);
        } else {
            this.mVodPlayerView.showShareBtn(false);
        }
    }

    private long getDownloadTaskId() {
        if (this.mDownloadedTaskId > 0) {
            return this.mDownloadedTaskId;
        }
        return (this.mDownloadVodParams == null || this.mDownloadVodParams.b <= 0 || this.mDownloadVodParams.c >= 0) ? 0 : this.mDownloadVodParams.b;
    }

    private boolean shouldShowShareBtn() {
        if (this.mDownloadedTaskId > 0) {
            return true;
        }
        return this.mDownloadVodParams != null && this.mDownloadVodParams.b > 0 && this.mDownloadVodParams.c < 0;
    }

    public final void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        String a = ba.a(share_media, shareBean);
        com.xunlei.downloadprovidercommon.a.c a2 = com.xunlei.downloadprovidercommon.a.a.a("android_player", "player_share_to");
        a2.a("to", a);
        com.xunlei.downloadprovidercommon.a.d.a(a2);
    }

    public final void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        String a = ba.a(share_media, shareBean);
        String a2 = com.xunlei.downloadprovidershare.d.a(i);
        com.xunlei.downloadprovidercommon.a.c a3 = com.xunlei.downloadprovidercommon.a.a.a("android_player", "player_share_result");
        a3.a("to", a);
        a3.a("result", a2);
        com.xunlei.downloadprovidercommon.a.d.a(a3);
    }

    public final void onDLNAClicked() {
        com.xunlei.downloadprovidercommon.a.d.a(com.xunlei.downloadprovidercommon.a.a.a("android_player", "player_dlna_show"));
        startDLNA();
    }
}
