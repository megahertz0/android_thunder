package cn.nodemedia;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.uc.addon.sdk.remote.EventIds;
import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class LivePlayer implements Callback {
    private static LivePlayer sInstance;
    private AudioManager am;
    private Surface mCurrentSurface;
    private LivePlayerDelegate mLivePlayerDelegate;

    final class AnonymousClass_2 extends ContextWrapper {
        AnonymousClass_2(Context context) {
            super(context);
        }

        public final String getPackageName() {
            return "cn.nodemedia.nodemediaclient";
        }
    }

    public static interface LivePlayerDelegate {
        void onEventCallback(int i, String str);
    }

    private native byte[] jniCapturePicture();

    private native int jniGetBufferLength();

    private native int jniGetVideoHeight();

    private native int jniGetVideoWidth();

    private native int jniInit(Object obj);

    private native void jniPause();

    private native void jniResume();

    private native int jniSetBufferTime(int i);

    private native int jniSetMaxBufferTime(int i);

    private native int jniSetUIVIew(Object obj);

    private native int jniStartPlay(String str, String str2, String str3);

    private native int jniStopPlay();

    public LivePlayer() {
        this.mLivePlayerDelegate = null;
        this.mCurrentSurface = null;
        this.am = null;
    }

    public static int init(Context context) {
        if (sInstance != null) {
            return 0;
        }
        System.loadLibrary("NodeMediaClient");
        LivePlayer livePlayer = new LivePlayer();
        sInstance = livePlayer;
        livePlayer.am = (AudioManager) context.getSystemService("audio");
        sInstance.am.requestAudioFocus(new OnAudioFocusChangeListener() {
            public final void onAudioFocusChange(int i) {
                if (i == -2) {
                    sInstance.jniPause();
                } else if (i == 1) {
                    sInstance.jniResume();
                }
            }
        }, XZBDevice.DOWNLOAD_LIST_FAILED, 1);
        return sInstance.jniInit(new AnonymousClass_2(context));
    }

    public static void setUIVIew(SurfaceView surfaceView) {
        if (surfaceView == null) {
            sInstance.jniSetUIVIew(null);
            return;
        }
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().removeCallback(sInstance);
        surfaceView.getHolder().addCallback(sInstance);
    }

    public static void setBufferTime(int i) {
        if (i > 0) {
            sInstance.jniSetBufferTime(i);
        }
    }

    public static void setMaxBufferTime(int i) {
        sInstance.jniSetMaxBufferTime(i);
    }

    public static void startPlay(String str) {
        startPlay(str, a.d, a.d);
    }

    public static void startPlay(String str, String str2, String str3) {
        sInstance.jniStartPlay(str, str2, str3);
        if (sInstance.mCurrentSurface != null) {
            sInstance.jniSetUIVIew(sInstance.mCurrentSurface);
        }
    }

    public static void stopPlay() {
        sInstance.jniStopPlay();
    }

    public static void pause() {
        sInstance.jniPause();
    }

    public static void resume() {
        sInstance.jniResume();
    }

    public static boolean capturePicture(String str) {
        boolean z = false;
        if (sInstance.jniGetVideoWidth() == 0 || sInstance.jniGetVideoHeight() == 0) {
            return false;
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(str));
            Bitmap createBitmap = Bitmap.createBitmap(sInstance.jniGetVideoWidth(), sInstance.jniGetVideoHeight(), Config.RGB_565);
            createBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(sInstance.jniCapturePicture()));
            createBitmap.compress(CompressFormat.JPEG, R.styleable.AppCompatTheme_colorControlNormal, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            z = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    public static int getVideoWidth() {
        return sInstance != null ? sInstance.jniGetVideoWidth() : 0;
    }

    public static int getVideoHeight() {
        return sInstance != null ? sInstance.jniGetVideoHeight() : 0;
    }

    public static int getBufferLength() {
        return sInstance.jniGetBufferLength();
    }

    public static void setDelegate(LivePlayerDelegate livePlayerDelegate) {
        sInstance.mLivePlayerDelegate = livePlayerDelegate;
    }

    private void onEvent(int i, String str) {
        if (this.mLivePlayerDelegate != null) {
            new StringBuilder("event:").append(i).append("  msg:").append(str);
            this.mLivePlayerDelegate.onEventCallback(i, str);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            Canvas lockCanvas = surfaceHolder.lockCanvas();
            lockCanvas.drawColor(0);
            surfaceHolder.unlockCanvasAndPost(lockCanvas);
        } catch (Exception e) {
        }
        this.mCurrentSurface = surfaceHolder.getSurface();
        sInstance.jniSetUIVIew(this.mCurrentSurface);
        onEvent(EventIds.EVENT_TRANSLATE, "surfaceCreated");
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mCurrentSurface = null;
        onEvent(1501, "surfaceDestroyed");
    }
}
