package cn.nodemedia;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.n;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class VideoRecorderNM extends VideoRecorderBase implements PreviewCallback, Callback {
    private Camera mCamera;
    private int mCameraId;
    private WeakReference<Context> mContext;
    private boolean mIsPause;
    private float mScale;
    private SurfaceHolder mSurfaceHolder;
    private int mUIOrientation;

    public VideoRecorderNM() {
        this.mCamera = null;
        this.mCameraId = 0;
        this.mUIOrientation = 0;
        this.mSurfaceHolder = null;
        this.mIsPause = false;
        this.mScale = 0.0f;
    }

    public int startVideoRecorder(SurfaceView surfaceView, int i, int i2) {
        Rect rect = new Rect();
        surfaceView.getDrawingRect(rect);
        this.mScale = (((float) rect.height()) * 1.0f) / ((float) rect.width());
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.mUIOrientation = 90;
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.mUIOrientation = 0;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.mUIOrientation = 270;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.mUIOrientation = 180;
                break;
        }
        releaseCamera();
        this.mCameraId = i2;
        if (openCamera(i2) != 0) {
            return -1;
        }
        surfaceView.getHolder().addCallback(this);
        surfaceView.getHolder().setKeepScreenOn(true);
        this.mContext = new WeakReference(surfaceView.getContext());
        this.mIsPause = false;
        return 0;
    }

    public int stopVideoRecorder() {
        releaseCamera();
        return 0;
    }

    public int setFilterEnable(boolean z) {
        if (this.mContext.get() != null) {
            n.a((Context) this.mContext.get(), "\u5f00\u53d1\u54e5\u54e5\u6b63\u5728\u52aa\u529b\uff0c\u656c\u8bf7\u671f\u5f85^^");
        }
        return -1;
    }

    public int getFilterState() {
        return 0;
    }

    public int setFlashEnable(boolean z) {
        try {
            Parameters parameters = this.mCamera.getParameters();
            parameters.setFlashMode(z ? "torch" : "off");
            this.mCamera.setParameters(parameters);
            return z ? 1 : 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int getFlashState() {
        try {
            String flashMode = this.mCamera.getParameters().getFlashMode();
            if (flashMode != null) {
                return flashMode.equals("off") ? 0 : 1;
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public int getCamera() {
        return this.mCameraId;
    }

    public int switchCamera() {
        int i = 1;
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras <= 0) {
            return -1;
        }
        if (numberOfCameras == 1) {
            return 0;
        }
        releaseCamera();
        if (this.mCameraId == 0) {
            numberOfCameras = 1;
        } else {
            numberOfCameras = 0;
        }
        if (openCamera(numberOfCameras) != 0) {
            return -1;
        }
        try {
            this.mCamera.setPreviewCallbackWithBuffer(this);
            this.mCamera.setPreviewDisplay(this.mSurfaceHolder);
            this.mCamera.startPreview();
            if (this.mCameraId != 0) {
                i = 0;
            }
            this.mCameraId = i;
            return i;
        } catch (Exception e) {
            releaseCamera();
            return -1;
        }
    }

    public void pause() {
        this.mIsPause = true;
    }

    public void resume() {
        this.mIsPause = false;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            if (this.mCamera != null) {
                this.mCamera.stopPreview();
                this.mCamera.setPreviewCallbackWithBuffer(this);
                this.mCamera.setPreviewDisplay(surfaceHolder);
                this.mCamera.startPreview();
                this.mSurfaceHolder = surfaceHolder;
            }
        } catch (Exception e) {
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.mCamera != null) {
            try {
                this.mCamera.stopPreview();
                this.mCamera.setPreviewDisplay(null);
            } catch (Exception e) {
            }
        }
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (!this.mIsPause) {
            LivePublisher.putVideoData(bArr, bArr.length);
        }
        camera.addCallbackBuffer(bArr);
    }

    private int openCamera(int i) {
        try {
            this.mCamera = Camera.open(i);
            Parameters parameters = this.mCamera.getParameters();
            List<Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            Size size = (Size) supportedPreviewSizes.get(0);
            float abs = Math.abs((((float) size.width) / ((float) size.height)) - this.mScale);
            Size size2 = size;
            for (Size size3 : supportedPreviewSizes) {
                float f;
                Size size4;
                float abs2 = Math.abs((((float) size3.width) / ((float) size3.height)) - this.mScale);
                if (abs2 < abs) {
                    f = abs2;
                    size4 = size3;
                } else {
                    size4 = size2;
                    f = abs;
                }
                if (size3.width == 1280 && size3.height == 720) {
                    break;
                }
                abs = f;
                size2 = size4;
            }
            size3 = size2;
            parameters.setPreviewSize(size3.width, size3.height);
            parameters.setPreviewFormat(R.styleable.Toolbar_maxButtonHeight);
            this.mCamera.setParameters(parameters);
            Camera camera = this.mCamera;
            int i2 = size3.width;
            this.mPreviewWidth = i2;
            int i3 = size3.height;
            this.mPreviewHeight = i3;
            camera.addCallbackBuffer(new byte[(((i3 * i2) * 3) / 2)]);
            this.mCamera.setDisplayOrientation(this.mUIOrientation);
            try {
                Parameters parameters2 = this.mCamera.getParameters();
                parameters2.setFocusMode("continuous-video");
                this.mCamera.setParameters(parameters2);
            } catch (Exception e) {
            }
            return 0;
        } catch (Exception e2) {
            releaseCamera();
            new StringBuilder("Camera id:").append(i).append(" open error:").append(e2.getMessage());
            return -1;
        }
    }

    private void releaseCamera() {
        try {
            if (this.mCamera != null) {
                this.mCamera.stopPreview();
                this.mCamera.release();
                this.mCamera = null;
            }
        } catch (Exception e) {
        }
        this.mCamera = null;
    }
}
