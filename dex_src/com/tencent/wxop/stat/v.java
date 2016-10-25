package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.c;

final class v implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ c b;
    final /* synthetic */ Context c;

    v(String str, c cVar, Context context) {
        this.a = str;
        this.b = cVar;
        this.c = context;
    }

    public final void run() {
        try {
            if (StatServiceImpl.a(this.a)) {
                StatServiceImpl.q.error((Object) "The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
                return;
            }
            if (StatConfig.isDebugEnable()) {
                StatServiceImpl.q.i(new StringBuilder("add begin key:").append(this.b.toString()).toString());
            }
            if (StatServiceImpl.e.containsKey(this.b)) {
                StatServiceImpl.q.error(new StringBuilder("Duplicate CustomEvent key: ").append(this.b.toString()).append(", trackCustomBeginEvent() repeated?").toString());
            } else if (StatServiceImpl.e.size() <= StatConfig.getMaxParallelTimmingEvents()) {
                StatServiceImpl.e.put(this.b, Long.valueOf(System.currentTimeMillis()));
            } else {
                StatServiceImpl.q.error(new StringBuilder("The number of timedEvent exceeds the maximum value ").append(Integer.toString(StatConfig.getMaxParallelTimmingEvents())).toString());
            }
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
            StatServiceImpl.a(this.c, th);
        }
    }
}
