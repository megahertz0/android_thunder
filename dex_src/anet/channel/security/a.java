package anet.channel.security;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.util.ALog;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.alibaba.wireless.security.open.dynamicdatastore.IDynamicDataStoreComponent;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
class a implements ISecurity {
    private static String a;
    private static boolean b;
    private Map<String, Integer> c;
    private Map<String, Integer> d;

    static {
        a = "awcn.DefaultSecurityGuard";
        b = false;
        try {
            SecurityGuardManager.getInstance(GlobalAppRuntimeInfo.getContext());
            b = true;
        } catch (SecException e) {
            b = true;
        } catch (Exception e2) {
            b = false;
        }
    }

    a() {
        this.c = new HashMap();
        this.d = new HashMap();
        if (b) {
            this.c.put(ISecurity.SIGN_ALGORITHM_HMAC_SHA1, Integer.valueOf(XZBDevice.DOWNLOAD_LIST_FAILED));
            this.c.put(ISecurity.SIGN_ALGORITHM_MD5, Integer.valueOf(XZBDevice.DOWNLOAD_LIST_ALL));
            this.d.put(ISecurity.CIPHER_ALGORITHM_AES128, Integer.valueOf(R.styleable.Toolbar_titleMarginBottom));
            this.d.put(ISecurity.CIPHER_ALGORITHM_AES256, Integer.valueOf(R.styleable.Toolbar_collapseIcon));
        }
    }

    public String sign(Context context, String str, String str2, String str3, String str4) {
        if (!b || context == null || TextUtils.isEmpty(str2) || !this.c.containsKey(str)) {
            return null;
        }
        try {
            ISecureSignatureComponent secureSignatureComp = SecurityGuardManager.getInstance(context).getSecureSignatureComp();
            if (secureSignatureComp != null) {
                SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
                securityGuardParamContext.appKey = str2;
                securityGuardParamContext.paramMap.put("INPUT", str3);
                securityGuardParamContext.requestType = ((Integer) this.c.get(str)).intValue();
                return secureSignatureComp.signRequest(securityGuardParamContext, str4);
            }
        } catch (SecException e) {
            ALog.e(a, "Securityguard sign request failed.", null, e, new Object[0]);
        }
        return null;
    }

    public String signAuth(Context context, String str, String str2, String str3, String str4) {
        return sign(context, str, str2, str3 + str2, str4);
    }

    public byte[] staticDecrypt(Context context, String str, String str2, byte[] bArr, String str3) {
        if (!b || context == null || bArr == null || TextUtils.isEmpty(str2) || !this.d.containsKey(str)) {
            return null;
        }
        Integer num = (Integer) this.d.get(str);
        if (num == null) {
            return null;
        }
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
            if (instance != null) {
                IStaticDataEncryptComponent staticDataEncryptComp = instance.getStaticDataEncryptComp();
                if (staticDataEncryptComp != null) {
                    return staticDataEncryptComp.staticBinarySafeDecryptNoB64(num.intValue(), str2, bArr, str3);
                }
            }
        } catch (Throwable th) {
            ALog.e(a, "staticBinarySafeDecryptNoB64", null, th, new Object[0]);
        }
        return null;
    }

    public boolean dynamicPutBytes(Context context, String str, byte[] bArr) {
        if (context == null || bArr == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
            if (instance == null) {
                return false;
            }
            IDynamicDataStoreComponent dynamicDataStoreComp = instance.getDynamicDataStoreComp();
            return (dynamicDataStoreComp == null || dynamicDataStoreComp.putByteArray(str, bArr) == 0) ? false : true;
        } catch (Throwable th) {
            ALog.e(a, "dynamicPutBytes", null, th, new Object[0]);
            return false;
        }
    }

    public byte[] dynamicGetBytes(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
            if (instance == null) {
                return null;
            }
            IDynamicDataStoreComponent dynamicDataStoreComp = instance.getDynamicDataStoreComp();
            return dynamicDataStoreComp != null ? dynamicDataStoreComp.getByteArray(str) : null;
        } catch (Throwable th) {
            ALog.e(a, "dynamicGetBytes", null, th, new Object[0]);
            return null;
        }
    }
}
