package com.google.zxing.client.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.WindowManager;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import org.android.spdy.SpdyAgent;

// compiled from: CameraConfigurationManager.java
public final class b {
    private static final String h;
    private static final Pattern i;
    public final Context a;
    public Point b;
    public Point c;
    public int d;
    public String e;
    public String f;
    public boolean g;

    static {
        h = b.class.getSimpleName();
        i = Pattern.compile(MiPushClient.ACCEPT_TIME_SEPARATOR);
    }

    b(Context context) {
        this.g = true;
        this.a = context;
    }

    public final void a(Camera camera) {
        int i;
        int i2;
        Point point;
        Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(this.c.x, this.c.y);
        List<Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        Point point2 = this.c;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        int i5 = 0;
        for (Size size : supportedPictureSizes) {
            i = size.width;
            i2 = size.height;
            int i6 = ((i - point2.x) + i2) - point2.y;
            if (i6 == 0) {
                break;
            }
            if (i6 <= 0 || i6 >= i3) {
                i6 = i3;
                i3 = i4;
                i4 = i5;
            } else {
                i3 = i2;
                i4 = i;
            }
            i5 = i4;
            i4 = i3;
            i3 = i6;
        }
        i2 = i4;
        i = i5;
        if (i <= 0 || i2 <= 0) {
            point = null;
        } else {
            point = new Point(i, i2);
        }
        if (point != null) {
            parameters.setPictureSize(point.x, point.y);
        }
        String a = a(parameters.getSupportedFocusModes(), "auto", "macro");
        if (a != null) {
            parameters.setFocusMode(a);
        }
        parameters.setPreviewFormat(R.styleable.Toolbar_maxButtonHeight);
        try {
            Method method = camera.getClass().getMethod("setDisplayOrientation", new Class[]{Integer.TYPE});
            if (method != null) {
                method.invoke(camera, new Object[]{Integer.valueOf(R.styleable.AppCompatTheme_controlBackground)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        a = Build.PRODUCT;
        String str = VERSION.RELEASE;
        if (a == null || !"meizu_m9".equals(a) || str == null || !str.equals("2.3.5")) {
            i6 = 0;
        } else {
            i6 = 1;
        }
        if (i6 != 0) {
            switch (((WindowManager) this.a.getSystemService("window")).getDefaultDisplay().getRotation()) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    i6 = 0;
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    i6 = 90;
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    i6 = 180;
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    i6 = 270;
                    break;
                default:
                    i6 = 0;
                    break;
            }
            CameraInfo cameraInfo = new CameraInfo();
            Camera.getCameraInfo(0, cameraInfo);
            camera.setDisplayOrientation(((cameraInfo.orientation - i6) + 180) % 360);
        }
        camera.setParameters(parameters);
    }

    @SuppressLint({"DefaultLocale"})
    public static Point a(CharSequence charSequence, Point point) {
        int indexOf;
        int parseInt;
        int i;
        int i2;
        Object obj = InMobiClientPositioning.NO_REPEAT;
        String[] split = i.split(charSequence);
        int length = split.length;
        int i3 = 0;
        Object obj2 = null;
        Object obj3 = null;
        while (i3 < length) {
            int abs;
            int i4;
            String trim = split[i3].trim();
            indexOf = trim.indexOf(120);
            if (indexOf >= 0) {
                try {
                    parseInt = Integer.parseInt(trim.substring(0, indexOf));
                    indexOf = Integer.parseInt(trim.substring(indexOf + 1));
                    if (Build.BRAND.toLowerCase().contains("meizu")) {
                        abs = Math.abs(parseInt - point.x) + Math.abs(indexOf - point.y);
                    } else {
                        abs = Math.abs(parseInt - point.y) + Math.abs(indexOf - point.x);
                    }
                } catch (NumberFormatException e) {
                    abs = i4;
                    indexOf = i;
                    i4 = i2;
                }
                if (abs == 0) {
                    break;
                } else if (abs < i4) {
                    i4 = indexOf;
                    indexOf = parseInt;
                    i3++;
                    i = indexOf;
                    i2 = i4;
                    i4 = abs;
                }
            }
            abs = i4;
            indexOf = i;
            i4 = i2;
            i3++;
            i = indexOf;
            i2 = i4;
            i4 = abs;
        }
        indexOf = i2;
        parseInt = i;
        return (parseInt <= 0 || indexOf <= 0) ? null : new Point(parseInt, indexOf);
    }

    public static String a(Collection<String> collection, String... strArr) {
        new StringBuilder("Supported values: ").append(collection);
        if (collection != null) {
            for (int i = 0; i < 2; i++) {
                String str = strArr[i];
                if (collection.contains(str)) {
                    return str;
                }
            }
        }
        return null;
    }
}
