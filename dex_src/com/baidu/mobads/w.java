package com.baidu.mobads;

class w implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ v b;

    w(v vVar, String str) {
        this.b = vVar;
        this.a = str;
    }

    public void run() {
        this.b.d.onUrl(this.a);
    }
}
