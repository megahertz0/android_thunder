package com.xunlei.tdlive;

// compiled from: RegisterActivity.java
class dz implements Runnable {
    final /* synthetic */ RegisterActivity a;

    dz(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public void run() {
        RegisterActivity.b(this.a);
        if (this.a.a == 60) {
            this.a.h();
            this.a.a = 0;
        }
        this.a.i();
    }
}
