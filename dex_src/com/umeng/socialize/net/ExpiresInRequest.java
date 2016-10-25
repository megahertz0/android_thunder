package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.SocializeUtils;
import com.xunlei.download.proguard.c;
import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ExpiresInRequest extends SocializeRequest {
    private static final String a = "/share/validate_token/";
    private static final int b = 24;
    private SHARE_MEDIA[] c;

    public ExpiresInRequest(Context context, SHARE_MEDIA[] share_mediaArr) {
        super(context, BuildConfig.VERSION_NAME, ExpiresInResponse.class, 24, SocializeRequest$RequestMethod.GET);
        this.c = share_mediaArr;
    }

    public void onPrepareRequest() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.c != null && this.c.length > 0) {
            for (SHARE_MEDIA share_media : this.c) {
                if (share_media != SHARE_MEDIA.GENERIC) {
                    stringBuilder.append(share_media.toString()).append(",");
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_PLATFORM, stringBuilder.toString());
        addStringParams(c.f, SocializeConstants.UID);
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.mContext)).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).toString();
    }
}
