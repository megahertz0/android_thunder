package com.xunlei.tdlive.aniengine;

// compiled from: AniSurfaceView.java
class b implements Runnable {
    final /* synthetic */ AniSurfaceView a;

    b(AniSurfaceView aniSurfaceView) {
        this.a = aniSurfaceView;
    }

    public void run() {
        if (this.a.a != null) {
            this.a.a.d();
            this.a.a.a();
            this.a.a.b();
        }
    }
}
