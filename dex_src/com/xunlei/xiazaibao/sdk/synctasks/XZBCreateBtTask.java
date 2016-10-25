package com.xunlei.xiazaibao.sdk.synctasks;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import com.xunlei.xiazaibao.sdk.entities.DownloadCreateBtTaskResponse;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XZBCreateBtTask extends XZBRemoteDownloadBaseTask {
    private static final String TAG;
    private String mPid;
    private CreateBtTaskInfo mTaskInfo;

    public static class CreateBtTaskInfo extends NoObfuscationClassBase {
        public ArrayList<Integer> btSub;
        public String infohash;
        public String name;
        public String path;

        public CreateBtTaskInfo() {
            this.btSub = new ArrayList();
        }
    }

    static {
        TAG = XZBCreateBtTask.class.getSimpleName();
    }

    public XZBCreateBtTask(String str, CreateBtTaskInfo createBtTaskInfo) {
        this.mPid = str;
        this.mTaskInfo = createBtTaskInfo;
    }

    public String getUrl() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getBaseUrl()).append("/createBtTask");
        stringBuffer.append("?pid=");
        stringBuffer.append(this.mPid);
        stringBuffer.append(appendCommonParams());
        XZBLog.d(TAG, new StringBuilder("url = ").append(stringBuffer.toString()).toString());
        return stringBuffer.toString();
    }

    public Header[] getHeader() {
        return super.getHeader();
    }

    public void writeBody(OutputStream outputStream) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("json=");
        try {
            String encode = URLEncoder.encode(objectJson(this.mTaskInfo), "utf-8");
            stringBuffer.append(encode);
            XZBLog.d(TAG, new StringBuilder("body = ").append(encode).toString());
            outputStream.write(stringBuffer.toString().getBytes());
        } catch (Exception e) {
        }
    }

    public static DownloadCreateBtTaskResponse parseResult(String str) {
        try {
            return parserJson(str);
        } catch (Exception e) {
            return null;
        }
    }

    private static DownloadCreateBtTaskResponse parserJson(String str) {
        DownloadCreateBtTaskResponse downloadCreateBtTaskResponse = new DownloadCreateBtTaskResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            downloadCreateBtTaskResponse.rtn = jSONObject.optInt("rtn");
            downloadCreateBtTaskResponse.url = jSONObject.optString(SHubBatchQueryKeys.url);
            downloadCreateBtTaskResponse.name = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
            downloadCreateBtTaskResponse.msg = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
        } catch (JSONException e) {
        }
        return downloadCreateBtTaskResponse;
    }

    private static String objectJson(CreateBtTaskInfo createBtTaskInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("path", createBtTaskInfo.path);
            jSONObject.put("infohash", createBtTaskInfo.infohash);
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME, createBtTaskInfo.name);
            JSONArray jSONArray = new JSONArray();
            if (createBtTaskInfo.btSub != null) {
                Iterator it = createBtTaskInfo.btSub.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((Integer) it.next()).intValue());
                }
            }
            jSONObject.put("btSub", jSONArray);
            String toString = jSONObject.toString();
            XZBLog.d(TAG, new StringBuilder("objectJson = ").append(toString).toString());
            return toString;
        } catch (JSONException e) {
            return BuildConfig.VERSION_NAME;
        }
    }
}
