package com.umeng.socialize.media;

import com.umeng.socialize.ShareContent;

public class SimpleShareContent {
    private UMImage a;
    private String b;
    private String c;
    private String d;
    private UMVideo e;
    private UMusic f;

    public SimpleShareContent(ShareContent shareContent) {
        this.b = shareContent.mText;
        this.c = shareContent.mTitle;
        this.d = shareContent.mTargetUrl;
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMImage)) {
            this.a = (UMImage) shareContent.mMedia;
        }
    }

    public void setTitle(String str) {
        this.c = str;
    }

    public String getTitle() {
        return this.c;
    }

    public void setText(String str) {
        this.b = str;
    }

    public String getText() {
        return this.b;
    }

    public void setImage(UMImage uMImage) {
        this.a = uMImage;
    }

    public UMImage getImage() {
        return this.a;
    }

    public void setTargeturl(String str) {
        this.d = str;
    }

    public String getTargeturl() {
        return this.d;
    }

    public void setMusic(UMusic uMusic) {
        this.f = uMusic;
    }

    public UMusic getMusic() {
        return this.f;
    }

    public void setVideo(UMVideo uMVideo) {
        this.e = uMVideo;
    }

    public UMVideo getVideo() {
        return this.e;
    }
}
