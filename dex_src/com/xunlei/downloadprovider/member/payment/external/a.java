package com.xunlei.downloadprovider.member.payment.external;

import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.tdlive.R;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import org.android.agoo.message.MessageService;

// compiled from: DESEncryptionUtil.java
public final class a {
    public static Key a() throws Exception {
        return (Key) new ObjectInputStream(new FileInputStream(BrothersApplication.a().getFilesDir().getPath() + "pay_common_info.txt")).readObject();
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(toHexString).toString();
            }
            stringBuffer.append(toHexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static byte[] a(String str) {
        if (str.length() <= 0) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i * 2, (i * 2) + 1), R.styleable.Toolbar_titleMarginBottom) * 16) + Integer.parseInt(str.substring((i * 2) + 1, (i * 2) + 2), R.styleable.Toolbar_titleMarginBottom));
        }
        return bArr;
    }
}
