package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnClickListener;
import com.android.volley.Request;
import com.xunlei.downloadprovider.c.a;
import com.xunlei.downloadprovider.c.a.e;
import com.xunlei.downloadprovider.c.a.h;
import com.xunlei.downloadprovider.c.a.j;
import com.xunlei.downloadprovider.c.f;
import com.xunlei.downloadprovider.c.g;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.x;
import com.xunlei.xllib.a.b;

// compiled from: ReportActivity.java
final class au implements OnClickListener {
    final /* synthetic */ ReportActivity a;

    au(ReportActivity reportActivity) {
        this.a = reportActivity;
    }

    public final void onClick(View view) {
        if (!b.a(this.a)) {
            XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
        } else if (this.a.f == 1) {
            xVar = new x(this.a);
            xVar.a("\u6b63\u5728\u63d0\u4ea4...");
            xVar.show();
            a aVar = new a();
            e eVar = new e();
            eVar.c = this.a.h;
            eVar.b = this.a.g;
            eVar.a = 1;
            aVar.a(eVar);
            long h = this.a.i;
            int i = this.a.e;
            av avVar = new av(this, xVar);
            if (aVar.c == null) {
                throw new IllegalStateException("no comment resource attached");
            }
            j jVar = new j();
            jVar.d = h;
            jVar.a = aVar.c.b;
            jVar.b = aVar.c.c;
            jVar.c = aVar.c.a;
            jVar.e = i;
            Request hVar = new h(0, new StringBuilder("https://comment-shoulei-ssl.xunlei.com/comment/api/report?").append(jVar.f()).toString(), null, new f(aVar, avVar), new g(aVar, avVar));
            hVar.setRetryPolicy(new com.android.volley.f(10000, 1, 1.0f));
            hVar.setTag(a.a);
            aVar.b.a(hVar);
        } else if (this.a.f == 2) {
            xVar = new x(this.a);
            xVar.a("\u6b63\u5728\u63d0\u4ea4...");
            xVar.show();
            this.a.d.postDelayed(new aw(this, xVar), 1500);
        } else if (this.a.f == 3) {
            ReportActivity.j(this.a);
        }
    }
}
