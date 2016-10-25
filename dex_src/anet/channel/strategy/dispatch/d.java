package anet.channel.strategy.dispatch;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.security.ISecurity;
import anet.channel.security.c;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import com.alipay.sdk.sys.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import java.util.Map;
import org.android.agoo.common.b;

// compiled from: Taobao
class d {
    d() {
    }

    public static Map<String, String> a(Map<String, String> map) {
        map.put(a.TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(StringUtils.stringNull2Empty((String) map.get(Constants.SP_KEY_APPKEY))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.DOMAIN))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(SocialConstants.PARAM_APPNAME))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(Constants.KEY_APP_VERSION))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.BSSID))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(LogBuilder.KEY_CHANNEL))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(b.KEY_DEVICE_TOKEN))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.LATITUDE))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.OTHER))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.MACHINE))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.NET_TYPE))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.OTHER))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(com.tencent.connect.common.Constants.PARAM_PLATFORM))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.PLATFORM_VERSION))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.PRE_IP))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(Constants.KEY_SID))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.TIMESTAMP))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(IXAdRequestInfo.V))).append(a.b).append(StringUtils.stringNull2Empty((String) map.get(a.SIGNTYPE)));
        map.put(Constants.KEY_SECURITY_SIGN, a(stringBuilder.toString()));
        return map;
    }

    public static String a(String str) {
        String str2 = com.umeng.a.d;
        try {
            if (TextUtils.isEmpty(GlobalAppRuntimeInfo.getAppKey())) {
                ALog.e("awcn.DispatchSecurityUtil", "getAppSign appkey null", null, new Object[0]);
                return null;
            }
            String sign;
            try {
                sign = c.a().sign(GlobalAppRuntimeInfo.getContext(), ISecurity.SIGN_ALGORITHM_HMAC_SHA1, GlobalAppRuntimeInfo.getAppKey(), str, GlobalAppRuntimeInfo.getAuthCode());
            } catch (Throwable th) {
                ALog.e("awcn.DispatchSecurityUtil", "getAppSign", null, th, new Object[0]);
                sign = null;
            }
            return sign;
        } catch (Throwable th2) {
            ALog.e("awcn.DispatchSecurityUtil", "getSign", null, th2, new Object[0]);
            return str2;
        }
    }
}
