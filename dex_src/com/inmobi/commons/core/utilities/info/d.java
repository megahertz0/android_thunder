package com.inmobi.commons.core.utilities.info;

import com.inmobi.commons.a.b;
import com.inmobi.commons.core.utilities.uid.c;
import com.umeng.message.MsgConstant;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

// compiled from: IdentityInfo.java
public class d {
    private static final String a;

    static {
        a = d.class.getSimpleName();
    }

    private static String b() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis());
    }

    private static String c() {
        Calendar instance = Calendar.getInstance();
        return String.valueOf(instance.get(R.styleable.Toolbar_titleMarginBottom) + instance.get(XZBDevice.Delete));
    }

    public static Map<String, String> a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("mk-version", b.a());
        hashMap.put("u-id-adt", String.valueOf(c.a().m() ? 1 : 0));
        hashMap.put(MsgConstant.KEY_TS, b());
        hashMap.put("tz", c());
        hashMap.putAll(f.a().c());
        return hashMap;
    }
}
