package cn.nodemedia;

import android.view.SurfaceView;

public abstract class VideoRecorderBase {
    public int mPreviewHeight;
    public int mPreviewWidth;

    public abstract int getCamera();

    public abstract int getFilterState();

    public abstract int getFlashState();

    public abstract void pause();

    public abstract void resume();

    public abstract int setFilterEnable(boolean z);

    public abstract int setFlashEnable(boolean z);

    public abstract int startVideoRecorder(SurfaceView surfaceView, int i, int i2);

    public abstract int stopVideoRecorder();

    public abstract int switchCamera();

    public VideoRecorderBase() {
        this.mPreviewWidth = 1280;
        this.mPreviewHeight = 720;
    }
}
