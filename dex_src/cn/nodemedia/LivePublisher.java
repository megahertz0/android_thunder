package cn.nodemedia;

import android.app.ActivityManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Handler;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import com.tencent.tauth.Tencent;
import com.umeng.a;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.util.XLog;

public class LivePublisher {
    public static final int AAC_PROFILE_HE = 1;
    public static final int AAC_PROFILE_LC = 0;
    public static final int AVC_PROFILE_BASELINE = 0;
    public static final int AVC_PROFILE_MAIN = 1;
    public static final int CAMERA_BACK = 0;
    public static final int CAMERA_FRONT = 1;
    public static final int CAMERA_NONE = -1;
    public static final int FILTER_NOT_SUPPORT = -1;
    public static final int FILTER_OFF = 0;
    public static final int FILTER_ON = 1;
    public static final int FLASH_NOT_SUPPORT = -1;
    public static final int FLASH_OFF = 0;
    public static final int FLASH_ON = 1;
    private static final String TAG = "NodeMedia.LivePublisher";
    public static final int VIDEO_ORI_LANDSCAPE = 1;
    public static final int VIDEO_ORI_LANDSCAPE_REVERSE = 3;
    public static final int VIDEO_ORI_PORTRAIT = 0;
    public static final int VIDEO_ORI_PORTRAIT_REVERSE = 2;
    private static LivePublisher sInstance;
    private AudioManager am;
    private AudioRecorder mAudioRecorder;
    private int mBeautyLowFPS;
    private int mCamId;
    private FrameLayout mContainerView;
    private int mFPSCount;
    private int mFilter;
    private int mInterfaceOrientation;
    private LivePublishDelegate mLivePublishDelegate;
    private String mPublishURL;
    private SurfaceView mSurfaceView;
    private VideoRecorderBase mVideoRecorder;

    final class AnonymousClass_2 extends ContextWrapper {
        AnonymousClass_2(Context context) {
            super(context);
        }

        public final String getPackageName() {
            return "cn.nodemedia.nodemediaclient";
        }
    }

    public static interface LivePublishDelegate {
        void onEventCallback(int i, String str);
    }

    private native int jniInit(Object obj);

    private static native int jniStartPublish(String str, String str2, String str3);

    public static native int putAudioData(byte[] bArr, int i);

    public static native int putVideoData(byte[] bArr, int i);

    public static native int setAudioParam(int i, int i2);

    public static native int setCamEnable(boolean z);

    private static native int setCameraParm(int i, int i2, int i3);

    public static native int setDenoiseEnable(boolean z);

    public static native int setMicEnable(boolean z);

    public static native int setVideoOrientation(int i);

    public static native int setVideoParam(int i, int i2, int i3, int i4, int i5);

    public static native int stopPublish();

    public LivePublisher() {
        this.mLivePublishDelegate = null;
        this.mAudioRecorder = null;
        this.mVideoRecorder = null;
        this.am = null;
        this.mContainerView = null;
        this.mSurfaceView = null;
        this.mPublishURL = null;
        this.mBeautyLowFPS = 12;
    }

    static {
        sInstance = null;
    }

    public static boolean detectOpenGLES20(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    public static int init(Context context, boolean z, int i) {
        boolean detectOpenGLES20 = detectOpenGLES20(context);
        new StringBuilder("supportGLES20:").append(detectOpenGLES20).append(", beautyFilter:").append(z).append(", beautyLowFPS:").append(i);
        if (sInstance != null) {
            return VIDEO_ORI_PORTRAIT;
        }
        System.loadLibrary("NodeMediaClient");
        LivePublisher livePublisher = new LivePublisher();
        sInstance = livePublisher;
        livePublisher.mAudioRecorder = new AudioRecorder();
        sInstance.mVideoRecorder = new VideoRecorderNM();
        sInstance.am = (AudioManager) context.getSystemService("audio");
        sInstance.am.requestAudioFocus(new OnAudioFocusChangeListener() {
            public final void onAudioFocusChange(int i) {
                if (i == -2) {
                    sInstance.mAudioRecorder.pause();
                    sInstance.mVideoRecorder.pause();
                } else if (i == 1) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            sInstance.mAudioRecorder.resume();
                            sInstance.mVideoRecorder.resume();
                        }
                    }, 500);
                }
            }
        }, VIDEO_ORI_LANDSCAPE_REVERSE, VIDEO_ORI_LANDSCAPE);
        sInstance.mBeautyLowFPS = i;
        LivePublisher livePublisher2 = sInstance;
        int i2 = (detectOpenGLES20 && z) ? 1 : 0;
        livePublisher2.mFilter = i2;
        return sInstance.jniInit(new AnonymousClass_2(context));
    }

    public static int startPreview(FrameLayout frameLayout, int i, int i2) {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras <= 0 && frameLayout != null) {
            XLog.e(TAG, "have no camera");
            return FLASH_NOT_SUPPORT;
        } else if (sInstance.mAudioRecorder.startAudioRecoder(44100, VIDEO_ORI_LANDSCAPE, JsInterface.MSG_JS_COLLECT_WEBSITE) != 0) {
            setAudioParam(VIDEO_ORI_PORTRAIT, VIDEO_ORI_PORTRAIT);
            XLog.e(TAG, "Microphone cannot be open. preview Error.");
            return FLASH_NOT_SUPPORT;
        } else {
            if (frameLayout != null) {
                if (i2 >= numberOfCameras) {
                    i2 = numberOfCameras - 1;
                }
                sInstance.mCamId = i2;
                sInstance.mContainerView = frameLayout;
                sInstance.mInterfaceOrientation = i;
                sInstance.mSurfaceView = new SurfaceView(frameLayout.getContext());
                sInstance.mContainerView.addView(sInstance.mSurfaceView, FLASH_NOT_SUPPORT, FLASH_NOT_SUPPORT);
                if (sInstance.mVideoRecorder.startVideoRecorder(sInstance.mSurfaceView, i, i2) != 0) {
                    sInstance.mAudioRecorder.releaseAudioRecorder();
                    setVideoParam(VIDEO_ORI_PORTRAIT, VIDEO_ORI_PORTRAIT, VIDEO_ORI_PORTRAIT, VIDEO_ORI_PORTRAIT, VIDEO_ORI_PORTRAIT);
                    XLog.e(TAG, "Camera cannot be open. preview Error.");
                    return FLASH_NOT_SUPPORT;
                }
                setCameraParm(sInstance.mVideoRecorder.mPreviewWidth, sInstance.mVideoRecorder.mPreviewHeight, i2);
                setVideoOrientation(i);
            }
            sInstance.onEvent(10000, "\u9884\u89c8\u5f00\u59cb");
            sInstance.onEvent(10002, sInstance.mVideoRecorder.mPreviewWidth + "x" + sInstance.mVideoRecorder.mPreviewHeight);
            return 0;
        }
    }

    public static int stopPreview() {
        if (sInstance.mContainerView != null) {
            sInstance.mContainerView.removeView(sInstance.mSurfaceView);
            sInstance.mSurfaceView = null;
        }
        sInstance.mVideoRecorder.stopVideoRecorder();
        sInstance.mAudioRecorder.releaseAudioRecorder();
        sInstance.onEvent(Tencent.REQUEST_LOGIN, "\u9884\u89c8\u7ed3\u675f");
        return VIDEO_ORI_PORTRAIT;
    }

    public static SurfaceView getSurfaceView() {
        return sInstance.mSurfaceView;
    }

    public static int getCamera() {
        return (sInstance == null || sInstance.mVideoRecorder == null) ? FLASH_NOT_SUPPORT : sInstance.mVideoRecorder.getCamera();
    }

    public static int switchCamera() {
        if (sInstance == null || sInstance.mVideoRecorder == null) {
            return -1;
        }
        int switchCamera = sInstance.mVideoRecorder.switchCamera();
        if (switchCamera == -1) {
            return switchCamera;
        }
        sInstance.mCamId = switchCamera;
        setCameraParm(sInstance.mVideoRecorder.mPreviewWidth, sInstance.mVideoRecorder.mPreviewHeight, switchCamera);
        sInstance.onEvent(10002, sInstance.mVideoRecorder.mPreviewWidth + "x" + sInstance.mVideoRecorder.mPreviewHeight);
        return switchCamera;
    }

    public static int setFilterEnable(boolean z) {
        return FLASH_NOT_SUPPORT;
    }

    public static int getFilterState() {
        return (sInstance == null || sInstance.mVideoRecorder == null) ? FLASH_NOT_SUPPORT : sInstance.mVideoRecorder.getFilterState();
    }

    public static int setFlashEnable(boolean z) {
        return (sInstance == null || sInstance.mVideoRecorder == null) ? FLASH_NOT_SUPPORT : sInstance.mVideoRecorder.setFlashEnable(z);
    }

    public static int getFlashState() {
        return (sInstance == null || sInstance.mVideoRecorder == null) ? FLASH_NOT_SUPPORT : sInstance.mVideoRecorder.getFlashState();
    }

    public static int startPublish(String str) {
        return startPublish(str, a.d, a.d);
    }

    public static int startPublish(String str, String str2, String str3) {
        if (str == null) {
            return FLASH_NOT_SUPPORT;
        }
        sInstance.mPublishURL = str;
        int jniStartPublish = jniStartPublish(str, str2, str3);
        if (jniStartPublish != 0) {
            return jniStartPublish;
        }
        sInstance.mAudioRecorder.resume();
        sInstance.mVideoRecorder.resume();
        return jniStartPublish;
    }

    public static int stopPublish2() {
        sInstance.mAudioRecorder.pause();
        sInstance.mVideoRecorder.pause();
        return stopPublish();
    }

    public static void setDelegate(LivePublishDelegate livePublishDelegate) {
        sInstance.mLivePublishDelegate = livePublishDelegate;
    }

    private void onEvent(int i, String str) {
        if (this.mLivePublishDelegate != null) {
            XLog.d("NodeMedia.JAVA", new StringBuilder("event:").append(i).append("  msg:").append(str).toString());
            this.mLivePublishDelegate.onEventCallback(i, str);
        }
    }
}
