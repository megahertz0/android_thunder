package com.baidu.mobads;

class l implements Runnable {
    final /* synthetic */ j a;

    l(j jVar) {
        this.a = jVar;
    }

    public void run() {
        this.a.b.curWebview.stopLoading();
    }
}
