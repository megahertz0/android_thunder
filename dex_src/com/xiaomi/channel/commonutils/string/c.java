package com.xiaomi.channel.commonutils.string;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import anet.channel.security.ISecurity;
import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.android.agoo.message.MessageService;

public class c {
    private static String a(byte b) {
        int i = (b & 127) + (b < null ? AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS : 0);
        return (i < 16 ? MessageService.MSG_DB_READY_REPORT : a.d) + Integer.toHexString(i).toLowerCase();
    }

    public static String a(String str) {
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            StringBuffer stringBuffer = new StringBuffer();
            instance.update(str.getBytes(), 0, str.length());
            byte[] digest = instance.digest();
            while (i < digest.length) {
                stringBuffer.append(a(digest[i]));
                i++;
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String b(String str) {
        return a(str).subSequence(XZBDevice.Wait, R.styleable.Toolbar_subtitleTextColor).toString();
    }
}
