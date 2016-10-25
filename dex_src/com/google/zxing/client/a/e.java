package com.google.zxing.client.a;

import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import com.google.zxing.b;
import com.google.zxing.client.a.a.d;
import com.google.zxing.common.i;
import com.google.zxing.f;
import com.google.zxing.g;
import com.google.zxing.m;
import com.google.zxing.n;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.android.spdy.SpdyAgent;

// compiled from: ScanCodeDecodeHandler.java
final class e extends Handler {
    boolean a;
    private final d b;
    private final g c;

    e(Object obj) {
        this.a = false;
        this.c = new g();
        this.c.a(null);
        this.b = (d) obj;
    }

    public final void handleMessage(Message message) {
        Parcelable parcelable = null;
        switch (message.what) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                byte[] bArr = (byte[]) message.obj;
                int i = message.arg1;
                int i2 = message.arg2;
                String a = a(bArr, i, i2);
                Handler b = this.b.b();
                if (a != null) {
                    b bVar;
                    if (this.a) {
                        d b2 = d.b();
                        byte[] bArr2 = new byte[bArr.length];
                        for (int i3 = 0; i3 < i2; i3++) {
                            for (int i4 = 0; i4 < i; i4++) {
                                bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
                            }
                        }
                        b a2 = b2.a(bArr2, i, i2);
                        this.a = false;
                        bVar = a2;
                    } else {
                        this.a = true;
                        if (d.b() != null) {
                            bVar = d.b().a(bArr, i, i2);
                        } else {
                            bVar = null;
                        }
                    }
                    if (b != null) {
                        Message obtain = Message.obtain(b, XZBDevice.DOWNLOAD_LIST_ALL, a);
                        Bundle bundle = new Bundle();
                        String str = "barcode_bitmap";
                        if (d.b() != null) {
                            Camera camera = d.b().c;
                            if (camera != null) {
                                Parameters parameters = camera.getParameters();
                                if (!(parameters == null || parameters.getPreviewSize() == null)) {
                                    YuvImage yuvImage = new YuvImage(bVar.c, 17, d.b().c.getParameters().getPreviewSize().width, d.b().c.getParameters().getPreviewSize().height, null);
                                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    yuvImage.compressToJpeg(new Rect(0, 0, d.b().c.getParameters().getPreviewSize().width, d.b().c.getParameters().getPreviewSize().height), R.styleable.AppCompatTheme_buttonStyle, byteArrayOutputStream);
                                    bArr = byteArrayOutputStream.toByteArray();
                                    parcelable = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                                }
                            }
                        }
                        bundle.putParcelable(str, parcelable);
                        obtain.setData(bundle);
                        obtain.sendToTarget();
                    }
                } else if (b != null) {
                    Message.obtain(b, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED).sendToTarget();
                }
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                Looper.myLooper().quit();
            default:
                break;
        }
    }

    private String a(byte[] bArr, int i, int i2) {
        String str = null;
        f a = d.b().a(bArr, i, i2);
        if (a != null) {
            b bVar = new b(new i(a));
            try {
                g gVar = this.c;
                if (gVar.a == null) {
                    gVar.a(null);
                }
                n a2 = gVar.a(bVar);
                if (a2 != null) {
                    str = a2.a;
                }
                this.c.a();
            } catch (m e) {
                this.c.a();
            }
        }
        return str;
    }
}
