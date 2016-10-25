package anetwork.channel.h;

import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.d;
import anetwork.channel.c.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: Taobao
public final class b {
    public static int a(Map<String, List<String>> map) {
        int intValue;
        try {
            String b = d.b(map, HttpConstant.CONTENT_LENGTH);
            if (StringUtils.isNotBlank(b)) {
                intValue = Integer.valueOf(b).intValue();
            } else {
                if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    ALog.i("ANet.ResponseHelper", "[parseBodyLength]there is no Content-Length response header field ", null, new Object[0]);
                }
                intValue = 0;
            }
        } catch (Exception e) {
            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_FAILED)) {
                ALog.w("ANet.ResponseHelper", "[parseBodyLength]content-length prase exception", null, new Object[0]);
            }
            intValue = 0;
        }
        if (intValue != 0) {
            return intValue;
        }
        int intValue2;
        try {
            String b2 = d.b(map, "x-bin-length");
            if (StringUtils.isNotBlank(b2)) {
                intValue2 = Integer.valueOf(b2).intValue();
                if (intValue2 == -1) {
                    intValue2 = intValue;
                }
                return intValue2;
            }
        } catch (Exception e2) {
            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                ALog.i("ANet.ResponseHelper", "[parseBodyLength]x-bin-length prase exception", null, new Object[0]);
            }
        }
        intValue2 = -1;
        if (intValue2 == -1) {
            intValue2 = intValue;
        }
        return intValue2;
    }

    public static void a(String str, Map<String, List<String>> map) {
        if (str != null && map != null) {
            try {
                for (Entry entry : map.entrySet()) {
                    String str2 = (String) entry.getKey();
                    if (str2 != null) {
                        if (str2.equalsIgnoreCase(HttpConstant.SET_COOKIE) || str2.equalsIgnoreCase(HttpConstant.SET_COOKIE2)) {
                            for (String str3 : (List) entry.getValue()) {
                                a.a(str, str3);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
