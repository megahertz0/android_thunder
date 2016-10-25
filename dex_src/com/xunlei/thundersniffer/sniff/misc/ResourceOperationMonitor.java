package com.xunlei.thundersniffer.sniff.misc;

public class ResourceOperationMonitor {
    protected boolean mFileInfoUpdating;
    protected ResourceOperationListener mListener;
    protected Object mUserObject;
    protected boolean mVodplayStatusUpdating;

    public Object getUserObject() {
        return this.mUserObject;
    }

    public void setUserObject(Object obj) {
        this.mUserObject = obj;
    }

    public ResourceOperationListener getListener() {
        return this.mListener;
    }

    public void setListener(ResourceOperationListener resourceOperationListener) {
        this.mListener = resourceOperationListener;
    }

    public boolean isVodplayStatusUpdating() {
        return this.mVodplayStatusUpdating;
    }

    public void setVodplayStatusUpdating(boolean z) {
        this.mVodplayStatusUpdating = z;
    }

    public void notifyVodplayStatusUpdated() {
        if (this.mListener != null) {
            this.mListener.onResourceFileInfoUpdated(0, this.mUserObject);
        }
    }

    public boolean isFileInfoUpdating() {
        return this.mFileInfoUpdating;
    }

    public void setFileInfoUpdating(boolean z) {
        this.mFileInfoUpdating = z;
    }

    public void notifyFileInfoUpdated() {
        if (this.mListener != null) {
            this.mListener.onResourceFileInfoUpdated(0, this.mUserObject);
        }
    }
}
