package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.SocializeUtils;
import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class UploadImageRequest extends SocializeRequest {
    private static final String a = "/api/upload_pic/";
    private static final int b = 23;
    private Context c;
    private String d;
    private UMediaObject e;

    public UploadImageRequest(Context context, UMediaObject uMediaObject, String str) {
        super(context, BuildConfig.VERSION_NAME, UploadImageResponse.class, 23, SocializeRequest$RequestMethod.POST);
        this.c = context;
        this.d = str;
        this.e = uMediaObject;
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.c)).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).toString();
    }

    public void onPrepareRequest() {
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_USID, this.d);
        addMediaParams(this.e);
    }
}
