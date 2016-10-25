package com.xunlei.xiazaibao.sdk.synctasks;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask;
import com.xunlei.xiazaibao.sdk.entities.GetUSBInfoResponse;
import com.xunlei.xiazaibao.sdk.entities.GetUSBInfoResponse.DiskInfo;
import com.xunlei.xiazaibao.sdk.entities.GetUSBInfoResponse.Partition;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.util.ArrayList;
import org.android.agoo.common.AgooConstants;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XZBGetUsbInfo extends SyncHttpTask {
    private static final String TAG;
    private String mDeviceId;
    private String mTransitIp;
    private String mTransitPort;

    static {
        TAG = XZBGetUsbInfo.class.getSimpleName();
    }

    public XZBGetUsbInfo(String str, String str2, String str3) {
        this.mDeviceId = str;
        this.mTransitIp = str2;
        this.mTransitPort = str3;
    }

    public String getUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://");
        stringBuilder.append(this.mTransitIp).append(":").append(this.mTransitPort);
        stringBuilder.append("/dlna.csp?fname=dlna&opt=getusbinfo");
        stringBuilder.append("&x-xl-devid=").append(this.mDeviceId);
        stringBuilder.append("&x-xl-pri=2");
        stringBuilder.append("&x-xl-port=8200");
        XZBLog.d(TAG, new StringBuilder("url = ").append(stringBuilder.toString()).toString());
        return stringBuilder.toString();
    }

    public Header[] getHeader() {
        return new Header[0];
    }

    public GetUSBInfoResponse parseResult(String str) {
        try {
            return parserJson(str);
        } catch (Exception e) {
            return null;
        }
    }

    private static GetUSBInfoResponse parserJson(String str) {
        GetUSBInfoResponse getUSBInfoResponse = new GetUSBInfoResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            getUSBInfoResponse.rtn = jSONObject.optInt("rtn");
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("disklist");
            for (int i = 0; i < jSONArray.length(); i++) {
                jSONObject = jSONArray.optJSONObject(i);
                DiskInfo diskInfo = new DiskInfo();
                diskInfo.brand = jSONObject.optString("brand");
                diskInfo.sn = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_NUM);
                ArrayList arrayList2 = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("Partitionlist");
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    Partition partition = new Partition();
                    partition.key = optJSONObject.optString("key");
                    partition.root = optJSONObject.optString("path");
                    partition.partName = optJSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
                    partition.totleSize = optJSONObject.optLong("size");
                    partition.usedSize = optJSONObject.optLong("used");
                    partition.letter = optJSONObject.optString("letter");
                    partition.volume = optJSONObject.optString("volume");
                    partition.type = optJSONObject.optString(AgooConstants.MESSAGE_TYPE);
                    arrayList2.add(partition);
                }
                diskInfo.partitionList = arrayList2;
            }
            getUSBInfoResponse.disklist = arrayList;
        } catch (JSONException e) {
        }
        return getUSBInfoResponse;
    }
}
