package com.xunlei.tdlive.control;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.media.MediaRecorder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.xunlei.tdlive.base.n;
import com.xunlei.xllib.R;
import java.io.IOException;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class VideoRecordView extends SurfaceView implements Callback {
    private MediaRecorder a;
    private String b;
    private boolean c;
    private a d;
    private Camera e;

    public static interface a {
        void a();
    }

    public VideoRecordView(Context context) {
        super(context);
        this.b = "/sdcard/test.3gp";
        this.c = true;
        this.d = null;
        init();
    }

    public VideoRecordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = "/sdcard/test.3gp";
        this.c = true;
        this.d = null;
        init();
    }

    public VideoRecordView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = "/sdcard/test.3gp";
        this.c = true;
        this.d = null;
        init();
    }

    public void init() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        holder.setType(MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public void startRecord() {
        if (this.a == null) {
            this.a = new MediaRecorder();
            if (this.e != null) {
                this.e.unlock();
                this.a.setCamera(this.e);
                this.a.setVideoSource(1);
                this.a.setOutputFormat(1);
                this.a.setVideoEncoder(SimpleLog.LOG_LEVEL_DEBUG);
                this.a.setVideoSize(640, 480);
                this.a.setVideoFrameRate(R.styleable.Toolbar_navigationIcon);
                if (this.c) {
                    this.a.setOrientationHint(270);
                } else {
                    this.a.setOrientationHint(R.styleable.AppCompatTheme_controlBackground);
                }
                this.a.setPreviewDisplay(getHolder().getSurface());
                this.a.setOutputFile(this.b);
                try {
                    this.a.prepare();
                    this.a.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void stopRecord() {
        if (this.a != null) {
            this.a.stop();
            this.a.release();
            this.a = null;
            if (this.d != null) {
                this.d.a();
            }
        }
    }

    public void setPath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b = str;
        }
    }

    public String getPath() {
        return this.b;
    }

    public void selectFrontCamera(boolean z) {
        this.c = z;
        if (this.a != null) {
            stopRecord();
        }
        if (this.e != null) {
            this.e.stopPreview();
            this.e.release();
            a();
        }
    }

    public boolean isFrontCamera() {
        return this.c;
    }

    public void openFlashLight(boolean z) {
        if (this.e == null) {
            return;
        }
        if (z) {
            Parameters parameters = this.e.getParameters();
            parameters.setFlashMode("torch");
            this.e.setParameters(parameters);
            return;
        }
        parameters = this.e.getParameters();
        parameters.setFlashMode("off");
        this.e.setParameters(parameters);
    }

    public boolean isFlashLightOpen() {
        return this.e != null && "torch".equals(this.e.getParameters().getFlashMode());
    }

    public void setOnVideoRecordListenner(a aVar) {
        this.d = aVar;
    }

    private void a() {
        try {
            int defaultCameraId = getDefaultCameraId();
            if (defaultCameraId < 0) {
                n.a(getContext(), "\u6ca1\u6709\u6444\u50cf\u5934");
            }
            this.e = Camera.open(defaultCameraId);
            Parameters parameters = this.e.getParameters();
            this.e.setDisplayOrientation(R.styleable.AppCompatTheme_controlBackground);
            this.e.setParameters(parameters);
            this.e.setPreviewDisplay(getHolder());
            this.e.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getDefaultCameraId() {
        Object obj = 1;
        if (!this.c) {
            int i = 0;
        }
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras <= 0) {
            return -1;
        }
        CameraInfo cameraInfo = new CameraInfo();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == i) {
                return i2;
            }
        }
        return 0;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        a();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stopRecord();
        if (this.e != null) {
            this.e.stopPreview();
            this.e.release();
            this.e = null;
        }
    }
}
