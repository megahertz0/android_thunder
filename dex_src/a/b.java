package a;

import com.sina.weibo.sdk.component.GameManager;
import java.nio.charset.Charset;

// compiled from: Util.java
final class b {
    public static final Charset a;

    static {
        a = Charset.forName(GameManager.DEFAULT_CHARSET);
    }

    public static boolean a(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2 + 0] != bArr2[i2 + 0]) {
                return false;
            }
        }
        return true;
    }
}
