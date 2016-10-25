package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.k;

final class m implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ StatSpecifyReportedInfo b;

    m(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.a = context;
        this.b = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.a == null) {
            StatServiceImpl.q.error((Object) "The Context of StatService.onPause() can not be null!");
        } else {
            StatServiceImpl.trackEndPage(this.a, k.h(this.a), this.b);
        }
    }
}
