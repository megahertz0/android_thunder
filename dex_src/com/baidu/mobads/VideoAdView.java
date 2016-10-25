package com.baidu.mobads;

import android.content.Context;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.production.i.b;

public class VideoAdView extends RelativeLayout {
    private b a;
    private IOAdEventListener b;
    private VideoAdViewListener c;

    public enum VideoDuration {
        DURATION_15_SECONDS(15),
        DURATION_30_SECONDS(30),
        DURATION_45_SECONDS(45);
        private int a;

        static {
            DURATION_15_SECONDS = new com.baidu.mobads.VideoAdView.VideoDuration("DURATION_15_SECONDS", 0, 15);
            DURATION_30_SECONDS = new com.baidu.mobads.VideoAdView.VideoDuration("DURATION_30_SECONDS", 1, 30);
            DURATION_45_SECONDS = new com.baidu.mobads.VideoAdView.VideoDuration("DURATION_45_SECONDS", 2, 45);
            b = new com.baidu.mobads.VideoAdView.VideoDuration[]{DURATION_15_SECONDS, DURATION_30_SECONDS, DURATION_45_SECONDS};
        }

        private VideoDuration(int i) {
            this.a = i;
        }

        protected final int getValue() {
            return this.a;
        }
    }

    public enum VideoSize {
        SIZE_16x9(320, 180),
        SIZE_4x3(400, 300);
        private int a;
        private int b;

        static {
            SIZE_16x9 = new com.baidu.mobads.VideoAdView.VideoSize("SIZE_16x9", 0, 320, 180);
            SIZE_4x3 = new com.baidu.mobads.VideoAdView.VideoSize("SIZE_4x3", 1, 400, 300);
            c = new com.baidu.mobads.VideoAdView.VideoSize[]{SIZE_16x9, SIZE_4x3};
        }

        private VideoSize(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        protected final int getWidth() {
            return this.a;
        }

        protected final int getHeight() {
            return this.b;
        }
    }

    public VideoAdView(Context context) {
        super(context);
        this.b = new ak(this);
    }

    public VideoAdView(Context context, String str) {
        super(context, null);
        this.b = new ak(this);
    }

    public void requestAd(VideoAdRequest videoAdRequest) {
        this.a = new b(getContext(), "TODO");
        this.a.setActivity(getContext());
        this.a.setAdSlotBase(this);
        this.a.addEventListener(IXAdEvent.AD_CLICK_THRU, this.b);
        this.a.addEventListener(IXAdEvent.AD_LOADED, this.b);
        this.a.addEventListener(IXAdEvent.AD_STARTED, this.b);
        this.a.addEventListener(IXAdEvent.AD_STOPPED, this.b);
        this.a.addEventListener(IXAdEvent.AD_ERROR, this.b);
        this.a.request();
    }

    public void startVideo() {
        this.a.start();
    }

    public static void setAppSid(Context context, String str) {
        AdView.setAppSid(context, str);
    }

    public void setListener(VideoAdViewListener videoAdViewListener) {
        this.c = videoAdViewListener;
    }
}
