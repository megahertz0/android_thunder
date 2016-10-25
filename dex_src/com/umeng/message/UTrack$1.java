package com.umeng.message;

class UTrack$1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ long c;
    final /* synthetic */ UTrack d;

    UTrack$1(UTrack uTrack, String str, int i, long j) {
        this.d = uTrack;
        this.a = str;
        this.b = i;
        this.c = j;
    }

    public void run() {
        UTrack.a(this.d, this.a, this.b, this.c);
    }
}
