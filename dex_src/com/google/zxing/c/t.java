package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.b;
import com.google.zxing.q;
import com.google.zxing.r;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map;
import org.android.agoo.message.MessageService;

// compiled from: UPCAWriter.java
public final class t implements q {
    private final i a;

    public t() {
        this.a = new i();
    }

    public final b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws r {
        if (barcodeFormat == BarcodeFormat.UPC_A) {
            return this.a.a(a(str), BarcodeFormat.EAN_13, i, i2, map);
        }
        throw new IllegalArgumentException(new StringBuilder("Can only encode UPC-A, but got ").append(barcodeFormat).toString());
    }

    private static String a(String str) {
        int length = str.length();
        if (length == 11) {
            int i = 0;
            for (length = 0; length < 11; length++) {
                i += (length % 2 == 0 ? XZBDevice.DOWNLOAD_LIST_FAILED : 1) * (str.charAt(length) - 48);
            }
            str = str + ((1000 - i) % 10);
        } else if (length != 12) {
            throw new IllegalArgumentException(new StringBuilder("Requested contents should be 11 or 12 digits long, but got ").append(str.length()).toString());
        }
        return new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(str).toString();
    }
}
