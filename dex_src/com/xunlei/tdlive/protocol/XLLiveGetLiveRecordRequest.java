package com.xunlei.tdlive.protocol;

public class XLLiveGetLiveRecordRequest extends XLLiveRequest {
    private int mCount;
    private int mStart;
    private String mUid;

    public static final class ReocodItem {
        public String current_user;
        public String end_time;
        public String image;
        public String play_hls_url;
        public String roomid;
        public String start_time;
        public String timeshow;
        public String title;
        public String userid;
    }

    public XLLiveGetLiveRecordRequest(String str, String str2, String str3) {
        super(str, str2);
        this.mStart = 0;
        this.mCount = 0;
        this.mUid = str3;
    }

    public void setRange(int i, int i2) {
        this.mStart = i;
        this.mCount = i2;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=getplayerlist&userid=").append(this.mUid).append("&start=").append(this.mStart).append("&count=").append(this.mCount).toString();
    }

    protected Class<?> onGetObjectClass() {
        return LiveRecords.class;
    }
}
