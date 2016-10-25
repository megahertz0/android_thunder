package com.aplayer.aplayerandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.qq.e.comm.constants.ErrorCode.AdError;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public class APlayerAndroid {
    private static final String TAG;
    public static int gObjId;
    private k OnSystemPlayerFailListener;
    private boolean mDestroy;
    private a mEventHandler;
    private String mFileName;
    private i mHwDecoder;
    private int mHwReCreatePos;
    private boolean mIsAutoPlay;
    private boolean mIsCurrentUseSysmediaplay;
    private boolean mIsSuccess;
    private boolean mIsVrTouchRotateEnable;
    private int mObjId;
    private c mOnBufferListener;
    private d mOnOpenCompleteListener;
    private e mOnOpenSuccessListener;
    private f mOnPlayCompleteListener;
    private g mOnPlayStateChangeListener;
    private h mOnSeekCompleteListener;
    private i mOnShowSubtitleListener;
    private j mOnSurfaceDestroyListener;
    private TextView mSubtilteview;
    private String mSubtitleShow;
    private int mSubtitleViewTop;
    private Surface mSurface;
    private SurfaceView mSurfaceview;
    private SurfaceHolder mSurholder;
    private l mSystemPlayer;
    private OnTouchListener onTouchListener;

    private class a extends Handler {
        APlayerAndroid a;

        public a(APlayerAndroid aPlayerAndroid, Looper looper) {
            super(looper);
            this.a = null;
            this.a = aPlayerAndroid;
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    TAG;
                    APlayerAndroid.this.openSuccess();
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    if (APlayerAndroid.this.mOnPlayStateChangeListener != null) {
                        APlayerAndroid.this.mOnPlayStateChangeListener;
                        int i = message.arg1;
                        i = message.arg2;
                    }
                    APlayerAndroid.this.stateChange(message.arg2, message.arg1, message.obj);
                case R.styleable.Toolbar_contentInsetEnd:
                    if (APlayerAndroid.this.mOnSeekCompleteListener != null) {
                        APlayerAndroid.this.mOnSeekCompleteListener;
                    }
                case R.styleable.AppCompatTheme_checkboxStyle:
                    if (APlayerAndroid.this.mOnBufferListener != null) {
                        APlayerAndroid.this.mOnBufferListener.a(message.arg1);
                    }
                case R.styleable.AppCompatTheme_checkedTextViewStyle:
                    if (message.obj instanceof String) {
                        CharSequence access$9 = APlayerAndroid.subtitleFormat((String) message.obj);
                        if (APlayerAndroid.this.mOnShowSubtitleListener != null) {
                            APlayerAndroid.this.mOnShowSubtitleListener;
                        }
                        APlayerAndroid.this.showSubtitle(access$9);
                    }
                default:
                    TAG;
                    new StringBuilder("Unknown message tyupe ").append(message.what);
            }
        }
    }

    public static class b {
    }

    public static interface c {
        void a(int i);
    }

    public static interface d {
        void a(boolean z);
    }

    public static interface e {
    }

    public static interface f {
        void a(String str);
    }

    public static interface g {
    }

    public static interface h {
    }

    public static interface i {
    }

    public static interface j {
    }

    public static interface k {
    }

    private class l {
        MediaPlayer a;
        int b;
        String c;
        int d;

        public l() {
            this.b = 0;
            this.d = 0;
            this.a = new MediaPlayer();
            if (this.a != null) {
                this.a.setOnSeekCompleteListener(new d(this));
                this.a.setOnErrorListener(new e(this));
                this.a.setOnBufferingUpdateListener(new f(this));
                this.a.setOnCompletionListener(new g(this));
                this.a.setOnPreparedListener(new h(this));
            }
        }

        public final boolean a() {
            return this.a != null;
        }

        public final int b() {
            if (this.b != 0) {
                c();
            }
            this.a.release();
            this.a = null;
            return 1;
        }

        public final int a(Surface surface) {
            this.a.setSurface(surface);
            return 1;
        }

        public final int a(String str) {
            int i = -1;
            TAG;
            this.c = str;
            if (this.b != 0) {
                return -1;
            }
            this.b = 1;
            try {
                this.a.setDataSource(this.c);
                this.a.prepareAsync();
                i = 1;
                return i;
            } catch (IOException e) {
                TAG;
                new StringBuilder("SystemMediaPlay IOException: ").append(e.toString());
                if (APlayerAndroid.this.OnSystemPlayerFailListener != null) {
                    APlayerAndroid.this.OnSystemPlayerFailListener;
                }
                b();
                access$15 = APlayerAndroid.this.native_open(str, APlayerAndroid.this.mObjId);
                int access$15;
                if (access$15 != i) {
                    return access$15;
                }
                throw new RuntimeException("state is not right or other fatal error");
            }
        }

        public final int c() {
            TAG;
            if (this.b == 0) {
                return -1;
            }
            this.b = 6;
            this.a.stop();
            this.b = 0;
            return 1;
        }

        public final int d() {
            TAG;
            if (this.b == 7) {
                try {
                    TAG;
                    this.b = 1;
                    this.a.setDataSource(this.c);
                    this.a.prepareAsync();
                } catch (IOException e) {
                }
            }
            if (this.b == 3) {
                this.a.start();
                this.b = 4;
            }
            return 0;
        }
    }

    private native int native_close(int i);

    private native int native_getState(int i);

    private native int native_getbufferprogress(int i);

    private native String native_getconfig(int i, int i2);

    private native int native_getduration(int i);

    private native int native_getheight(int i);

    private native int native_getposition(int i);

    private native int native_getwidth(int i);

    private native int native_init(Object obj, int i);

    private native int native_isseeking(int i);

    private native int native_open(String str, int i);

    private native int native_pause(int i);

    private native int native_play(int i);

    private native int native_rotate(float f, float f2, int i);

    private native int native_setVideoOrientation(int i, int i2);

    private native int native_setconfig(int i, String str, int i2);

    private native int native_setdisplay(Surface surface, int i);

    private native int native_setposition(int i, int i2);

    private native int native_thumbnail_parse(Object obj, String str, long j, int i, int i2);

    private native int native_uninit(int i);

    static {
        TAG = APlayerAndroid.class.getSimpleName();
        gObjId = 0;
    }

    public APlayerAndroid() {
        this.mSurholder = null;
        this.mSurfaceview = null;
        this.mSubtilteview = null;
        this.mSurface = null;
        this.mSystemPlayer = null;
        this.mIsVrTouchRotateEnable = false;
        this.mIsSuccess = false;
        this.mIsAutoPlay = false;
        this.mIsCurrentUseSysmediaplay = false;
        this.mObjId = 0;
        this.mDestroy = false;
        this.mHwDecoder = null;
        this.mEventHandler = null;
        this.mSubtitleShow = MessageService.MSG_DB_NOTIFY_REACHED;
        this.mFileName = com.umeng.a.d;
        this.mSubtitleViewTop = 0;
        this.mHwReCreatePos = 0;
        this.onTouchListener = new a(this);
        this.mHwDecoder = new i(this);
        int i = gObjId;
        gObjId = i + 1;
        this.mObjId = i;
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            myLooper = Looper.getMainLooper();
        }
        if (myLooper != null) {
            this.mEventHandler = new a(this, myLooper);
        } else {
            this.mEventHandler = null;
        }
        try {
            System.loadLibrary("aplayer_ffmpeg");
            System.loadLibrary("aplayer_android");
        } catch (UnsatisfiedLinkError e) {
            new StringBuilder("loadLibrary aplayer_android fail").append(e.toString());
        }
        native_init(new WeakReference(this), this.mObjId);
    }

    public void Destroy() {
        if (!this.mDestroy) {
            this.mDestroy = true;
            new Thread(new b(this)).start();
        }
    }

    public int SetView(Surface surface) {
        if (!this.mDestroy) {
            this.mSurface = surface;
            if (this.mSurface != null) {
                native_setdisplay(this.mSurface, this.mObjId);
                if (IsSystemPlayer()) {
                    this.mSystemPlayer.a(this.mSurface);
                }
            } else if (isHwDecode()) {
                this.mHwDecoder.c();
            }
        }
        return 0;
    }

    public int SetView(SurfaceView surfaceView) {
        if (!this.mDestroy) {
            this.mSurfaceview = surfaceView;
            this.mSurfaceview.getHolder().addCallback(new c(this));
        }
        return 0;
    }

    public void UseSystemPlayer(boolean z) {
        this.mIsCurrentUseSysmediaplay = z;
        if (!z) {
            return;
        }
        if (this.mSystemPlayer == null || !this.mSystemPlayer.a()) {
            this.mSystemPlayer = new l();
        }
    }

    public boolean IsSystemPlayer() {
        return this.mIsCurrentUseSysmediaplay && this.mSystemPlayer != null && this.mSystemPlayer.a();
    }

    public int Open(String str) {
        if (this.mDestroy) {
            return 0;
        }
        this.mIsSuccess = false;
        if (IsSystemPlayer()) {
            return this.mSystemPlayer.a(str);
        }
        int native_open = native_open(str, this.mObjId);
        if (native_open != -1) {
            return native_open;
        }
        throw new RuntimeException("state is not right or other fatal error");
    }

    public int Close() {
        if (IsSystemPlayer()) {
            return this.mSystemPlayer.c();
        }
        if (isHwDecode()) {
            this.mHwDecoder.c();
        }
        return native_close(this.mObjId);
    }

    public int Play() {
        if (this.mDestroy) {
            return 0;
        }
        createSubtitleView();
        if (!this.mIsSuccess) {
            return 0;
        }
        if (IsSystemPlayer()) {
            return this.mSystemPlayer.d();
        }
        return (!isHwDecode() || this.mHwDecoder.d()) ? native_play(this.mObjId) : 0;
    }

    public int Pause() {
        if (this.mDestroy) {
            return 0;
        }
        if (!IsSystemPlayer()) {
            return native_pause(this.mObjId);
        }
        l lVar = this.mSystemPlayer;
        lVar.b = 2;
        lVar.a.pause();
        lVar.b = 3;
        return 1;
    }

    public static String GetVersion() {
        return "1.1.0.32";
    }

    public int GetState() {
        if (this.mDestroy) {
            return 0;
        }
        return IsSystemPlayer() ? this.mSystemPlayer.b : native_getState(this.mObjId);
    }

    public int GetDuration() {
        if (this.mDestroy) {
            return 0;
        }
        if (!IsSystemPlayer()) {
            return native_getduration(this.mObjId);
        }
        l lVar = this.mSystemPlayer;
        return lVar.b != 0 ? lVar.a.getDuration() : 0;
    }

    public int GetPosition() {
        if (this.mDestroy) {
            return 0;
        }
        if (!IsSystemPlayer()) {
            return native_getposition(this.mObjId);
        }
        l lVar = this.mSystemPlayer;
        return lVar.b != 0 ? lVar.a.getCurrentPosition() : 0;
    }

    public int SetPosition(int i) {
        if (this.mDestroy) {
            return 0;
        }
        if (!IsSystemPlayer()) {
            return native_setposition(i, this.mObjId);
        }
        l lVar = this.mSystemPlayer;
        if (lVar.b != 0) {
            lVar.a.seekTo(i);
        }
        return 1;
    }

    public int GetVideoWidth() {
        if (this.mDestroy) {
            return 0;
        }
        if (!IsSystemPlayer()) {
            return native_getwidth(this.mObjId);
        }
        l lVar = this.mSystemPlayer;
        return lVar.b != 0 ? lVar.a.getVideoWidth() : 0;
    }

    public int GetVideoHeight() {
        if (this.mDestroy) {
            return 0;
        }
        if (!IsSystemPlayer()) {
            return native_getheight(this.mObjId);
        }
        l lVar = this.mSystemPlayer;
        return lVar.b != 0 ? lVar.a.getVideoHeight() : 0;
    }

    public int GetVolume() {
        return 0;
    }

    public int SetVolume(int i) {
        return 0;
    }

    public int GetBufferProgress() {
        if (this.mDestroy) {
            return 0;
        }
        return IsSystemPlayer() ? this.mSystemPlayer.d : native_getbufferprogress(this.mObjId);
    }

    public String GetConfig(int i) {
        if (this.mDestroy) {
            return com.umeng.a.d;
        }
        if (IsSystemPlayer()) {
            return com.umeng.a.d;
        }
        switch (i) {
            case XZBDevice.Wait:
                return config_get_auto_play();
            case AdError.DETAIl_URL_ERROR:
                return config_get_subtitle_file_name();
            case 504:
                return config_get_subtitle_show();
            case 2414:
                return config_get_vr_touch_rotate();
            default:
                return native_getconfig(i, this.mObjId);
        }
    }

    public int SetConfig(int i, String str) {
        if (this.mDestroy || IsSystemPlayer()) {
            return 0;
        }
        switch (i) {
            case XZBDevice.Wait:
                return config_set_auto_play(str);
            case 209:
                return config_set_hwdecode_use(str);
            case AdError.DETAIl_URL_ERROR:
                return config_set_subtitle_file_name(str);
            case 504:
                return config_set_subtitle_show(str);
            case 2401:
                if (!str.equalsIgnoreCase(MessageService.MSG_DB_NOTIFY_REACHED)) {
                    config_set_vr_touch_rotate(MessageService.MSG_DB_READY_REPORT);
                }
                return native_setconfig(i, str, this.mObjId);
            case 2414:
                return config_set_vr_touch_rotate(str);
            default:
                return native_setconfig(i, str, this.mObjId);
        }
    }

    public int SetVideoOrientation(int i) {
        return (this.mDestroy || IsSystemPlayer()) ? 0 : native_setVideoOrientation(i, this.mObjId);
    }

    public static Bitmap GetThumbnail(String str, int i) {
        return null;
    }

    private boolean isHwDecode() {
        return GetConfig(209).equals(MessageService.MSG_DB_NOTIFY_REACHED) && GetConfig(230).equals(MessageService.MSG_DB_NOTIFY_REACHED);
    }

    private int config_set_subtitle_show(String str) {
        if (this.mSubtilteview == null) {
            createSubtitleView();
        }
        if (this.mSubtilteview == null) {
            return 0;
        }
        if (str.equalsIgnoreCase(MessageService.MSG_DB_NOTIFY_REACHED)) {
            this.mSubtilteview.setVisibility(0);
            this.mSubtitleShow = MessageService.MSG_DB_NOTIFY_REACHED;
        } else {
            this.mSubtilteview.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            this.mSubtitleShow = MessageService.MSG_DB_READY_REPORT;
        }
        return 1;
    }

    private String config_get_subtitle_show() {
        return this.mSubtitleShow;
    }

    private int config_set_auto_play(String str) {
        this.mIsAutoPlay = str.equalsIgnoreCase(MessageService.MSG_DB_NOTIFY_REACHED);
        return 1;
    }

    private String config_get_auto_play() {
        return this.mIsAutoPlay ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT;
    }

    private int config_set_subtitle_file_name(String str) {
        this.mFileName = str;
        return native_setconfig(AdError.DETAIl_URL_ERROR, str, this.mObjId);
    }

    private String config_get_subtitle_file_name() {
        return this.mFileName;
    }

    private int config_set_vr_touch_rotate(String str) {
        boolean equalsIgnoreCase = str.equalsIgnoreCase(MessageService.MSG_DB_NOTIFY_REACHED);
        if (equalsIgnoreCase && this.mSurfaceview != null) {
            this.mSurfaceview.setOnTouchListener(this.onTouchListener);
            this.mIsVrTouchRotateEnable = true;
        }
        if (!(equalsIgnoreCase || this.mSurfaceview == null)) {
            this.mSurfaceview.setOnTouchListener(null);
            this.mIsVrTouchRotateEnable = false;
        }
        return 1;
    }

    private String config_get_vr_touch_rotate() {
        return new StringBuilder(String.valueOf(this.mIsVrTouchRotateEnable ? 1 : 0)).toString();
    }

    private int config_set_hwdecode_use(String str) {
        if (GetConfig(209).equals(str)) {
            return 1;
        }
        int GetState = GetState();
        if (GetState == 3 || GetState == 2 || GetState == 5 || GetState == 4) {
            return 0;
        }
        native_setconfig(209, str, this.mObjId);
        return 1;
    }

    public b parseThumbnail(String str, long j, int i, int i2) {
        b bVar = new b();
        return native_thumbnail_parse(bVar, str, j, i, i2) != 0 ? null : bVar;
    }

    private int openSuccess() {
        this.mIsSuccess = true;
        if (this.mIsAutoPlay) {
            Play();
        }
        if (this.mOnOpenCompleteListener != null) {
            this.mOnOpenCompleteListener.a(true);
        }
        return 1;
    }

    private void stateChange(int i, int i2, Object obj) {
        if (this.mOnPlayCompleteListener != null && i == 6 && i2 == 0) {
            if (isHwDecode()) {
                this.mHwDecoder.c();
            }
            this.mOnPlayCompleteListener.a((String) obj);
            if (((String) obj).equals("0x80000001") && this.mOnOpenCompleteListener != null) {
                this.mOnOpenCompleteListener.a(false);
            }
            new StringBuilder("Event mOnPlayCompleteListener result = ").append((String) obj);
        }
    }

    public void setOnOpenSuccessListener(e eVar) {
        this.mOnOpenSuccessListener = eVar;
    }

    public void setOnPlayStateChangeListener(g gVar) {
        this.mOnPlayStateChangeListener = gVar;
    }

    public void setOnOpenCompleteListener(d dVar) {
        this.mOnOpenCompleteListener = dVar;
    }

    public void setOnPlayCompleteListener(f fVar) {
        this.mOnPlayCompleteListener = fVar;
    }

    public void setOnBufferListener(c cVar) {
        this.mOnBufferListener = cVar;
    }

    public void setOnSeekCompleteListener(h hVar) {
        this.mOnSeekCompleteListener = hVar;
    }

    public void setOnSurfaceDestroyListener(j jVar) {
        this.mOnSurfaceDestroyListener = jVar;
    }

    public void setOnSystemPlayerFailListener(k kVar) {
        this.OnSystemPlayerFailListener = kVar;
    }

    public void setOnShowSubtitleListener(i iVar) {
        this.mOnShowSubtitleListener = iVar;
    }

    private void showSubtitle(CharSequence charSequence) {
        new StringBuilder("ShowSubtitle ").append(charSequence.toString());
        if (this.mSubtilteview == null) {
            createSubtitleView();
        }
        if (this.mSubtilteview != null) {
            this.mSubtilteview.setText(charSequence);
            int lineCount = this.mSubtilteview.getLineCount();
            if (lineCount <= 0) {
                lineCount = 1;
            }
            int lineHeight = this.mSubtilteview.getLineHeight() * lineCount;
            LayoutParams layoutParams = (LayoutParams) this.mSubtilteview.getLayoutParams();
            layoutParams.topMargin = this.mSubtitleViewTop - lineHeight;
            new StringBuilder("ShowSubtitle mSubtitleViewTop = ").append(this.mSubtitleViewTop).append(" textViewHeight =  ").append(lineHeight);
            this.mSubtilteview.setLayoutParams(layoutParams);
            this.mSubtilteview.setHeight(lineHeight);
        }
    }

    private void changeSubtitleViewSize() {
        if (this.mSubtilteview != null && this.mSurfaceview != null && this.mSurfaceview.getWidth() != 0 && this.mSurfaceview.getBottom() != 0 && (this.mSurfaceview.getContext() instanceof Activity)) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.leftMargin = this.mSurfaceview.getLeft();
            layoutParams.topMargin = this.mSurfaceview.getBottom();
            this.mSubtitleViewTop = layoutParams.topMargin;
            this.mSubtilteview.setWidth(this.mSurfaceview.getWidth());
            this.mSubtilteview.setGravity(R.styleable.Toolbar_maxButtonHeight);
            this.mSubtilteview.setTextSize(0, 40.0f);
            this.mSubtilteview.setLayoutParams(layoutParams);
            this.mSubtilteview.setVisibility(0);
        }
    }

    private boolean createSubtitleView() {
        if (this.mSubtilteview != null || (this.mSurfaceview == null || this.mSurfaceview.getWidth() == 0 || this.mSurfaceview.getBottom() == 0)) {
            return false;
        }
        Context context = this.mSurfaceview.getContext();
        this.mSubtilteview = new TextView(context);
        if (context instanceof Activity) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.leftMargin = 0;
            layoutParams.topMargin = 0;
            ((Activity) context).addContentView(this.mSubtilteview, layoutParams);
            this.mSubtilteview.setTextColor(Color.rgb(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
            this.mSubtilteview.setText(com.umeng.a.d);
        }
        changeSubtitleViewSize();
        return true;
    }

    protected Surface getInnerSurface() {
        while (this.mSurface == null) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
        return this.mSurface;
    }

    private i getHardwareDecoder() {
        return this.mHwDecoder;
    }

    private static int callFNFindHardwareDecoder(Object obj, int i) {
        i hardwareDecoder = ((APlayerAndroid) ((WeakReference) obj).get()).getHardwareDecoder();
        hardwareDecoder.b = i;
        new StringBuilder("sdk version ").append(VERSION.SDK_INT);
        if (VERSION.SDK_INT < 16) {
            return 0;
        }
        return VERSION.SDK_INT < 21 ? hardwareDecoder.a() : hardwareDecoder.b();
    }

    private static int callFNCreateHardwareDecoder(Object obj, Object obj2) {
        return ((APlayerAndroid) ((WeakReference) obj).get()).getHardwareDecoder().a((ByteBuffer) obj2);
    }

    private static int callFNHardwareDecode(Object obj, Object obj2, long j, Object obj3) {
        return ((APlayerAndroid) ((WeakReference) obj).get()).getHardwareDecoder().a((ByteBuffer) obj2, j);
    }

    private static int callFNFlushHardwareDecoder(Object obj) {
        ((APlayerAndroid) ((WeakReference) obj).get()).getHardwareDecoder().e();
        return 1;
    }

    private static int callFNCloseHardwareDecoder(Object obj) {
        ((APlayerAndroid) ((WeakReference) obj).get()).getHardwareDecoder().c();
        return 1;
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        if (obj != null) {
            APlayerAndroid aPlayerAndroid = (APlayerAndroid) ((WeakReference) obj).get();
            if (aPlayerAndroid != null && aPlayerAndroid.mEventHandler != null) {
                Message obtainMessage = aPlayerAndroid.mEventHandler.obtainMessage(i, i2, i3, obj2);
                if (obtainMessage != null) {
                    obtainMessage.arg1 = i2;
                    obtainMessage.arg2 = i3;
                    obtainMessage.obj = obj2;
                    aPlayerAndroid.mEventHandler.sendMessage(obtainMessage);
                }
            }
        }
    }

    private static String subtitleFormat(String str) {
        return str == null ? null : subtitleFormat(subtitleFormat(str, "{", com.alipay.sdk.util.h.d), "<", ">");
    }

    private static String subtitleFormat(String str, String str2, String str3) {
        int i;
        Object obj = 1;
        int indexOf = str.indexOf(str2);
        int i2 = -1 != indexOf ? 1 : 0;
        int indexOf2 = str.indexOf(str3);
        if (-1 == indexOf2) {
            i = 0;
        }
        return (i2 == 0 || i == 0 || indexOf >= indexOf2) ? str : new StringBuilder(String.valueOf(str.substring(0, indexOf))).append(str.substring(indexOf2 + 1)).toString();
    }
}
