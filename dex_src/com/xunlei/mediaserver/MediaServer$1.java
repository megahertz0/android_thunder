package com.xunlei.mediaserver;

class MediaServer$1 implements Runnable {
    final /* synthetic */ MediaServer this$0;

    MediaServer$1(MediaServer mediaServer) {
        this.this$0 = mediaServer;
    }

    public void run() {
        MediaServer.access$100();
        synchronized (this.this$0.mMediaServerRunnable) {
            this.this$0.mMediaServerRunnable.notify();
        }
    }
}
