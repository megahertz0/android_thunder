package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.SocializeUtils;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class UrlRequest extends SocializeRequest {
    private static final String a = "/link/add/";
    private static final int d = 26;
    private String b;
    private String c;

    public UrlRequest(Context context, String str, String str2) {
        super(context, BuildConfig.VERSION_NAME, UrlResponse.class, 26, SocializeRequest$RequestMethod.POST);
        this.mContext = context;
        this.b = str2;
        this.c = str;
    }

    public void onPrepareRequest() {
        super.onPrepareRequest();
        addStringParams(SHubBatchQueryKeys.url, this.b);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_TO, this.c);
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.mContext)).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).toString();
    }
}
