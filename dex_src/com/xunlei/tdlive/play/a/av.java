package com.xunlei.tdlive.play.a;

import android.content.Context;
import android.text.TextUtils;
import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.d.g;
import com.xunlei.tdlive.protocol.XLLiveMicConnectClose;
import com.xunlei.tdlive.protocol.XLLiveMicConnectReply;
import com.xunlei.tdlive.protocol.XLLiveMicConnectRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.r;
import com.xunlei.tdlive.util.v;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: UserConnectMicPresenter.java
public class av implements com.xunlei.tdlive.d.b.a {
    private Context a;
    private String b;
    private String c;
    private String d;
    private c e;
    private r f;
    private a g;
    private b h;
    private com.xunlei.tdlive.d.b i;
    private int j;
    private boolean k;
    private final int l;

    // compiled from: UserConnectMicPresenter.java
    public static interface b {
        void a();
    }

    // compiled from: UserConnectMicPresenter.java
    public static interface a {
        void a(com.xunlei.tdlive.play.view.ConnectMicView.a aVar);

        void a(String str);
    }

    public av(Context context) {
        this.l = 30000;
        this.a = context;
        this.i = g.d();
    }

    public void c(String str) {
        String k = f.a().k();
        this.d = k;
        new XLLiveMicConnectRequest(k, f.a().l(), str).send(new aw(this));
    }

    public void a(String str, String str2, String str3) {
        this.d = str;
        this.c = str2;
        if (this.e == null) {
            this.e = new c(this.a, null, BuildConfig.VERSION_NAME, "\u62d2 \u7edd", new String[]{"\u63a5 \u53d7"});
        }
        this.e.b(v.a(str2, R.styleable.Toolbar_titleMarginEnd) + "\u9080\u8bf7\u4f60\u8fde\u9ea6");
        this.e.b(new ax(this, str, str3));
        b((int) SimpleLog.LOG_LEVEL_DEBUG);
        e();
        a(com.xunlei.tdlive.play.view.ConnectMicView.a.b);
        this.g.a(str);
    }

    public void a(int i, String str, b bVar) {
        this.h = bVar;
        a(i, str);
    }

    public void a(int i, String str) {
        new c(this.a, null, "\u7ed3\u675f\u5f53\u524d\u8fde\u9ea6\u4e92\u52a8\uff1f", "\u53d6 \u6d88", new String[]{"\u786e \u5b9a"}).b(new ay(this, i, str));
    }

    public void a(int i) {
        if (i == 0) {
            e(v.a(this.c, R.styleable.Toolbar_titleMarginEnd) + " \u5df2\u7ed3\u675f\u8bed\u97f3\u8fde\u9ea6");
        } else {
            e("\u4e3b\u64ad\u5df2\u7ed3\u675f\u8bed\u97f3\u8fde\u9ea6");
            f();
        }
        a(com.xunlei.tdlive.play.view.ConnectMicView.a.a);
    }

    public void b(int i, String str) {
        if (i == 1) {
            a(com.xunlei.tdlive.play.view.ConnectMicView.a.c);
            d(str);
        } else {
            e("\u4e3b\u64ad\u62d2\u7edd\u4e86\u4f60\u7684\u8fde\u9ea6\u8bf7\u6c42");
            a(com.xunlei.tdlive.play.view.ConnectMicView.a.a);
        }
        d();
    }

    private void d(String str) {
        this.b = str;
        this.i.a(this.a, false, 0, 0, 0, 0, this);
    }

    public void c(int i, String str) {
        new XLLiveMicConnectClose(f.a().k(), this.d, f.a().l(), String.valueOf(i), str).send(new az(this, i));
    }

    private void b() {
        if (this.f == null) {
            this.f = new r(30000, new ba(this));
            this.f.a(true);
        }
    }

    private void c() {
        if (this.k) {
            if (this.j == 1) {
                e("\u4e3b\u64ad\u6ca1\u6709\u56de\u590d\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
            } else if (this.j == 2) {
                this.e.dismiss();
            }
            d();
            this.f.c();
            a(com.xunlei.tdlive.play.view.ConnectMicView.a.a);
        }
    }

    private void b(String str, String str2, String str3) {
        new XLLiveMicConnectReply(f.a().k(), str, f.a().l(), str2, str3).send(new bb(this, str3));
    }

    private void d(int i, String str) {
        if (i == 0) {
            b(1);
            e();
            return;
        }
        if (TextUtils.isEmpty(str)) {
            e("\u7f51\u7edc\u5f02\u5e38\uff0c\u65e0\u6cd5\u53d1\u9001\u8bed\u97f3\u8fde\u9ea6\u8bf7\u6c42");
        } else {
            e(str);
        }
        a(com.xunlei.tdlive.play.view.ConnectMicView.a.a);
    }

    private void b(int i) {
        this.j = i;
        this.k = true;
    }

    private void d() {
        this.k = false;
    }

    private void e() {
        b();
        this.f.b();
    }

    private void e(String str) {
        n.a(this.a, str);
    }

    private void f() {
        this.i.a();
    }

    private void a(com.xunlei.tdlive.play.view.ConnectMicView.a aVar) {
        if (this.g != null) {
            this.g.a(aVar);
        }
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void a(String str) {
    }

    public void b(String str) {
    }

    public void a() {
        this.i.a(BuildConfig.VERSION_NAME, this.b);
    }

    public void a(long j, int i, int i2, int i3, int i4, String str, int i5) {
    }
}
