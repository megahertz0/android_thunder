package com.xunlei.tdlive.modal;

import java.util.LinkedList;

// compiled from: DoubleGiftRemindInfoQueue.java
public class a {
    private LinkedList<com.xunlei.tdlive.modal.b.a> a;
    private LinkedList<com.xunlei.tdlive.modal.b.a> b;

    public a() {
        this.a = new LinkedList();
        this.b = new LinkedList();
    }

    public void a(com.xunlei.tdlive.modal.b.a aVar) {
        if (this.a.size() <= 0 || b(aVar)) {
            this.a.addLast(aVar);
        } else {
            this.b.addLast(aVar);
        }
    }

    private boolean b(com.xunlei.tdlive.modal.b.a aVar) {
        return a((com.xunlei.tdlive.modal.b.a) this.a.getLast(), aVar) || (this.a.size() <= this.b.size() && !a((com.xunlei.tdlive.modal.b.a) this.b.getLast(), aVar));
    }

    private boolean a(com.xunlei.tdlive.modal.b.a aVar, com.xunlei.tdlive.modal.b.a aVar2) {
        return aVar.a.equals(aVar2.a) && aVar.k.equals(aVar2.k);
    }

    public boolean a() {
        return this.a.size() <= 0 && this.b.size() <= 0;
    }

    public LinkedList<com.xunlei.tdlive.modal.b.a> b() {
        return this.a;
    }

    public LinkedList<com.xunlei.tdlive.modal.b.a> c() {
        return this.b;
    }
}
