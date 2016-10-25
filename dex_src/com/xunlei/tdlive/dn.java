package com.xunlei.tdlive;

// compiled from: MainActivity.java
class dn implements Runnable {
    final /* synthetic */ MainActivity a;

    dn(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        MainActivity.a(this.a, true);
    }
}
