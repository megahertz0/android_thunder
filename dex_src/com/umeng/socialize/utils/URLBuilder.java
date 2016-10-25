package com.umeng.socialize.utils;

import android.content.Context;
import android.os.Build;
import com.umeng.fb.model.Constants;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.utils.AesHelper;
import com.xunlei.common.encrypt.CharsetConvert;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class URLBuilder {
    private String idmd5;
    private String imei;
    private String mAppkey;
    private String mEntityKey;
    private String mHost;
    private String mOpId;
    private String mPath;
    private String mPlatfrom;
    private String mReqType;
    private String mSessionId;
    private String mUID;
    private String mac;
    private String model;
    private String network;
    private String os;
    private String protoversion;
    private String sdkversion;
    private String ts;

    public URLBuilder(Context context) {
        this.mReqType = "0";
        this.mHost = null;
        this.mPath = null;
        this.mAppkey = null;
        this.mEntityKey = null;
        this.mOpId = null;
        this.mUID = null;
        this.mSessionId = null;
        this.mPlatfrom = null;
        this.imei = null;
        this.idmd5 = null;
        this.mac = null;
        this.network = null;
        this.model = null;
        this.sdkversion = null;
        this.os = null;
        this.ts = null;
        this.protoversion = null;
        this.imei = DeviceConfig.getDeviceId(context);
        if (this.imei != null) {
            this.idmd5 = AesHelper.md5(this.imei);
        }
        this.mac = DeviceConfig.getMac(context);
        this.network = DeviceConfig.getNetworkAccessMode(context)[0];
        this.model = Build.MODEL;
        this.sdkversion = "5.2.1";
        this.os = Constants.SDK_TYPE;
        this.ts = String.valueOf(System.currentTimeMillis());
        this.protoversion = "2.0";
    }

    public URLBuilder setHost(String str) {
        this.mHost = str;
        return this;
    }

    public URLBuilder setPath(String str) {
        this.mPath = str;
        return this;
    }

    public URLBuilder setAppkey(String str) {
        this.mAppkey = str;
        return this;
    }

    public URLBuilder setEntityKey(String str) {
        this.mEntityKey = str;
        return this;
    }

    public URLBuilder withMedia(SHARE_MEDIA share_media) {
        this.mPlatfrom = share_media.toString();
        return this;
    }

    public URLBuilder withOpId(String str) {
        this.mOpId = str;
        return this;
    }

    public URLBuilder withSessionId(String str) {
        this.mSessionId = str;
        return this;
    }

    public URLBuilder withUID(String str) {
        this.mUID = str;
        return this;
    }

    public URLBuilder withQuery(String str, String str2) {
        return this;
    }

    public String to() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mHost);
        stringBuilder.append(this.mPath);
        stringBuilder.append(this.mAppkey);
        stringBuilder.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        stringBuilder.append(this.mEntityKey);
        stringBuilder.append("/?");
        stringBuilder.append(buildParams());
        return stringBuilder.toString();
    }

    public String toEncript() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mHost);
        stringBuilder.append(this.mPath);
        stringBuilder.append(this.mAppkey);
        stringBuilder.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        stringBuilder.append(this.mEntityKey);
        stringBuilder.append("/?");
        String buildParams = buildParams();
        Log.i(new StringBuilder("base url: ").append(stringBuilder.toString()).toString());
        Log.i(new StringBuilder("params: ").append(buildParams).toString());
        AesHelper.setPassword(this.mAppkey);
        try {
            String encryptNoPadding = AesHelper.encryptNoPadding(buildParams, CharsetConvert.UTF_8);
            stringBuilder.append("ud_get=");
            stringBuilder.append(encryptNoPadding);
        } catch (Exception e) {
            Log.w("fail to encrypt query string");
            stringBuilder.append(buildParams);
        }
        return stringBuilder.toString();
    }

    private String buildParams() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("via=").append(this.mPlatfrom.toLowerCase());
        stringBuilder.append("&opid=").append(this.mOpId);
        stringBuilder.append("&ak=").append(this.mAppkey);
        stringBuilder.append("&pcv=").append(this.protoversion);
        stringBuilder.append("&tp=").append(this.mReqType);
        if (this.imei != null) {
            stringBuilder.append("&imei=").append(this.imei);
        }
        if (this.idmd5 != null) {
            stringBuilder.append("&md5imei=").append(this.idmd5);
        }
        if (this.mac != null) {
            stringBuilder.append("&mac=").append(this.mac);
        }
        if (this.network != null) {
            stringBuilder.append("&en=").append(this.network);
        }
        if (this.model != null) {
            stringBuilder.append("&de=").append(this.model);
        }
        if (this.sdkversion != null) {
            stringBuilder.append("&sdkv=").append(this.sdkversion);
        }
        if (this.os != null) {
            stringBuilder.append("&os=").append(this.os);
        }
        if (this.ts != null) {
            stringBuilder.append("&dt=").append(this.ts);
        }
        if (this.mUID != null) {
            stringBuilder.append("&uid=").append(this.mUID);
        }
        if (this.mEntityKey != null) {
            stringBuilder.append("&ek=").append(this.mEntityKey);
        }
        if (this.mSessionId != null) {
            stringBuilder.append("&sid=").append(this.mSessionId);
        }
        return stringBuilder.toString();
    }
}
