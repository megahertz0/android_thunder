package com.google.zxing;

import com.google.zxing.a.c;
import com.google.zxing.c.d;
import com.google.zxing.c.f;
import com.google.zxing.c.i;
import com.google.zxing.c.k;
import com.google.zxing.c.n;
import com.google.zxing.c.t;
import com.google.zxing.common.b;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;
import org.android.spdy.SpdyAgent;

// compiled from: MultiFormatWriter.java
public final class h implements q {
    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2) throws r {
        return a(str, barcodeFormat, i, i2, null);
    }

    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws r {
        q kVar;
        switch (i.a[barcodeFormat.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                kVar = new k();
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                kVar = new i();
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                kVar = new t();
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                kVar = new com.google.zxing.qrcode.b();
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                kVar = new f();
                break;
            case R.styleable.Toolbar_contentInsetEnd:
                kVar = new d();
                break;
            case R.styleable.Toolbar_contentInsetLeft:
                kVar = new n();
                break;
            case XZBDevice.Wait:
                kVar = new com.google.zxing.pdf417.d();
                break;
            case XZBDevice.Pause:
                kVar = new com.google.zxing.c.b();
                break;
            case XZBDevice.Stop:
                kVar = new com.google.zxing.datamatrix.b();
                break;
            case XZBDevice.Success:
                kVar = new c();
                break;
            default:
                throw new IllegalArgumentException(new StringBuilder("No encoder available for format ").append(barcodeFormat).toString());
        }
        return kVar.a(str, barcodeFormat, i, i2, map);
    }
}
