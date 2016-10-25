package com.inmobi.commons.core.utilities;

import com.inmobi.commons.a.a;
import com.taobao.accs.common.Constants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.HashMap;
import java.util.Map;

// compiled from: PermissionUtils.java
public class d {
    public static boolean a(String str, String str2) {
        try {
            return a.b().checkCallingOrSelfPermission(str2) == 0;
        } catch (RuntimeException e) {
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "RuntimeException");
            hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e.getMessage());
            com.inmobi.commons.core.c.a.a().a(str, "ExceptionCaught", hashMap);
            return false;
        }
    }
}
