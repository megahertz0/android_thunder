package com.sina.weibo.sdk.call;

import org.android.agoo.message.MessageService;

public class Position {
    private float mLatitude;
    private float mLongitude;
    private boolean mOffset;

    public Position(float f, float f2) {
        this.mLongitude = f;
        this.mLatitude = f2;
        this.mOffset = true;
    }

    public Position(float f, float f2, boolean z) {
        this.mLongitude = f;
        this.mLatitude = f2;
        this.mOffset = z;
    }

    public float getLongitude() {
        return this.mLongitude;
    }

    public float getLatitude() {
        return this.mLatitude;
    }

    public boolean isOffset() {
        return this.mOffset;
    }

    public String getStrLongitude() {
        return String.valueOf(this.mLongitude);
    }

    public String getStrLatitude() {
        return String.valueOf(this.mLatitude);
    }

    public String getStrOffset() {
        return this.mOffset ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT;
    }

    boolean checkValid() {
        return !Float.isNaN(this.mLongitude) && this.mLongitude >= -180.0f && this.mLongitude <= 180.0f && !Float.isNaN(this.mLatitude) && this.mLatitude >= -180.0f && this.mLatitude <= 180.0f;
    }
}
