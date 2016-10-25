package com.xunlei.xiazaibao.sdk.synctasks;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.xiazaibao.sdk.entities.DownloadBoxSpaceResponse;
import com.xunlei.xiazaibao.sdk.entities.DownloadBoxSpaceResponse.SpaceEntity;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XZBDownloadBoxSpace extends XZBRemoteDownloadBaseTask {
    private static final String TAG;
    private String mPid;

    static {
        TAG = XZBDownloadBoxSpace.class.getSimpleName();
    }

    public XZBDownloadBoxSpace(String str) {
        this.mPid = str;
    }

    public String getUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getBaseUrl());
        stringBuilder.append("/boxSpace");
        stringBuilder.append("?pid=").append(this.mPid);
        stringBuilder.append(appendCommonParams());
        XZBLog.d(TAG, stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static DownloadBoxSpaceResponse parseResult(String str) {
        try {
            return parserJson(str);
        } catch (Exception e) {
            return null;
        }
    }

    private static DownloadBoxSpaceResponse parserJson(String str) {
        DownloadBoxSpaceResponse downloadBoxSpaceResponse = new DownloadBoxSpaceResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            downloadBoxSpaceResponse.setRtn(jSONObject.optInt("rtn"));
            downloadBoxSpaceResponse.setMsg(jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG));
            List arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("space");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                SpaceEntity spaceEntity = new SpaceEntity();
                spaceEntity.setRemain(jSONObject2.optString("remain"));
                spaceEntity.setPath(jSONObject2.optString("path"));
                arrayList.add(spaceEntity);
            }
            downloadBoxSpaceResponse.setSpace(arrayList);
        } catch (JSONException e) {
        }
        return downloadBoxSpaceResponse;
    }
}
