package com.baidu.mobads;

class f implements Runnable {
    final /* synthetic */ AppActivity a;

    f(AppActivity appActivity) {
        this.a = appActivity;
    }

    public void run() {
        this.a.finish();
    }
}
