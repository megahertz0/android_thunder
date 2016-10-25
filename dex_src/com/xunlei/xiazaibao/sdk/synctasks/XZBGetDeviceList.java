package com.xunlei.xiazaibao.sdk.synctasks;

import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.sdk.XZBDeviceManager;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpHeader;
import com.xunlei.xiazaibao.sdk.entities.GetDeviceListResponse;
import com.xunlei.xiazaibao.sdk.entities.GetDeviceListResponse.QueryDeviceInfo;
import com.xunlei.xiazaibao.sdk.tools.ConvertUtil;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XZBGetDeviceList extends SyncHttpTask {
    private static final String TAG;
    private static String mid;

    static {
        TAG = XZBGetDeviceList.class.getSimpleName();
        mid = BuildConfig.VERSION_NAME;
    }

    public String getUrl() {
        String toString = new StringBuilder("http://dm.xiazaibao.xunlei.com/xiazaibao/user/querydevice?userid=").append(XZBDeviceManager.getInstance().getUserInfo().userId).toString();
        XZBLog.d(TAG, new StringBuilder("url = ").append(toString).toString());
        return toString;
    }

    public Header[] getHeader() {
        return new Header[]{new HttpHeader("Cookie", getCookie())};
    }

    public static String generateAnonymousId() {
        if (mid.isEmpty()) {
            mid = ConvertUtil.md5(UUID.randomUUID().toString());
        }
        return mid;
    }

    private String getCookie() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("userid=");
        stringBuffer.append(XZBDeviceManager.getInstance().getUserInfo().userId);
        stringBuffer.append(";");
        stringBuffer.append(" sessionid=");
        stringBuffer.append(XZBDeviceManager.getInstance().getUserInfo().sessionId);
        stringBuffer.append(";");
        stringBuffer.append(new StringBuilder(" bindtype=1; mbid=").append(generateAnonymousId()).append(";").toString());
        stringBuffer.append(" v=");
        stringBuffer.append("1.0.0.0;");
        stringBuffer.append(" from=1;");
        stringBuffer.append(" clientoperationid=").append(XZBDeviceManager.getInstance().getUserInfo().businessType).append(";");
        XZBLog.d(TAG, new StringBuilder("cookie = ").append(stringBuffer.toString()).toString());
        return stringBuffer.toString();
    }

    public GetDeviceListResponse parseResult(String str) {
        try {
            return parserJson(str);
        } catch (Exception e) {
            return null;
        }
    }

    private static GetDeviceListResponse parserJson(String str) {
        GetDeviceListResponse getDeviceListResponse = new GetDeviceListResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            getDeviceListResponse.ret = jSONObject.optInt("ret");
            List arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("devices");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                QueryDeviceInfo queryDeviceInfo = new QueryDeviceInfo();
                queryDeviceInfo.deviceid = jSONObject2.optString("deviceid");
                queryDeviceInfo.aliasname = jSONObject2.optString("aliasname");
                queryDeviceInfo.onlinestatus = jSONObject2.optInt("onlinestatus");
                queryDeviceInfo.interip = jSONObject2.optString("interip");
                queryDeviceInfo.interport = jSONObject2.optInt("interport");
                queryDeviceInfo.intraip = jSONObject2.optString("intraip");
                queryDeviceInfo.intraport = jSONObject2.optInt("intraport");
                queryDeviceInfo.serverip = jSONObject2.optString("serverip");
                queryDeviceInfo.pid = jSONObject2.optString("pid");
                queryDeviceInfo.box_status = jSONObject2.optString("box_status");
                queryDeviceInfo.rights = jSONObject2.optString("rights");
                queryDeviceInfo.box_version = jSONObject2.optString("box_version");
                arrayList.add(queryDeviceInfo);
            }
            getDeviceListResponse.devices = arrayList;
        } catch (JSONException e) {
        }
        return getDeviceListResponse;
    }
}
