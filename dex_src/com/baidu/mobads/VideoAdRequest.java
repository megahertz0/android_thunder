package com.baidu.mobads;

import com.baidu.mobads.VideoAdView.VideoDuration;
import com.baidu.mobads.VideoAdView.VideoSize;

public class VideoAdRequest {
    private VideoDuration a;
    private boolean b;
    private VideoSize c;

    public static class Builder {
        private VideoDuration a;
        private boolean b;
        private VideoSize c;

        public Builder() {
            this.b = false;
        }

        public com.baidu.mobads.VideoAdRequest.Builder setVideoDuration(VideoDuration videoDuration) {
            this.a = videoDuration;
            return this;
        }

        public com.baidu.mobads.VideoAdRequest.Builder isShowCountdown(boolean z) {
            this.b = z;
            return this;
        }

        public com.baidu.mobads.VideoAdRequest.Builder setVideoSize(VideoSize videoSize) {
            this.c = videoSize;
            return this;
        }

        public VideoAdRequest build() {
            return new VideoAdRequest();
        }
    }

    private VideoAdRequest(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
    }

    protected int getVideoDuration() {
        return this.a == null ? VideoDuration.DURATION_15_SECONDS.getValue() : this.a.getValue();
    }

    protected boolean isShowCountdown() {
        return this.b;
    }

    protected int getVideoWidth() {
        if (this.c == null) {
            this.c = VideoSize.SIZE_16x9;
        }
        return this.c.getWidth();
    }

    protected int getVideoHeight() {
        if (this.c == null) {
            this.c = VideoSize.SIZE_16x9;
        }
        return this.c.getHeight();
    }
}
