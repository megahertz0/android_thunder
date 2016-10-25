package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.SocializeUtils;
import com.xunlei.download.proguard.c;
import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ShareDeleteOauthRequest extends SocializeRequest {
    private static final String a = "/share/auth_delete/";
    private static final int b = 15;
    private SHARE_MEDIA c;
    private String d;

    public ShareDeleteOauthRequest(Context context, SHARE_MEDIA share_media, String str) {
        super(context, BuildConfig.VERSION_NAME, SocializeReseponse.class, 15, SocializeRequest$RequestMethod.POST);
        this.mContext = context;
        this.c = share_media;
        this.d = str;
    }

    public void onPrepareRequest() {
        addStringParams(c.f, this.d);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_VERIFY_MEDIA, this.c.toString());
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.mContext)).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).toString();
    }
}
