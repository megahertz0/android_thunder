package anet.channel.util;

import com.umeng.a;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

// compiled from: Taobao
public class HMacUtil {
    private static final String TAG = "awcn.HMacUtil";

    public static String hmacSha1Hex(byte[] bArr, byte[] bArr2) {
        String str = a.d;
        try {
            str = StringUtils.bytesToHexString(hmacSha1(bArr, bArr2));
            ALog.i(TAG, "hmacSha1Hex", null, "result", str);
            return str;
        } catch (Throwable th) {
            ALog.e(TAG, "hmacSha1Hex", null, "result", str);
            return str;
        }
    }

    private static byte[] hmacSha1(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, "HmacSHA256");
        try {
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeyException e2) {
            return null;
        }
    }
}
