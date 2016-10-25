package com.xunlei.tdlive.modal;

import android.text.TextUtils;
import com.xunlei.tdlive.modal.b.a;
import com.xunlei.tdlive.util.r;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

// compiled from: GiftRemindManager.java
public class b {
    private final int a;
    private r b;
    private b c;
    private a d;
    private ArrayList<c> e;
    private LinkedList<d> f;

    // compiled from: GiftRemindManager.java
    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public int h;
        public String i;
        public String j;
        public String k;
        public int l;

        public a(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, String str8, String str9, String str10, int i2) {
            this.l = 1;
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
            this.g = str7;
            this.h = i;
            this.i = str8;
            this.j = str9;
            this.k = str10;
            this.l = i2;
        }
    }

    // compiled from: GiftRemindManager.java
    public static interface b {
        boolean onMsgPop(a aVar, int i);
    }

    // compiled from: GiftRemindManager.java
    public static class c {
        public int a;
        public String b;

        public c() {
            this.a = 0;
            this.b = BuildConfig.VERSION_NAME;
        }
    }

    public b(int i) {
        this.a = 700;
        this.d = new a();
        this.e = new ArrayList();
        this.f = new LinkedList();
        if (i <= 0) {
            throw new IllegalArgumentException("reminderCount <= 0!");
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.e.add(new c());
        }
        this.b = new r(700, new c(this));
    }

    public void a() {
        this.b.b();
    }

    public void b() {
        this.b.c();
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.d.a(aVar);
        }
    }

    public void a(int i, int i2) {
        if (i >= 0 && i < this.e.size()) {
            ((c) this.e.get(i)).a = i2;
        }
    }

    public void a(int i, String str) {
        ((c) this.e.get(i)).b = str;
    }

    private void c() {
        if (!this.d.a()) {
            a(this.d.b());
            a(this.d.c());
        }
    }

    private void a(LinkedList<a> linkedList) {
        if (linkedList.size() > 0) {
            int i;
            d b = b((a) linkedList.getFirst());
            int e = e();
            if (b != null) {
                int b2 = b.b();
                if (b2 != e) {
                    if (((c) this.e.get(b2)).b.equals(b.a().a)) {
                        a(e, 0);
                        i = b2;
                        if (i != -1) {
                            a(i, b, linkedList);
                        }
                    } else if (e != -1) {
                        b.a(e);
                    }
                }
            }
            i = e;
            if (i != -1) {
                a(i, b, linkedList);
            }
        }
    }

    private void a(int i, d dVar, LinkedList<a> linkedList) {
        if (this.c != null) {
            a aVar = (a) linkedList.poll();
            if (dVar != null) {
                aVar = dVar.a();
            } else {
                this.f.add(new d(i, aVar));
                d();
            }
            this.c.onMsgPop(aVar, i);
        }
    }

    private void d() {
        if (this.f.size() > 2) {
            this.f.poll();
        }
    }

    private d b(a aVar) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            if (dVar != null && dVar.a() != null && !TextUtils.isEmpty(aVar.a) && aVar.a.equals(dVar.a().a) && !TextUtils.isEmpty(aVar.k) && aVar.k.equals(dVar.a().k)) {
                dVar.a(aVar);
                return dVar;
            }
        }
        return null;
    }

    private int e() {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            if (((c) this.e.get(size)).a == 0) {
                a(size, 1);
                return size;
            }
        }
        return -1;
    }
}
