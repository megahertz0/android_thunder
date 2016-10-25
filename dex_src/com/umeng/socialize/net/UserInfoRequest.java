package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.utils.SocializeUtils;
import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class UserInfoRequest extends SocializeRequest {
    private static final String a = "/share/userinfo/";
    private static final int b = 12;
    private String c;

    public UserInfoRequest(Context context, String str) {
        super(context, BuildConfig.VERSION_NAME, UserInfoResponse.class, 12, SocializeRequest$RequestMethod.GET);
        this.mContext = context;
        this.c = str;
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.mContext)).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(this.c).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).toString();
    }
}
