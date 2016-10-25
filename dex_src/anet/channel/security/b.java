package anet.channel.security;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.util.HMacUtil;

// compiled from: Taobao
public class b implements ISecurity {
    public String sign(Context context, String str, String str2, String str3, String str4) {
        return (TextUtils.isEmpty(GlobalAppRuntimeInfo.getAppSecret()) || TextUtils.isEmpty(str2) || !ISecurity.SIGN_ALGORITHM_HMAC_SHA1.equalsIgnoreCase(str)) ? null : HMacUtil.hmacSha1Hex(GlobalAppRuntimeInfo.getAppSecret().getBytes(), str3.getBytes());
    }

    public String signAuth(Context context, String str, String str2, String str3, String str4) {
        return sign(context, str, str2, str2 + str3, str4);
    }

    public byte[] staticDecrypt(Context context, String str, String str2, byte[] bArr, String str3) {
        return null;
    }

    public boolean dynamicPutBytes(Context context, String str, byte[] bArr) {
        return false;
    }

    public byte[] dynamicGetBytes(Context context, String str) {
        return null;
    }
}
