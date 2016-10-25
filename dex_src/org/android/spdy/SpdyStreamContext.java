package org.android.spdy;

public class SpdyStreamContext {
    public Spdycb callBack;
    public Object streamContext;
    public int streamId;

    SpdyStreamContext(Object obj) {
        this.streamContext = obj;
        this.callBack = null;
        this.streamId = -1;
    }

    SpdyStreamContext(Object obj, Spdycb spdycb) {
        this.streamContext = obj;
        this.callBack = spdycb;
    }

    static int getContext(Object obj) {
        return (obj == null || !(obj instanceof Integer)) ? 0 : ((Integer) obj).intValue();
    }
}
