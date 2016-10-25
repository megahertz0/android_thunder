package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.c.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

final class aa {
    ArrayList<String> a;
    ArrayList<z> b;
    ArrayList<z> c;
    private HashSet<z> d;
    private ArrayList<z> e;
    private HashSet<z> f;

    aa() {
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new HashSet();
        this.e = new ArrayList();
        this.f = new HashSet();
    }

    public final void a(z zVar) {
        if (zVar != null && !b(zVar)) {
            if (zVar.b.startsWith("thunder://")) {
                String i = b.i(zVar.b);
                Object obj = zVar.e;
                if (!TextUtils.isEmpty(i) && !TextUtils.isEmpty(obj)) {
                    if (!i.equals(zVar.b)) {
                        zVar = new z(i);
                        zVar.e = obj;
                    }
                    this.d.add(zVar);
                    if (!b(zVar)) {
                        c(zVar);
                        return;
                    }
                    return;
                }
                return;
            }
            c(zVar);
        }
    }

    private boolean b(z zVar) {
        return this.f.contains(zVar);
    }

    private void c(z zVar) {
        this.e.add(zVar);
        this.f.add(zVar);
    }

    private void d(z zVar) {
        this.e.remove(zVar);
    }

    public final void a() {
        Iterator it;
        if (!this.d.isEmpty()) {
            it = this.d.iterator();
            while (it.hasNext()) {
                z zVar = (z) it.next();
                String str = zVar.b;
                Object f = b.f(str);
                if (!TextUtils.isEmpty(f)) {
                    String replaceAll = f.replaceAll(" ", "%20");
                    CharSequence e = b.e(replaceAll);
                    z zVar2 = new z(e);
                    if (b(new z(replaceAll))) {
                        d(new z(str));
                    } else if (!str.equals(e) && !TextUtils.isEmpty(e)) {
                        d(new z(str));
                        zVar2.e = zVar.e;
                        c(zVar2);
                    }
                }
            }
        }
        this.d.clear();
        this.f.clear();
        this.c.clear();
        this.f.addAll(this.e);
        this.c.addAll(this.e);
        this.a.clear();
        if (!this.e.isEmpty()) {
            it = this.e.iterator();
            while (it.hasNext()) {
                this.a.add(((z) it.next()).b);
            }
        }
    }
}
