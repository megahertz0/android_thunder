package com.xunlei.common.member.c;

import android.os.Bundle;
import android.text.TextUtils;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.a.l;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.b.h;
import com.xunlei.tdlive.R;
import java.util.LinkedList;
import java.util.List;

// compiled from: UserTask.java
public abstract class p {
    private static int b;
    private int a;
    private int c;
    private m d;
    private Object e;
    private List<XLOnUserListener> f;
    private boolean g;

    // compiled from: UserTask.java
    enum a {
        ;

        private static int[] a() {
            return (int[]) e.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = new int[]{1, 2, 3, 4};
        }
    }

    public abstract boolean a(XLOnUserListener xLOnUserListener, Bundle bundle);

    public abstract boolean b();

    static {
        b = 1000000;
    }

    public final boolean e() {
        return this.g;
    }

    public final void b(boolean z) {
        this.g = false;
    }

    public p(m mVar) {
        this.a = a.a;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = new LinkedList();
        this.g = true;
        this.d = mVar;
        int i = b;
        b = i + 1;
        this.c = i;
    }

    public void a() {
        this.a = a.a;
        this.f.clear();
    }

    public final void d(int i) {
        this.a = i;
    }

    public final int f() {
        return this.a;
    }

    public final m g() {
        return this.d;
    }

    public final l h() {
        return (l) this.d.i();
    }

    public final void b(Object obj) {
        this.e = obj;
    }

    public final Object i() {
        return this.e;
    }

    public final int j() {
        return this.c;
    }

    public final String k() {
        return this.d.g();
    }

    public static int l() {
        return R.styleable.AppCompatTheme_spinnerStyle;
    }

    public static int m() {
        return 1;
    }

    private void a(p pVar) {
        if (pVar != null) {
            for (int i = 0; i < pVar.f.size(); i++) {
                a((XLOnUserListener) pVar.f.get(i));
            }
        }
    }

    public final synchronized void a(XLOnUserListener xLOnUserListener) {
        if (xLOnUserListener != null) {
            this.f.add(xLOnUserListener);
        }
    }

    private synchronized void b(XLOnUserListener xLOnUserListener) {
        this.f.remove(xLOnUserListener);
    }

    public final synchronized boolean a(Bundle bundle) {
        boolean z;
        if (a.d == this.a) {
            z = false;
        } else {
            this.a = a.c;
            for (int i = 0; i < this.f.size(); i++) {
                if (!a((XLOnUserListener) this.f.get(i), bundle)) {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    private static boolean a(String str) {
        return TextUtils.isEmpty(str);
    }

    public void a(int i) {
    }

    public void a(int i, String str) {
    }

    public void a(int i, h hVar) {
    }

    public void a(int i, String str, String str2, String str3, String str4) {
    }

    protected static boolean e(int i) {
        return i == 2 || i == 3 || i == 7 || i == 12 || i == 14 || i == 15;
    }

    protected static boolean f(int i) {
        return i == 2 || i == 3 || i == 7 || i == 12 || i == 14 || i == 15 || i == 6;
    }

    protected final String n() {
        return getClass().getSimpleName();
    }

    private boolean c() {
        if (this.a == a.c) {
            return false;
        }
        this.a = a.d;
        return true;
    }
}
