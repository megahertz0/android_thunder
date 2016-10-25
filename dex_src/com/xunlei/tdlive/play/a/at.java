package com.xunlei.tdlive.play.a;

import android.content.Context;
import cn.nodemedia.LivePublisher;
import com.xunlei.tdlive.a$a;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.util.XLog;

// compiled from: SignalLevelPresenter.java
public class at {
    private static final String a;
    private Context b;
    private b c;
    private a d;
    private boolean e;
    private String f;
    private int g;
    private a$a h;

    // compiled from: SignalLevelPresenter.java
    public static interface a {
        void a();
    }

    // compiled from: SignalLevelPresenter.java
    public static interface b {
        void a(float f, com.xunlei.tdlive.play.view.SignalLevelView.a aVar);
    }

    static {
        a = at.class.getSimpleName();
    }

    public at(Context context) {
        this.g = 0;
        this.h = new au(this);
        this.b = context;
    }

    public void a(boolean z, String str) {
        this.e = z;
        this.f = str;
    }

    private void a(float f, float f2, float f3, float f4) {
        if (this.e) {
            a(f2 / 1024.0f);
        }
    }

    private void a(float f) {
        float f2;
        com.xunlei.tdlive.play.view.SignalLevelView.a aVar;
        if (LivePublisher.getFilterState() == 1) {
            f2 = -147456.0f;
        } else {
            f2 = 0.0f;
        }
        com.xunlei.tdlive.play.view.SignalLevelView.a aVar2 = com.xunlei.tdlive.play.view.SignalLevelView.a.a;
        if (f > ((((float) e.k) + f2) / 1000.0f) / 8.0f) {
            aVar = com.xunlei.tdlive.play.view.SignalLevelView.a.d;
            this.g = 0;
        } else if (f > ((f2 + ((float) e.l)) / 1000.0f) / 8.0f) {
            aVar = com.xunlei.tdlive.play.view.SignalLevelView.a.c;
            if (this.g > 0) {
                this.g--;
            }
        } else if (f > 0.0f) {
            c();
            aVar = com.xunlei.tdlive.play.view.SignalLevelView.a.b;
        } else {
            aVar = aVar2;
        }
        if (this.c != null) {
            this.c.a(f, aVar);
        }
    }

    private void c() {
        XLog.d(a, new StringBuilder("recordLowSignalCount: signalLowCount:").append(this.g).toString());
        if (this.g > e.m) {
            String str = "\u7531\u4e8e\u7f51\u7edc\u957f\u65f6\u95f4\u8fc7\u5dee\uff0c\u6211\u4eec\u5c06\u5173\u95ed\u76f4\u64ad\u95f4\uff0c\u8bf7\u60a8\u6539\u5584\u7f51\u7edc\u73af\u5883\u91cd\u65b0\u5f00\u64ad\uff0c\u8c22\u5566~";
            XLog.d(a, str);
            a(str);
            if (this.d != null) {
                this.d.a();
            }
            this.g = 0;
            return;
        }
        this.g++;
    }

    public void a() {
        b(true, this.f);
    }

    public void b() {
        b(false, null);
    }

    private void b(boolean z, String str) {
        com.xunlei.tdlive.a.a(this.h, z);
    }

    private void a(String str) {
        n.a(this.b, str);
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.c = bVar;
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.d = aVar;
        }
    }
}
