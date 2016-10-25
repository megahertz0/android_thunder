package com.umeng.socialize.media;

import android.os.Parcel;
import android.text.TextUtils;
import com.umeng.a;

public abstract class BaseMediaObject implements UMediaObject {
    protected String a;
    protected String b;
    protected String c;
    protected String d;
    protected String e;
    protected String f;
    protected int g;
    protected String h;
    public String mText;

    public BaseMediaObject() {
        this.mText = null;
        this.a = a.d;
        this.b = a.d;
        this.c = a.d;
        this.d = a.d;
        this.e = a.d;
        this.f = a.d;
        this.g = 0;
        this.h = a.d;
    }

    public BaseMediaObject(String str) {
        this.mText = null;
        this.a = a.d;
        this.b = a.d;
        this.c = a.d;
        this.d = a.d;
        this.e = a.d;
        this.f = a.d;
        this.g = 0;
        this.h = a.d;
        this.a = str;
    }

    public String getDescription() {
        return this.h;
    }

    public void setDescription(String str) {
        this.h = str;
    }

    public String toUrl() {
        return this.a;
    }

    public void setMediaUrl(String str) {
        this.a = str;
    }

    public boolean isUrlMedia() {
        return !TextUtils.isEmpty(this.a);
    }

    public String getTitle() {
        return this.b;
    }

    public void setTitle(String str) {
        this.b = str;
    }

    public String getThumb() {
        return this.c;
    }

    public void setThumb(String str) {
        this.c = str;
    }

    public void setTargetUrl(String str) {
        this.d = str;
    }

    public String getTargetUrl() {
        return this.d;
    }

    protected BaseMediaObject(Parcel parcel) {
        this.mText = null;
        this.a = a.d;
        this.b = a.d;
        this.c = a.d;
        this.d = a.d;
        this.e = a.d;
        this.f = a.d;
        this.g = 0;
        this.h = a.d;
        if (parcel != null) {
            this.a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
        }
    }

    public String toString() {
        return new StringBuilder("BaseMediaObject [media_url=").append(this.a).append(", qzone_title=").append(this.b).append(", qzone_thumb=").append(this.c).append("]").toString();
    }
}
