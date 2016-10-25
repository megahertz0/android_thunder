package com.alipay.b.a.a.e;

import com.alipay.b.a.a.c.b.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

public final class b {
    private File a;
    private a b;

    public b(String str, a aVar) {
        this.a = null;
        this.b = null;
        this.a = new File(str);
        this.b = aVar;
    }

    private static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JsInterface.FUNPLAY_AD_TRPE, SocializeConstants.WEIBO_ID);
            jSONObject.put("error", str);
            return jSONObject.toString();
        } catch (Exception e) {
            return com.umeng.a.d;
        }
    }

    private synchronized void b() {
        int i = 0;
        synchronized (this) {
            if (this.a != null) {
                if (this.a.exists() && this.a.isDirectory() && this.a.list().length != 0) {
                    int i2;
                    String str;
                    List arrayList = new ArrayList();
                    String[] list = this.a.list();
                    int length = list.length;
                    for (i2 = 0; i2 < length; i2++) {
                        arrayList.add(list[i2]);
                    }
                    Collections.sort(arrayList);
                    String str2 = (String) arrayList.get(arrayList.size() - 1);
                    int size = arrayList.size();
                    int i3;
                    if (!str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + MsgConstant.CACHE_LOG_FILE_EXT)) {
                        i3 = size;
                        str = str2;
                        i2 = i3;
                    } else if (arrayList.size() >= 2) {
                        i3 = size - 1;
                        str = (String) arrayList.get(arrayList.size() - 2);
                        i2 = i3;
                    }
                    size = !this.b.a(a(com.alipay.b.a.a.a.b.a(this.a.getAbsolutePath(), str))) ? i2 - 1 : i2;
                    while (i < size) {
                        new File(this.a, (String) arrayList.get(i)).delete();
                        i++;
                    }
                }
            }
        }
    }

    public final void a() {
        new Thread(new c(this)).start();
    }
}
