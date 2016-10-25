package com.xiaomi.channel.commonutils.stats;

import java.util.LinkedList;

public class a {
    private LinkedList<a> a;

    public static class a {
        private static final a d;
        public int a;
        public String b;
        public Object c;

        static {
            d = new a();
        }

        a(int i, Object obj) {
            this.a = i;
            this.c = obj;
        }
    }

    public a() {
        this.a = new LinkedList();
    }

    public static a a() {
        return a.d;
    }

    private void d() {
        if (this.a.size() > 100) {
            this.a.removeFirst();
        }
    }

    public synchronized void a(Object obj) {
        this.a.add(new a(0, obj));
        d();
    }

    public synchronized int b() {
        return this.a.size();
    }

    public synchronized LinkedList<a> c() {
        LinkedList<a> linkedList;
        linkedList = this.a;
        this.a = new LinkedList();
        return linkedList;
    }
}
