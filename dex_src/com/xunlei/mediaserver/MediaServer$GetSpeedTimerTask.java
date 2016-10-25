package com.xunlei.mediaserver;

import java.util.TimerTask;

private class MediaServer$GetSpeedTimerTask extends TimerTask {
    private MediaServer$OnVodTaskInfoListener mOnVodTaskInfoListener;
    private String mPlayUrl;
    private VodTaskInfoService mVodTaskInfoService;
    final /* synthetic */ MediaServer this$0;

    public MediaServer$GetSpeedTimerTask(MediaServer mediaServer, MediaServer$OnVodTaskInfoListener mediaServer$OnVodTaskInfoListener, String str) {
        this.this$0 = mediaServer;
        this.mPlayUrl = str;
        this.mOnVodTaskInfoListener = mediaServer$OnVodTaskInfoListener;
        this.mVodTaskInfoService = new VodTaskInfoService(mediaServer$OnVodTaskInfoListener, "127.0.0.1", MediaServer.getHttpListenPort());
    }

    public void run() {
        synchronized (MediaServer.access$000(this.this$0)) {
            if (this.mVodTaskInfoService == null) {
                this.mVodTaskInfoService = new VodTaskInfoService(this.mOnVodTaskInfoListener, "127.0.0.1", MediaServer.getHttpListenPort());
            }
            this.mVodTaskInfoService.sendCommand(this.this$0.generateGetVodPlayInfoURL(this.mPlayUrl));
        }
    }

    public void release() {
        if (this.mVodTaskInfoService != null) {
            this.mVodTaskInfoService.uninit();
            this.mVodTaskInfoService = null;
        }
    }
}
