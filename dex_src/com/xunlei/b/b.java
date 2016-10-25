package com.xunlei.b;

import android.app.Activity;
import android.content.Context;
import com.umeng.analytics.MobclickAgent;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;

// compiled from: UMengReportHelper.java
public final class b {
    public WeakReference<Activity> a;
    public Context b;

    public b(Context context) {
        this.b = context;
    }

    public final void a(a aVar) {
        Context context = this.b;
        if (!(this.a == null || this.a.get() == null)) {
            context = (Context) this.a.get();
        }
        String valueOf = String.valueOf(aVar.a);
        a(aVar.b);
        if (aVar.b == null || aVar.b.size() == 0) {
            MobclickAgent.onEvent(context, valueOf);
        } else {
            MobclickAgent.onEvent(context, valueOf, aVar.b);
        }
    }

    public static void a(HashMap<String, String> hashMap) {
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) hashMap.get((String) it.next());
            if (str == null || str.length() == 0) {
                it.remove();
            }
        }
    }
}
