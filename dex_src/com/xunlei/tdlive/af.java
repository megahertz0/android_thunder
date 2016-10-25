package com.xunlei.tdlive;

// compiled from: LivePlayerActivity.java
class af implements Runnable {
    final /* synthetic */ LivePlayerActivity a;

    af(LivePlayerActivity livePlayerActivity) {
        this.a = livePlayerActivity;
    }

    public void run() {
        this.a.e = new au(this.a, false, this.a.i.a, this.a.i.b);
        this.a.e.setOwnerActivity(this.a);
        this.a.e.show();
    }
}
