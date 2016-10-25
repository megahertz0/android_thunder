package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.a;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.base.SocializeRequest.RequestMethod;
import com.umeng.socialize.utils.SocializeUtils;

public class ShareMultiFollowRequest extends SocializeRequest {
    private static final String a = "/share/follow/";
    private static final int b = 18;
    private String c;
    private String d;
    private String e;

    public ShareMultiFollowRequest(Context context, String str, String str2, String str3) {
        super(context, a.d, ShareMultiFollowResponse.class, 18, RequestMethod.POST);
        this.mContext = context;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public void onPrepareRequest() {
        super.onPrepareRequest();
        addStringParams("to", this.c);
        addStringParams("fusid", this.e);
    }

    protected String getPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a).append(SocializeUtils.getAppkey(this.mContext)).append("/").append(this.d).append("/");
        return stringBuilder.toString();
    }
}
