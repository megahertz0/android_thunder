package com.xunlei.downloadprovider.download.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.b;
import com.google.zxing.h;
import com.google.zxing.r;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: QrCodeHelper.java
public final class m {
    private h a;

    public m() {
        this.a = null;
    }

    public final Bitmap a(String str) throws r {
        Bitmap bitmap = null;
        if (this.a == null) {
            this.a = new h();
        }
        int a = g.a(BrothersApplication.a(), 200.0f);
        if (str != null) {
            try {
                b a2 = this.a.a(str, BarcodeFormat.QR_CODE, a, a);
                int i = a2.a;
                int i2 = a2.b;
                if (i > 0 && i2 > 0) {
                    int[] iArr = new int[(i * i2)];
                    for (int i3 = 0; i3 < i2; i3++) {
                        for (int i4 = 0; i4 < i; i4++) {
                            if (a2.a(i4, i3)) {
                                iArr[(i3 * i) + i4] = -16777216;
                            }
                        }
                    }
                    bitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
                    bitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
                }
            } catch (Exception e) {
                throw new r("Bitmap#setPixels() error!");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return bitmap;
    }
}
