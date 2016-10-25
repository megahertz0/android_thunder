package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.a;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.base.SocializeRequest.RequestMethod;
import com.umeng.socialize.utils.SocializeUtils;

public class ShareFriendsRequest extends SocializeRequest {
    private static final String a = "/share/friends/";
    private static final int b = 14;
    private String c;
    private SHARE_MEDIA d;

    public ShareFriendsRequest(Context context, SHARE_MEDIA share_media, String str) {
        super(context, a.d, ShareFriendsResponse.class, 14, RequestMethod.GET);
        this.mContext = context;
        this.c = str;
        this.d = share_media;
    }

    public void onPrepareRequest() {
        addStringParams("to", this.d.toString().toLowerCase());
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.mContext)).append("/").append(this.c).append("/").toString();
    }
}
