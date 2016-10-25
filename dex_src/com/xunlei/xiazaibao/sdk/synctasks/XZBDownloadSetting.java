package com.xunlei.xiazaibao.sdk.synctasks;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.xiazaibao.sdk.entities.DownloadSettingResponse;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import org.json.JSONException;
import org.json.JSONObject;

public class XZBDownloadSetting extends XZBRemoteDownloadBaseTask {
    private static final String TAG;
    private String mPid;

    static {
        TAG = XZBDownloadSetting.class.getSimpleName();
    }

    public XZBDownloadSetting(String str) {
        this.mPid = str;
    }

    public String getUrl() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getBaseUrl()).append("/settings");
        stringBuffer.append("?pid=");
        stringBuffer.append(this.mPid);
        stringBuffer.append(appendCommonParams());
        XZBLog.d(TAG, new StringBuilder("url = ").append(stringBuffer.toString()).toString());
        return stringBuffer.toString();
    }

    public static DownloadSettingResponse parseResult(String str) {
        try {
            return parserJson(str);
        } catch (Exception e) {
            return null;
        }
    }

    private static DownloadSettingResponse parserJson(String str) throws JSONException {
        DownloadSettingResponse downloadSettingResponse = new DownloadSettingResponse();
        JSONObject jSONObject = new JSONObject(str);
        downloadSettingResponse.rtn = jSONObject.optInt("rtn");
        downloadSettingResponse.msg = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
        downloadSettingResponse.downloadSpeedLimit = jSONObject.optInt("downloadSpeedLimit");
        downloadSettingResponse.uploadSpeedLimit = jSONObject.optInt("uploadSpeedLimit");
        downloadSettingResponse.maxRunTaskNumber = jSONObject.optInt("maxRunTaskNumber");
        downloadSettingResponse.autoOpenLixian = jSONObject.optInt("autoOpenLixian");
        downloadSettingResponse.autoOpenVip = jSONObject.optInt("autoOpenVip");
        downloadSettingResponse.slStartTime = jSONObject.optInt("slStartTime");
        downloadSettingResponse.slEndTime = jSONObject.optInt("slEndTime");
        downloadSettingResponse.autoDlSubtitle = jSONObject.optInt("autoDlSubtitle");
        downloadSettingResponse.syncRange = jSONObject.optInt("syncRange");
        downloadSettingResponse.defaultPath = jSONObject.optString("defaultPath");
        return downloadSettingResponse;
    }
}
