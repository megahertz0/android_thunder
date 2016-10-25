package com.qq.e.comm.util;

import android.util.Base64;
import anet.channel.security.ISecurity;
import com.tencent.connect.common.Constants;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import org.android.agoo.message.MessageService;

public class Md5Util {
    private static final String[] a;

    static {
        a = new String[]{MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_REACHED, MessageService.MSG_DB_NOTIFY_CLICK, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_ACCS_READY_REPORT, Constants.VIA_SHARE_TYPE_TEXT, Constants.VIA_SHARE_TYPE_INFO, MsgConstant.MESSAGE_NOTIFY_ARRIVAL, MessageService.MSG_ACCS_NOTIFY_CLICK, MessageService.MSG_ACCS_NOTIFY_DISMISS, "a", "b", "c", "d", "e", "f"};
    }

    public static String byteArrayToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : bArr) {
            int i2;
            if (i2 < 0) {
                i2 += 256;
            }
            stringBuffer.append(a[i2 / 16] + a[i2 % 16]);
        }
        return stringBuffer.toString();
    }

    public static String encode(File file) {
        FileInputStream fileInputStream;
        if (file == null) {
            return a.d;
        }
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            FileInputStream fileInputStream2 = new FileInputStream(file);
            i = JsInterface.MSG_JS_COLLECT_WEBSITE;
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read > 0) {
                        instance.update(bArr, 0, read);
                    } else {
                        String byteArrayToHexString = byteArrayToHexString(instance.digest());
                        try {
                            fileInputStream2.close();
                            return byteArrayToHexString;
                        } catch (Exception e) {
                            return byteArrayToHexString;
                        }
                    }
                }
            } catch (Exception e2) {
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e3) {
                    }
                }
                return a.d;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e4) {
                    }
                }
                throw th2;
            }
        } catch (Exception e5) {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return a.d;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileInputStream2 = i;
            th2 = th4;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th2;
        }
    }

    public static String encode(String str) {
        try {
            String str2 = new String(str);
            try {
                return byteArrayToHexString(MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5).digest(str2.getBytes()));
            } catch (Exception e) {
                return str2;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static String encodeBase64String(String str) {
        try {
            return byteArrayToHexString(MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5).digest(Base64.decode(str, 0)));
        } catch (Throwable e) {
            GDTLogger.e("Exception while md5 base64String", e);
            return null;
        }
    }

    public static byte[] hexStringtoByteArray(String str) {
        if (str.length() % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() - 1; i += 2) {
            char charAt = str.charAt(i);
            char charAt2 = str.charAt(i + 1);
            charAt = Character.toLowerCase(charAt);
            charAt2 = Character.toLowerCase(charAt2);
            int i2 = (charAt <= '9' ? charAt - 48 : (charAt - 97) + 10) << 4;
            i2 = charAt2 <= '9' ? i2 + (charAt2 - 48) : i2 + ((charAt2 - 97) + 10);
            if (i2 > 127) {
                i2 -= 256;
            }
            bArr[i / 2] = (byte) i2;
        }
        return bArr;
    }
}
