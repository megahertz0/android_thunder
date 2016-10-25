package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.a;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.base.SocializeRequest.RequestMethod;
import com.umeng.socialize.utils.SocializeUtils;

public class GetPlatformKeyRequest extends SocializeRequest {
    private static final String a = "/share/keysecret/";
    private static final int b = 20;

    public GetPlatformKeyRequest(Context context) {
        super(context, a.d, GetPlatformKeyResponse.class, 20, RequestMethod.GET);
        this.mContext = context;
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.mContext)).append("/").toString();
    }
}
