package com.baidu.mobads;

class g implements Runnable {
    final /* synthetic */ AppActivity a;

    g(AppActivity appActivity) {
        this.a = appActivity;
    }

    public void run() {
        this.a.finish();
    }
}
