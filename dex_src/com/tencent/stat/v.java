package com.tencent.stat;

class v implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ n b;

    v(n nVar, int i) {
        this.b = nVar;
        this.a = i;
    }

    public void run() {
        int a = StatConfig.a();
        int i = this.a == -1 ? this.b.b : this.a;
        int i2 = i / a;
        int i3 = i % a;
        for (i = 0; i < i2 + 1; i++) {
            this.b.b(a);
        }
        if (i3 > 0) {
            this.b.b(i3);
        }
    }
}
