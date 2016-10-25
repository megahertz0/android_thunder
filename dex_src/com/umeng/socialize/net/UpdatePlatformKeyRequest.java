package com.umeng.socialize.net;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.SocializeUtils;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class UpdatePlatformKeyRequest extends SocializeRequest {
    private static final String a = "/share/keysecret/";
    private static final int b = 25;
    private Map<String, String> c;

    public UpdatePlatformKeyRequest(Context context, Map<String, String> map) {
        super(context, BuildConfig.VERSION_NAME, UpdatePlatformKeyResponse.class, 25, SocializeRequest$RequestMethod.POST);
        this.c = null;
        this.mContext = context;
        this.c = map;
    }

    public void onPrepareRequest() {
        String str = (String) this.c.get("wx_appid");
        String str2 = (String) this.c.get("wx_secret");
        String str3 = (String) this.c.get("qzone_id");
        String str4 = (String) this.c.get(SocializeProtocolConstants.PROTOCOL_KEY_QZONE_SECRET);
        if (!TextUtils.isEmpty(str)) {
            addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_WX_APPID, str);
            addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_WX_SECRET, str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_QZONE_ID, str3);
            addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_QZONE_SECRET, str4);
        }
        str = SocializeUtils.getAppkey(this.mContext);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_AK, str);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_UMENG_SECRET, SocializeUtils.reverse(str));
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.mContext)).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).toString();
    }
}
