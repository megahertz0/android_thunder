package com.xunlei.downloadprovider.web.base;

import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.player.a;
import com.xunlei.downloadprovider.player.ai;
import com.xunlei.downloadprovider.player.y;
import com.xunlei.downloadprovider.web.base.model.b;
import com.xunlei.downloadprovider.web.base.model.u;

// compiled from: ShortMovieDetailActivity.java
final class al implements y {
    final /* synthetic */ ShortMovieDetailActivity a;

    al(ShortMovieDetailActivity shortMovieDetailActivity) {
        this.a = shortMovieDetailActivity;
    }

    public final void a() {
        this.a.W.removeCallbacks(this.a.Z);
        this.a.V = false;
        a.a(this.a.b, "previous");
        if (this.a.O.a()) {
            XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u6ca1\u6709\u64ad\u653e\u5386\u53f2");
            this.a.y.e(false);
            return;
        }
        Object obj;
        b i = this.a.O;
        if (i.a <= 0) {
            obj = null;
        } else {
            int i2 = i.a - 1;
            i.a = i2;
            obj = i.get(i2);
        }
        u uVar = (u) obj;
        ai aiVar = new ai(uVar.a, uVar.e, uVar.b, "video_screen");
        aiVar.d = uVar.g;
        this.a.y.a(aiVar);
        this.a.c(uVar);
        if (this.a.q != null) {
            this.a.q.a(uVar);
        }
        this.a.y.e(!this.a.O.a());
    }

    public final void b() {
        this.a.W.removeCallbacks(this.a.Z);
        this.a.V = false;
        a.a(this.a.b, "next");
        if (this.a.q == null) {
            return;
        }
        if (!this.a.O.b()) {
            Object obj;
            b i = this.a.O;
            if (i.a >= i.size() - 1) {
                obj = null;
            } else {
                int i2 = i.a + 1;
                i.a = i2;
                obj = i.get(i2);
            }
            u uVar = (u) obj;
            ai aiVar = new ai(uVar.a, uVar.e, uVar.b, "video_screen");
            aiVar.d = uVar.g;
            this.a.y.a(aiVar);
            if (this.a.q != null) {
                this.a.q.a(uVar);
            }
            this.a.y.e(!this.a.O.a());
        } else if (!this.a.q.a(false)) {
            XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, "\u6ca1\u6709\u89c6\u9891\u4e86");
            this.a.y.f(false);
        }
    }

    public final void c() {
        this.a.W.removeCallbacks(this.a.Z);
        this.a.V = false;
        this.a.a(false);
    }

    public final void a(boolean z) {
        if (!z) {
            if (this.a.d()) {
                this.a.a(true);
                if (!this.a.V) {
                    this.a.X = ((this.a.y.g() - this.a.y.h()) + 1000) / 1000;
                    this.a.V = true;
                    ShortMovieDetailActivity.l(this.a);
                    return;
                }
                return;
            }
            this.a.a(false);
        }
    }
}
