package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.utils.SocializeUtils;
import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PlatformTokenUploadReq extends SocializeRequest {
    private static final String a = "/share/token/";
    private static final int b = 21;

    public PlatformTokenUploadReq(Context context) {
        super(context, BuildConfig.VERSION_NAME, PlatformTokenUploadResponse.class, 21, SocializeRequest$RequestMethod.POST);
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.mContext)).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).toString();
    }
}
